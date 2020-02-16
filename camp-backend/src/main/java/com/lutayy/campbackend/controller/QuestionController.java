package com.lutayy.campbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
