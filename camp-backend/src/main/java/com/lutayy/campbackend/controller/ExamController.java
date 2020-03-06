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
    public Object getHalfExamList(@RequestParam("id") Integer studentId){
        return examService.getHalfExamList(studentId);
    }

    @RequestMapping("/student/getTodoExamList")
    @ResponseBody
    public Object getTodoExamList(@RequestParam("id") Integer studentId){
        return examService.getTodoExamList(studentId);
    }

    @RequestMapping("/student/getDoneExamList")
    @ResponseBody
    public Object getDoneExamList(@RequestParam("id") Integer studentId){
        return examService.getDoneExamList(studentId);
    }

    @RequestMapping("/student/getExamInfo")
    @ResponseBody
    public Object getExamInfo(@RequestParam("id") Integer studentId,
                                  @RequestParam("examId") Integer examId){
        return examService.getExamInfo(studentId,examId);
    }

    @RequestMapping("/student/getExamQuestions")
    @ResponseBody
    public Object getExamQuestions(@RequestParam("examId") Integer examId){
        return examService.getExamQuestions(examId);
    }

    @RequestMapping("/student/getExamDetail")
    @ResponseBody
    public Object getExamDetail(@RequestParam("id") Integer studentId,
                                @RequestParam("examId") Integer examId){
        return examService.getExamDetail(studentId,examId);
    }

    @RequestMapping("/student/submitExam")
    @ResponseBody
    public JSONObject submitExam(@RequestBody JSONObject jsonObject){
        return examService.submitExam(jsonObject);
    }

    @RequestMapping("/admin/addNewExam")
    @ResponseBody
    public JSONObject addNewExam(@RequestBody JSONObject jsonObject){
        return examService.addNewExam(jsonObject);
    }

    @RequestMapping("/admin/getQuestionList")
    @ResponseBody
    public JSONObject getQuestionList(@RequestBody JSONObject jsonObject){
        return examService.getQuestionList(jsonObject);
    }


    @RequestMapping("/admin/getNotPostExam")
    @ResponseBody
    public Object getNotPostExam(@RequestParam("pageSize") Integer pageSize,@RequestParam("currentPage") Integer currentPage){
        return examService.getNotPostExam(pageSize,currentPage);
    }

    @RequestMapping("/admin/randomFillExam")
    @ResponseBody
    public Object randomFillExam(@RequestParam("examId") Integer examId){
        return examService.randomFillExam(examId);
    }

    @RequestMapping("/admin/publishExam")
    @ResponseBody
    public Object publishExam(@RequestParam("examId") Integer examId){
        return examService.publishExam(examId);
    }
}
