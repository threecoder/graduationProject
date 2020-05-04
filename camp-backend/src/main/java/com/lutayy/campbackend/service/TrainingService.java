package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;

public interface TrainingService {

    JSONObject getCourses(String keyWord, String startDateStr, String endDateStr, Integer pageSize, Integer currentPage, String type);

    JSONObject getJoinableTraining(Integer memberId, Integer pageSize, Integer currentPage, String name);

    JSONObject getJoinableTraining(Integer pageSize, Integer currentPage, String name);
    //学院已报名培训
    JSONObject getStudentSignedTraining(Integer studentId, Integer currentPage, Integer pageSize, String name);

    JSONObject studentJoinTraining(JSONObject jsonObject);

    JSONObject getMemberSignedTraining(Integer memberId, Integer currentPage, Integer pageSize, String name);

    JSONObject memberJoinTraining(JSONObject jsonObject);

    JSONObject addNewTraining(JSONObject jsonObject);

    JSONObject adminGetTrainingList();
    //管理员获取学员参加过的培训记录
    JSONObject getStudentTrainingHistory(String idCard, Integer currentPage, Integer pageSize);
    //管理员获取已经发布的培训
    JSONObject getPublishedTraining(Integer pageSize, Integer currentPage, String startDateStr, String endDateStr, String name);
    //管理员根据id获取已报名培训学员列表
    JSONObject getEnrolledStudentList(Integer pageSize, Integer currentPage, Integer trainingId, String studentName, String idCard, String company);
}
