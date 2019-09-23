package com.lutayy.campbackend.controller;


import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TrainingController {

    @Autowired
    TrainingService trainingService;

    @RequestMapping("/index/getCourses")
    @ResponseBody
    public JSONObject getCourses(@RequestBody JSONObject jsonObject){

        return trainingService.getCourses(jsonObject);
    }
}
