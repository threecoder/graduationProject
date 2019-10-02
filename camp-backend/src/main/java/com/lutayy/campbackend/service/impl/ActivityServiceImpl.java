package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.util.OrderIdGenerator;
import com.lutayy.campbackend.common.util.UUIDUtil;
import com.lutayy.campbackend.dao.ActivityMapper;
import com.lutayy.campbackend.dao.ActivityOrderMapper;
import com.lutayy.campbackend.dao.ActivityStudentMapper;
import com.lutayy.campbackend.dao.StudentMapper;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


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

    /** 由身份证获得学员Id **/
    private int getStudentId(String idcard){
        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        criteria.andStudentIdcardEqualTo(idcard);
        List<Student> students=studentMapper.selectByExample(studentExample);
        Student student=students.get(0);
        return student.getStudentId();
    }

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
        criteria.andStudentIdEqualTo(getStudentId(idcard));
        List<ActivityStudent> activityStudents=activityStudentMapper.selectByExample(activityStudentExample);
        if(activityStudents.size()==0){
            result.put("code", "fail");
            result.put("msg", "该用户暂无报名任何活动");
            result.put("data", null);
            return result;
        }
        JSONArray data=new JSONArray();
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
            if(!activityStudents.get(i).getIsSuccess()){
                object.put("status", "未支付");
            }else {
                object.put("status", "已支付");
            }
            data.add(object);
        }
        result.put("code", "success");
        result.put("msg", "查询成功!");
        result.put("data", data);
        return result;
    }

    @Override
    public JSONObject getSeatNum(String idcard,int activityId) {
        JSONObject result=new JSONObject();

        ActivityStudentExample activityStudentExample=new ActivityStudentExample();
        ActivityStudentExample.Criteria criteria=activityStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(getStudentId(idcard)).andActivityIdEqualTo(activityId);
        List<ActivityStudent> activityStudents=activityStudentMapper.selectByExample(activityStudentExample);
        if(activityStudents.size()==0){
            result.put("code", "fail");
            result.put("msg", "当前用户未参加此活动");
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
        if(!activityStudent.getIsSuccess()){
            result.put("code", "fail");
            result.put("msg", "未完成缴费，无法安排座位");
            result.put("data", null);
            return result;
        }
        result.put("code", "success");
        result.put("msg", "查询成功");
        result.put("data", activityStudent.getSeatNumber());
        return result;
    }

    @Override
    public JSONObject joinActivity(JSONObject jsonObject) {
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
        ActivityStudentExample activityStudentExample0=new ActivityStudentExample();
        ActivityStudentExample.Criteria criteria0=activityStudentExample0.createCriteria();
        criteria0.andActivityIdEqualTo(activityId).andIsInvalidEqualTo(false);
        List<ActivityStudent> activityStudents0=activityStudentMapper.selectByExample(activityStudentExample0);
        if(activityStudents0.size()>=activity.getMaxNum()){
            result.put("code", "fail");
            result.put("msg", "活动人数已满");
            return result;
        }

        ActivityStudent activityStudent=new ActivityStudent();
        String applyNumber= UUIDUtil.getActivityApplyNumber(activityId);//生成报名编号
        //排重
        while(activityStudentMapper.selectByPrimaryKey(applyNumber)!=null){
            applyNumber= UUIDUtil.getActivityApplyNumber(activityId);
        }
        activityStudent.setApplyNumber(applyNumber);
        activityStudent.setActivityId(activityId);
        activityStudent.setStudentId(studentId);
        activityStudent.setIsSuccess(false);
        activityStudent.setApplyTime(new Date());
        if(activityStudentMapper.insert(activityStudent)>0){
            String orderId=OrderIdGenerator.getUniqueId();
            //订单号生成并查重（查重如非高并发系统基本上可以省略）
            while(activityOrderMapper.selectByPrimaryKey(orderId)!=null){
                orderId=OrderIdGenerator.getUniqueId();
            }
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
                result.put("msg", "报名成功!待支付");
            }else{
                result.put("code", "fail");
                result.put("msg", "订单生成失败!");
                activityStudentMapper.deleteByExample(activityStudentExample);
            }
            return result;
        }else {
            result.put("code", "fail");
            result.put("msg", "报名失败，请稍后尝试");
            return result;
        }
    }
}
