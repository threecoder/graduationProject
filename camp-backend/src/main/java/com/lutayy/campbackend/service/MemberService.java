package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberService {

    JSONObject setUserInfo(@RequestBody JSONObject jsonObject);

    Object getUserInfo(Integer id);

    JSONObject setNewPassword(JSONObject jsonObject);

    JSONObject rechargeVIP(JSONObject jsonObject);

    JSONObject importSingleStudent(JSONObject jsonObject);

    JSONObject deleteOneStudent(JSONObject jsonObject);

    ResponseEntity<byte[]> getStudentTemplate(HttpServletRequest request);

    JSONObject importStudentByFile(HttpServletRequest request);

    JSONObject getStudentList(Integer memberId);

    JSONObject getStudentListByCondition(Integer memberId, String phone, String idNum, String name,
                                         Integer currentPage, Integer pageSize);

    JSONObject getStudentTrainingHistory(Integer memberId, String idNum, Integer currentPage, Integer pageSize);

}
