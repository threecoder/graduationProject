package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface ActivityService {

    JSONObject getJoinableActivities();

    JSONObject getSignedActivities(Integer studentId);

    JSONObject studentGetSeatNum(Integer studentId,int activityId);

    JSONObject studentJoinActivity(JSONObject jsonObject);

    JSONObject memberJoinActivity(JSONObject jsonObject);

    JSONObject memberGetSeatNum(Integer memberId,int activityId);

    JSONObject addNewActivity(JSONObject jsonObject);

    JSONObject adminGetActivityList();

    JSONObject memberGetSignedActivities(Integer memberId);
}
