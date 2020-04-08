package com.lutayy.campbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Object getUserInfo(@RequestParam("id") Integer studentId){
        return studentService.getUserInfo(studentId);
    }

    @RequestMapping("/setUserInfo")
    @ResponseBody
    public JSONObject setUserInfo(@RequestBody JSONObject jsonObject,HttpServletResponse response){
        return studentService.setUserInfo(jsonObject,response);
    }

    @RequestMapping("/setNewPassword")
    @ResponseBody
    public JSONObject setNewPassword(@RequestBody JSONObject jsonObject){
        return studentService.setNewPassword(jsonObject);
    }

}

