package com.lutayy.campbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VoteController {

    @Autowired
    VoteService voteService;

    //管理员——投票
    @RequestMapping("/admin/addNewVote")
    @ResponseBody
    public JSONObject addNewVote(@RequestBody JSONObject jsonObject){
        return voteService.addNewVote(jsonObject);
    }

    @RequestMapping("/admin/deleteVote")
    @ResponseBody
    public JSONObject deleteVote(@RequestBody JSONObject jsonObject){
        return voteService.deleteVote(jsonObject);
    }
}
