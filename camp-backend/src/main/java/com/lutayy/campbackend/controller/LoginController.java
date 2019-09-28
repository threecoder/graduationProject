package com.lutayy.campbackend.controller;


import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.LoginService;
import com.lutayy.campbackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    @ResponseBody
    public JSONObject login(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        return loginService.login(jsonObject,response);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Object getJoinableActivities(HttpServletResponse response){
        return loginService.logout(response);
    }
}
