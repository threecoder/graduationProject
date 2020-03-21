package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.util.OrderIdGenerator;
import com.lutayy.campbackend.common.util.UUIDUtil;
import com.lutayy.campbackend.dao.*;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.ActivityService;
import com.lutayy.campbackend.service.SQLConn.ActivityStudentSQLConn;
import com.lutayy.campbackend.service.SQLConn.SystemParamManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


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
    ActivityOrderStudentMapper activityOrderStudentMapper;
    @Autowired
    MemberReStudentMapper memberReStudentMapper;
    @Autowired
    GetObjectHelper getObjectHelper;


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
    public JSONObject getJoinableActivities() {
        JSONObject result = new JSONObject();

        ActivityExample activityExample = new ActivityExample();
        ActivityExample.Criteria criteria = activityExample.createCriteria();
        criteria.andOpenTimeLessThan(new Date()).andCloseTimeGreaterThan(new Date());
        List<Activity> activities = activityMapper.selectByExample(activityExample);
        if (activities.size() == 0) {
            result.put("code", "success");
            result.put("msg", "暂无可报名活动");
            result.put("data", null);
            return result;
        }
        JSONArray data = new JSONArray();
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
            data.add(object);
        }
        result.put("code", "success");
        result.put("msg", "查询成功!");
        result.put("data", data);
        return result;
    }

    /**
     * 学员中心的活动管理
     **/
    @Override
    public JSONObject getSignedActivities(Integer studentId) {
        JSONObject result = new JSONObject();

        ActivityStudentExample activityStudentExample = new ActivityStudentExample();
        ActivityStudentExample.Criteria criteria = activityStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId);
        List<ActivityStudent> activityStudents = activityStudentMapper.selectByExample(activityStudentExample);

        ActivityOrderExample activityOrderExample = new ActivityOrderExample();
        ActivityOrderExample.Criteria criteria1 = activityOrderExample.createCriteria();
        criteria1.andStudentIdEqualTo(studentId).andCloseEqualTo(false).andPaymentStateEqualTo(false).andOrderTypeEqualTo(true);
        List<ActivityOrder> activityOrders = activityOrderMapper.selectByExample(activityOrderExample);

        if (activityStudents.size() == 0 && activityOrders.size() == 0) {
            result.put("code", "success");
            result.put("msg", "该用户暂无报名任何活动");
            result.put("data", null);
            return result;
        }
        JSONArray data = new JSONArray();

        for (ActivityOrder activityOrder : activityOrders) {
            Activity activity = activityMapper.selectByPrimaryKey(activityOrder.getActivityId());
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
            data.add(object);
        }

        for (int i = 0; i < activityStudents.size(); i++) {
            Activity activity = activityMapper.selectByPrimaryKey(activityStudents.get(i).getActivityId());
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
            data.add(object);
        }
        result.put("code", "success");
        result.put("msg", "查询成功!");
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
        //订单号生成并查重（查重如非高并发系统基本上可以省略）
        //while(activityOrderMapper.selectByPrimaryKey(orderId)!=null){
        //    orderId=OrderIdGenerator.getUniqueId();
        //}
        /**
         * 由学员自行报名，对应的订单，无须插入“订单—学生”表
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
        if (activityOrderMapper.insert(activityOrder) > 0) {
            result.put("code", "success");
            result.put("msg", "订单生成成功!待支付");
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
    public JSONObject memberGetSignedActivities(Integer memberId) {
        JSONObject result = new JSONObject();

        ActivityOrderExample activityOrderExample = new ActivityOrderExample();
        ActivityOrderExample.Criteria criteria = activityOrderExample.createCriteria();
        criteria.andOrderTypeEqualTo(false).andMemberKeyIdEqualTo(memberId).andPaymentStateEqualTo(false).andCloseEqualTo(false);
        activityOrderExample.setOrderByClause("order_begin_time DESC");
        List<ActivityOrder> activityOrders = activityOrderMapper.selectByExample(activityOrderExample);

        List<Integer> activityIds = ActivityStudentSQLConn.getActivityIdByMemberId(memberId);

        if (activityIds.size() == 0 && activityOrders.size() == 0) {
            result.put("code", "fail");
            result.put("msg", "暂无已报名活动");
            result.put("data", null);
        }
        List<Activity> activities = new ArrayList<>();
        for (int i = 0; i < activityOrders.size(); i++) {
            activities.add(activityMapper.selectByPrimaryKey(activityOrders.get(i).getActivityId()));
        }
        for (int i = activityIds.size() - 1; i >= 0; i--) {
            activities.add(activityMapper.selectByPrimaryKey(activityIds.get(i)));
        }
        JSONArray data = new JSONArray();
        int num = 0;
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
            if (num < activityOrders.size()) {
                object.put("status", "有订单未支付");
            } else {
                object.put("status", "已支付");
            }
            object.put("contacts", activity.getContactName());
            object.put("phone", activity.getContactPhone());
            data.add(object);
            num += 1;
        }
        result.put("code", "success");
        result.put("msg", "成功获取已报名的活动");
        result.put("data", data);
        return result;
    }

    /**
     * 协会管理员的活动管理
     **/
    @Override
    public JSONObject addNewActivity(JSONObject jsonObject) {
        JSONObject result = new JSONObject();

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
            List<ActivityStudent> activityStudents = activityStudentMapper.selectByExample(activityStudentExample);
            object.put("joinNum", activityStudents.size());
            data.add(object);
        }
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取已发布活动成功");
        return result;
    }

}
