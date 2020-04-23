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

    @RequestMapping("/member/getMsgDetail")
    @ResponseBody
    public Object memberGetMsgDetail(@RequestParam("msgID") Integer msgID) {
        return messageService.getMsgDetail(msgID, "member");
    }
    @RequestMapping("/admin/getMsgDetail")
    @ResponseBody
    public Object adminGetMsgDetail(@RequestParam("msgID") Integer msgID) {
        return messageService.getMsgDetail(msgID, "admin");
    }
    @RequestMapping("/student/getMsgDetail")
    @ResponseBody
    public Object studentGetMsgDetail(@RequestParam("msgID") Integer msgID) {
        return messageService.getMsgDetail(msgID, "student");
    }

    @RequestMapping("/member/deleteMsg")
    @ResponseBody
    public JSONObject memberDeleteMsg(JSONObject jsonObject) {
        return messageService.deleteMsg(jsonObject, "member");
    }
    @RequestMapping("/admin/deleteMsg")
    @ResponseBody
    public JSONObject adminDeleteMsg(JSONObject jsonObject) {
        return messageService.deleteMsg(jsonObject, "admin");
    }
    @RequestMapping("/student/deleteMsg")
    @ResponseBody
    public JSONObject studentDeleteMsg(JSONObject jsonObject) {
        return messageService.deleteMsg(jsonObject, "student");
    }

    @RequestMapping("/member/getMsgList")
    @ResponseBody
    public Object memberGetMsgList(@RequestParam("pageSize") Integer pageSize, @RequestParam("currentPage") Integer currentPage,
                                   @RequestParam("id") Integer roleId) {
        return messageService.getMsgList(pageSize, currentPage, roleId, "member");
    }
    @RequestMapping("/admin/getMsgList")
    @ResponseBody
    public Object adminGetMsgList(@RequestParam("pageSize") Integer pageSize, @RequestParam("currentPage") Integer currentPage,
                                   @RequestParam("id") Integer roleId) {
        return messageService.getMsgList(pageSize, currentPage, roleId, "admin");
    }
    @RequestMapping("/student/getMsgList")
    @ResponseBody
    public Object studentGetMsgList(@RequestParam("pageSize") Integer pageSize, @RequestParam("currentPage") Integer currentPage,
                                   @RequestParam("id") Integer roleId) {
        return messageService.getMsgList(pageSize, currentPage, roleId, "student");
    }
}
