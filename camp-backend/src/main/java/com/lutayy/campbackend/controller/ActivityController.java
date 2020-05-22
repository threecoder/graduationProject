package com.lutayy.campbackend.controller;


import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public Object getJoinableActivities(@RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("currentPage") Integer currentPage,
                                        @RequestParam(value = "name", required = false) String name){
        return activityService.getJoinableActivities(pageSize, currentPage, name);
    }

    @RequestMapping("/student/getSignedActivities")
    @ResponseBody
    public Object getSignedActivities(@RequestParam("id") Integer studentId,
                                      @RequestParam("currentPage") Integer currentPage,
                                      @RequestParam("pageSize") Integer pageSize,
                                      @RequestParam(value = "name",required = false) String name){
        return activityService.getSignedActivities(studentId, currentPage, pageSize, name);
    }

    @RequestMapping("/student/getSeatNum")
    @ResponseBody
    public Object studentGetSeatNum(@RequestParam("id") Integer studentId,@RequestParam("activityId") int activityId){
        return activityService.studentGetSeatNum(studentId,activityId);
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
    public Object memberGetSeatNum(@RequestParam("id") Integer memberId,@RequestParam("activityId") int activityId){
        return activityService.memberGetSeatNum(memberId,activityId);
    }

    @RequestMapping("/member/getSignedActivities")
    @ResponseBody
    public Object memberGetSignedActivities(@RequestParam("id") Integer memberId,
                                            @RequestParam("currentPage") Integer currentPage,
                                            @RequestParam("pageSize") Integer pageSize,
                                            @RequestParam(value = "name",required = false) String name){
        return activityService.memberGetSignedActivities(memberId, currentPage, pageSize, name);
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


    @RequestMapping("/admin/getEntryForm")
    @ResponseBody
    public ResponseEntity<byte[]> getEntryForm(@RequestParam("activityId") Integer activityId,
                                               @RequestParam("id") Integer adminId){
        return activityService.getEntryForm(activityId, adminId);
    }

    @RequestMapping("/admin/setSEATInfo")
    @ResponseBody
    public JSONObject setSEATInfo(@RequestBody JSONObject jsonObject){
        return activityService.setSEATInfo(jsonObject);
    }

    @RequestMapping("/admin/getActivitySEAT")
    @ResponseBody
    public Object getActivitySEAT(@RequestParam("activityId") Integer activityId){
        return activityService.getActivitySEAT(activityId);
    }

    @RequestMapping("/admin/autoSEAT")
    @ResponseBody
    public JSONObject autoSEAT(@RequestBody JSONObject jsonObject){
        return activityService.autoSEAT(jsonObject);
    }

}
