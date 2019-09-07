package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;

public interface StudentService {

    JSONObject login(JSONObject jsonObject, HttpServletResponse response);
}
