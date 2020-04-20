package com.lutayy.campbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping("/member/getUnReadMsgNum")
    @ResponseBody
    public Object memberGetUnReadMsgNum(@RequestParam("id") Integer memberId) {
        return messageService.getUnReadMsgNum(memberId, "member");
    }
    @RequestMapping("/student/getUnReadMsgNum")
    @ResponseBody
    public Object studentGetUnReadMsgNum(@RequestParam("id") Integer studentId) {
        return messageService.getUnReadMsgNum(studentId, "student");
    }
    @RequestMapping("/admin/getUnReadMsgNum")
    @ResponseBody
    public Object adminGetUnReadMsgNum(@RequestParam("id") Integer adminId) {
        return messageService.getUnReadMsgNum(adminId, "admin");
    }

    @RequestMapping("/member/signAsRead")
    @ResponseBody
    public JSONObject memberSignAsRead(JSONObject jsonObject) {
        return messageService.signAsRead(jsonObject, "member");
    }
    @RequestMapping("/student/signAsRead")
    @ResponseBody
    public JSONObject studentGignAsRead(JSONObject jsonObject) {
        return messageService.signAsRead(jsonObject, "student");
    }
    @RequestMapping("/admin/signAsRead")
    @ResponseBody
    public JSONObject adminSignAsRead(JSONObject jsonObject) {
        return messageService.signAsRead(jsonObject, "admin");
    }

}
