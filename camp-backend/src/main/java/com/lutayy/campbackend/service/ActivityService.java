package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;

public interface ActivityService {

    JSONObject getJoinableActivities(Integer pageSize, Integer currentPage, String name);
    //学员获取已报名活动
    JSONObject getSignedActivities(Integer studentId, Integer currentPage, Integer pageSize, String name);

    JSONObject studentGetSeatNum(Integer studentId,int activityId);

    JSONObject studentJoinActivity(JSONObject jsonObject);

    JSONObject memberJoinActivity(JSONObject jsonObject);

    JSONObject memberGetSeatNum(Integer memberId,int activityId);

    JSONObject addNewActivity(JSONObject jsonObject);

    JSONObject adminGetActivityList();

    JSONObject memberGetSignedActivities(Integer memberId, Integer currentPage, Integer pageSize, String name);
    //管理员导出报名表
    ResponseEntity<byte[]> getEntryForm(Integer activityId, Integer adminId);
    //管理员导出座位信息表
//    ResponseEntity<byte[]> getSeatForm(Integer activityId, Integer adminId);
    //管理员设置座位信息
    JSONObject setSEATInfo(JSONObject jsonObject);
    //管理员获取活动座位表
    JSONObject getActivitySEAT(Integer activityId);
    //管理员提交自动排位
    JSONObject autoSEAT(JSONObject jsonObject);
}
