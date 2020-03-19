package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;

public interface ExamService {

    JSONObject getHalfExamList(Integer studentId);

    JSONObject getTodoExamList(Integer studentId);

    JSONObject getDoneExamList(Integer studentId);

    JSONObject getExamInfo(Integer studentId, Integer examId);

    JSONObject getExamQuestions(Integer examId);

    JSONObject getExamDetail(Integer studentId, Integer examId);

    JSONObject addNewExam(JSONObject jsonObject);

    //管理员获取已发布考试列表
    JSONObject getPublishedList(Integer currentPage, Integer pageSize);

    JSONObject getQuestionList(JSONObject jsonObject);

    JSONObject getNotPostExam(Integer pageSize, Integer currentPage);

    JSONObject randomFillExam(JSONObject jsonObject);

    JSONObject publishExam(JSONObject jsonObject);

    JSONObject submitExam(JSONObject jsonObject);

    JSONObject closeExam(JSONObject jsonObject);

    JSONObject modifyExamInfo(JSONObject jsonObject);
}
