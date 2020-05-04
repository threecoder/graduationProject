package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.lutayy.campbackend.common.config.AuthorityParam;
import com.lutayy.campbackend.common.util.OrderIdGenerator;
import com.lutayy.campbackend.common.util.PdfUtil;
import com.lutayy.campbackend.common.util.RedisUtil;
import com.lutayy.campbackend.common.util.UUIDUtil;
import com.lutayy.campbackend.dao.*;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.SQLConn.SystemParamManager;
import com.lutayy.campbackend.service.SQLConn.TrainingStudentSQLConn;
import com.lutayy.campbackend.service.TrainingService;
import org.apache.poi.ss.formula.functions.T;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    TrainingMapper trainingMapper;
    @Autowired
    TrainingReStudentMapper trainingReStudentMapper;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TrainingOrderMapper trainingOrderMapper;
    @Autowired
    TrainingOrderStudentMapper trainingOrderStudentMapper;
    @Autowired
    MemberReStudentMapper memberReStudentMapper;
    @Autowired
    ExamMapper examMapper;
    @Autowired
    ExamReStudentMapper examReStudentMapper;
    @Autowired
    CertificateMapper certificateMapper;
    @Autowired
    CertificateImageMapper certificateImageMapper;
    @Autowired
    AssociationTextMapper associationTextMapper;

    @Autowired
    GetObjectHelper getObjectHelper;
    @Resource
    private RedisUtil redisUtil;


    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    /**
     * 学员培训报名记录查询
     * 有有效报名记录返回1，无则返回0
     **/
    private int checkStudentTraining(int studentId, int trainingId) {
        TrainingReStudentExample trainingReStudentExample = new TrainingReStudentExample();
        TrainingReStudentExample.Criteria criteria = trainingReStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId).andTrainingIdEqualTo(trainingId).andIsInvalidEqualTo(false);
        long hasOrNot = trainingReStudentMapper.countByExample(trainingReStudentExample);
        return (int) hasOrNot;
    }


    @Override
    public JSONObject getCourses(String keyWord, String startDateStr, String endDateStr,
                                 Integer pageSize, Integer currentPage, String type) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("data", data);
        result.put("code", "fail");
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        TrainingExample trainingExample = new TrainingExample();
        TrainingExample.Criteria criteria = trainingExample.createCriteria();
        if ((startDateStr == null || startDateStr.equals("")) && (endDateStr == null || endDateStr.equals(""))) {
            if (type.equals("previous")) {
                criteria.andTrainingEndTimeLessThan(new Date());
            } else if (type.equals("now")) {
                criteria.andTrainingStartTimeLessThanOrEqualTo(new Date());
                criteria.andTrainingEndTimeGreaterThanOrEqualTo(new Date());
            } else {
                criteria.andTrainingStartTimeGreaterThanOrEqualTo(new Date());
            }
        }

        if (keyWord != null && !keyWord.equals("")) {
            criteria.andTrainingNameLike("%" + keyWord + "%");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (startDateStr != null && !startDateStr.equals("")) {
            Date startDate;
            try {
                startDate = simpleDateFormat.parse(startDateStr);
            } catch (Exception e) {
                result.put("msg", "日期格式错误");
                return result;
            }
            criteria.andTrainingStartTimeGreaterThanOrEqualTo(startDate);
        }
        if (endDateStr != null && !endDateStr.equals("")) {
//            Calendar c = Calendar.getInstance();
//            c.setTime(endDate);
//            c.add(Calendar.DAY_OF_MONTH, +1);
//            criteria.andTrainingEndTimeLessThanOrEqualTo(c.getTime());
            Date endDate;
            try {
                endDate = simpleDateFormat.parse(endDateStr);
            } catch (Exception e) {
                result.put("msg", "日期格式错误");
                return result;
            }
            criteria.andTrainingEndTimeLessThan(endDate);
        }
        trainingExample.setOrderByClause("post_time DESC");
        List<Training> trainings = trainingMapper.selectByExample(trainingExample);
        int size = trainings.size();
        JSONArray list = new JSONArray();
        int i = 1;//计数
        int sum = 0;//每页数目;
        for (Training training : trainings) {
            if (i <= pageSize * (currentPage - 1)) {
                i++;
                continue;
            }
            /*获取参与培训的学生数目*/
            TrainingReStudentExample trainingReStudentExample = new TrainingReStudentExample();
            TrainingReStudentExample.Criteria criteria1 = trainingReStudentExample.createCriteria();
            criteria1.andTrainingIdEqualTo(training.getTrainingId());
            List<TrainingReStudent> trainingReStudents = trainingReStudentMapper.selectByExample(trainingReStudentExample);
            int studentNums = trainingReStudents.size();

            JSONObject data0 = new JSONObject();
            data0.put("title", training.getTrainingName());
            data0.put("abstract", training.getTrainingIntroduce());
            data0.put("viewers", studentNums);
            data0.put("url", training.getTrainingPic());
            data0.put("level", training.getLevel());
            list.add(data0);
            sum++;
            if (sum == pageSize) {
                break;
            }
        }

        data.put("list", list);
        data.put("allNum", size);
        data.put("pageSize", sum);
        data.put("currentPage", currentPage);
        result.put("data", data);
        if (size == 0) {
            result.put("code", "success");
            result.put("msg", "无结果");
        } else {
            result.put("code", "success");
            result.put("msg", "查询成功!");
        }
        return result;
    }

    @Override
    public JSONObject getJoinableTraining(Integer memberId, Integer pageSize, Integer currentPage, String name) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();

        TrainingExample trainingExample = new TrainingExample();
        TrainingExample.Criteria criteria = trainingExample.createCriteria();
        criteria.andTrainingStartTimeLessThan(new Date()).andTrainingEndTimeGreaterThan(new Date());
        if (name != null && !name.equals("")) {
            criteria.andTrainingNameLike("%" + name + "%");
        }
        long total = trainingMapper.countByExample(trainingExample);
        trainingExample.setOffset((currentPage - 1) * pageSize);
        trainingExample.setLimit(pageSize);
        List<Training> trainings = trainingMapper.selectByExample(trainingExample);
        if (total == 0) {
            result.put("code", "success");
            result.put("msg", "无可报名培训");
            result.put("data", null);
            return result;
        }
        int isVip = 0;
        Member member = memberMapper.selectByPrimaryKey(memberId);
        if (member != null) {
            if (member.getIsVip()) {
                isVip = 1;
            }
        }
        for (Training training : trainings) {
            JSONObject object = new JSONObject();
            object.put("id", training.getTrainingId());
            object.put("name", training.getTrainingName());
            object.put("date", simpleDateFormat.format(training.getTrainingStartTime()) + "----" + simpleDateFormat.format(training.getTrainingEndTime()));
            object.put("address", training.getTrainingAddress());
            JSONArray introduce = new JSONArray();
            String[] introduceStrs = training.getTrainingIntroduce().split("\\|");
            for (String intro : introduceStrs) {
                introduce.add(intro);
            }
            object.put("introduction", introduce);
            if (isVip == 0) {
                object.put("fee", training.getTrainingFeeNormal());
            } else {
                object.put("fee", training.getTrainingFeeVip());
            }
            object.put("contacts", training.getContactName());
            object.put("phone", training.getContactPhone());
            list.add(object);
        }
        data.put("list", list);
        data.put("total", total);
        result.put("code", "success");
        result.put("msg", "获取可报名培训成功");
        result.put("data", data);
        return result;
    }

    @Override
    public JSONObject getJoinableTraining(Integer pageSize, Integer currentPage, String name) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();

        TrainingExample trainingExample = new TrainingExample();
        TrainingExample.Criteria criteria = trainingExample.createCriteria();
        criteria.andTrainingStartTimeLessThan(new Date()).andTrainingEndTimeGreaterThan(new Date());
        if (name != null && !name.equals("")) {
            criteria.andTrainingNameLike("%" + name + "%");
        }
        long total = trainingMapper.countByExample(trainingExample);
        trainingExample.setOffset((currentPage - 1) * pageSize);
        trainingExample.setLimit(pageSize);
        List<Training> trainings = trainingMapper.selectByExample(trainingExample);
        if (trainings.size() == 0) {
            result.put("code", "success");
            result.put("msg", "无可报名培训");
            result.put("data", null);
            return result;
        }
        for (Training training : trainings) {
            JSONObject object = new JSONObject();
            object.put("id", training.getTrainingId());
            object.put("name", training.getTrainingName());
            object.put("date", simpleDateFormat.format(training.getTrainingStartTime()) + "----" + simpleDateFormat.format(training.getTrainingEndTime()));
            object.put("address", training.getTrainingAddress());
            JSONArray introduce = new JSONArray();
            String[] introduceStrs = training.getTrainingIntroduce().split("\\|");
            for (String intro : introduceStrs) {
                introduce.add(intro);
            }
            object.put("introduction", introduce);
            object.put("fee", training.getTrainingFeeNormal());
            object.put("contacts", training.getContactName());
            object.put("phone", training.getContactPhone());
            list.add(object);
        }
        data.put("list", list);
        data.put("total", total);
        result.put("code", "success");
        result.put("msg", "获取可报名培训成功");
        result.put("data", data);
        return result;
    }

    /**
     * 学员中心接口
     **/
    @Override
    public JSONObject getStudentSignedTraining(Integer studentId, Integer currentPage, Integer pageSize, String name) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();

        Student student = studentMapper.selectByPrimaryKey(studentId);
        if (student == null) {
            result.put("code", "fail");
            result.put("msg", "当前用户不存在！");
            result.put("data", null);
            return result;
        }
        TrainingOrderExample trainingOrderExample = new TrainingOrderExample();
        TrainingOrderExample.Criteria criteria1 = trainingOrderExample.createCriteria();
        criteria1.andOrderTypeEqualTo(true).andPaymentStateEqualTo(false).andStudentIdEqualTo(studentId);
        List<TrainingOrder> trainingOrders = trainingOrderMapper.selectByExample(trainingOrderExample);

        TrainingReStudentExample trainingReStudentExample = new TrainingReStudentExample();
        TrainingReStudentExample.Criteria criteria = trainingReStudentExample.createCriteria();
        criteria.andIsInvalidEqualTo(false).andStudentIdEqualTo(studentId);
        List<TrainingReStudent> trainingReStudents = trainingReStudentMapper.selectByExample(trainingReStudentExample);

        int total = -1;

        if (trainingOrders.size() == 0 && trainingReStudents.size() == 0) {
            result.put("code", "success");
            result.put("msg", "该用户暂无报名任何培训");
            data.put("total", total + 1);
            data.put("list", list);
            result.put("data", data);
            return result;
        }
        List<Training> trainings = new ArrayList<>();
        for (TrainingOrder trainingOrder : trainingOrders) {
            trainings.add(trainingMapper.selectByPrimaryKey(trainingOrder.getTrainingId()));
        }
        for (TrainingReStudent trainingReStudent : trainingReStudents) {
            trainings.add(trainingMapper.selectByPrimaryKey(trainingReStudent.getTrainingId()));
        }

        int num = -1; //用于区分未支付和报名成功的培训
        int orderIndex = -1;
        Set<Integer> trainingIdsSet = new HashSet<>();//用于将未支付的订单属于同一活动的进行去重
        for (Training training : trainings) {
            num++;
            if (num < trainingOrders.size()) {
                orderIndex++;
                if (getObjectHelper.checkTrainingOrderOutOfTime(trainingOrders.get(orderIndex)))
                    continue;
                if (!trainingIdsSet.add(training.getTrainingId()))
                    continue;
            }
            if (name != null && !name.equals("")) {
                if (!training.getTrainingName().contains(name))
                    continue;
            }
            total++;
            if (total >= (currentPage - 1) * pageSize && total < currentPage * pageSize) {
                JSONObject object = new JSONObject();
                object.put("id", training.getTrainingId());
                object.put("name", training.getTrainingName());
                object.put("date", simpleDateFormat.format(training.getTrainingStartTime()) + "----" + simpleDateFormat.format(training.getTrainingEndTime()));
                object.put("address", training.getTrainingAddress());
                JSONArray introduce = new JSONArray();
                String[] introduceStrs = training.getTrainingIntroduce().split("\\|");
                for (String intro : introduceStrs) {
                    introduce.add(intro);
                }
                object.put("introduction", introduce);
                object.put("fee", training.getTrainingFeeNormal());
                if (num < trainingOrders.size()) {
                    object.put("status", "未支付");
                } else {
                    object.put("status", "已支付");
                }
                object.put("contacts", training.getContactName());
                object.put("phone", training.getContactPhone());
                list.add(object);
            }
        }
        result.put("code", "success");
        result.put("msg", "成功获取已报名的培训");
        data.put("list", list);
        data.put("total", total + 1);
        result.put("data", data);
        return result;

    }

    @Override
    public JSONObject studentJoinTraining(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        if (SystemParamManager.getValueByKey("stu_tran_permission").equals("0")) {
            result.put("code", "fail");
            result.put("msg", "当前用户没有报名培训的权限");
            return result;
        }
        Integer studentId = jsonObject.getInteger("id");
        Student student = studentMapper.selectByPrimaryKey(studentId);
        MemberReStudentExample memberReStudentExample = new MemberReStudentExample();
        MemberReStudentExample.Criteria criteria = memberReStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId);
        if (memberReStudentMapper.selectByExample(memberReStudentExample) != null) {
            result.put("code", "fail");
            result.put("msg", "请由所属单位报名");
            return result;
        }

        int trainingId = jsonObject.getInteger("trainingId");

        Training training = trainingMapper.selectByPrimaryKey(trainingId);
        if (training == null) {
            result.put("code", "fail");
            result.put("msg", "系统中查询不到该项培训");
            return result;
        }

        if (checkStudentTraining(studentId, trainingId) == 1) {
            result.put("code", "fail");
            result.put("msg", "已报名该培训");
            return result;
        }

        TrainingOrderExample trainingOrderExample = new TrainingOrderExample();
        TrainingOrderExample.Criteria criteria1 = trainingOrderExample.createCriteria();
        criteria1.andTrainingIdEqualTo(trainingId).andStudentIdEqualTo(studentId).andCloseEqualTo(false).andPaymentStateEqualTo(false).andOrderTypeEqualTo(true);
        List<TrainingOrder> trainingOrders = trainingOrderMapper.selectByExample(trainingOrderExample);
        if (trainingOrders.size() != 0) {
            result.put("code", "fail");
            result.put("msg", "已报名此培训但未支付，请前往订单中心查看");
            return result;
        }

        String orderId = OrderIdGenerator.getUniqueId();
        while (!redisUtil.hset("order_no_map", orderId, "training")) {
            orderId = OrderIdGenerator.getUniqueId();
        }
        //订单号生成并查重（查重如非高并发系统基本上可以省略）
