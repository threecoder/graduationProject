package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;

public interface ActivityService {

    JSONObject getJoinableActivities();

    JSONObject getSignedActivities(String idcard);

    JSONObject getSeatNum(String idcard,int activityId);

    JSONObject joinActivity(JSONObject jsonObject);

}
