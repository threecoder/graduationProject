package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {

    ResponseEntity<byte[]> getMemberTemplate(HttpServletRequest request);

    JSONObject deleteOneStudentFromMember(JSONObject jsonObject);

    JSONObject getMemberList(JSONObject jsonObject);

    JSONObject getOneMemberStudentList(String memberId);
}
