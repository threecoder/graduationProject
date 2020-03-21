package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {

    JSONObject getStudentList(String name, String idNum, String phone, String company, Integer hasOrg,
                              Integer currentPage, Integer pageSize);

    JSONObject deleteOneStudentFromMember(JSONObject jsonObject);

    ResponseEntity<byte[]> getMemberTemplate(HttpServletRequest request);

    JSONObject getMemberList(Integer type, Integer deadline, String name,
                             Integer currentPage, Integer pageSize);

    JSONObject getOneMemberStudentList(Integer memberId);

}
