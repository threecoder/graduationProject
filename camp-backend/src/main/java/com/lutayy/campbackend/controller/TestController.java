package com.lutayy.campbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.util.Md5;
import com.lutayy.campbackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/campback")
public class TestController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/test")
    @ResponseBody
    public String getall(){
        String a="1231";
        return Md5.digest(a);
    }
}
