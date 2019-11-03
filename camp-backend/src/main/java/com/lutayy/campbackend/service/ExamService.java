package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;

public interface ExamService {

    JSONObject getHalfExamList(String idcard);

    JSONObject getTodoExamList(String idcard);

    JSONObject getDoneExamList(String idcard);

    JSONObject getExamInfo(JSONObject jsonObject);

    JSONObject getExamQuestions(JSONObject jsonObject);

    JSONObject getExamDetail(JSONObject jsonObject);

    JSONObject addNewExam(JSONObject jsonObject);

    JSONObject getQuestionList(JSONObject jsonObject);

    JSONObject getNotPostExam(Integer pageSize,Integer currentPage);

    JSONObject randomFillExam(Integer examId);

    JSONObject publishExam(Integer examId);

    JSONObject submitExam(JSONObject jsonObject);
}
