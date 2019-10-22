package com.lutayy.campbackend.controller;


import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @RequestMapping(value={"/student/getJoinableActivities","/member/getJoinableActivities"})
    @ResponseBody
    public Object getJoinableActivities(){
        return activityService.getJoinableActivities();
    }

    @RequestMapping("/student/getSignedActivities")
    @ResponseBody
    public Object getSignedActivities(@RequestParam("id") String idcard){
        return activityService.getSignedActivities(idcard);
    }

    @RequestMapping("/student/getSeatNum")
    @ResponseBody
    public Object studentGetSeatNum(@RequestParam("id") String idcard,@RequestParam("activityId") int activityId){
        return activityService.studentGetSeatNum(idcard,activityId);
    }

    @RequestMapping("/student/joinActivity")
    @ResponseBody
    public JSONObject studentJoinActivity(@RequestBody JSONObject jsonObject){
        return activityService.studentJoinActivity(jsonObject);
    }

    @RequestMapping("/member/joinActivity")
    @ResponseBody
    public JSONObject memberJoinActivity(@RequestBody JSONObject jsonObject){
        return activityService.memberJoinActivity(jsonObject);
    }

    @RequestMapping("/member/getSeatNum")
    @ResponseBody
    public Object memberGetSeatNum(@RequestParam("id") String memberId,@RequestParam("activityId") int activityId){
        return activityService.memberGetSeatNum(memberId,activityId);
    }

    @RequestMapping("/member/getSignedActivities")
    @ResponseBody
    public Object memberGetSignedActivities(@RequestParam("id") String id){
        return activityService.memberGetSignedActivities(id);
    }

    @RequestMapping("/admin/addNewActivity")
    @ResponseBody
    public JSONObject addNewActivity(@RequestBody JSONObject jsonObject){
        return activityService.addNewActivity(jsonObject);
    }

    @RequestMapping("/admin/getActivityList")
    @ResponseBody
    public Object adminGetActivityList(){
        return activityService.adminGetActivityList();
    }


}
