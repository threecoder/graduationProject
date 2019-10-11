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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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

    /** 由身份证获得学员Id **/
    private int getStudentId(String idcard){
        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        criteria.andStudentIdcardEqualTo(idcard);
        List<Student> students=studentMapper.selectByExample(studentExample);
        if(students.size()==0){
            return -1;
        }
        Student student=students.get(0);
        return student.getStudentId();
    }

    private Student getStudentByIdcard(String idcard){
        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        criteria.andStudentIdcardEqualTo(idcard);
        List<Student> students=studentMapper.selectByExample(studentExample);
        if(students.size()==0){
            return null;
        }
        return students.get(0);
    }

    /**
     *  学员活动报名记录查询
     *  有有效报名记录返回1，无则返回0
     * **/
    private int checkStudentActiviy(int studentId,int activityId){
        ActivityStudentExample activityStudentExample=new ActivityStudentExample();
        ActivityStudentExample.Criteria criteria=activityStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId).andActivityIdEqualTo(activityId);
        List<ActivityStudent> activityStudents=activityStudentMapper.selectByExample(activityStudentExample);
        if(activityStudents.size()==0){
            return 0;
        }else {
            return 1;
        }
    }


    /**
     * 学员中心的活动管理
     * **/
    @Override
    public JSONObject getJoinableActivities() {
        JSONObject result=new JSONObject();

        ActivityExample activityExample=new ActivityExample();
        ActivityExample.Criteria criteria=activityExample.createCriteria();
        criteria.andOpenTimeLessThan(new Date()).andCloseTimeGreaterThan(new Date());
        List<Activity> activities=activityMapper.selectByExample(activityExample);
        if(activities.size()==0){
            result.put("code", "fail");
            result.put("msg", "暂无可报名活动");
            result.put("data", null);
            return result;
        }
        JSONArray data=new JSONArray();
        for(Activity activity:activities){
            JSONObject object=new JSONObject();
            object.put("id", activity.getActivityId());
            object.put("name", activity.getActivityName());
            object.put("date", activity.getActivityDate());
            object.put("address", activity.getActivityAddress());
            object.put("fee", activity.getActivityFee());
            JSONArray introduce=new JSONArray();
            introduce.add(activity.getActivityIntroduction());
            object.put("introduce",introduce);
            object.put("contacts", activity.getContacts());
            data.add(object);
        }
        result.put("code", "success");
        result.put("msg", "查询成功!");
        result.put("data", data);
        return result;
    }

    @Override
    public JSONObject getSignedActivities(String idcard) {
        JSONObject result =new JSONObject();

        ActivityStudentExample activityStudentExample=new ActivityStudentExample();
        ActivityStudentExample.Criteria criteria=activityStudentExample.createCriteria();
        int studentId=getStudentId(idcard);
        criteria.andStudentIdEqualTo(studentId);
        List<ActivityStudent> activityStudents=activityStudentMapper.selectByExample(activityStudentExample);

        ActivityOrderExample activityOrderExample=new ActivityOrderExample();
        ActivityOrderExample.Criteria criteria1=activityOrderExample.createCriteria();
        criteria1.andStudentIdEqualTo(studentId).andCloseEqualTo(false).andPaymentStateEqualTo(false).andOrderTypeEqualTo(true);
        List<ActivityOrder> activityOrders=activityOrderMapper.selectByExample(activityOrderExample);

        if(activityStudents.size()==0 && activityOrders.size()==0){
            result.put("code", "fail");
            result.put("msg", "该用户暂无报名任何活动");
            result.put("data", null);
            return result;
        }
        JSONArray data=new JSONArray();

        for(ActivityOrder activityOrder:activityOrders){
            Activity activity=activityMapper.selectByPrimaryKey(activityOrder.getActivityId());
            JSONObject object=new JSONObject();
            object.put("id", activity.getActivityId());
            object.put("name", activity.getActivityName());
            object.put("date", activity.getActivityDate());
            object.put("address", activity.getActivityAddress());
            object.put("fee", activity.getActivityFee());
            JSONArray introduce=new JSONArray();
            introduce.add(activity.getActivityIntroduction());
            object.put("introduce",introduce);
            object.put("contacts", activity.getContacts());
            object.put("status", "未支付");
            data.add(object);
        }

        for(int i=0;i<activityStudents.size();i++){
            Activity activity=activityMapper.selectByPrimaryKey(activityStudents.get(i).getActivityId());
            JSONObject object=new JSONObject();
            object.put("id", activity.getActivityId());
            object.put("name", activity.getActivityName());
            object.put("date", activity.getActivityDate());
            object.put("address", activity.getActivityAddress());
            object.put("fee", activity.getActivityFee());
            JSONArray introduce=new JSONArray();
            introduce.add(activity.getActivityIntroduction());
            object.put("introduce",introduce);
            object.put("contacts", activity.getContacts());
            object.put("status", "已支付");
            data.add(object);
        }
        result.put("code", "success");
        result.put("msg", "查询成功!");
        result.put("data", data);
        return result;
    }

    @Override
    public JSONObject studentGetSeatNum(String idcard,int activityId) {
        JSONObject result=new JSONObject();

        ActivityStudentExample activityStudentExample=new ActivityStudentExample();
        ActivityStudentExample.Criteria criteria=activityStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(getStudentId(idcard)).andActivityIdEqualTo(activityId);
        List<ActivityStudent> activityStudents=activityStudentMapper.selectByExample(activityStudentExample);
        if(activityStudents.size()==0){
            result.put("code", "fail");
            result.put("msg", "当前用户未成功报名此活动");
            result.put("data", null);
            return result;
        }
        ActivityStudent activityStudent=activityStudents.get(0);
        if(activityStudent.getSeatNumber()==null){
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
        JSONObject result=new JSONObject();

        String idcard=jsonObject.getString("id");
        int studentId=getStudentId(idcard);
        int activityId=jsonObject.getInteger("activityId");

        Activity activity=activityMapper.selectByPrimaryKey(activityId);
        if(activity==null){
            result.put("code", "fail");
            result.put("msg", "报名的活动不存在!");
            return result;
        }

        ActivityStudentExample activityStudentExample=new ActivityStudentExample();
        ActivityStudentExample.Criteria criteria=activityStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId).andActivityIdEqualTo(activityId);
        List<ActivityStudent> activityStudents=activityStudentMapper.selectByExample(activityStudentExample);
        if(activityStudents.size()!=0){
            result.put("code", "fail");
            result.put("msg", "已报名此活动");
            return result;
        }
        ActivityOrderExample activityOrderExample=new ActivityOrderExample();
        ActivityOrderExample.Criteria criteria1=activityOrderExample.createCriteria();
        criteria1.andActivityIdEqualTo(activityId).andStudentIdEqualTo(studentId).andCloseEqualTo(false).andPaymentStateEqualTo(false).andOrderTypeEqualTo(true);
        List<ActivityOrder> activityOrders=activityOrderMapper.selectByExample(activityOrderExample);
        if(activityOrders.size()!=0){
            /** 此处可补一个订单失效判断 **/
            result.put("code", "fail");
            result.put("msg", "已报名此活动但未支付，请前往订单中心查看");
            return result;
        }

        ActivityStudentExample activityStudentExample0=new ActivityStudentExample();
        ActivityStudentExample.Criteria criteria0=activityStudentExample0.createCriteria();
        criteria0.andActivityIdEqualTo(activityId);
        List<ActivityStudent> activityStudents0=activityStudentMapper.selectByExample(activityStudentExample0);
        if(activityStudents0.size()>=activity.getMaxNum()){
            result.put("code", "fail");
            result.put("msg", "活动人数已满");
            return result;
        }

        /** 报名成功操作，搬到订单支付成功后 **/
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
            String orderId=OrderIdGenerator.getUniqueId();
            //订单号生成并查重（查重如非高并发系统基本上可以省略）
            while(activityOrderMapper.selectByPrimaryKey(orderId)!=null){
                orderId=OrderIdGenerator.getUniqueId();
            }
            /**
             * 由学员自行报名，对应的订单，无须插入“订单—学生”表
             * **/
            ActivityOrder activityOrder=new ActivityOrder();
            activityOrder.setActivityOrderId(orderId);
            activityOrder.setActivityId(activityId);
            activityOrder.setStudentId(studentId);
            activityOrder.setOrderType(true);//"1"即学生提交的订单
            activityOrder.setOrderPrice(activity.getActivityFee());
            activityOrder.setOrderBeginTime(new Date());
            activityOrder.setPaymentState(false);
            activityOrder.setClose(false);
            if(activityOrderMapper.insert(activityOrder)>0){
                result.put("code", "success");
                result.put("msg", "订单生成生成!待支付");
            }else{
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
     * **/
    @Override
    public JSONObject memberJoinActivity(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        String memberId=jsonObject.getString("id");
        int activityId=jsonObject.getInteger("activityId");
        JSONArray idNums=jsonObject.getJSONArray("idNums");
        Activity activity=activityMapper.selectByPrimaryKey(activityId);
        if(activity==null){
            result.put("code", "fail");
            result.put("msg", "系统中没有该活动");
            return result;
        }

        String orderId=OrderIdGenerator.getUniqueId();
        //订单号生成并查重（如非高并发系统基本上可以省略）
        while(activityOrderMapper.selectByPrimaryKey(orderId)!=null){
            orderId=OrderIdGenerator.getUniqueId();
        }
        /**
         * 由会员报名，对应的订单，还需插入“订单—学生”表
         **/
        ActivityOrder activityOrder=new ActivityOrder();
        activityOrder.setActivityOrderId(orderId);
        activityOrder.setActivityId(activityId);
        activityOrder.setMemberId(memberId);
        activityOrder.setOrderType(false);//"0"即会员提交的订单
        //activityOrder.setOrderPrice(activity.getActivityFee());
        activityOrder.setOrderBeginTime(new Date());
        activityOrder.setPaymentState(false);
        activityOrder.setClose(false);
        activityOrderMapper.insert(activityOrder);

        int existTotalCount=0;
        String existTagTip="";
        int existOrderTotalCount=0;
        String existOrderTagTip="";
        for(int i=0;i<idNums.size();i++){
            Student student=getStudentByIdcard(idNums.getString(i));
            if(checkStudentActiviy(student.getStudentId(), activityId)!=0){
                existTotalCount+=1;
                existTagTip+=student.getStudentName()+"("+idNums.getString(i)+") ";
                continue;
            }
            String checkOrderId=ActivityStudentSQLConn.getActivityOrderByStudentId(student.getStudentId(),activityId);
//            System.out.println(checkOrderId);
            if(checkOrderId!=null){
                existOrderTotalCount+=1;
                existOrderTagTip+=student.getStudentName()+"(订单"+checkOrderId+") ";
                continue;
            }
            //插入“订单—学生”表
            ActivityOrderStudent activityOrderStudent=new ActivityOrderStudent();
            activityOrderStudent.setActivityOrderId(orderId);
            activityOrderStudent.setStudentId(student.getStudentId());
            activityOrderStudentMapper.insertSelective(activityOrderStudent);
        }
        BigDecimal successNums = new BigDecimal(idNums.size() - existTotalCount - existOrderTotalCount);
//        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        activityOrder.setOrderPrice(activity.getActivityFee().multiply(successNums));
        activityOrderMapper.updateByPrimaryKeySelective(activityOrder);
        String msg="尝试为"+idNums.size()+"名学员报名； 成功"+(idNums.size()-existTotalCount-existOrderTotalCount)+"名； ";
        result.put("code", "success");
        if(existTotalCount>0){
            msg+="剔除重复报名"+existTotalCount+"名,分别是:["+existTagTip+"] ";
        }
        if(existOrderTotalCount>0){
            msg+="剔除已存在于该活动未支付订单中的学员"+existOrderTotalCount+"名,分别是:["+existOrderTagTip+"] ";
        }
        if(idNums.size() - existTotalCount - existOrderTotalCount < 1){
            result.put("code", "fail");
            result.put("data",null);
            //提交的名单没有报名成功的，删除订单
            activityOrderMapper.deleteByPrimaryKey(orderId);
        }else {
            result.put("code", "success");
            result.put("data", orderId);
            msg+="订单生成成功！订单号："+orderId;
        }
        result.put("msg", msg);

        return result;
    }

    @Override
    public JSONObject memberGetSeatNum(String memberId, int activityId) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();

        List<Map<String,String>> studentActivityInfos = ActivityStudentSQLConn.getStudentActivityInfoFromMember(memberId, activityId);
        if(studentActivityInfos.size()==0){
            result.put("code", "fail");
            result.put("msg", "名下学员无报名此活动");
            result.put("data", null);
            return result;
        }
        JSONArray list=new JSONArray();
        for(int i=0;i<studentActivityInfos.size();i++){
            JSONObject object=new JSONObject();
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
}
