package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.lutayy.campbackend.common.util.OrderIdGenerator;
import com.lutayy.campbackend.common.util.UUIDUtil;
import com.lutayy.campbackend.dao.*;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.SQLConn.SystemParamManager;
import com.lutayy.campbackend.service.SQLConn.TrainingStudentSQLConn;
import com.lutayy.campbackend.service.TrainingService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    GetObjectHelper getObjectHelper;


    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    /**
     *  学员培训报名记录查询
     *  有有效报名记录返回1，无则返回0
     **/
    private int checkStudentTraining(int studentId,int trainingId){
        TrainingReStudentExample trainingReStudentExample=new TrainingReStudentExample();
        TrainingReStudentExample.Criteria criteria=trainingReStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId).andTrainingIdEqualTo(trainingId).andIsInvalidEqualTo(false);
        long hasOrNot=trainingReStudentMapper.countByExample(trainingReStudentExample);
        return (int)hasOrNot;
    }


    @Override
    public JSONObject getCourses(JSONObject jsonObject) {
        String keyword = jsonObject.getString("keyWord");
        Date startDate = jsonObject.getDate("startDate");
        Date endDate = jsonObject.getDate("endDate");
        Integer pageSize = jsonObject.getInteger("pageSize");
        Integer currentPage = jsonObject.getInteger("currentPage");
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        TrainingExample trainingExample = new TrainingExample();
        TrainingExample.Criteria criteria = trainingExample.createCriteria();
        criteria.andTrainingNameLike("%" + keyword + "%");
        if (startDate != null) {
            criteria.andTrainingStartTimeGreaterThanOrEqualTo(startDate);
        }
        if (endDate != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(endDate);
            c.add(Calendar.DAY_OF_MONTH, +1);
            criteria.andTrainingEndTimeLessThanOrEqualTo(c.getTime());
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
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
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
    public JSONObject getJoinableTraining(Integer memberId) {
        JSONObject result=new JSONObject();
        JSONArray data=new JSONArray();

        TrainingExample trainingExample=new TrainingExample();
        TrainingExample.Criteria criteria=trainingExample.createCriteria();
        criteria.andTrainingStartTimeLessThan(new Date()).andTrainingEndTimeGreaterThan(new Date());
        List<Training> trainings=trainingMapper.selectByExample(trainingExample);
        if(trainings.size()==0){
            result.put("code", "success");
            result.put("msg", "无可报名培训");
            result.put("data", null);
            return result;
        }
        int isVip=0;
        Member member=memberMapper.selectByPrimaryKey(memberId);
        if(member!=null){
            if(member.getIsVip()==true){
                isVip=1;
            }
        }
        for(Training training:trainings){
            JSONObject object=new JSONObject();
            object.put("id", training.getTrainingId());
            object.put("name", training.getTrainingName());
            object.put("date", simpleDateFormat.format(training.getTrainingStartTime())+"----"+simpleDateFormat.format(training.getTrainingEndTime()));
            object.put("address", training.getTrainingAddress());
            JSONArray introduce=new JSONArray();
            String[] introduceStrs=training.getTrainingIntroduce().split("\\|");
            for(String intro:introduceStrs){
                introduce.add(intro);
            }
            object.put("introduction", introduce);
            if(isVip==0){
                object.put("fee", training.getTrainingFeeNormal());
            }else {
                object.put("fee", training.getTrainingFeeVip());
            }
            object.put("contacts", training.getContactName());
            object.put("phone", training.getContactPhone());
            data.add(object);
        }
        result.put("code", "success");
        result.put("msg", "获取可报名培训成功");
        result.put("data", data);
        return result;
    }

    @Override
    public JSONObject getJoinableTraining() {
        JSONObject result=new JSONObject();
        JSONArray data=new JSONArray();

        TrainingExample trainingExample=new TrainingExample();
        TrainingExample.Criteria criteria=trainingExample.createCriteria();
        criteria.andTrainingStartTimeLessThan(new Date()).andTrainingEndTimeGreaterThan(new Date());
        List<Training> trainings=trainingMapper.selectByExample(trainingExample);
        if(trainings.size()==0){
            result.put("code", "success");
            result.put("msg", "无可报名培训");
            result.put("data", null);
            return result;
        }
        for(Training training:trainings){
            JSONObject object=new JSONObject();
            object.put("id", training.getTrainingId());
            object.put("name", training.getTrainingName());
            object.put("date", simpleDateFormat.format(training.getTrainingStartTime())+"----"+simpleDateFormat.format(training.getTrainingEndTime()));
            object.put("address", training.getTrainingAddress());
            JSONArray introduce=new JSONArray();
            String[] introduceStrs=training.getTrainingIntroduce().split("\\|");
            for(String intro:introduceStrs){
                introduce.add(intro);
            }
            object.put("introduction", introduce);
            object.put("fee", training.getTrainingFeeNormal());
            object.put("contacts", training.getContactName());
            object.put("phone", training.getContactPhone());
            data.add(object);
        }
        result.put("code", "success");
        result.put("msg", "获取可报名培训成功");
        result.put("data", data);
        return result;
    }

    /** 学员中心接口 **/
    @Override
    public JSONObject getStudentSignedTraining(Integer studentId) {
        JSONObject result=new JSONObject();
        Student student=studentMapper.selectByPrimaryKey(studentId);
        if(student==null){
            result.put("code", "fail");
            result.put("msg", "当前用户不存在！");
            result.put("data", null);
            return result;
        }
        TrainingOrderExample trainingOrderExample=new TrainingOrderExample();
        TrainingOrderExample.Criteria criteria1=trainingOrderExample.createCriteria();
        criteria1.andOrderTypeEqualTo(true).andPaymentStateEqualTo(false).andStudentIdEqualTo(studentId);
        List<TrainingOrder> trainingOrders=trainingOrderMapper.selectByExample(trainingOrderExample);

        TrainingReStudentExample trainingReStudentExample=new TrainingReStudentExample();
        TrainingReStudentExample.Criteria criteria=trainingReStudentExample.createCriteria();
        criteria.andIsInvalidEqualTo(false).andStudentIdEqualTo(studentId);
        List<TrainingReStudent> trainingReStudents=trainingReStudentMapper.selectByExample(trainingReStudentExample);

        if(trainingOrders.size()==0 && trainingReStudents.size()==0){
            result.put("code", "success");
            result.put("msg", "用户暂无报名任何活动");
            result.put("data", null);
            return result;
        }
        List<Training> trainings=new ArrayList<>();
        for(TrainingOrder trainingOrder:trainingOrders){
            trainings.add(trainingMapper.selectByPrimaryKey(trainingOrder.getTrainingId()));
        }
        for(TrainingReStudent trainingReStudent:trainingReStudents){
            trainings.add(trainingMapper.selectByPrimaryKey(trainingReStudent.getTrainingId()));
        }
        JSONArray data=new JSONArray();
        int num=0;
        for(Training training:trainings){
            JSONObject object=new JSONObject();
            object.put("id", training.getTrainingId());
            object.put("name", training.getTrainingName());
            object.put("date", simpleDateFormat.format(training.getTrainingStartTime())+"----"+simpleDateFormat.format(training.getTrainingEndTime()));
            object.put("address", training.getTrainingAddress());
            JSONArray introduce=new JSONArray();
            String[] introduceStrs=training.getTrainingIntroduce().split("\\|");
            for(String intro:introduceStrs){
                introduce.add(intro);
            }
            object.put("introduction", introduce);
            object.put("fee", training.getTrainingFeeNormal());
            if(num<trainingOrders.size()){
                object.put("status", "未支付");
            } else {
                object.put("status", "已支付");
            }
            object.put("contacts", training.getContactName());
            object.put("phone", training.getContactPhone());
            data.add(object);
            num+=1;
        }
        result.put("code", "success");
        result.put("msg", "成功获取已报名的培训");
        result.put("data", data);
        return result;

    }

    @Override
    public JSONObject studentJoinTraining(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        if(SystemParamManager.getValueByKey("stu_tran_permission").equals("0")){
            result.put("code", "fail");
            result.put("msg", "当前用户没有报名培训的权限");
            return result;
        }
        Integer studentId=jsonObject.getInteger("id");
        MemberReStudentExample memberReStudentExample=new MemberReStudentExample();
        MemberReStudentExample.Criteria criteria=memberReStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId);
        if(memberReStudentMapper.selectByExample(memberReStudentExample)!=null){
            result.put("code", "fail");
            result.put("msg", "请由所属单位报名");
            return result;
        }

        int trainingId=jsonObject.getInteger("trainingId");

        Training training=trainingMapper.selectByPrimaryKey(trainingId);
        if(training==null){
            result.put("code", "fail");
            result.put("msg", "系统中查询不到该项培训");
            return result;
        }

        if(checkStudentTraining(studentId, trainingId)==1){
            result.put("code", "fail");
            result.put("msg", "已报名该培训");
            return result;
        }

        TrainingOrderExample trainingOrderExample=new TrainingOrderExample();
        TrainingOrderExample.Criteria criteria1=trainingOrderExample.createCriteria();
        criteria1.andTrainingIdEqualTo(trainingId).andStudentIdEqualTo(studentId).andCloseEqualTo(false).andPaymentStateEqualTo(false).andOrderTypeEqualTo(true);
        List<TrainingOrder> trainingOrders=trainingOrderMapper.selectByExample(trainingOrderExample);
        if(trainingOrders.size()!=0){
            result.put("code", "fail");
            result.put("msg", "已报名此培训但未支付，请前往订单中心查看");
            return result;
        }

        String orderId=OrderIdGenerator.getUniqueId();
        //订单号生成并查重（查重如非高并发系统基本上可以省略）
//        while(trainingOrderMapper.selectByPrimaryKey(orderId)!=null){
//            orderId=OrderIdGenerator.getUniqueId();
//        }
        /**
         * 由学员自行报名，对应的订单，无须插入“订单—学生”表
         * **/
        TrainingOrder trainingOrder=new TrainingOrder();
        trainingOrder.setTrainingOrderId(orderId);
        trainingOrder.setTrainingId(trainingId);
        trainingOrder.setOrderType(true);
        trainingOrder.setStudentId(studentId);
        trainingOrder.setOrderPrice(training.getTrainingFeeNormal());
        trainingOrder.setOrderBeginTime(new Date());
        trainingOrder.setPaymentState(false);
        trainingOrder.setClose(false);
        if(trainingOrderMapper.insert(trainingOrder)>0){
            result.put("code", "success");
            result.put("msg", "订单生成生成!待支付");
        }else{
            result.put("code", "fail");
            result.put("msg", "订单生成失败!");
        }
        confirmOrder(orderId);
        return result;
    }

    /** 会员中心接口 **/
    @Override
    public JSONObject getMemberSignedTraining(Integer memberId) {
        JSONObject result=new JSONObject();

        TrainingOrderExample trainingOrderExample=new TrainingOrderExample();
        TrainingOrderExample.Criteria criteria=trainingOrderExample.createCriteria();
        criteria.andOrderTypeEqualTo(false).andMemberKeyIdEqualTo(memberId).andCloseEqualTo(false).andPaymentStateEqualTo(false);
        trainingOrderExample.setOrderByClause("order_begin_time DESC");
        List<TrainingOrder> trainingOrders=trainingOrderMapper.selectByExample(trainingOrderExample);

        List<Integer> trainingIds=TrainingStudentSQLConn.getTrainingIdByMemberId(memberId);

        if(trainingIds.size()==0 && trainingOrders.size()==0){
            result.put("code", "success");
            result.put("msg", "暂无已报名培训");
            result.put("data", null);
        }
        List<Training> trainings=new ArrayList<Training>();
        //此处可以使排列倒过来，使排在前面的是最新报名的培训
        for(int i=0;i<trainingOrders.size();i++){
            trainings.add(trainingMapper.selectByPrimaryKey(trainingOrders.get(i).getTrainingId()));
        }
        for(int i=trainingIds.size()-1;i>=0;i--){
            trainings.add(trainingMapper.selectByPrimaryKey(trainingIds.get(i)));
        }
        int isVip=0;
        Member member=memberMapper.selectByPrimaryKey(memberId);
        if(member!=null){
            if(member.getIsVip()==true){
                isVip=1;
            }
        }
        JSONArray data=new JSONArray();
        int num=0;
        for(Training training:trainings){
            JSONObject object=new JSONObject();
            object.put("id", training.getTrainingId());
            object.put("name", training.getTrainingName());
            object.put("date", simpleDateFormat.format(training.getTrainingStartTime())+"----"+simpleDateFormat.format(training.getTrainingEndTime()));
            object.put("address", training.getTrainingAddress());
            JSONArray introduce=new JSONArray();
            String[] introduceStrs=training.getTrainingIntroduce().split("\\|");
            for(String intro:introduceStrs){
                introduce.add(intro);
            }
            object.put("introduction", introduce);
            if(isVip==0){
                object.put("fee", training.getTrainingFeeNormal());
            }else {
                object.put("fee", training.getTrainingFeeVip());
            }
            if(num<trainingOrders.size()){
                object.put("status", "有订单未支付");
            } else {
                object.put("status", "已支付");
            }
            object.put("contacts", training.getContactName());
            object.put("phone", training.getContactPhone());
            data.add(object);
            num+=1;
        }
        result.put("code", "success");
        result.put("msg", "成功获取已报名的培训");
        result.put("data", data);
        return result;
    }

    @Override
    public JSONObject memberJoinTraining(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        Integer memberId=jsonObject.getInteger("id");
        int trainingId=jsonObject.getInteger("trainingId");
        JSONArray idNums=jsonObject.getJSONArray("idNums");
        Training training=trainingMapper.selectByPrimaryKey(trainingId);
        if(training==null){
            result.put("code", "fail");
            result.put("msg", "系统中没有该培训");
            return result;
        }

        String orderId= OrderIdGenerator.getUniqueId();
        //订单号生成并查重（如非高并发系统基本上可以省略）
//        while(trainingOrderMapper.selectByPrimaryKey(orderId)!=null){
//            orderId=OrderIdGenerator.getUniqueId();
//        }
        /**
         * 由会员报名，对应的订单，还需插入“订单—学生”表
         **/
        TrainingOrder trainingOrder=new TrainingOrder();
        trainingOrder.setTrainingOrderId(orderId);
        trainingOrder.setTrainingId(trainingId);
        trainingOrder.setOrderType(false);//"0"即会员提交的订单
        trainingOrder.setMemberKeyId(memberId);
        trainingOrder.setOrderBeginTime(new Date());
        trainingOrder.setClose(false);
        trainingOrder.setPaymentState(false);
        trainingOrderMapper.insertSelective(trainingOrder);
        int isVip=0;
        Member member=memberMapper.selectByPrimaryKey(memberId);
        if(member!=null){
            if(member.getIsVip()){
                isVip=1;
            }
        }
        BigDecimal fee = isVip==0 ? training.getTrainingFeeNormal() : training.getTrainingFeeVip();

        int existTotalCount=0;
        String existTagTip="";
        int existOrderTotalCount=0;
        String existOrderTagTip="";
        for(int i=0;i<idNums.size();i++){
            Student student=getObjectHelper.getStudentFromIdCard(idNums.getString(i));
            if(checkStudentTraining(student.getStudentId(), trainingId)!=0){
                existTotalCount+=1;
                existTagTip+=student.getStudentName()+"("+idNums.getString(i)+") ";
                continue;
            }
            String checkOrderId=TrainingStudentSQLConn.getTrainingOrderByStudentId(student.getStudentId(),trainingId);
//            System.out.println(checkOrderId);
            if(checkOrderId!=null){
                existOrderTotalCount+=1;
                existOrderTagTip+=student.getStudentName()+"(订单"+checkOrderId+") ";
                continue;
            }
            //插入“订单—学生”表
            TrainingOrderStudent trainingOrderStudent=new TrainingOrderStudent();
            trainingOrderStudent.setOrderKeyId(trainingOrder.getOrderKeyId());
            trainingOrderStudent.setStudentId(student.getStudentId());
            trainingOrderStudentMapper.insertSelective(trainingOrderStudent);
        }
        BigDecimal successNums = new BigDecimal(idNums.size() - existTotalCount - existOrderTotalCount);
//        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        trainingOrder.setOrderPrice(fee.multiply(successNums));
        trainingOrderMapper.updateByPrimaryKeySelective(trainingOrder);
        String msg="尝试为"+idNums.size()+"名学员报名； 成功"+(idNums.size()-existTotalCount-existOrderTotalCount)+"名； ";
        result.put("code", "success");
        if(existTotalCount>0){
            msg+="剔除重复报名"+existTotalCount+"名,分别是:["+existTagTip+"] ";
        }
        if(existOrderTotalCount>0){
            msg+="剔除已存在于该培训未支付订单中的学员"+existOrderTotalCount+"名,分别是:["+existOrderTagTip+"] ";
        }
        if(idNums.size() - existTotalCount - existOrderTotalCount < 1){
            result.put("code", "fail");
            result.put("data",null);
            //提交的名单没有报名成功的，删除订单
            trainingOrderMapper.deleteByPrimaryKey(trainingOrder.getOrderKeyId());
        }else {
            result.put("code", "success");
            result.put("data", orderId);
            msg+="订单生成成功！订单号："+orderId;
        }

        /** 模拟订单支付确认 **/
        if(confirmOrder(orderId)==1){
            msg+="  模拟订单支付确认完成";
        }
        result.put("msg", msg);
        return result;
    }

    /** 会员订单成功支付 **/
    private int confirmOrder(String orderId){
        System.out.println("---- 模拟订单支付确认 开启 ----");
        TrainingOrderExample trainingOrderExample=new TrainingOrderExample();
        TrainingOrderExample.Criteria criteriaOrder=trainingOrderExample.createCriteria();
        criteriaOrder.andTrainingOrderIdEqualTo(orderId);
        List<TrainingOrder> trainingOrders=trainingOrderMapper.selectByExample(trainingOrderExample);
        if(trainingOrders.size()<1){
            System.out.println("---- 该订单不存在 ----");
            return -1;
        }
        TrainingOrder trainingOrder=trainingOrders.get(0);
        int trainingId=trainingOrder.getTrainingId();
        trainingOrder.setPaymentState(true);
        trainingOrderMapper.updateByPrimaryKeySelective(trainingOrder);
        List<Integer> studentIds=new ArrayList<>();
        if(trainingOrder.getOrderType()!=null && !trainingOrder.getOrderType()){
            TrainingOrderStudentExample trainingOrderStudentExample=new TrainingOrderStudentExample();
            TrainingOrderStudentExample.Criteria criteria=trainingOrderStudentExample.createCriteria();
            criteria.andOrderKeyIdEqualTo(trainingOrder.getOrderKeyId());
            List<TrainingOrderStudent> trainingOrderStudents=trainingOrderStudentMapper.selectByExample(trainingOrderStudentExample);
            if(trainingOrderStudents.size()==0){
                System.out.println("---- 该订单下无学员 结束 ----");
                return -1;
            }
            for(TrainingOrderStudent trainingOrderStudent:trainingOrderStudents){
                studentIds.add(trainingOrderStudent.getStudentId());
            }
        }
        if(trainingOrder.getOrderType()!=null && trainingOrder.getOrderType()){
            studentIds.add(trainingOrder.getStudentId());
        }
        /** 检查该培训是否已发布试卷,有的话就添加到学生的试卷可考列表中 **/
        ExamExample examExample=new ExamExample();
        ExamExample.Criteria criteria1=examExample.createCriteria();
        criteria1.andTrainingIdEqualTo(trainingId).andIsPostedEqualTo(true);
        List<Exam> exams=examMapper.selectByExample(examExample);
        boolean isExamPosted=false;
        if(exams.size()!=0){
            isExamPosted=true;
        }
        for(Integer studentId:studentIds){
            TrainingReStudentExample trainingReStudentExample=new TrainingReStudentExample();
            TrainingReStudentExample.Criteria criteria=trainingReStudentExample.createCriteria();
            criteria.andStudentIdEqualTo(studentId).andTrainingIdEqualTo(trainingId).andIsInvalidEqualTo(false);
            if (trainingReStudentMapper.selectByExample(trainingReStudentExample).size()==0){
                TrainingReStudent trainingReStudent=new TrainingReStudent();
                trainingReStudent.setStudentId(studentId);
                trainingReStudent.setTrainingId(trainingId);
                trainingReStudent.setApplyId(UUIDUtil.getTrainingApplyNumber(trainingId));
                trainingReStudent.setBeginTime(new Date());
                trainingReStudentMapper.insertSelective(trainingReStudent);
                System.out.println("---- 学员"+studentId+"报名培训成功 ----");
            }
            if(isExamPosted){
                ExamReStudentExample examReStudentExample=new ExamReStudentExample();
                ExamReStudentExample.Criteria criteria2=examReStudentExample.createCriteria();
                criteria2.andStudentIdEqualTo(studentId).andExamIdEqualTo(exams.get(0).getExamId());
                if(examReStudentMapper.selectByExample(examReStudentExample).size()==0){
                    ExamReStudent examReStudent=new ExamReStudent();
                    examReStudent.setStudentId(studentId);
                    examReStudent.setExamId(exams.get(0).getExamId());
                    examReStudentMapper.insertSelective(examReStudent);
                    System.out.println("---- 学员"+studentId+"报名考试成功(考试已添加进列表) ----");
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
        String trainingName=jsonObject.getString("name");
        BigDecimal fee=jsonObject.getBigDecimal("fee");
        BigDecimal vipFee=jsonObject.getBigDecimal("vipFee");
        String contactName=jsonObject.getString("contacts");
        String contactPhone=jsonObject.getString("phone");
        Date startTime=jsonObject.getDate("openTime");
        Date endTime=jsonObject.getDate("closeTime");
        Byte level=jsonObject.getByte("level");
        String address=jsonObject.getString("address");
        JSONArray trainingIntro=jsonObject.getJSONArray("desc");
        StringBuilder intro=new StringBuilder();
        for(int i=0;i<trainingIntro.size()-1;i++){
            intro.append(trainingIntro.get(i));
            intro.append("|");
        }
        intro.append(trainingIntro.get(trainingIntro.size()-1));

        JSONObject result=new JSONObject();
        //培训名称查重
        TrainingExample trainingExample=new TrainingExample();
        TrainingExample.Criteria criteria=trainingExample.createCriteria();
        criteria.andTrainingNameEqualTo(trainingName);
        if(trainingMapper.selectByExample(trainingExample).size()>0){
            result.put("code", "fail");
            result.put("msg", "已有同名的培训,新建失败！");
            return result;
        }
        Training training=new Training();
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
        if(trainingMapper.insert(training)>0){
            result.put("code", "success");
            result.put("msg", "新建培训成功！");
        }else{
            result.put("code", "fail");
            result.put("msg", "新建培训失败！请重试");
        }
        return result;
    }

    @Override
    public JSONObject adminGetTrainingList() {
        JSONObject result = new JSONObject();
        JSONArray data=new JSONArray();

        TrainingExample trainingExample=new TrainingExample();
        trainingExample.setOrderByClause("post_time DESC");
        List<Training> trainings=trainingMapper.selectByExample(trainingExample);
        if(trainings.size()==0){
            result.put("code", "success");
            result.put("msg", "系统中暂无已发布的培训");
            result.put("data", null);
            return result;
        }
        for(Training training:trainings){
            JSONObject object=new JSONObject();
            object.put("label", training.getTrainingName());
            object.put("id", training.getTrainingId());
            object.put("postTime", training.getPostTime());
            object.put("level", training.getLevel());
            object.put("normalFee", training.getTrainingFeeNormal());
            object.put("vipFee", training.getTrainingFeeVip());
            JSONArray introduce=new JSONArray();
            String[] introduceStrs=training.getTrainingIntroduce().split("\\|");
            for(String intro:introduceStrs){
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
}

