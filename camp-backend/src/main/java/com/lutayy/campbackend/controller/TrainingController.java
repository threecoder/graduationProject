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
    public Object getCourses(@RequestParam(value = "keyWord",required = false) String keyWord,
                             @RequestParam(value = "startDate",required = false) String startDateStr,
                             @RequestParam(value = "endDate",required = false) String endDateStr,
                             @RequestParam("pageSize") Integer pageSize,
                             @RequestParam("currentPage") Integer currentPage,
                             @RequestParam("type") String type) {
        return trainingService.getCourses(keyWord, startDateStr, endDateStr, pageSize, currentPage, type);
    }

    @RequestMapping("/student/joinTraining")
    @ResponseBody
    public JSONObject studentJoinTraining(@RequestBody JSONObject jsonObject) {
        return trainingService.studentJoinTraining(jsonObject);
    }

    @RequestMapping("/student/getSignedTraining")
    @ResponseBody
    public Object getStudentSignedTraining(@RequestParam("id") Integer studentId) {
        return trainingService.getStudentSignedTraining(studentId);
    }

    @RequestMapping(value = {"/member/getJoinableTraining"})
    @ResponseBody
    public Object getJoinableTraining(@RequestParam("id") Integer memberId) {
        return trainingService.getJoinableTraining(memberId);
    }

    @RequestMapping(value = {"/student/getJoinableTraining"})
    @ResponseBody
    public Object getJoinableTraining() {
        return trainingService.getJoinableTraining();
    }

    @RequestMapping("/member/getSignedTraining")
    @ResponseBody
    public Object getMemberSignedTraining(@RequestParam("id") Integer memberId) {
        return trainingService.getMemberSignedTraining(memberId);
    }

    @RequestMapping("/member/joinTraining")
    @ResponseBody
    public JSONObject memberJoinActivity(@RequestBody JSONObject jsonObject) {
        return trainingService.memberJoinTraining(jsonObject);
    }

    @RequestMapping("/admin/addNewTraining")
    @ResponseBody
    public JSONObject addNewTraining(@RequestBody JSONObject jsonObject) {
        return trainingService.addNewTraining(jsonObject);
    }

    @RequestMapping("/admin/getTrainingList")
    @ResponseBody
    public Object adminGetTrainingList() {
        return trainingService.adminGetTrainingList();
    }

    @RequestMapping("/admin/getStudentTrainingHistory")
    @ResponseBody
    public Object getStudentTrainingHistory(@RequestParam("idNum") String idCard,
                                            @RequestParam("currentPage") Integer currentPage,
                                            @RequestParam("pageSize") Integer pageSize) {
        return trainingService.getStudentTrainingHistory(idCard, currentPage, pageSize);
    }
}
