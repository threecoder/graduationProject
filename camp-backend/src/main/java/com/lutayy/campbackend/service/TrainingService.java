package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;

public interface TrainingService {

    JSONObject getCourses(JSONObject jsonObject);

    JSONObject getJoinableTraining(Integer memberId);

    JSONObject getJoinableTraining();

    JSONObject getStudentSignedTraining(Integer studentId);

    JSONObject studentJoinTraining(JSONObject jsonObject);

    JSONObject getMemberSignedTraining(Integer memberId);

    JSONObject memberJoinTraining(JSONObject jsonObject);

    JSONObject addNewTraining(JSONObject jsonObject);

    JSONObject adminGetTrainingList();
}
