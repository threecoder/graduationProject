package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;

public interface OrderAndPayService {
    //获取订单列表（增加必选字段type）
    JSONObject getOrderList(String name, String orderNum, String businessName, String type, Integer currentPage, Integer pageSize);
    //修改订单金额
    JSONObject modifyOrderPrice(JSONObject jsonObject);
    //管理员修改某培训订单金额
    JSONObject modifyTrainingOrderPrice(JSONObject jsonObject);
}
