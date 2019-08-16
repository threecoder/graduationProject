package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;

public interface StudentService {

    JSONObject getall();
    JSONObject login(JSONObject jsonObject, HttpServletResponse response);
}
