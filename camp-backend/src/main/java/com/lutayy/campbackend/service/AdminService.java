package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {

    JSONObject setNewPassword(JSONObject jsonObject);
    //管理员添加单个学员
    JSONObject addSingleStudent(JSONObject jsonObject);

    JSONObject importStudentByFile(Integer memberId, MultipartFile file);

    JSONObject getStudentList(String name, String idNum, String phone, String company, Integer hasOrg,
                              Integer currentPage, Integer pageSize);
    //管理员修改学员挂靠公司
    JSONObject modifyRely(JSONObject jsonObject);
    //管理员修改学员资料
    JSONObject modifyStudentInfo(JSONObject jsonObject);

    JSONObject deleteOneStudentFromMember(JSONObject jsonObject);

    ResponseEntity<byte[]> getMemberTemplate(HttpServletRequest request);

    JSONObject getMemberList(Integer type, Integer deadline, String name,
                             Integer currentPage, Integer pageSize);
    //管理员获取会员列表用于下拉框
    JSONObject getMemSelectList();

    JSONObject getOneMemberStudentList(Integer memberId);

}
