package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;

public interface ActivityService {

    JSONObject getJoinableActivities();

    JSONObject getSignedActivities(String idcard);

    JSONObject studentGetSeatNum(String idcard,int activityId);

    JSONObject studentJoinActivity(JSONObject jsonObject);

    JSONObject memberJoinActivity(JSONObject jsonObject);

    JSONObject memberGetSeatNum(String memberId,int activityId);

    JSONObject addNewActivity(JSONObject jsonObject);

    JSONObject adminGetActivityList();
}
