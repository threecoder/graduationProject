package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface StudentService {

    JSONObject getUserInfo(Integer studentId);

    JSONObject setUserInfo(JSONObject jsonObject,HttpServletResponse response);

    JSONObject setNewPassword(JSONObject jsonObject);
}
