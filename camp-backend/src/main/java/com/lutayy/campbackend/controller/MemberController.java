package com.lutayy.campbackend.controller;


import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Object getUserInfo(@RequestParam("id") String id){
        return memberService.getUserInfo(id);
    }

    @RequestMapping("/setUserInfo")
    @ResponseBody
    public JSONObject setUserInfo(@RequestBody JSONObject jsonObject){
        return memberService.setUserInfo(jsonObject);
    }

    @RequestMapping("/setNewPassword")
    @ResponseBody
    public JSONObject setNewPassword(@RequestBody JSONObject jsonObject){
        return memberService.setNewPassword(jsonObject);
    }

    @RequestMapping("/rechargeVIP")
    @ResponseBody
    public Object rechargeVIP(@RequestParam("id") String id){
        return memberService.rechargeVIP(id);
    }

    @RequestMapping("/importSingleStudent")
    @ResponseBody
    public JSONObject importSingleStudent(@RequestBody JSONObject jsonObject){
        return memberService.importSingleStudent(jsonObject);
    }

    @RequestMapping("/deleteOneStudent")
    @ResponseBody
    public JSONObject deleteOneStudent(@RequestBody JSONObject jsonObject){
        return memberService.deleteOneStudent(jsonObject);
    }
}
