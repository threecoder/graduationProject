package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface OrderAndPayService {
    //获取订单列表（增加必选字段type）
    JSONObject getOrderList(String name, String orderNum, String businessName, String type, Integer currentPage, Integer pageSize);
    //修改订单金额
    JSONObject modifyOrderPrice(JSONObject jsonObject);
    //管理员修改订单状态为已支付
    JSONObject confirmPay(JSONObject jsonObject);
    //管理员修改某培训订单金额
    JSONObject modifyTrainingOrderPrice(JSONObject jsonObject);
    //会员获取订单列表（增加必选字段type）
    JSONObject memberGetOrderList(Integer memberId, String orderNum, String businessName, String type, Integer currentPage, Integer pageSize);
    //学员获取订单列表
    JSONObject studentGetOrderList(Integer studentId, String orderNum, String businessName, String type, Integer currentPage, Integer pageSize);

    JSONObject getTrainingOrderList(String orderNum, Integer userId, String userName, Integer trainingId, Integer currentPage, Integer pageSize);

    JSONObject alipay(JSONObject jsonObject);

    void aliPayNotify(HttpServletRequest request);
}
