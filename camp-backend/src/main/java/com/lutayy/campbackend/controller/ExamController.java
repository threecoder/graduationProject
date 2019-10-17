package com.lutayy.campbackend.controller;


import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExamController {

    @Autowired
    ExamService examService;

    @RequestMapping("/student/getHalfExamList")
    @ResponseBody
    public Object getHalfExamList(@RequestParam("id") String idcard){
        return examService.getHalfExamList(idcard);
    }

    @RequestMapping("/student/getTodoExamList")
    @ResponseBody
    public Object getTodoExamList(@RequestParam("id") String idcard){
        return examService.getTodoExamList(idcard);
    }

    @RequestMapping("/student/getDoneExamList")
    @ResponseBody
    public Object getDoneExamList(@RequestParam("id") String idcard){
        return examService.getDoneExamList(idcard);
    }

    @RequestMapping("/student/getExamInfo")
    @ResponseBody
    public JSONObject getExamInfo(@RequestBody JSONObject jsonObject){
        return examService.getExamInfo(jsonObject);
    }

    @RequestMapping("/student/getExamQuestions")
    @ResponseBody
    public JSONObject getExamQuestions(@RequestBody JSONObject jsonObject){
        return examService.getExamQuestions(jsonObject);
    }

    @RequestMapping("/student/getExamDetail")
    @ResponseBody
    public JSONObject getExamDetail(@RequestBody JSONObject jsonObject){
        return examService.getExamDetail(jsonObject);
    }

    @RequestMapping("/admin/addNewExam")
    @ResponseBody
    public JSONObject addNewExam(@RequestBody JSONObject jsonObject){
        return examService.addNewExam(jsonObject);
    }


}
