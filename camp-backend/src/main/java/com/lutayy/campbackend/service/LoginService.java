package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;

import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    JSONObject login(JSONObject jsonObject, HttpServletResponse response);

    JSONObject logout(HttpServletResponse response);
}
