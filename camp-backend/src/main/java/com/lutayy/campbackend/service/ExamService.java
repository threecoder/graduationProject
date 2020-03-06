package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;

public interface ExamService {

    JSONObject getHalfExamList(Integer studentId);

    JSONObject getTodoExamList(Integer studentId);

    JSONObject getDoneExamList(Integer studentId);

    JSONObject getExamInfo(Integer studentId,Integer examId);

    JSONObject getExamQuestions(Integer examId);

    JSONObject getExamDetail(Integer studentId,Integer examId);

    JSONObject addNewExam(JSONObject jsonObject);

    JSONObject getQuestionList(JSONObject jsonObject);

    JSONObject getNotPostExam(Integer pageSize,Integer currentPage);

    JSONObject randomFillExam(Integer examId);

    JSONObject publishExam(Integer examId);

    JSONObject submitExam(JSONObject jsonObject);
}
