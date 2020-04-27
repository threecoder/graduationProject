package com.lutayy.campbackend.controller;


import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.ExamService;
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
public class ExamController {

    @Autowired
    ExamService examService;

    @RequestMapping("/student/getHalfExamList")
    @ResponseBody
    public Object getHalfExamList(@RequestParam("id") Integer studentId) {
        return examService.getHalfExamList(studentId);
    }

    @RequestMapping("/student/getTodoExamList")
    @ResponseBody
    public Object getTodoExamList(@RequestParam("id") Integer studentId) {
        return examService.getTodoExamList(studentId);
    }

    @RequestMapping("/student/getDoneExamList")
    @ResponseBody
    public Object getDoneExamList(@RequestParam("id") Integer studentId) {
        return examService.getDoneExamList(studentId);
    }

    @RequestMapping("/student/getExamInfo")
    @ResponseBody
    public Object getExamInfo(@RequestParam("id") Integer studentId,
                              @RequestParam("examId") Integer examId) {
        return examService.getExamInfo(studentId, examId);
    }

    @RequestMapping("/student/getExamQuestions")
    @ResponseBody
    public Object getExamQuestions(@RequestParam("examId") Integer examId) {
        return examService.getExamQuestions(examId);
    }

    @RequestMapping(value = {"/student/getExamDetail", "/admin/getExamDetail"})
    @ResponseBody
    public Object getExamDetail(@RequestParam("id") Integer studentId,
                                @RequestParam("examId") Integer examId) {
        return examService.getExamDetail(studentId, examId);
    }

    @RequestMapping("/student/rejoinExam")
    @ResponseBody
    public JSONObject rejoinExam(@RequestBody JSONObject jsonObject) {
        return examService.rejoinExam(jsonObject);
    }

    @RequestMapping("/student/submitExam")
    @ResponseBody
    public JSONObject submitExam(@RequestBody JSONObject jsonObject) {
        return examService.submitExam(jsonObject);
    }

    @RequestMapping("/admin/addNewExam")
    @ResponseBody
    public JSONObject addNewExam(@RequestBody JSONObject jsonObject) {
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
    public Object getQuestionList(@RequestParam("currentPage") Integer currentPage,
                                  @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "type", required = false) Integer type) {
        return examService.getQuestionList(currentPage, pageSize, keyword, type);
    }

    @RequestMapping("/admin/getNotPostExam")
    @ResponseBody
    public Object getNotPostExam(@RequestParam("pageSize") Integer pageSize, @RequestParam("currentPage") Integer currentPage) {
        return examService.getNotPostExam(pageSize, currentPage);
    }

    @RequestMapping("/admin/randomFillExam")
    @ResponseBody
    public JSONObject randomFillExam(@RequestBody JSONObject jsonObject) {
        return examService.randomFillExam(jsonObject);
    }

    @RequestMapping("/admin/saveQuestionForExam")
    @ResponseBody
    public JSONObject saveQuestionForExam(@RequestBody JSONObject jsonObject) {
        return examService.saveQuestionForExam(jsonObject);
    }

    @RequestMapping("/admin/publishExam")
    @ResponseBody
    public JSONObject publishExam(@RequestBody JSONObject jsonObject) {
        return examService.publishExam(jsonObject);
    }

    @RequestMapping("/admin/closeExam")
    @ResponseBody
    public JSONObject closeExam(@RequestBody JSONObject jsonObject) {
        return examService.closeExam(jsonObject);
    }

    @RequestMapping("/admin/getExamInfo")
    @ResponseBody
    public Object adminGetExamInfo(@RequestParam("examId") Integer examId) {
        return examService.adminGetExamInfo(examId);
    }

    @RequestMapping("/admin/getExamQuestionList")
    @ResponseBody
    public Object getExamQuestionList(@RequestParam("examId") Integer examId) {
        return examService.getExamQuestionList(examId);
    }

    @RequestMapping("/admin/modifyExamInfo")
    @ResponseBody
    public JSONObject modifyExamInfo(@RequestBody JSONObject jsonObject) {
        return examService.modifyExamInfo(jsonObject);
    }

    @RequestMapping("/admin/getFinishedStudentList")
    @ResponseBody
    public Object getFinishedStudentList(@RequestParam("examId") Integer examId) {
        return examService.getFinishedStudentList(examId);
    }

    @RequestMapping("/admin/getGradeList")
    @ResponseBody
    public Object getGradeList(@RequestParam("examId") Integer examId) {
        return examService.getGradeList(examId);
    }


    @RequestMapping("/admin/submitGradeList")
    @ResponseBody
    public JSONObject submitGradeList(@RequestBody JSONObject jsonObject) {
        return examService.submitGradeList(jsonObject);
    }

    @RequestMapping("/admin/getWaitingGradeList")
    @ResponseBody
    public Object getWaitingGradeList(@RequestParam("id") Integer adminId,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("currentPage") Integer currentPage,
                                        @RequestParam(value = "trainingName", required = false) String trainingName,
                                        @RequestParam(value = "idNum", required = false) String idNum,
                                        @RequestParam(value = "studentName", required = false) String studentName) {
        return examService.getWaitingGradeList(adminId, pageSize, currentPage, trainingName, idNum, studentName);
    }

    @RequestMapping("/admin/approvalManyRecords")
    @ResponseBody
    public JSONObject approvalManyRecords(@RequestBody JSONObject jsonObject) {
        return examService.approvalManyRecords(jsonObject);
    }

    @RequestMapping("/admin/refuseSingleRecord")
    @ResponseBody
    public JSONObject refuseSingleRecord(@RequestBody JSONObject jsonObject) {
        return examService.refuseSingleRecord(jsonObject);
    }

    @RequestMapping("/admin/refuseManyRecords")
    @ResponseBody
    public JSONObject refuseManyRecords(@RequestBody JSONObject jsonObject) {
        return examService.refuseManyRecords(jsonObject);
    }

    @RequestMapping("/admin/getCheckRecordList")
    @ResponseBody
    public Object getCheckRecordList(@RequestParam("examId") Integer examId,
                                     @RequestParam("pageSize") Integer pageSize,
                                     @RequestParam("currentPage") Integer currentPage,
                                     @RequestParam(value = "checker",required = false) String checker,
                                     @RequestParam(value = "studentName",required = false) String studentName,
                                     @RequestParam(value = "isPass",required = false) Integer isPass) {
        return examService.getCheckRecordList(examId, pageSize, currentPage, checker, studentName, isPass);
    }

    @RequestMapping("/admin/getGradeTemplate")
    @ResponseBody
    public ResponseEntity<byte[]> getGradeTemplate(HttpServletRequest request) {
        return examService.getGradeTemplate(request);
    }

    @RequestMapping("/admin/uploadGradeOfExam")
    @ResponseBody
    public JSONObject uploadGradeOfExam(@RequestParam("file") MultipartFile file) {
        return examService.uploadGradeOfExam(file);
    }

    @RequestMapping("/admin/modifyGrade")
    @ResponseBody
    public JSONObject modifyGrade(@RequestBody JSONObject jsonObject) {
        return examService.modifyGrade(jsonObject);
    }
}
