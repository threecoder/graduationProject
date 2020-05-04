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
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping("/lockAccount")
    @ResponseBody
    public JSONObject lockAccount(@RequestBody JSONObject jsonObject) {
        return adminService.lockOrUnlockAccount(jsonObject, true);
    }

    @RequestMapping("/unLockAccount")
    @ResponseBody
    public JSONObject unLockAccount(@RequestBody JSONObject jsonObject) {
        return adminService.lockOrUnlockAccount(jsonObject, false);
    }

    @RequestMapping("/getAdminList")
    @ResponseBody
    public Object getAdminList(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "account", required = false) String account,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam("currentPage") Integer currentPage) {
        return adminService.getAdminList(name, account, currentPage, pageSize);
    }

    @RequestMapping("/getCheckerList")
    @ResponseBody
    public Object getCheckerList() {
        return adminService.getCheckerList();
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
                                 @RequestParam(value = "company", required = false) Integer memberId,
                                 @RequestParam(value = "hasOrg", required = false) Integer hasOrg,
                                 @RequestParam("currentPage") int currentPage,
                                 @RequestParam("pageSize") int pageSize) {
        return adminService.getStudentList(name, idNum, phone, memberId, hasOrg, currentPage, pageSize);
    }

    //管理员获取所有学员列表用于下拉框
    @RequestMapping("/getStudentSelect")
    @ResponseBody
    public Object getStudentSelect() {
        return adminService.getStudentSelect();
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
    public void getMemberTemplate(HttpServletResponse response) {
        adminService.getMemberTemplate(response);
        return;
    }

    @RequestMapping("/addSingleMember")
    @ResponseBody
    public JSONObject addSingleMember(@RequestBody JSONObject jsonObject) {
        return adminService.addSingleMember(jsonObject);
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
                                @RequestParam("pageSize") Integer pageSize,
                                @RequestParam(value = "becomeTimeStart", required = false) String becomeTimeStart,
                                @RequestParam(value = "becomeTimeEnd", required = false) String becomeTimeEnd,
                                @RequestParam(value = "endTimeStart", required = false) String endTimeStart,
                                @RequestParam(value = "endTimeEnd", required = false) String endTimeEnd,
                                @RequestParam(value = "province", required = false) String province,
                                @RequestParam(value = "city", required = false) String city,
                                @RequestParam(value = "area", required = false) String area) {
        return adminService.getMemberList(type, deadline, name, currentPage, pageSize, becomeTimeStart, becomeTimeEnd,
                endTimeStart, endTimeEnd, province, city, area);
    }

    @RequestMapping("/modifyMemberInfo")
    @ResponseBody
    public JSONObject modifyMemberInfo(@RequestBody JSONObject jsonObject) {
        return adminService.modifyMemberInfo(jsonObject);
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
