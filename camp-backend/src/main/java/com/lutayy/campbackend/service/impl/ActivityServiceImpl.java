package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.config.AuthorityParam;
import com.lutayy.campbackend.common.util.ExcelUtil;
import com.lutayy.campbackend.common.util.OrderIdGenerator;
import com.lutayy.campbackend.common.util.RedisUtil;
import com.lutayy.campbackend.common.util.UUIDUtil;
import com.lutayy.campbackend.dao.*;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.ActivityService;
import com.lutayy.campbackend.service.SQLConn.ActivityStudentSQLConn;
import com.lutayy.campbackend.service.SQLConn.SystemParamManager;
import com.lutayy.campbackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    ActivityStudentMapper activityStudentMapper;
    @Autowired
    ActivityOrderMapper activityOrderMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    ActivityOrderStudentMapper activityOrderStudentMapper;
    @Autowired
    MemberReStudentMapper memberReStudentMapper;
    @Autowired
    ActivitySeatMapper activitySeatMapper;
    @Autowired
    GetObjectHelper getObjectHelper;
    @Resource
    private RedisUtil redisUtil;


    /**
     * 学员活动报名记录查询
     * 有有效报名记录返回1，无则返回0
     **/
    private int checkStudentActiviy(int studentId, int activityId) {
        ActivityStudentExample activityStudentExample = new ActivityStudentExample();
        ActivityStudentExample.Criteria criteria = activityStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId).andActivityIdEqualTo(activityId);
        long hasOrNot = activityStudentMapper.countByExample(activityStudentExample);
        return (int) hasOrNot;
    }


    @Override
    public JSONObject getJoinableActivities(Integer pageSize, Integer currentPage, String name) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();

        ActivityExample activityExample = new ActivityExample();
        ActivityExample.Criteria criteria = activityExample.createCriteria();
        criteria.andOpenTimeLessThan(new Date()).andCloseTimeGreaterThan(new Date());
        if (name != null && !name.equals("")) {
            criteria.andActivityNameLike("%" + name + "%");
        }
        long total=activityMapper.countByExample(activityExample);
        activityExample.setOffset((currentPage-1)*pageSize);
        activityExample.setLimit(pageSize);
        List<Activity> activities = activityMapper.selectByExample(activityExample);
        if (activities.size() == 0) {
            result.put("code", "success");
            result.put("msg", "暂无可报名活动");
            result.put("data", null);
            return result;
        }

        for (Activity activity : activities) {
            JSONObject object = new JSONObject();
            object.put("id", activity.getActivityId());
            object.put("name", activity.getActivityName());
            object.put("date", activity.getActivityDate());
            object.put("address", activity.getActivityAddress());
            object.put("fee", activity.getActivityFee());
            JSONArray introduce = new JSONArray();
            String[] introduceStrs = activity.getActivityIntroduction().split("\\|");
            for (String intro : introduceStrs) {
                introduce.add(intro);
            }
            object.put("introduce", introduce);
            object.put("contacts", activity.getContactName());
            object.put("phone", activity.getContactPhone());
            list.add(object);
        }
        data.put("list", list);
        data.put("total", total);
        result.put("code", "success");
        result.put("msg", "查询成功!");
        result.put("data", data);
        return result;
    }

    /**
     * 学员中心的活动管理
     **/
    @Override
    public JSONObject getSignedActivities(Integer studentId, Integer currentPage, Integer pageSize, String name) {
        JSONObject result = new JSONObject();
        JSONObject data=new JSONObject();
        JSONArray list=new JSONArray();

        ActivityStudentExample activityStudentExample = new ActivityStudentExample();
        ActivityStudentExample.Criteria criteria = activityStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId);
        List<ActivityStudent> activityStudents = activityStudentMapper.selectByExample(activityStudentExample);

        ActivityOrderExample activityOrderExample = new ActivityOrderExample();
        ActivityOrderExample.Criteria criteria1 = activityOrderExample.createCriteria();
        criteria1.andStudentIdEqualTo(studentId).andCloseEqualTo(false).andPaymentStateEqualTo(false).andOrderTypeEqualTo(true);
        List<ActivityOrder> activityOrders = activityOrderMapper.selectByExample(activityOrderExample);

        int total=-1;

        if (activityStudents.size() == 0 && activityOrders.size() == 0) {
            result.put("code", "success");
            result.put("msg", "该用户暂无报名任何活动");
            data.put("total", total+1);
            data.put("list", list);
            result.put("data", data);
            return result;
        }

        Set<Integer> activityIdsSet=new HashSet<>();//用于将未支付的订单属于同一活动的进行去重

        for (ActivityOrder activityOrder : activityOrders) {
            if(getObjectHelper.checkActivityOrderOutOfTime(activityOrder))
                continue;
            if(!activityIdsSet.add(activityOrder.getActivityId()))
                continue;
            Activity activity = activityMapper.selectByPrimaryKey(activityOrder.getActivityId());
            if(name!=null && !name.equals("")){
                if(!activity.getActivityName().contains(name))
                    continue;
            }
            total++;
            if(total>=(currentPage-1)*pageSize && total<currentPage*pageSize) {
                JSONObject object = new JSONObject();
                object.put("id", activity.getActivityId());
                object.put("name", activity.getActivityName());
                object.put("date", activity.getActivityDate());
                object.put("address", activity.getActivityAddress());
                object.put("fee", activity.getActivityFee());
                JSONArray introduce = new JSONArray();
                String[] introduceStrs = activity.getActivityIntroduction().split("\\|");
                for (String intro : introduceStrs) {
                    introduce.add(intro);
                }
                object.put("introduce", introduce);
                object.put("contacts", activity.getContactName());
                object.put("phone", activity.getContactPhone());
                object.put("status", "未支付");
                list.add(object);
            }
        }

        for (int i = 0; i < activityStudents.size(); i++) {
            Activity activity = activityMapper.selectByPrimaryKey(activityStudents.get(i).getActivityId());
            if(name!=null && !name.equals("")){
                if(!activity.getActivityName().contains(name))
                    continue;
            }
            total++;
            JSONObject object = new JSONObject();
            object.put("id", activity.getActivityId());
            object.put("name", activity.getActivityName());
            object.put("date", activity.getActivityDate());
            object.put("address", activity.getActivityAddress());
            object.put("fee", activity.getActivityFee());
            JSONArray introduce = new JSONArray();
            introduce.add(activity.getActivityIntroduction());
            object.put("introduce", introduce);
            object.put("contacts", activity.getContactName());
            object.put("phone", activity.getContactPhone());
            object.put("status", "已支付");
            if(total>=(currentPage-1)*pageSize && total<currentPage*pageSize)
                list.add(object);
        }
        result.put("code", "success");
        result.put("msg", "查询成功!");
        data.put("list", list);
        data.put("total", total+1);
        result.put("data", data);
        return result;
    }

    @Override
    public JSONObject studentGetSeatNum(Integer studentId, int activityId) {
        JSONObject result = new JSONObject();

        ActivityStudentExample activityStudentExample = new ActivityStudentExample();
        ActivityStudentExample.Criteria criteria = activityStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId).andActivityIdEqualTo(activityId);
        List<ActivityStudent> activityStudents = activityStudentMapper.selectByExample(activityStudentExample);
        if (activityStudents.size() == 0) {
            result.put("code", "fail");
            result.put("msg", "当前用户未成功报名此活动");
            result.put("data", null);
            return result;
        }
        ActivityStudent activityStudent = activityStudents.get(0);
        if (activityStudent.getSeatNumber() == null) {
            result.put("code", "fail");
            result.put("msg", "抱歉，暂时未安排座位");
            result.put("data", null);
            return result;
        }
        result.put("code", "success");
        result.put("msg", "查询成功");
        result.put("data", activityStudent.getSeatNumber());
        return result;
    }

    @Override
    public JSONObject studentJoinActivity(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        if (SystemParamManager.getValueByKey("stu_tran_permission").equals("0")) {
            result.put("code", "fail");
            result.put("msg", "当前用户没有报名培训的权限");
            return result;
        }
        Integer studentId = jsonObject.getInteger("id");
        Student student=studentMapper.selectByPrimaryKey(studentId);
        MemberReStudentExample memberReStudentExample = new MemberReStudentExample();
        MemberReStudentExample.Criteria criteria2 = memberReStudentExample.createCriteria();
        criteria2.andStudentIdEqualTo(studentId);
        if (memberReStudentMapper.selectByExample(memberReStudentExample) != null) {
            result.put("code", "fail");
            result.put("msg", "请由所属单位报名");
            return result;
        }

        int activityId = jsonObject.getInteger("activityId");

        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        if (activity == null) {
            result.put("code", "fail");
            result.put("msg", "报名的活动不存在!");
            return result;
        }

        ActivityStudentExample activityStudentExample = new ActivityStudentExample();
        ActivityStudentExample.Criteria criteria = activityStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId).andActivityIdEqualTo(activityId);
        List<ActivityStudent> activityStudents = activityStudentMapper.selectByExample(activityStudentExample);
        if (activityStudents.size() != 0) {
            result.put("code", "fail");
            result.put("msg", "已报名此活动");
            return result;
        }
        ActivityOrderExample activityOrderExample = new ActivityOrderExample();
        ActivityOrderExample.Criteria criteria1 = activityOrderExample.createCriteria();
        criteria1.andActivityIdEqualTo(activityId).andStudentIdEqualTo(studentId).andCloseEqualTo(false).andPaymentStateEqualTo(false).andOrderTypeEqualTo(true);
        List<ActivityOrder> activityOrders = activityOrderMapper.selectByExample(activityOrderExample);
        if (activityOrders.size() != 0) {
            /**
             * 此处可补一个订单失效判断
             * **/
            result.put("code", "fail");
            result.put("msg", "已报名此活动但未支付，请前往订单中心查看");
            return result;
        }

        ActivityStudentExample activityStudentExample0 = new ActivityStudentExample();
        ActivityStudentExample.Criteria criteria0 = activityStudentExample0.createCriteria();
        criteria0.andActivityIdEqualTo(activityId);
        List<ActivityStudent> activityStudents0 = activityStudentMapper.selectByExample(activityStudentExample0);
        if (activity.getMaxNum() != null) {
            if (activityStudents0.size() >= activity.getMaxNum()) {
                result.put("code", "fail");
                result.put("msg", "活动人数已满");
                return result;
            }
        }

        /** 报名成功操作，下面注释内容搬到订单支付成功后 **/
