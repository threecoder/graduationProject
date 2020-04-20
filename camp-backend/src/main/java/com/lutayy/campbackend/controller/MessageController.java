package com.lutayy.campbackend.controller;

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
    public Object getUnReadMsgNum(@RequestParam("id") Integer memberId) {
        return messageService.getUnReadMsgNum(memberId);
    }

}
