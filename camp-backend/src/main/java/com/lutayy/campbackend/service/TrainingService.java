package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;

public interface TrainingService {

    JSONObject getCourses(JSONObject jsonObject);

    JSONObject getJoinableTraining(String id);

    JSONObject getStudentSignedTraining(String id);

    JSONObject studentJoinTraining(JSONObject jsonObject);

    JSONObject getMemberSignedTraining(String id);

    JSONObject memberJoinTraining(JSONObject jsonObject);
}
