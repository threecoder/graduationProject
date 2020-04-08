package com.lutayy.campbackend.controller;


import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.AdminService;
import com.lutayy.campbackend.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    MemberService memberService;

    @RequestMapping("/setNewPassword")
    @ResponseBody
    public JSONObject setNewPassword(@RequestBody JSONObject jsonObject) {
        return adminService.setNewPassword(jsonObject);
    }

    @RequestMapping("/addNewAdmin")
    @ResponseBody
    public JSONObject addNewAdmin(@RequestBody JSONObject jsonObject) {
        return adminService.addNewAdmin(jsonObject);
    }

    //-------学员管理-----------
    @RequestMapping("/getStudentTemplate")
    @ResponseBody
    public ResponseEntity<byte[]> getTemplate(HttpServletRequest request) {
        return memberService.getStudentTemplate(request);
    }

    @RequestMapping("/addSingleStudent")
    @ResponseBody
    public JSONObject addSingleStudent(@RequestBody JSONObject jsonObject) {
        return adminService.addSingleStudent(jsonObject);
    }

    @RequestMapping("/importStudentByFile")
    @ResponseBody
    public JSONObject importStudentByFile(@RequestParam("memberId") Integer memberId, @RequestParam("file") MultipartFile file) {
        return adminService.importStudentByFile(memberId, file);
    }

    @RequestMapping("/getStudentList")
    @ResponseBody
    public Object getStudentList(@RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "idNum", required = false) String idNum,
                                 @RequestParam(value = "phone", required = false) String phone,
                                 @RequestParam(value = "company", required = false) String company,
                                 @RequestParam(value = "hasOrg", required = false) Integer hasOrg,
                                 @RequestParam("currentPage") int currentPage,
                                 @RequestParam("pageSize") int pageSize) {
        return adminService.getStudentList(name, idNum, phone, company, hasOrg, currentPage, pageSize);
    }

    @RequestMapping("/deleteOneStudentFromMember")
    @ResponseBody
    public JSONObject deleteOneStudentFromMember(@RequestBody JSONObject jsonObject) {
        return adminService.deleteOneStudentFromMember(jsonObject);
    }

    @RequestMapping("/modifyRely")
    @ResponseBody
    public JSONObject modifyRely(@RequestBody JSONObject jsonObject) {
        return adminService.modifyRely(jsonObject);
    }

    @RequestMapping("/resetPassword")
    @ResponseBody
    public JSONObject resetPassword(@RequestBody JSONObject jsonObject) {
        return adminService.resetPassword(jsonObject);
    }

    @RequestMapping("/modifyStudentInfo")
    @ResponseBody
    public JSONObject modifyStudentInfo(@RequestBody JSONObject jsonObject) {
        return adminService.modifyStudentInfo(jsonObject);
    }

    //-----------会员管理-------------
    @RequestMapping("/getMemberTemplate")
    @ResponseBody
    public ResponseEntity<byte[]> getMemberTemplate(HttpServletRequest request) {
        return adminService.getMemberTemplate(request);
    }

    @RequestMapping("/importMemberByFile")
    @ResponseBody
    public JSONObject importMemberByFile(@RequestParam("file") MultipartFile file) {
        return adminService.importMemberByFile(file);
    }

    @RequestMapping("/getMemberList")
    @ResponseBody
    public Object getMemberList(@RequestParam(value = "type", required = false) Integer type,
                                @RequestParam(value = "deadline", required = false) Integer deadline,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam("currentPage") Integer currentPage,
                                @RequestParam("pageSize") Integer pageSize) {
        return adminService.getMemberList(type, deadline, name, currentPage, pageSize);
    }

    @RequestMapping("/getMemSelectList")
    @ResponseBody
    public Object getMemSelectList() {
        return adminService.getMemSelectList();
    }

    @RequestMapping("/getOneMemberStudentList")
    @ResponseBody
    public Object getOneMemberStudentList(@RequestParam("memberId") Integer memberId) {
        return adminService.getOneMemberStudentList(memberId);
    }

}
