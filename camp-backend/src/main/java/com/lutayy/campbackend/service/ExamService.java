package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;

public interface ExamService {

    JSONObject getHalfExamList(String idcard);

    JSONObject getTodoExamList(String idcard);
}