//        ActivityStudent activityStudent=new ActivityStudent();
//        String applyNumber= UUIDUtil.getActivityApplyNumber(activityId);//生成报名编号
//        //排重
//        while(activityStudentMapper.selectByPrimaryKey(applyNumber)!=null){
//            applyNumber= UUIDUtil.getActivityApplyNumber(activityId);
//        }
//        activityStudent.setApplyNumber(applyNumber);
//        activityStudent.setActivityId(activityId);
//        activityStudent.setStudentId(studentId);
//        activityStudent.setApplyTime(new Date());
//        if(activityStudentMapper.insert(activityStudent)>0){
        String orderId = OrderIdGenerator.getUniqueId();
        while (!redisUtil.hset("order_no_map", orderId, "activity")) {
            orderId = OrderIdGenerator.getUniqueId();
        }
        //订单号生成并查重（查重如非高并发系统基本上可以省略）
        //while(activityOrderMapper.selectByPrimaryKey(orderId)!=null){
        //    orderId=OrderIdGenerator.getUniqueId();
        //}
        /**
         * 由学员自行报名，对应的订单，！！也要！！插入“订单—学生”表
         * **/
        ActivityOrder activityOrder = new ActivityOrder();
        activityOrder.setActivityOrderId(orderId);
        activityOrder.setActivityId(activityId);
        activityOrder.setStudentId(studentId);
        activityOrder.setOrderType(true);//"1"即学生提交的订单
        activityOrder.setOrderPrice(activity.getActivityFee());
        activityOrder.setOrderBeginTime(new Date());
        activityOrder.setPaymentState(false);
        activityOrder.setClose(false);
        activityOrder.setOpManName(student.getStudentName());
        activityOrder.setBusinessName(activity.getActivityName());
        if (activityOrderMapper.insert(activityOrder) > 0) {
            result.put("code", "success");
            result.put("msg", "订单生成成功!待支付");
            // TODO 插入“订单—学生”表
            ActivityOrderStudent activityOrderStudent=new ActivityOrderStudent();
            activityOrderStudent.setStudentId(studentId);
            activityOrderStudent.setOrderKeyId(activityOrder.getOrderKeyId());
            activityOrderStudentMapper.insertSelective(activityOrderStudent);
        } else {
            result.put("code", "fail");
            result.put("msg", "订单生成失败!");
//                activityStudentMapper.deleteByExample(activityStudentExample);
        }
