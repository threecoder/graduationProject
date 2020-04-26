package com.lutayy.campbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CouponController {

    @Autowired
    CouponService couponService;

    @RequestMapping("/admin/addCoupon")
    @ResponseBody
    public JSONObject addCoupon(@RequestBody JSONObject jsonObject){
        return couponService.addCoupon(jsonObject);
    }

    @RequestMapping("/admin/deleteCoupon")
    @ResponseBody
    public JSONObject deleteCoupon(@RequestBody JSONObject jsonObject){
        return couponService.deleteCoupon(jsonObject);
    }

    @RequestMapping("/admin/getCouponList")
    @ResponseBody
    public Object getCouponList(@RequestParam("pageSize") Integer pageSize,
                                    @RequestParam("currentPage") Integer currentPage,
                                    @RequestParam(value = "name",required = false) String name,
                                    @RequestParam(value = "value",required = false) Double value,
                                    @RequestParam(value = "startTime",required = false) String startTime,
                                    @RequestParam(value = "endTime",required = false) String endTime){
        return couponService.getCouponList(pageSize, currentPage, name, value, startTime, endTime);
    }

    @RequestMapping("/admin/allGrantCoupon")
    @ResponseBody
    public JSONObject allGrantCoupon(@RequestBody JSONObject jsonObject){
        return couponService.allGrantCoupon(jsonObject);
    }

    @RequestMapping("/admin/grantCoupon")
    @ResponseBody
    public JSONObject grantCoupon(@RequestBody JSONObject jsonObject){
        return couponService.grantCoupon(jsonObject);
    }

    @RequestMapping("/admin/couponGetMemberList")
    @ResponseBody
    public JSONObject couponGetMemberList(@RequestBody JSONObject jsonObject){
        return couponService.couponGetMemberList(jsonObject);
    }

}
