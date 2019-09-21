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
@RequestMapping("/student")
public class ExamController {

    @Autowired
    ExamService examService;

    @RequestMapping("/getHalfExamList")
    @ResponseBody
    public Object getHalfExamList(@RequestParam("id") String idcard){
        return examService.getHalfExamList(idcard);
    }

    @RequestMapping("/getTodoExamList")
    @ResponseBody
    public Object getTodoExamList(@RequestParam("id") String idcard){
        return examService.getTodoExamList(idcard);
    }

    @RequestMapping("/getExamInfo")
    @ResponseBody
    public JSONObject getExamInfo(@RequestBody JSONObject jsonObject){
        return examService.getExamInfo(jsonObject);
    }

    @RequestMapping("/getExamQuestions")
    @ResponseBody
    public JSONObject getExamQuestions(@RequestBody JSONObject jsonObject){
        return examService.getExamQuestions(jsonObject);
    }

}
