package com.lutayy.campbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @RequestMapping("/modifyQuestion")
    @ResponseBody
    public JSONObject modifyQuestion(@RequestBody JSONObject jsonObject){   //修改试题
        return questionService.modifyQuestion(jsonObject);
    }

    @RequestMapping("/getQuestionTemplate")
    @ResponseBody
    public ResponseEntity<byte[]> getQuestionTemplate(HttpServletRequest request) {
        return questionService.getQuestionTemplate(request);
    }

    @RequestMapping("/importQuestionByFile")
    @ResponseBody
    public JSONObject importQuestionByFile(@RequestParam("file") MultipartFile questionFile){   //修改试题
        return questionService.importQuestionByFile(questionFile);
    }

    @RequestMapping("/getQuestionInfo")
    @ResponseBody
    public Object getQuestionInfo(@RequestParam("questionId") Integer questionId){   //修改试题
        return questionService.getQuestionInfo(questionId);
    }
}
