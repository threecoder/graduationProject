package com.lutayy.campbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VoteController {

    @Autowired
    VoteService voteService;

    //管理员——投票管理
    @RequestMapping("/admin/addNewVote")
    @ResponseBody
    public JSONObject addNewVote(@RequestBody JSONObject jsonObject) {
        return voteService.addNewVote(jsonObject);
    }

    @RequestMapping("/admin/deleteVote")
    @ResponseBody
    public JSONObject deleteVote(@RequestBody JSONObject jsonObject) {
        return voteService.deleteVote(jsonObject);
    }

    @RequestMapping("/admin/getVoteStatistic")
    @ResponseBody
    public Object getVoteStatistic(@RequestParam("voteId") Integer voteId) {
        return voteService.getVoteStatistic(voteId);
    }

    @RequestMapping("/admin/getVoteList")
    @ResponseBody
    public Object getVoteList(@RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "type", required = false) Integer type,
                                       @RequestParam("currentPage") Integer currentPage,
                                       @RequestParam("pageSize") Integer pageSize) {
        return voteService.getVoteList(name, type, currentPage, pageSize);
    }

    //会员--投票管理
    @RequestMapping("/member/getMemberCanVoteList")
    @ResponseBody
    public Object getMemberCanVoteList(@RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "isFinish", required = false) Integer isFinish,
                                       @RequestParam("currentPage") Integer currentPage,
                                       @RequestParam("pageSize") Integer pageSize) {
        return voteService.getMemberCanVoteList(name, isFinish, currentPage, pageSize);
    }


    @RequestMapping(value = {"/member/getVoteDetail","/student/getVoteDetail"})
    @ResponseBody
    public Object getVoteDetail(@RequestParam("voteId") Integer voteId) {
        return voteService.getVoteDetail(voteId);
    }

    @RequestMapping("/admin/memberVote")
    @ResponseBody
    public JSONObject memberVote(@RequestBody JSONObject jsonObject) {
        return voteService.memberVote(jsonObject);
    }

    //学员--投票管理
    @RequestMapping("/member/getStudentCanVoteList")
    @ResponseBody
    public Object getStudentCanVoteList(@RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "isFinish", required = false) Integer isFinish,
                                       @RequestParam("currentPage") Integer currentPage,
                                       @RequestParam("pageSize") Integer pageSize) {
        return voteService.getStudentCanVoteList(name, isFinish, currentPage, pageSize);
    }
}
