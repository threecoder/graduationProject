package com.lutayy.campbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.OrderAndPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderAndPayController {

    @Autowired
    OrderAndPayService orderAndPayService;

    @RequestMapping("/admin/getOrderList")
    @ResponseBody
    public Object getOrderList(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "orderNum", required = false) String orderNum,
                               @RequestParam(value = "businessName", required = false) String businessName,
                               @RequestParam("currentPage") Integer currentPage,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam("type") String type) {
        return orderAndPayService.getOrderList(name, orderNum, businessName, type, currentPage, pageSize);
    }

    @RequestMapping("/admin/modifyOrderPrice")
    @ResponseBody
    public JSONObject modifyOrderPrice(@RequestBody JSONObject jsonObject) {
        return orderAndPayService.modifyOrderPrice(jsonObject);
    }


    @RequestMapping("/admin/modifyTrainingOrderPrice")
    @ResponseBody
    public JSONObject modifyTrainingOrderPrice(@RequestBody JSONObject jsonObject) {
        return orderAndPayService.modifyTrainingOrderPrice(jsonObject);
    }

    @RequestMapping("/member/getOrderList")
    @ResponseBody
    public Object memberGetOrderList(@RequestParam("id") Integer memberId,
                                     @RequestParam(value = "orderNum", required = false) String orderNum,
                                     @RequestParam(value = "businessName", required = false) String businessName,
                                     @RequestParam("currentPage") Integer currentPage,
                                     @RequestParam("pageSize") Integer pageSize,
                                     @RequestParam("type") String type) {
        return orderAndPayService.memberGetOrderList(memberId, orderNum, businessName, type, currentPage, pageSize);
    }


    @RequestMapping(value = {"/student/aliPay", "/member/aliPay"})
    @ResponseBody
    public JSONObject aliPay(@RequestBody JSONObject jsonObject) {
        return orderAndPayService.alipay(jsonObject);
    }

    @RequestMapping("/aliPayNotify")
    @ResponseBody
    public void aliPayNotify(HttpServletRequest request) {
        orderAndPayService.aliPayNotify(request);
    }
}
