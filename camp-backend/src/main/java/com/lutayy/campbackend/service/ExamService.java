package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;

public interface ExamService {

    JSONObject getHalfExamList(Integer studentId);

    JSONObject getTodoExamList(Integer studentId);

    JSONObject getDoneExamList(Integer studentId);

    JSONObject getExamInfo(Integer studentId, Integer examId);

    JSONObject getExamQuestions(Integer examId);

    JSONObject getExamDetail(Integer studentId, Integer examId);

    JSONObject submitExam(JSONObject jsonObject);

    JSONObject rejoinExam(JSONObject jsonObject);

    //以下为管理员
    JSONObject addNewExam(JSONObject jsonObject);
    //管理员获取已发布考试列表
    JSONObject getPublishedList(Integer currentPage, Integer pageSize);

    JSONObject getQuestionList(JSONObject jsonObject);

    JSONObject getNotPostExam(Integer pageSize, Integer currentPage);

    JSONObject saveQuestionForExam(JSONObject jsonObject);

    JSONObject randomFillExam(JSONObject jsonObject);

    JSONObject publishExam(JSONObject jsonObject);

    JSONObject closeExam(JSONObject jsonObject);

    JSONObject adminGetExamInfo(Integer examId);

    JSONObject getExamQuestionList(Integer examId);

    JSONObject modifyExamInfo(JSONObject jsonObject);
    //管理员获取已经作答的学生情况
    JSONObject getFinishedStudentList(Integer examId);
    //管理员获取待审核成绩列表
    JSONObject getGradeList(Integer examId);
}
