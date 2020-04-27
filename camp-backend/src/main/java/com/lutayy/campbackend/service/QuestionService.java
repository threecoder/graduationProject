package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface QuestionService {

    JSONObject modifyQuestion(JSONObject jsonObject);

    ResponseEntity<byte[]> getQuestionTemplate(HttpServletRequest request);

    JSONObject importQuestionByFile(MultipartFile questionFile);

    JSONObject getQuestionInfo(Integer questionId);
}
