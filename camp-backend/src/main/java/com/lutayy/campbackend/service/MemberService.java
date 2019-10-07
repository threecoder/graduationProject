package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberService {

    JSONObject setUserInfo(@RequestBody JSONObject jsonObject);

    Object getUserInfo(String id);

    JSONObject setNewPassword(JSONObject jsonObject);

    JSONObject rechargeVIP(String id);

    JSONObject importSingleStudent(JSONObject jsonObject);

    JSONObject deleteOneStudent(JSONObject jsonObject);

    ResponseEntity<byte[]> getStudentTemplate(HttpServletRequest request);

    JSONObject importStudentByFile(HttpServletRequest request);
}
