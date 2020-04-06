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

    @RequestMapping(value = {"/student/getExamQuestions","/admin/getExamDetail"})
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

    @RequestMapping("/student/rejoinExam")
    @ResponseBody
    public JSONObject rejoinExam(@RequestBody JSONObject jsonObject){
        return examService.rejoinExam(jsonObject);
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

    @RequestMapping("/admin/getPublishedList")
    @ResponseBody
    public Object getPublishedList(@RequestParam("currentPage") Integer currentPage,
                                   @RequestParam("pageSize") Integer pageSize) { //管理员获取已发布考试列表
        return examService.getPublishedList(currentPage, pageSize);
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
    public JSONObject randomFillExam(JSONObject jsonObject){
        return examService.randomFillExam(jsonObject);
    }

    @RequestMapping("/admin/saveQuestionForExam")
    @ResponseBody
    public JSONObject saveQuestionForExam(@RequestBody JSONObject jsonObject){
        return examService.saveQuestionForExam(jsonObject);
    }

    @RequestMapping("/admin/publishExam")
    @ResponseBody
    public JSONObject publishExam(@RequestBody JSONObject jsonObject){
        return examService.publishExam(jsonObject);
    }

    @RequestMapping("/admin/closeExam")
    @ResponseBody
    public JSONObject closeExam(@RequestBody JSONObject jsonObject){
        return examService.closeExam(jsonObject);
    }

    @RequestMapping("/admin/getExamInfo")
    @ResponseBody
    public Object adminGetExamInfo(@RequestParam("examId") Integer examId){
        return examService.adminGetExamInfo(examId);
    }

    @RequestMapping("/admin/getExamQuestionList")
    @ResponseBody
    public Object getExamQuestionList(@RequestParam("examId") Integer examId){
        return examService.getExamQuestionList(examId);
    }

    @RequestMapping("/admin/modifyExamInfo")
    @ResponseBody
    public JSONObject modifyExamInfo(@RequestBody JSONObject jsonObject){
        return examService.modifyExamInfo(jsonObject);
    }

    @RequestMapping("/admin/getFinishedStudentList")
    @ResponseBody
    public Object getFinishedStudentList(@RequestParam("examId") Integer examId){
        return examService.getFinishedStudentList(examId);
    }

    @RequestMapping("/admin/getGradeList")
    @ResponseBody
    public Object getGradeList(@RequestParam("examId") Integer examId){
        return examService.getGradeList(examId);
    }
}