//        while(trainingOrderMapper.selectByPrimaryKey(orderId)!=null){
//            orderId=OrderIdGenerator.getUniqueId();
//        }
        /**
         * 由学员自行报名，对应的订单，！！也要！！插入“订单—学生”表
         * **/
        TrainingOrder trainingOrder = new TrainingOrder();
        trainingOrder.setTrainingOrderId(orderId);
        trainingOrder.setTrainingId(trainingId);
        trainingOrder.setOrderType(true);
        trainingOrder.setStudentId(studentId);
        trainingOrder.setOrderPrice(training.getTrainingFeeNormal());
        trainingOrder.setOrderBeginTime(new Date());
        trainingOrder.setPaymentState(false);
        trainingOrder.setClose(false);
        trainingOrder.setBusinessName(training.getTrainingName());
        trainingOrder.setOpManName(student.getStudentName());
        if (trainingOrderMapper.insert(trainingOrder) > 0) {
            result.put("code", "success");
            result.put("msg", "订单生成生成!待支付");
            // TODO 插入“订单—学生”表
            TrainingOrderStudent trainingOrderStudent = new TrainingOrderStudent();
            trainingOrderStudent.setStudentId(studentId);
            trainingOrderStudent.setOrderKeyId(trainingOrder.getOrderKeyId());
            trainingOrderStudentMapper.insertSelective(trainingOrderStudent);
        } else {
            result.put("code", "fail");
            result.put("msg", "订单生成失败!");
        }
        confirmOrder(orderId);
        return result;
    }

    /**
     * 会员中心接口
     **/
    @Override
    public JSONObject getMemberSignedTraining(Integer memberId, Integer currentPage, Integer pageSize, String name) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();

        TrainingOrderExample trainingOrderExample = new TrainingOrderExample();
        TrainingOrderExample.Criteria criteria = trainingOrderExample.createCriteria();
        criteria.andOrderTypeEqualTo(false).andMemberKeyIdEqualTo(memberId).andCloseEqualTo(false).andPaymentStateEqualTo(false);
        trainingOrderExample.setOrderByClause("order_begin_time DESC");
        List<TrainingOrder> trainingOrders = trainingOrderMapper.selectByExample(trainingOrderExample);

        List<Integer> trainingIds = TrainingStudentSQLConn.getTrainingIdByMemberId(memberId);

        int total = -1;

        if (trainingIds.size() == 0 && trainingOrders.size() == 0) {
            result.put("code", "success");
            result.put("msg", "暂未给名下学员报名培训");
            data.put("total", total);
            data.put("list", list);
            result.put("data", data);
            return result;
        }
        List<Training> trainings = new ArrayList<Training>();
        //此处可以使排列倒过来，使排在前面的是最新报名的培训
        for (int i = 0; i < trainingOrders.size(); i++) {
            trainings.add(trainingMapper.selectByPrimaryKey(trainingOrders.get(i).getTrainingId()));
        }
        for (int i = trainingIds.size() - 1; i >= 0; i--) {
            trainings.add(trainingMapper.selectByPrimaryKey(trainingIds.get(i)));
        }
        int isVip = 0;
        Member member = memberMapper.selectByPrimaryKey(memberId);
        if (member != null) {
            if (member.getIsVip()) {
                isVip = 1;
            }
        }

        int num = -1; //用于区分未支付和报名成功的培训
        int orderIndex = -1;
        Set<Integer> trainingIdsSet = new HashSet<>();//用于将未支付的订单属于同一活动的进行去重
        for (Training training : trainings) {
            num++;
            if (num < trainingOrders.size()) {
                orderIndex++;
                if (getObjectHelper.checkTrainingOrderOutOfTime(trainingOrders.get(orderIndex)))
                    continue;
                if (!trainingIdsSet.add(training.getTrainingId()))
                    continue;
            }
            if (name != null && !name.equals("")) {
                if (!training.getTrainingName().contains(name))
                    continue;
            }
            total++;
            if (total >= (currentPage - 1) * pageSize && total < currentPage * pageSize) {
                JSONObject object = new JSONObject();
                object.put("id", training.getTrainingId());
                object.put("name", training.getTrainingName());
                object.put("date", simpleDateFormat.format(training.getTrainingStartTime()) + "----" + simpleDateFormat.format(training.getTrainingEndTime()));
                object.put("address", training.getTrainingAddress());
                JSONArray introduce = new JSONArray();
                String[] introduceStrs = training.getTrainingIntroduce().split("\\|");
                for (String intro : introduceStrs) {
                    introduce.add(intro);
                }
                object.put("introduction", introduce);
                if (isVip == 0) {
                    object.put("fee", training.getTrainingFeeNormal());
                } else {
                    object.put("fee", training.getTrainingFeeVip());
                }
                if (num < trainingOrders.size()) {
                    object.put("status", "有订单未支付");
                } else {
                    object.put("status", "已支付");
                }
                object.put("contacts", training.getContactName());
                object.put("phone", training.getContactPhone());
                list.add(object);
            }
        }
        result.put("code", "success");
        result.put("msg", "成功获取已报名的培训");
        data.put("list", list);
        data.put("total", total + 1);
        result.put("data", data);
        return result;
    }

    @Override
    public JSONObject memberJoinTraining(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        Integer memberId = jsonObject.getInteger("id");
        Member member = memberMapper.selectByPrimaryKey(memberId);
        int trainingId = jsonObject.getInteger("trainingId");
        JSONArray idNums = jsonObject.getJSONArray("idNums");
        Training training = trainingMapper.selectByPrimaryKey(trainingId);
        if (training == null) {
            result.put("code", "fail");
            result.put("msg", "系统中没有该培训");
            return result;
        }

        String orderId = OrderIdGenerator.getUniqueId();
        while (!redisUtil.hset("order_no_map", orderId, "training")) {
            orderId = OrderIdGenerator.getUniqueId();
        }
        //订单号生成并查重（如非高并发系统基本上可以省略）
//        while(trainingOrderMapper.selectByPrimaryKey(orderId)!=null){
//            orderId=OrderIdGenerator.getUniqueId();
//        }
        /**
         * 由会员报名，对应的订单，还需插入“订单—学生”表
         **/
        TrainingOrder trainingOrder = new TrainingOrder();
        trainingOrder.setTrainingOrderId(orderId);
        trainingOrder.setTrainingId(trainingId);
        trainingOrder.setOrderType(false);//"0"即会员提交的订单
        trainingOrder.setMemberKeyId(memberId);
        trainingOrder.setOrderBeginTime(new Date());
        trainingOrder.setClose(false);
        trainingOrder.setPaymentState(false);
        trainingOrder.setBusinessName(training.getTrainingName());
        trainingOrder.setOpManName(member.getMemberName());
        trainingOrderMapper.insertSelective(trainingOrder);
        int isVip = 0;

        if (member != null) {
            if (member.getIsVip()) {
                isVip = 1;
            }
        }
        BigDecimal fee = isVip == 0 ? training.getTrainingFeeNormal() : training.getTrainingFeeVip();

        int existTotalCount = 0;
        String existTagTip = "";
        int existOrderTotalCount = 0;
        String existOrderTagTip = "";
        for (int i = 0; i < idNums.size(); i++) {
            Student student = getObjectHelper.getStudentFromIdCard(idNums.getString(i));
            if (checkStudentTraining(student.getStudentId(), trainingId) != 0) {
                existTotalCount += 1;
                existTagTip += student.getStudentName() + "(" + idNums.getString(i) + ") ";
                continue;
            }
            String checkOrderId = TrainingStudentSQLConn.getTrainingOrderByStudentId(student.getStudentId(), trainingId);
//            System.out.println(checkOrderId);
            if (checkOrderId != null) {
                existOrderTotalCount += 1;
                existOrderTagTip += student.getStudentName() + "(订单" + checkOrderId + ") ";
                continue;
            }
            //插入“订单—学生”表
            TrainingOrderStudent trainingOrderStudent = new TrainingOrderStudent();
            trainingOrderStudent.setOrderKeyId(trainingOrder.getOrderKeyId());
            trainingOrderStudent.setStudentId(student.getStudentId());
            trainingOrderStudentMapper.insertSelective(trainingOrderStudent);
        }
        BigDecimal successNums = new BigDecimal(idNums.size() - existTotalCount - existOrderTotalCount);
//        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        trainingOrder.setOrderPrice(fee.multiply(successNums));
        trainingOrderMapper.updateByPrimaryKeySelective(trainingOrder);
        String msg = "尝试为" + idNums.size() + "名学员报名； 成功" + (idNums.size() - existTotalCount - existOrderTotalCount) + "名； ";
        result.put("code", "success");
        if (existTotalCount > 0) {
            msg += "剔除重复报名" + existTotalCount + "名,分别是:[" + existTagTip + "] ";
        }
        if (existOrderTotalCount > 0) {
            msg += "剔除已存在于该培训未支付订单中的学员" + existOrderTotalCount + "名,分别是:[" + existOrderTagTip + "] ";
        }
        if (idNums.size() - existTotalCount - existOrderTotalCount < 1) {
            result.put("code", "fail");
            result.put("data", null);
            //提交的名单没有报名成功的，删除订单
            trainingOrderMapper.deleteByPrimaryKey(trainingOrder.getOrderKeyId());
        } else {
            result.put("code", "success");
            result.put("data", orderId);
            msg += "订单生成成功！订单号：" + orderId;
        }

        /** 模拟订单支付确认 **/
        if (confirmOrder(orderId) == 1) {
            msg += "  模拟订单支付确认完成";
        }
        result.put("msg", msg);
        return result;
    }

    /**
     * 会员订单成功支付
     **/
    private int confirmOrder(String orderId) {
        System.out.println("---- 模拟订单支付确认 开启 ----");
        TrainingOrderExample trainingOrderExample = new TrainingOrderExample();
        TrainingOrderExample.Criteria criteriaOrder = trainingOrderExample.createCriteria();
        criteriaOrder.andTrainingOrderIdEqualTo(orderId);
        List<TrainingOrder> trainingOrders = trainingOrderMapper.selectByExample(trainingOrderExample);
        if (trainingOrders.size() < 1) {
            System.out.println("---- 该订单不存在 ----");
            return -1;
        }
        TrainingOrder trainingOrder = trainingOrders.get(0);
        int trainingId = trainingOrder.getTrainingId();
        trainingOrder.setPaymentState(true);
        trainingOrderMapper.updateByPrimaryKeySelective(trainingOrder);
        List<Integer> studentIds = new ArrayList<>();
        if (trainingOrder.getOrderType() != null && !trainingOrder.getOrderType()) {
            TrainingOrderStudentExample trainingOrderStudentExample = new TrainingOrderStudentExample();
            TrainingOrderStudentExample.Criteria criteria = trainingOrderStudentExample.createCriteria();
            criteria.andOrderKeyIdEqualTo(trainingOrder.getOrderKeyId());
            List<TrainingOrderStudent> trainingOrderStudents = trainingOrderStudentMapper.selectByExample(trainingOrderStudentExample);
            if (trainingOrderStudents.size() == 0) {
                System.out.println("---- 该订单下无学员 结束 ----");
                return -1;
            }
            for (TrainingOrderStudent trainingOrderStudent : trainingOrderStudents) {
                studentIds.add(trainingOrderStudent.getStudentId());
            }
        }
        if (trainingOrder.getOrderType() != null && trainingOrder.getOrderType()) {
            studentIds.add(trainingOrder.getStudentId());
        }
        /** 检查该培训是否已发布试卷,有的话就添加到学生的试卷可考列表中 **/
        ExamExample examExample = new ExamExample();
        ExamExample.Criteria criteria1 = examExample.createCriteria();
        criteria1.andTrainingIdEqualTo(trainingId).andIsPostedEqualTo(true);
        List<Exam> exams = examMapper.selectByExample(examExample);
        boolean isExamPosted = false;
        if (exams.size() != 0) {
            isExamPosted = true;
        }
        for (Integer studentId : studentIds) {
            TrainingReStudentExample trainingReStudentExample = new TrainingReStudentExample();
            TrainingReStudentExample.Criteria criteria = trainingReStudentExample.createCriteria();
            criteria.andStudentIdEqualTo(studentId).andTrainingIdEqualTo(trainingId).andIsInvalidEqualTo(false);
            if (trainingReStudentMapper.selectByExample(trainingReStudentExample).size() == 0) {
                TrainingReStudent trainingReStudent = new TrainingReStudent();
                trainingReStudent.setStudentId(studentId);
                trainingReStudent.setTrainingId(trainingId);
                trainingReStudent.setApplyId(UUIDUtil.getTrainingApplyNumber(trainingId));
                trainingReStudent.setBeginTime(new Date());
                trainingReStudentMapper.insertSelective(trainingReStudent);
                System.out.println("---- 学员" + studentId + "报名培训成功 ----");
            }
            if (isExamPosted) {
                ExamReStudentExample examReStudentExample = new ExamReStudentExample();
                ExamReStudentExample.Criteria criteria2 = examReStudentExample.createCriteria();
                criteria2.andStudentIdEqualTo(studentId).andExamIdEqualTo(exams.get(0).getExamId());
                if (examReStudentMapper.selectByExample(examReStudentExample).size() == 0) {
                    ExamReStudent examReStudent = new ExamReStudent();
                    examReStudent.setStudentId(studentId);
                    examReStudent.setExamId(exams.get(0).getExamId());
                    examReStudentMapper.insertSelective(examReStudent);
                    System.out.println("---- 学员" + studentId + "报名考试成功(考试已添加进列表) ----");
                }
            }
        }
        System.out.println("---- 模拟订单支付确认 结束 ----");
        return 1;
    }


    /**
     * 管理员
     */
    @Override
    public JSONObject addNewTraining(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        //权限检查
        Integer opAdminId = jsonObject.getInteger("id");
        if (!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.TRAINING)) {
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }
        String trainingName = jsonObject.getString("name");
        BigDecimal fee = jsonObject.getBigDecimal("fee");
        BigDecimal vipFee = jsonObject.getBigDecimal("vipFee");
        String contactName = jsonObject.getString("contacts");
        String contactPhone = jsonObject.getString("phone");
        Date startTime = jsonObject.getDate("openTime");
        Date endTime = jsonObject.getDate("closeTime");
        Byte level = jsonObject.getByte("level");
        String address = jsonObject.getString("address");
        JSONArray trainingIntro = jsonObject.getJSONArray("desc");
        StringBuilder intro = new StringBuilder();
        for (int i = 0; i < trainingIntro.size() - 1; i++) {
            intro.append(trainingIntro.get(i));
            intro.append("|");
        }
        intro.append(trainingIntro.get(trainingIntro.size() - 1));

        //培训名称查重
        TrainingExample trainingExample = new TrainingExample();
        TrainingExample.Criteria criteria = trainingExample.createCriteria();
        criteria.andTrainingNameEqualTo(trainingName);
        if (trainingMapper.selectByExample(trainingExample).size() > 0) {
            result.put("code", "fail");
            result.put("msg", "已有同名的培训,新建失败！");
            return result;
        }
        Training training = new Training();
        training.setContactName(contactName);
        training.setContactPhone(contactPhone);
        training.setLevel(level);
        training.setPostTime(new Date());
        training.setTrainingAddress(address);
        training.setTrainingFeeNormal(fee);
        training.setTrainingFeeVip(vipFee);
        training.setTrainingIntroduce(intro.toString());
        training.setTrainingName(trainingName);
        training.setTrainingStartTime(startTime);
        training.setTrainingEndTime(endTime);
        if (trainingMapper.insert(training) > 0) {
            result.put("code", "success");

            // TODO 设置默认的证书模板
            String mainPath = "./src/main/resources/templates";
            String temPath = mainPath + "/certificate/certificate_template.pdf";
            String outPath = "/certificate/training_cer_templates/" + training.getTrainingId() + "_training_cer_template.pdf";
            CertificateImageExample certificateImageExample = new CertificateImageExample();
            String backImgPath = mainPath +
                    certificateImageMapper.selectByExample(certificateImageExample).get(0).getImgPath();
            Map<String, String> contentMap = new HashMap<>();
            contentMap.put("training_name", "\"" + training.getTrainingName() + "\"");
            AssociationTextExample associationTextExample = new AssociationTextExample();
            associationTextExample.createCriteria().andItemNameEqualTo("association_name");
            AssociationText associationText = associationTextMapper.selectByExample(associationTextExample).get(0);
            contentMap.put("association_name", associationText.getItemContext());

            if (PdfUtil.writePdf(contentMap, temPath, mainPath + outPath, backImgPath)) {
                result.put("msg", "新建培训成功！初始化培训证书模板成功！");
                training.setCerTemPath(outPath);
                trainingMapper.updateByPrimaryKeySelective(training);
            } else {
                result.put("msg", "新建培训成功！初始化培训证书模板失败！");
            }
        } else {
            result.put("code", "fail");
            result.put("msg", "新建培训失败！请重试");
        }
        return result;
    }

    @Override
    public JSONObject adminGetTrainingList() {
        JSONObject result = new JSONObject();
        JSONArray data = new JSONArray();

        TrainingExample trainingExample = new TrainingExample();
        trainingExample.setOrderByClause("post_time DESC");
        List<Training> trainings = trainingMapper.selectByExample(trainingExample);
        if (trainings.size() == 0) {
            result.put("code", "success");
            result.put("msg", "系统中暂无已发布的培训");
            result.put("data", null);
            return result;
        }
        for (Training training : trainings) {
            JSONObject object = new JSONObject();
            object.put("label", training.getTrainingName());
            object.put("id", training.getTrainingId());
            object.put("postTime", training.getPostTime());
            object.put("level", training.getLevel());
            object.put("normalFee", training.getTrainingFeeNormal());
            object.put("vipFee", training.getTrainingFeeVip());
            JSONArray introduce = new JSONArray();
            String[] introduceStrs = training.getTrainingIntroduce().split("\\|");
            for (String intro : introduceStrs) {
                introduce.add(intro);
            }
            object.put("introduction", introduce);
            object.put("startDate", training.getTrainingStartTime());
            object.put("endDate", training.getTrainingEndTime());
            data.add(object);
        }
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取已发布培训成功");
        return result;

    }

    //管理员获取学员参加过的培训记录
    @Override
    public JSONObject getStudentTrainingHistory(String idCard, Integer currentPage, Integer pageSize) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        Student student = getObjectHelper.getStudentFromIdCard(idCard);
        if (student == null) {
            result.put("code", "fail");
            result.put("msg", "系统中没有该名学员");
            result.put("data", data);
            return result;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        TrainingReStudentExample trainingReStudentExample = new TrainingReStudentExample();
        trainingReStudentExample.createCriteria().andStudentIdEqualTo(student.getStudentId());
        long total = trainingReStudentMapper.countByExample(trainingReStudentExample);
        trainingReStudentExample.setOffset((currentPage - 1) * pageSize);
        trainingReStudentExample.setLimit(pageSize);
        trainingReStudentExample.setOrderByClause("is_invalid ASC");
        List<TrainingReStudent> trainingReStudents = trainingReStudentMapper.selectByExample(trainingReStudentExample);
        data.put("total", total);
        JSONArray list = new JSONArray();
        for (TrainingReStudent trainingReStudent : trainingReStudents) {
            JSONObject object = new JSONObject();
            object.put("id", trainingReStudent.getTrainingId());
            Training training = trainingMapper.selectByPrimaryKey(trainingReStudent.getTrainingId());
            object.put("name", training.getTrainingName());
            object.put("startTime", trainingReStudent.getBeginTime());
            object.put("endTime", trainingReStudent.getFinishTime());
            object.put("address", training.getTrainingAddress());
            if (trainingReStudent.getIsInvalid()) {
                object.put("status", "培训失效(或考试不通过次数已达上限)");
            } else {
                if (trainingReStudent.getIsDone())
                    object.put("status", "已完成培训");
                else
                    object.put("status", "正在培训中");
            }
            list.add(object);
        }
        data.put("list", list);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取学员" + student.getStudentName() + "培训记录成功");
        return result;
    }

    //管理员获取已经发布的培训
    @Override
    public JSONObject getPublishedTraining(Integer pageSize, Integer currentPage,
                                           String startDateStr, String endDateStr, String name) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("data", data);
        result.put("code", "fail");
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        TrainingExample trainingExample = new TrainingExample();
        TrainingExample.Criteria criteria = trainingExample.createCriteria();
        if (name != null && !name.equals("")) {
            criteria.andTrainingNameLike("%" + name + "%");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (startDateStr != null && !startDateStr.equals("")) {
            Date startDate;
            try {
                startDate = simpleDateFormat.parse(startDateStr);
            } catch (Exception e) {
                result.put("msg", "日期格式错误");
                return result;
            }
            criteria.andTrainingStartTimeGreaterThanOrEqualTo(startDate);
        }
        if (endDateStr != null && !endDateStr.equals("")) {
            Date endDate;
            try {
                endDate = simpleDateFormat.parse(endDateStr);
            } catch (Exception e) {
                result.put("msg", "日期格式错误");
                return result;
            }
            criteria.andTrainingEndTimeLessThan(endDate);
        }
        long total = trainingMapper.countByExample(trainingExample);
        trainingExample.setOffset((currentPage - 1) * pageSize);
        trainingExample.setLimit(pageSize);
        trainingExample.setOrderByClause("post_time DESC");
        List<Training> trainings = trainingMapper.selectByExample(trainingExample);
        JSONArray list = new JSONArray();
        for (Training training : trainings) {
            JSONObject object = new JSONObject();
            object.put("id", training.getTrainingId());
            object.put("name", training.getTrainingName());
            object.put("startTime", training.getTrainingStartTime());
            object.put("endTime", training.getTrainingEndTime());
            object.put("address", training.getTrainingAddress());
            object.put("fee", training.getTrainingFeeNormal());
            object.put("vipFee", training.getTrainingFeeVip());
            object.put("level", training.getLevel());
            object.put("phone", training.getContactPhone());
            object.put("contacts", training.getContactName());
            JSONArray introduce = new JSONArray();
            String[] introduceStrs = training.getTrainingIntroduce().split("\\|");
            for (String intro : introduceStrs) {
                introduce.add(intro);
            }
            object.put("introduction", introduce);
            list.add(object);
        }
        data.put("list", list);
        data.put("total", total);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取已经发布的培训列表成功！");
        return result;
    }

    //管理员根据id获取已报名培训学员列表
    @Override
    public JSONObject getEnrolledStudentList(Integer pageSize, Integer currentPage, Integer trainingId, String studentName, String idCard, String company) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();
        data.put("total", TrainingStudentSQLConn.countStudentByTrainingId(trainingId, studentName, idCard, company));
        data.put("list", TrainingStudentSQLConn.getStudentByTrainingId(pageSize, currentPage, trainingId, studentName, idCard, company));
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取已报名培训的学员列表成功！");
        return result;
    }
}

