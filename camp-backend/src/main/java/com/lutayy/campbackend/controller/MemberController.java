package com.lutayy.campbackend.controller;


import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Object getUserInfo(@RequestParam("id") Integer memberId) {
        return memberService.getUserInfo(memberId);
    }

    @RequestMapping("/setUserInfo")
    @ResponseBody
    public JSONObject setUserInfo(@RequestBody JSONObject jsonObject) {
        return memberService.setUserInfo(jsonObject);
    }

    @RequestMapping("/setNewPassword")
    @ResponseBody
    public JSONObject setNewPassword(@RequestBody JSONObject jsonObject) {
        return memberService.setNewPassword(jsonObject);
    }

    @RequestMapping("/rechargeVIP")
    @ResponseBody
    public Object rechargeVIP(@RequestParam("id") Integer memberId) {
        return memberService.rechargeVIP(memberId);
    }

    @RequestMapping("/importSingleStudent")
    @ResponseBody
    public JSONObject importSingleStudent(@RequestBody JSONObject jsonObject) {
        return memberService.importSingleStudent(jsonObject);
    }

    @RequestMapping("/deleteOneStudent")
    @ResponseBody
    public JSONObject deleteOneStudent(@RequestBody JSONObject jsonObject) {
        return memberService.deleteOneStudent(jsonObject);
    }

    @RequestMapping("/getTemplate")
    @ResponseBody
    public ResponseEntity<byte[]> getTemplate(HttpServletRequest request) {
        return memberService.getStudentTemplate(request);
    }

    @RequestMapping("/importStudentByFile")
    @ResponseBody
    public JSONObject importStudentByFile(HttpServletRequest request) {
        return memberService.importStudentByFile(request);
    }

    @RequestMapping("/getStudentList")
    @ResponseBody
    public Object getStudentList(@RequestParam("id") Integer memberId) {
        return memberService.getStudentList(memberId);
    }

    @RequestMapping("/getStudentListByCondition")
    @ResponseBody
    public Object getStudentListByCondition(@RequestParam("id") Integer memberId,
                                            @RequestParam(value = "phone", required = false) String phone,
                                            @RequestParam(value = "idNum", required = false) String idNum,
                                            @RequestParam(value = "name", required = false) String name,
                                            @RequestParam("currentPage") Integer currentPage,
                                            @RequestParam("pageSize") Integer pageSize) {
        return memberService.getStudentListByCondition(memberId, phone, idNum, name, currentPage, pageSize);
    }

    @RequestMapping("/getStudentTrainingHistory")
    @ResponseBody
    public Object getStudentTrainingHistory(@RequestParam("id") Integer memberId,
                                            @RequestParam("idNum") String idNum,
                                            @RequestParam("currentPage") Integer currentPage,
                                            @RequestParam("pageSize") Integer pageSize) {
        return memberService.getStudentTrainingHistory(memberId, idNum, currentPage, pageSize);
    }

}