//        }else {
//            result.put("code", "fail");
//            result.put("msg", "报名失败，请稍后尝试");
//        }
        return result;
    }

    /** 学生支付订单前需要判断会员是不是已经帮自己报名了 **/

    /**
     * 会员中心的活动管理
     **/
    @Override
    public JSONObject memberJoinActivity(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        Integer memberId = jsonObject.getInteger("id");
        Member member=memberMapper.selectByPrimaryKey(memberId);
        int activityId = jsonObject.getInteger("activityId");
        JSONArray idNums = jsonObject.getJSONArray("idNums");
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        if (activity == null) {
            result.put("code", "fail");
            result.put("msg", "系统中没有该活动");
            return result;
        }

        ActivityStudentExample activityStudentExample0 = new ActivityStudentExample();
        ActivityStudentExample.Criteria criteria0 = activityStudentExample0.createCriteria();
        criteria0.andActivityIdEqualTo(activityId);
        List<ActivityStudent> activityStudents0 = activityStudentMapper.selectByExample(activityStudentExample0);
        if (activity.getMaxNum() != null) {
            if (activityStudents0.size() + idNums.size() > activity.getMaxNum()) {
                result.put("code", "fail");
                result.put("msg", "报名人数超过活动可容纳人数");
                return result;
            }
        }

        String orderId = OrderIdGenerator.getUniqueId();
        while (!redisUtil.hset("order_no_map", orderId, "activity")) {
            orderId = OrderIdGenerator.getUniqueId();
        }
        //订单号生成并查重（如非高并发系统基本上可以省略）
//        while(activityOrderMapper.selectByPrimaryKey(orderId)!=null){
//            orderId=OrderIdGenerator.getUniqueId();
//        }
        /**
         * 由会员报名，对应的订单，还需插入“订单—学生”表
         **/
        ActivityOrder activityOrder = new ActivityOrder();
        activityOrder.setActivityOrderId(orderId);
        activityOrder.setActivityId(activityId);
        activityOrder.setMemberKeyId(memberId);
        activityOrder.setOrderType(false);//"0"即会员提交的订单
        // activityOrder.setOrderPrice(activity.getActivityFee());//在筛选人数后再添加
        activityOrder.setOrderBeginTime(new Date());
        activityOrder.setPaymentState(false);
        activityOrder.setClose(false);
        activityOrder.setBusinessName(activity.getActivityName());
        activityOrder.setOpManName(member.getMemberName());
        activityOrderMapper.insert(activityOrder);

        int existTotalCount = 0;
        String existTagTip = "";
        int existOrderTotalCount = 0;
        String existOrderTagTip = "";
        for (int i = 0; i < idNums.size(); i++) {
            Student student = getObjectHelper.getStudentFromIdCard(idNums.getString(i));
            if (checkStudentActiviy(student.getStudentId(), activityId) != 0) {
                existTotalCount += 1;
                existTagTip += student.getStudentName() + "(" + idNums.getString(i) + ") ";
                continue;
            }
            String checkOrderId = ActivityStudentSQLConn.getActivityOrderByStudentId(student.getStudentId(), activityId);
//            System.out.println(checkOrderId);
            if (checkOrderId != null) {
                existOrderTotalCount += 1;
                existOrderTagTip += student.getStudentName() + "(订单" + checkOrderId + ") ";
                continue;
            }
            //插入“订单—学生”表
            ActivityOrderStudent activityOrderStudent = new ActivityOrderStudent();
            activityOrderStudent.setOrderKeyId(activityOrder.getOrderKeyId());
            activityOrderStudent.setStudentId(student.getStudentId());
            activityOrderStudentMapper.insertSelective(activityOrderStudent);
        }
        BigDecimal successNums = new BigDecimal(idNums.size() - existTotalCount - existOrderTotalCount);
//        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        activityOrder.setOrderPrice(activity.getActivityFee().multiply(successNums));
        activityOrderMapper.updateByPrimaryKeySelective(activityOrder);
        String msg = "尝试为" + idNums.size() + "名学员报名； 成功" + (idNums.size() - existTotalCount - existOrderTotalCount) + "名； ";
        result.put("code", "success");
        if (existTotalCount > 0) {
            msg += "剔除重复报名" + existTotalCount + "名,分别是:[" + existTagTip + "] ";
        }
        if (existOrderTotalCount > 0) {
            msg += "剔除已存在于该活动未支付订单中的学员" + existOrderTotalCount + "名,分别是:[" + existOrderTagTip + "] ";
        }
        if (idNums.size() - existTotalCount - existOrderTotalCount < 1) {
            result.put("code", "fail");
            result.put("data", null);
            //提交的名单没有报名成功的，删除订单
            activityOrderMapper.deleteByPrimaryKey(activityOrder.getOrderKeyId());
        } else {
            result.put("code", "success");
            result.put("data", orderId);
            msg += "订单生成成功！订单号：" + orderId;
        }
        result.put("msg", msg);

        return result;
    }

    @Override
    public JSONObject memberGetSeatNum(Integer memberId, int activityId) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();

        List<Map<String, String>> studentActivityInfos = ActivityStudentSQLConn.getStudentActivityInfoFromMember(memberId, activityId);
        if (studentActivityInfos.size() == 0) {
            result.put("code", "fail");
            result.put("msg", "名下学员无报名此活动");
            result.put("data", null);
            return result;
        }
        JSONArray list = new JSONArray();
        for (int i = 0; i < studentActivityInfos.size(); i++) {
            JSONObject object = new JSONObject();
            object.put("name", studentActivityInfos.get(i).get("name"));
            object.put("phone", studentActivityInfos.get(i).get("phone"));
            object.put("seatNumber", studentActivityInfos.get(i).get("seatNumber"));
            object.put("applyNumber", studentActivityInfos.get(i).get("applyNumber"));
            object.put("applyTime", studentActivityInfos.get(i).get("applyTime"));
            list.add(object);
        }
        data.put("list", list);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取学生报名信息成功");
        return result;
    }

    @Override
    public JSONObject memberGetSignedActivities(Integer memberId, Integer currentPage, Integer pageSize, String name) {
        JSONObject result = new JSONObject();
        JSONObject data=new JSONObject();
        JSONArray list=new JSONArray();

        ActivityOrderExample activityOrderExample = new ActivityOrderExample();
        ActivityOrderExample.Criteria criteria = activityOrderExample.createCriteria();
        criteria.andOrderTypeEqualTo(false).andMemberKeyIdEqualTo(memberId).andPaymentStateEqualTo(false).andCloseEqualTo(false);
        activityOrderExample.setOrderByClause("order_begin_time DESC");
        activityOrderExample.setDistinct(true); // TODO 这里去重修改了Mapper，以activity_id (group by activity_id)
        List<ActivityOrder> activityOrders = activityOrderMapper.selectByExample(activityOrderExample);

        List<Integer> activityIds = ActivityStudentSQLConn.getActivityIdByMemberId(memberId);

        int total=-1;

        if (activityIds.size() == 0 && activityOrders.size() == 0) {
            result.put("code", "success");
            result.put("msg", "暂未给名下学院报名活动");
            data.put("total", total+1);
            data.put("list", list);
            result.put("data", data);
            return result;
        }
        List<Activity> activities = new ArrayList<>();
        for (int i = 0; i < activityOrders.size(); i++) {
            activities.add(activityMapper.selectByPrimaryKey(activityOrders.get(i).getActivityId()));
        }
        for (int i = activityIds.size() - 1; i >= 0; i--) {
            activities.add(activityMapper.selectByPrimaryKey(activityIds.get(i)));
        }

        int num = -1; //用于区分未支付和报名成功的培训
        int orderIndex=-1;
        Set<Integer> activityIdsSet=new HashSet<>();//用于将未支付的订单属于同一活动的进行去重
        for (Activity activity : activities) {
            num++;
            if (num < activityOrders.size()){
                orderIndex++;
                if(getObjectHelper.checkActivityOrderOutOfTime(activityOrders.get(orderIndex)))
                    continue;
                if(!activityIdsSet.add(activity.getActivityId()))
                    continue;
            }
            if(name!=null && !name.equals("")){
                if(!activity.getActivityName().contains(name))
                    continue;
            }
            total++;
            if(total>=(currentPage-1)*pageSize && total<currentPage*pageSize) {
                JSONObject object = new JSONObject();
                object.put("id", activity.getActivityId());
                object.put("name", activity.getActivityName());
                object.put("date", activity.getActivityDate());
                object.put("address", activity.getActivityAddress());
                object.put("fee", activity.getActivityFee());
                JSONArray introduce = new JSONArray();
                String[] introduceStrs = activity.getActivityIntroduction().split("\\|");
                for (String intro : introduceStrs) {
                    introduce.add(intro);
                }
                object.put("introduce", introduce);
                if (num < activityOrders.size()) {
                    object.put("status", "有订单未支付");
                } else {
                    object.put("status", "已支付");
                }
                object.put("contacts", activity.getContactName());
                object.put("phone", activity.getContactPhone());
                list.add(object);
            }
        }
        result.put("code", "success");
        result.put("msg", "成功获取已报名的活动");
        data.put("list", list);
        data.put("total", total+1);
        result.put("data", data);
        return result;
    }

    /**
     * 协会管理员的活动管理
     **/
    @Override
    public JSONObject addNewActivity(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        //权限检查
        Integer opAdminId=jsonObject.getInteger("id");
        if(!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.ACTIVITY)){
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }

        String name = jsonObject.getString("name");
        BigDecimal fee = jsonObject.getBigDecimal("fee");
        String address = jsonObject.getString("address");
        Date startDate = jsonObject.getDate("date");//活动举行时间
        float lenHour = jsonObject.getFloat("len");
        String contactName = jsonObject.getString("contacts");
        String contactPhone = jsonObject.getString("phone");
        Date openTime = jsonObject.getDate("openTime");//开放报名时间
        Date closeTime = jsonObject.getDate("closeTime");//关闭报名时间
        JSONArray description = jsonObject.getJSONArray("desc");
        StringBuilder introduction = new StringBuilder();
        if (description != null) {
            for (int i = 0; i < description.size() - 1; i++) {
                introduction.append(description.get(i));
                introduction.append("|");
            }
            introduction.append(description.get(description.size() - 1));
        }

        Activity activity = new Activity();
        activity.setActivityAddress(address);
        activity.setActivityName(name);
        activity.setActivityDate(startDate);
        activity.setActivityFee(fee);
        activity.setActivityLengthMin((int) (lenHour * 60));
        activity.setOpenTime(openTime);
        activity.setCloseTime(closeTime);
        activity.setContactName(contactName);
        activity.setContactPhone(contactPhone);
        activity.setActivityIntroduction(introduction.toString());
        activity.setPostTime(new Date());
        if (activityMapper.insertSelective(activity) > 0) {
            result.put("code", "success");
            result.put("msg", "成功发布活动");
        } else {
            result.put("code", "fail");
            result.put("msg", "系统繁忙，发布失败，请重试");
        }
        return result;
    }

    @Override
    public JSONObject adminGetActivityList() {
        JSONObject result = new JSONObject();
        JSONArray data = new JSONArray();

        ActivityExample activityExample = new ActivityExample();
        activityExample.setOrderByClause("post_time DESC");
        List<Activity> activities = activityMapper.selectByExample(activityExample);
        if (activities.size() == 0) {
            result.put("code", "success");
            result.put("msg", "系统中暂无已发布活动");
            result.put("data", null);
            return result;
        }
        for (Activity activity : activities) {
            JSONObject object = new JSONObject();
            object.put("id", activity.getActivityId());
            object.put("name", activity.getActivityName());
            object.put("date", activity.getActivityDate());
            object.put("openDate", activity.getOpenTime());
            object.put("address", activity.getActivityAddress());
            object.put("fee", activity.getActivityFee());
            JSONArray introduce = new JSONArray();
            String[] introduceStrs = activity.getActivityIntroduction().split("\\|");
            for (String intro : introduceStrs) {
                introduce.add(intro);
            }
            object.put("introduce", introduce);
            object.put("contacts", activity.getContactName());
            object.put("phone", activity.getContactPhone());
            /* 获取参与活动的人数 */
            ActivityStudentExample activityStudentExample = new ActivityStudentExample();
            ActivityStudentExample.Criteria criteria = activityStudentExample.createCriteria();
            criteria.andActivityIdEqualTo(activity.getActivityId());
            long joinNum = activityStudentMapper.countByExample(activityStudentExample);
            object.put("joinNum", joinNum);
            data.add(object);
        }
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取已发布活动成功");
        return result;
    }

    //管理员导出报名表
    @Override
    public ResponseEntity<byte[]> getEntryForm(Integer activityId, Integer adminId) {

        String fileName = adminId+"_activity_"+activityId+"_signed_form.xlsx";
        String filePath = "./src/main/resources/templates/opXlsx/"+fileName;
        ExcelUtil.createExcel(filePath);

        InputStream in;
        ResponseEntity<byte[]> response = null;
        Activity activity=activityMapper.selectByPrimaryKey(activityId);
        ActivityStudentExample activityStudentExample=new ActivityStudentExample();
        activityStudentExample.createCriteria().andActivityIdEqualTo(activityId).andIsInvalidEqualTo(false);
        List<ActivityStudent> activityStudents=activityStudentMapper.selectByExample(activityStudentExample);

        List<Object> rowList = new ArrayList<>();
        List<String> cellList = new ArrayList<>();
        cellList.add("活动编号");
        cellList.add("活动名称");
        cellList.add("报名编号");
        cellList.add("人员编号");
        cellList.add("姓名");
        cellList.add("座位");
        cellList.add("报名时间");
        rowList.add(cellList);
        for(ActivityStudent activityStudent:activityStudents){
            cellList = new ArrayList<>();
            cellList.add(Integer.toString(activity.getActivityId()));
            cellList.add(activity.getActivityName());
            cellList.add(activityStudent.getApplyNumber());
            cellList.add(Integer.toString(activityStudent.getStudentId()));
            Student student=studentMapper.selectByPrimaryKey(activityStudent.getStudentId());
            cellList.add(student==null?"":student.getStudentName());
            cellList.add(activityStudent.getSeatNumber());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            cellList.add(simpleDateFormat.format(activityStudent.getApplyTime()));
            rowList.add(cellList);
        }
        try{
            ExcelUtil.writeExcel(rowList, filePath);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            File file=new File(filePath);
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            HttpHeaders headers = new HttpHeaders();
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
            headers.add("Content-Disposition", "attachment;filename=" + fileName);
            HttpStatus statusCode = HttpStatus.OK;
            response = new ResponseEntity<byte[]>(bytes, headers, statusCode);
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try{
            File file=new File(filePath);
            if(file.exists())
                file.delete();
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    //管理员设置座位信息
    @Override
    public JSONObject setSEATInfo(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        //权限检查
        Integer opAdminId=jsonObject.getInteger("id");
        if(!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.ACTIVITY)){
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }

        Integer activityId = jsonObject.getInteger("activityId");
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        if (activity == null) {
            result.put("msg", "设置失败！系统中找不到该活动");
            return result;
        }
        JSONArray allSeatInfo = jsonObject.getJSONArray("data");
        int rowNum, colNum = 0;
        rowNum = allSeatInfo.size(); //行数
        if (rowNum > 0) {
            colNum = allSeatInfo.getJSONArray(0).size(); //列数
        }
        activity.setAreaLength(rowNum);
        activity.setAreaWidth(colNum);
        activityMapper.updateByPrimaryKeySelective(activity);
        int fakeCol;
        for (int i = 0; i < rowNum; i++) {
            fakeCol = 0;
            JSONArray rowSeats = allSeatInfo.getJSONArray(i);
            for (int j = 0; j < rowSeats.size(); j++) {
                JSONObject seat = rowSeats.getJSONObject(j);
                Boolean lock = seat.getBoolean("lock");
                Integer studentId = seat.getInteger("num");
                boolean flag=false;//false:已有，true:新建
                ActivitySeat activitySeat = getObjectHelper.getSeatByActivityIdAndXY(activityId, i + 1, j + 1);
                if (activitySeat == null) {
                    activitySeat = new ActivitySeat();
                    flag=true;
                }
                activitySeat.setActivityId(activityId);
                activitySeat.setIsLock(lock);
                if (!lock) {
                    fakeCol++;
                    activitySeat.setFakeY(i + 1);
                    activitySeat.setFakeX(fakeCol);
                }
                if (!lock && studentId != null) {
                    activitySeat.setIsOccupied(true);
                    activitySeat.setStudentnum(studentId);
                    ActivityStudent activityStudent = getObjectHelper.getActivityReStudentByIds(activityId, studentId);
                    if (activityStudent != null) {
                        activityStudent.setSeatId(activitySeat.getSeatId());
                        activityStudent.setSeatNumber(activitySeat.getFakeY() + "行" + activitySeat.getFakeX() + "座");
                        activityStudentMapper.updateByPrimaryKeySelective(activityStudent);
                    }
                }
                activitySeat.setRealY(i + 1);
                activitySeat.setRealX(j + 1);
                if(flag) {
                    activitySeatMapper.insertSelective(activitySeat);
                }else {
                    activitySeatMapper.updateByPrimaryKeySelective(activitySeat);
                }
            }
        }
        result.put("code", "success");
        result.put("msg", "活动座位信息设置成功！");
        return result;
    }

    //管理员获取活动座位表
    @Override
    public JSONObject getActivitySEAT(Integer activityId) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();
        result.put("code", "fail");
        result.put("data", data);

        Activity activity=activityMapper.selectByPrimaryKey(activityId);
        if(activity==null){
            result.put("msg", "系统中找不到该活动");
            return result;
        }
        Integer rowNum=activity.getAreaLength();
        Integer colNum=activity.getAreaWidth();
        if(rowNum==null || colNum==null){
            result.put("msg", "获取失败！该活动还未安排座位");
            return result;
        }
        JSONObject[][] array=new JSONObject[rowNum][colNum];
        ActivitySeatExample activitySeatExample=new ActivitySeatExample();
        activitySeatExample.createCriteria().andActivityIdEqualTo(activityId);
        List<ActivitySeat> activitySeats=activitySeatMapper.selectByExample(activitySeatExample);
        for(ActivitySeat activitySeat:activitySeats){
            Boolean lock=activitySeat.getIsLock();
            Integer studentId=activitySeat.getStudentnum();
            try {
                array[activitySeat.getRealY() - 1][activitySeat.getRealX() - 1].put("lock", lock);
                array[activitySeat.getRealY() - 1][activitySeat.getRealX() - 1].put("num", studentId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        JSONArray arr=new JSONArray();
        for(int i=0;i<rowNum;i++){
            JSONArray arrInArr=new JSONArray();
            for(int j=0;j>colNum;j++){
                arrInArr.add(array[i][j]);
            }
            arr.add(arrInArr);
        }
        data.put("xLen", rowNum);
        data.put("yLen", colNum);
        data.put("arr", arr);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取座位表成功！");
        return result;
    }
}
