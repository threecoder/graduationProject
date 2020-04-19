package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;

public interface TrainingService {

    JSONObject getCourses(String keyWord, String startDateStr, String endDateStr, Integer pageSize, Integer currentPage, String type);

    JSONObject getJoinableTraining(Integer memberId);

    JSONObject getJoinableTraining();

    JSONObject getStudentSignedTraining(Integer studentId);

    JSONObject studentJoinTraining(JSONObject jsonObject);

    JSONObject getMemberSignedTraining(Integer memberId);

    JSONObject memberJoinTraining(JSONObject jsonObject);

    JSONObject addNewTraining(JSONObject jsonObject);

    JSONObject adminGetTrainingList();
    //管理员获取学员参加过的培训记录
    JSONObject getStudentTrainingHistory(String idCard, Integer currentPage, Integer pageSize);
}
