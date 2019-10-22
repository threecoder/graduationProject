package com.lutayy.campbackend.controller;


import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/getMemberTemplate")
    @ResponseBody
    public ResponseEntity<byte[]> getMemberTemplate(HttpServletRequest request){
        return adminService.getMemberTemplate(request);
    }

    @RequestMapping("/deleteOneStudentFromMember")
    @ResponseBody
    public JSONObject deleteOneStudentFromMember(@RequestBody JSONObject jsonObject){
        return adminService.deleteOneStudentFromMember(jsonObject);
    }

    @RequestMapping("/getMemberList")
    @ResponseBody
    public JSONObject getMemberList(@RequestBody JSONObject jsonObject){
        return adminService.getMemberList(jsonObject);
    }

    @RequestMapping("/getOneMemberStudentList")
    @ResponseBody
    public Object getOneMemberStudentList(@RequestParam("memberId") String memberId){
        return adminService.getOneMemberStudentList(memberId);
    }

}
