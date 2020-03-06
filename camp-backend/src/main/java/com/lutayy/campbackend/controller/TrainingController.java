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

    @RequestMapping("/admin/addNewTraining")
    @ResponseBody
    public JSONObject addNewTraining(@RequestBody JSONObject jsonObject){
        return trainingService.addNewTraining(jsonObject);
    }

    @RequestMapping("/student/joinTraining")
    @ResponseBody
    public JSONObject studentJoinTraining(@RequestBody JSONObject jsonObject){
        return trainingService.studentJoinTraining(jsonObject);
    }

    @RequestMapping("/student/getSignedTraining")
    @ResponseBody
    public Object getStudentSignedTraining(@RequestParam("id") Integer studentId){
        return trainingService.getStudentSignedTraining(studentId);
    }

    @RequestMapping(value = {"/member/getJoinableTraining"})
    @ResponseBody
    public Object getJoinableTraining(@RequestParam("id") Integer memberId){
        return trainingService.getJoinableTraining(memberId);
    }

    @RequestMapping(value = {"/student/getJoinableTraining"})
    @ResponseBody
    public Object getJoinableTraining(){
        return trainingService.getJoinableTraining();
    }

    @RequestMapping("/member/getSignedTraining")
    @ResponseBody
    public Object getMemberSignedTraining(@RequestParam("id") Integer memberId){
        return trainingService.getMemberSignedTraining(memberId);
    }

    @RequestMapping("/member/joinTraining")
    @ResponseBody
    public JSONObject memberJoinActivity(@RequestBody JSONObject jsonObject){
        return trainingService.memberJoinTraining(jsonObject);
    }

    @RequestMapping("/admin/getTrainingList")
    @ResponseBody
    public Object adminGetTrainingList(){
        return trainingService.adminGetTrainingList();
    }
}
