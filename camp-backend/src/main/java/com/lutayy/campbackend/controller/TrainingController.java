package com.lutayy.campbackend.controller;


import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/student/joinTraining")
    @ResponseBody
    public JSONObject studentJoinTraining(@RequestBody JSONObject jsonObject){
        return trainingService.studentJoinTraining(jsonObject);
    }

    @RequestMapping("/student/getSignedTraining")
    @ResponseBody
    public Object getStudentSignedTraining(@RequestParam("id") String id){
        return trainingService.getStudentSignedTraining(id);
    }

    @RequestMapping(value = {"/member/getJoinableTraining","/student/getJoinableTraining"})
    @ResponseBody
    public Object getJoinableTraining(@RequestParam("id") String id){
        return trainingService.getJoinableTraining(id);
    }

    @RequestMapping("/member/getSignedTraining")
    @ResponseBody
    public Object getMemberSignedTraining(@RequestParam("id") String id){
        return trainingService.getMemberSignedTraining(id);
    }

    @RequestMapping("/member/joinTraining")
    @ResponseBody
    public JSONObject memberJoinActivity(@RequestBody JSONObject jsonObject){
        return trainingService.memberJoinTraining(jsonObject);
    }
}
