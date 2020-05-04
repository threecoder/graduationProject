package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.Date;

public interface CouponService {

    //管理员新建优惠券
    JSONObject addCoupon(JSONObject jsonObject);
    //删除优惠券
    JSONObject deleteCoupon(JSONObject jsonObject);
    //管理员获取所有优惠券
    JSONObject getCouponList(Integer pageSize, Integer currentPage, String name, Double value, String startTime, String endTime);
    //发放优惠券给所有会员(已缴费)
    JSONObject allGrantCoupon(JSONObject jsonObject);
    //发放优惠券给指定会员（多个）
    JSONObject grantCoupon(JSONObject jsonObject);
    //根据优惠券获取相关会员列表
    JSONObject couponGetMemberList(Integer couponId, Integer pageSize, Integer currentPage, Integer memberKeyId, String memberName);


    //会员获取自己有的优惠券
    JSONObject memberGetCouponList(Integer memberId, Integer pageSize, Integer currentPage);
}
