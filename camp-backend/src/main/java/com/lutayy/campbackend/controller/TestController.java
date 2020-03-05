package com.lutayy.campbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.util.Md5;
import com.lutayy.campbackend.common.util.RedisUtil;
import com.lutayy.campbackend.dao.StudentMapper;
import com.lutayy.campbackend.pojo.Student;
import com.lutayy.campbackend.pojo.StudentExample;
import com.lutayy.campbackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/campback")
public class TestController {

    @Autowired
    StudentMapper studentMapper;
    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("/test")
    @ResponseBody
    public String getall(){
        Student student=(Student)redisUtil.get("studentId_38");
        return JSONObject.toJSON(student).toString();
    }

    public static void main(String[] args){
        TestController testController=new TestController();
        testController.getall();
    }
}
