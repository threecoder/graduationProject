package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lutayy.campbackend.common.config.AlipayConfig;
import com.lutayy.campbackend.common.util.RedisUtil;
import com.lutayy.campbackend.common.util.UUIDUtil;
import com.lutayy.campbackend.dao.*;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.OrderAndPayService;
import com.lutayy.campbackend.service.SQLConn.ActivityStudentSQLConn;
import com.lutayy.campbackend.service.SQLConn.TrainingStudentSQLConn;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderAndPayServiceImpl implements OrderAndPayService {

    @Autowired
    MemberMapper memberMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TrainingMapper trainingMapper;
    @Autowired
    TrainingOrderMapper trainingOrderMapper;
    @Autowired
    TrainingOrderStudentMapper trainingOrderStudentMapper;
    @Autowired
    TrainingReStudentMapper trainingReStudentMapper;
    @Autowired
    ExamMapper examMapper;
    @Autowired
    ExamReStudentMapper examReStudentMapper;
    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    ActivityOrderMapper activityOrderMapper;
    @Autowired
    ActivityOrderStudentMapper activityOrderStudentMapper;
    @Autowired
    ActivityStudentMapper activityStudentMapper;
    @Autowired
    CertificateChangeOrderMapper changeOrderMapper;
    @Autowired
    CertificateRecheckOrderMapper recheckOrderMapper;
    @Autowired
    MemberSubscriptionOrderMapper memberSubscriptionOrderMapper;
    @Autowired
    GetObjectHelper getObjectHelper;
    @Resource
    private RedisUtil redisUtil;
    @Value("${SERVER_HOST}")
    private String SERVER_HOST;

    //admin获取订单列表（增加必选字段type）
    @Override
    public JSONObject getOrderList(String name, String orderNum, String businessName, String type,
                                   Integer currentPage, Integer pageSize) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();
        result.put("code", "fail");
        result.put("data", data);
        JSONArray list=new JSONArray();
        long total;
        // TODO
        if(type.equals("activity")){
            ActivityOrderExample activityOrderExample=new ActivityOrderExample();
            ActivityOrderExample.Criteria criteria=activityOrderExample.createCriteria();
            criteria.andIsDeleteAdminEqualTo(false);//管理员这边还没有删除订单
            if(name!=null && !name.equals("")){
                criteria.andOpManNameLike("%"+name+"%");
            }
            if(orderNum!=null && orderNum.equals("")){
                criteria.andActivityOrderIdEqualTo(orderNum);
            }
            if(businessName!=null && businessName.equals("")){
                criteria.andBusinessNameLike("%"+businessName+"%");
            }
            total=activityOrderMapper.countByExample(activityOrderExample);
            activityOrderExample.setOrderByClause("order_begin_time DESC");
            activityOrderExample.setOffset((currentPage-1)*pageSize);
            activityOrderExample.setLimit(pageSize);
            List<ActivityOrder> activityOrders=activityOrderMapper.selectByExample(activityOrderExample);
            for(ActivityOrder activityOrder:activityOrders){
                JSONObject object=new JSONObject();
                object.put("orderNum", activityOrder.getActivityOrderId());
                object.put("orderType", type);
                object.put("businessName", activityOrder.getBusinessName());
                object.put("builder", activityOrder.getOpManName());
                object.put("price", activityOrder.getOrderPrice());
                object.put("buildTime", activityOrder.getOrderBeginTime());
                Date endTime=getObjectHelper.getOrderEndTime(type, activityOrder.getOrderBeginTime());
                object.put("endTime", endTime);
                object.put("payTime", activityOrder.getPayTime());
                //检查订单是否失效
                getObjectHelper.checkActivityOrderOutOfTime(activityOrder);
                if(activityOrder.getClose()){
                    object.put("status", "订单关闭");
                }else {
                    if(activityOrder.getPaymentState())
                        object.put("status", "已支付");
                    else
                        object.put("status", "未支付");
                }
                list.add(object);
            }
        }
        // TODO
        else if(type.equals("training")){
            TrainingOrderExample trainingOrderExample=new TrainingOrderExample();
            TrainingOrderExample.Criteria criteria=trainingOrderExample.createCriteria();
            criteria.andIsDeleteAdminEqualTo(false);//管理员这边还没有删除订单
            if(name!=null && !name.equals("")){
                criteria.andOpManNameLike("%"+name+"%");
            }
            if(orderNum!=null && orderNum.equals("")){
                criteria.andTrainingOrderIdEqualTo(orderNum);
            }
            if(businessName!=null && businessName.equals("")){
                criteria.andBusinessNameLike("%"+businessName+"%");
            }
            total=trainingOrderMapper.countByExample(trainingOrderExample);
            trainingOrderExample.setOrderByClause("order_begin_time DESC");
            trainingOrderExample.setOffset((currentPage-1)*pageSize);
            trainingOrderExample.setLimit(pageSize);
            List<TrainingOrder> trainingOrders=trainingOrderMapper.selectByExample(trainingOrderExample);
            for(TrainingOrder trainingOrder:trainingOrders){
                JSONObject object=new JSONObject();
                object.put("orderNum", trainingOrder.getTrainingOrderId());
                object.put("orderType", type);
                object.put("businessName", trainingOrder.getBusinessName());
                object.put("builder", trainingOrder.getOpManName());
                object.put("price", trainingOrder.getOrderPrice());
                object.put("buildTime", trainingOrder.getOrderBeginTime());
                Date endTime=getObjectHelper.getOrderEndTime(type, trainingOrder.getOrderBeginTime());
                object.put("endTime", endTime);
                object.put("payTime", trainingOrder.getPayTime());
                getObjectHelper.checkTrainingOrderOutOfTime(trainingOrder);
                if(trainingOrder.getClose()){
                    object.put("status", "订单关闭");
                }else {
                    if(trainingOrder.getPaymentState())
                        object.put("status", "已支付");
                    else
                        object.put("status", "未支付");
                }
                list.add(object);
            }
        }
        // TODO
        else if(type.equals("cerChange")){
            CertificateChangeOrderExample orderExample=new CertificateChangeOrderExample();
            CertificateChangeOrderExample.Criteria criteria=orderExample.createCriteria();
            criteria.andIsDeleteAdminEqualTo(false);//管理员这边还没有删除订单
            if(name!=null && !name.equals("")){
                criteria.andOpManNameLike("%"+name+"%");
            }
            if(orderNum!=null && orderNum.equals("")){
                criteria.andCertificateChangeOrderIdEqualTo(orderNum);
            }
            if(businessName!=null && businessName.equals("")){
                criteria.andBusinessNameLike("%"+businessName+"%");
            }
            total=changeOrderMapper.countByExample(orderExample);
            orderExample.setOrderByClause("order_begin_time DESC");
            orderExample.setOffset((currentPage-1)*pageSize);
            orderExample.setLimit(pageSize);
            List<CertificateChangeOrder> orders=changeOrderMapper.selectByExample(orderExample);
            for(CertificateChangeOrder order:orders){
                JSONObject object=new JSONObject();
                object.put("orderNum", order.getCertificateChangeOrderId());
                object.put("orderType", type);
                object.put("businessName", order.getBusinessName());
                object.put("builder", order.getOpManName());
                object.put("price", order.getOrderPrice());
                object.put("buildTime", order.getOrderBeginTime());
                Date endTime=getObjectHelper.getOrderEndTime(type, order.getOrderBeginTime());
                object.put("endTime", endTime);
                object.put("payTime", order.getPayTime());
                if(!order.getClose()){
                    if(!order.getPaymentState()){
                        //查看未支付的订单是否过期，失效则置close为1
                        if(endTime.compareTo(new Date())<0){
                            order.setClose(true);
                            changeOrderMapper.updateByPrimaryKeySelective(order);
                        }
                    }
                }
                if(order.getClose()){
                    object.put("status", "订单关闭");
                }else {
                    if(order.getPaymentState())
                        object.put("status", "已支付");
                    else
                        object.put("status", "未支付");
                }
                list.add(object);
            }
        }
        // TODO
        else if(type.equals("cerRecheck")){
            CertificateRecheckOrderExample orderExample=new CertificateRecheckOrderExample();
            CertificateRecheckOrderExample.Criteria criteria=orderExample.createCriteria();
            criteria.andIsDeleteAdminEqualTo(false);//管理员这边还没有删除订单
            if(name!=null && !name.equals("")){
                criteria.andOpManNameLike("%"+name+"%");
            }
            if(orderNum!=null && orderNum.equals("")){
                criteria.andCertificateRecheckOrderIdEqualTo(orderNum);
            }
            if(businessName!=null && businessName.equals("")){
                criteria.andBusinessNameLike("%"+businessName+"%");
            }
            total=recheckOrderMapper.countByExample(orderExample);
            orderExample.setOrderByClause("order_begin_time DESC");
            orderExample.setOffset((currentPage-1)*pageSize);
            orderExample.setLimit(pageSize);
            List<CertificateRecheckOrder> orders=recheckOrderMapper.selectByExample(orderExample);
            for(CertificateRecheckOrder order:orders){
                JSONObject object=new JSONObject();
                object.put("orderNum", order.getCertificateRecheckOrderId());
                object.put("orderType", type);
                object.put("businessName", order.getBusinessName());
                object.put("builder", order.getOpManName());
                object.put("price", order.getOrderPrice());
                object.put("buildTime", order.getOrderBeginTime());
                Date endTime=getObjectHelper.getOrderEndTime(type, order.getOrderBeginTime());
                object.put("endTime", endTime);
                object.put("payTime", order.getPayTime());
                if(!order.getClose()){
                    if(!order.getPaymentState()){
                        //查看未支付的订单是否过期，失效则置close为1
                        if(endTime.compareTo(new Date())<0){
                            order.setClose(true);
                            recheckOrderMapper.updateByPrimaryKeySelective(order);
                        }
                    }
                }
                if(order.getClose()){
                    object.put("status", "订单关闭");
                }else {
                    if(order.getPaymentState())
                        object.put("status", "已支付");
                    else
                        object.put("status", "未支付");
                }
                list.add(object);
            }
        }
        // TODO
        else if(type.equals("member")) {
            MemberSubscriptionOrderExample orderExample=new MemberSubscriptionOrderExample();
            MemberSubscriptionOrderExample.Criteria criteria=orderExample.createCriteria();
            criteria.andIsDeleteAdminEqualTo(false);//管理员这边还没有删除订单
            if(name!=null && !name.equals("")){
                criteria.andOpManNameLike("%"+name+"%");
            }
            if(orderNum!=null && orderNum.equals("")){
                criteria.andSubscriptionOrderIdEqualTo(orderNum);
            }
            if(businessName!=null && businessName.equals("")){
                criteria.andBusinessNameLike("%"+businessName+"%");
            }
            total=memberSubscriptionOrderMapper.countByExample(orderExample);
            orderExample.setOrderByClause("create_time DESC");
            orderExample.setOffset((currentPage-1)*pageSize);
            orderExample.setLimit(pageSize);
            List<MemberSubscriptionOrder> orders=memberSubscriptionOrderMapper.selectByExample(orderExample);
            for(MemberSubscriptionOrder order:orders){
                JSONObject object=new JSONObject();
                object.put("orderNum", order.getSubscriptionOrderId());
                object.put("orderType", type);
                object.put("businessName", order.getBusinessName());
                object.put("builder", order.getOpManName());
                object.put("price", order.getFee());
                object.put("buildTime", order.getCreateTime());
                Date endTime=getObjectHelper.getOrderEndTime(type, order.getCreateTime());
                object.put("endTime", endTime);
                object.put("payTime", order.getPayTime());
                if(!order.getClose()){
                    if(!order.getPaymentState()){
                        //查看未支付的订单是否过期，失效则置close为1
                        if(endTime.compareTo(new Date())<0){
                            order.setClose(true);
                            memberSubscriptionOrderMapper.updateByPrimaryKeySelective(order);
                        }
                    }
                }
                if(order.getClose()){
                    object.put("status", "订单关闭");
                }else {
                    if(order.getPaymentState())
                        object.put("status", "已支付");
                    else
                        object.put("status", "未支付");
                }
                list.add(object);
            }
        }else {
            result.put("msg", "订单类别参数type有误");
            return result;
        }
        data.put("list", list);
        data.put("total", total);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取"+type+"类订单列表成功！");
        return result;
    }

    //修改订单金额
    @Override
    public JSONObject modifyOrderPrice(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        String orderCode=jsonObject.getString("orderNum");
        Object orderTypeObject=redisUtil.hget("order_no_map", orderCode);
        BigDecimal newPrice=jsonObject.getBigDecimal("price");
        if(orderTypeObject==null){
            result.put("msg", "订单不存在，或已过期！");
            return result;
        }
        try {
            String orderType = (String) orderTypeObject;
            Object order = getObjectHelper.getOrderByOrderCode(orderCode, orderType);
            if (orderType.equals("activity")) {
                ActivityOrder activityOrder = (ActivityOrder) order;
                activityOrder.setOrderPrice(newPrice);
                activityOrderMapper.updateByPrimaryKeySelective(activityOrder);
            } else if (orderType.equals("training")) {
                TrainingOrder trainingOrder = (TrainingOrder) order;
                trainingOrder.setOrderPrice(newPrice);
                trainingOrderMapper.updateByPrimaryKeySelective(trainingOrder);
            } else if (orderType.equals("cerChange")) {
                CertificateChangeOrder certificateChangeOrder = (CertificateChangeOrder) order;
                certificateChangeOrder.setOrderPrice(newPrice);
                changeOrderMapper.updateByPrimaryKeySelective(certificateChangeOrder);
            } else if (orderType.equals("cerRecheck")) {
                CertificateRecheckOrder certificateRecheckOrder = (CertificateRecheckOrder) order;
                certificateRecheckOrder.setOrderPrice(newPrice);
                recheckOrderMapper.updateByPrimaryKeySelective(certificateRecheckOrder);
            } else if (orderType.equals("member")) {
                MemberSubscriptionOrder memberSubscriptionOrder = (MemberSubscriptionOrder) order;
                memberSubscriptionOrder.setFee(newPrice);
                memberSubscriptionOrderMapper.updateByPrimaryKeySelective(memberSubscriptionOrder);
            } else {
                result.put("msg", "修改失败，订单类型错误");
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("msg", "相关数据格式有误,请与工作人员联系");
            return result;
        }
        result.put("code", "success");
        result.put("msg", "订单金额修改成功！");
        return result;
    }

    //管理员修改某培训订单金额
    @Override
    public JSONObject modifyTrainingOrderPrice(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        String orderCode=jsonObject.getString("orderNum");
        BigDecimal newPrice=jsonObject.getBigDecimal("price");

        TrainingOrder trainingOrder=(TrainingOrder)getObjectHelper.getOrderByOrderCode(orderCode, "training");
        if(trainingOrder==null){
            result.put("msg", "订单不存在");
            return result;
        }
        if(getObjectHelper.checkTrainingOrderOutOfTime(trainingOrder)){
            result.put("msg", "订单已失效，无法进行修改");
            return result;
        }
        trainingOrder.setOrderPrice(newPrice);
        trainingOrderMapper.updateByPrimaryKeySelective(trainingOrder);
        result.put("code", "success");
        result.put("msg", "修改成功！");
        return result;
    }

    //会员获取订单列表
    @Override
    public JSONObject memberGetOrderList(Integer memberId, String orderNum, String businessName, String type, Integer currentPage, Integer pageSize) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();
        result.put("code", "fail");
        result.put("data", data);
        JSONArray list=new JSONArray();
        long total;
        // TODO
        if(type.equals("activity")){
            ActivityOrderExample activityOrderExample=new ActivityOrderExample();
            ActivityOrderExample.Criteria criteria=activityOrderExample.createCriteria();
            if(orderNum!=null && orderNum.equals("")){
                criteria.andActivityOrderIdEqualTo(orderNum);
            }
            criteria.andIsDeleteUserEqualTo(false).andOrderTypeEqualTo(false).andMemberKeyIdEqualTo(memberId);
            if(businessName!=null && businessName.equals("")){
                criteria.andBusinessNameLike("%"+businessName+"%");
            }
            total=activityOrderMapper.countByExample(activityOrderExample);
            activityOrderExample.setOrderByClause("order_begin_time DESC");
            activityOrderExample.setOffset((currentPage-1)*pageSize);
            activityOrderExample.setLimit(pageSize);
            List<ActivityOrder> activityOrders=activityOrderMapper.selectByExample(activityOrderExample);
            for(ActivityOrder activityOrder:activityOrders){
                JSONObject object=new JSONObject();
                object.put("orderNum", activityOrder.getActivityOrderId());
                object.put("orderType", type);
                object.put("businessName", activityOrder.getBusinessName());
                object.put("builder", activityOrder.getOpManName());
                object.put("price", activityOrder.getOrderPrice());
                object.put("buildTime", activityOrder.getOrderBeginTime());
                Date endTime=getObjectHelper.getOrderEndTime(type, activityOrder.getOrderBeginTime());
                object.put("endTime", endTime);
                object.put("payTime", activityOrder.getPayTime());
                //检查订单是否失效
                getObjectHelper.checkActivityOrderOutOfTime(activityOrder);
                if(activityOrder.getClose()){
                    object.put("status", "订单关闭");
                }else {
                    if(activityOrder.getPaymentState())
                        object.put("status", "已支付");
                    else
                        object.put("status", "未支付");
                }
                list.add(object);
            }
        }
        // TODO
        else if(type.equals("training")){
            TrainingOrderExample trainingOrderExample=new TrainingOrderExample();
            TrainingOrderExample.Criteria criteria=trainingOrderExample.createCriteria();
            if(orderNum!=null && orderNum.equals("")){
                criteria.andTrainingOrderIdEqualTo(orderNum);
            }
            criteria.andIsDeleteUserEqualTo(false).andOrderTypeEqualTo(false).andMemberKeyIdEqualTo(memberId);
            if(businessName!=null && businessName.equals("")){
                criteria.andBusinessNameLike("%"+businessName+"%");
            }
            total=trainingOrderMapper.countByExample(trainingOrderExample);
            trainingOrderExample.setOrderByClause("order_begin_time DESC");
            trainingOrderExample.setOffset((currentPage-1)*pageSize);
            trainingOrderExample.setLimit(pageSize);
            List<TrainingOrder> trainingOrders=trainingOrderMapper.selectByExample(trainingOrderExample);
            for(TrainingOrder trainingOrder:trainingOrders){
                JSONObject object=new JSONObject();
                object.put("orderNum", trainingOrder.getTrainingOrderId());
                object.put("orderType", type);
                object.put("businessName", trainingOrder.getBusinessName());
                object.put("builder", trainingOrder.getOpManName());
                object.put("price", trainingOrder.getOrderPrice());
                object.put("buildTime", trainingOrder.getOrderBeginTime());
                Date endTime=getObjectHelper.getOrderEndTime(type, trainingOrder.getOrderBeginTime());
                object.put("endTime", endTime);
                object.put("payTime", trainingOrder.getPayTime());
                getObjectHelper.checkTrainingOrderOutOfTime(trainingOrder);
                if(trainingOrder.getClose()){
                    object.put("status", "订单关闭");
                }else {
                    if(trainingOrder.getPaymentState())
                        object.put("status", "已支付");
                    else
                        object.put("status", "未支付");
                }
                list.add(object);
            }
        }
        // TODO
        else if(type.equals("cerChange")){
            CertificateChangeOrderExample orderExample=new CertificateChangeOrderExample();
            CertificateChangeOrderExample.Criteria criteria=orderExample.createCriteria();
            if(orderNum!=null && orderNum.equals("")){
                criteria.andCertificateChangeOrderIdEqualTo(orderNum);
            }
            criteria.andIsDeleteUserEqualTo(false).andOrderTypeEqualTo(false).andMemberKeyIdEqualTo(memberId);
            if(businessName!=null && businessName.equals("")){
                criteria.andBusinessNameLike("%"+businessName+"%");
            }
            total=changeOrderMapper.countByExample(orderExample);
            orderExample.setOrderByClause("order_begin_time DESC");
            orderExample.setOffset((currentPage-1)*pageSize);
            orderExample.setLimit(pageSize);
            List<CertificateChangeOrder> orders=changeOrderMapper.selectByExample(orderExample);
            for(CertificateChangeOrder order:orders){
                JSONObject object=new JSONObject();
                object.put("orderNum", order.getCertificateChangeOrderId());
                object.put("orderType", type);
                object.put("businessName", order.getBusinessName());
                object.put("builder", order.getOpManName());
                object.put("price", order.getOrderPrice());
                object.put("buildTime", order.getOrderBeginTime());
                Date endTime=getObjectHelper.getOrderEndTime(type, order.getOrderBeginTime());
                object.put("endTime", endTime);
                object.put("payTime", order.getPayTime());
                getObjectHelper.checkCerChangeOrderOutOfTime(order);
                if(order.getClose()){
                    object.put("status", "订单关闭");
                }else {
                    if(order.getPaymentState())
                        object.put("status", "已支付");
                    else
                        object.put("status", "未支付");
                }
                list.add(object);
            }
        }
        // TODO
//        else if(type.equals("cerRecheck")){
//            CertificateRecheckOrderExample orderExample=new CertificateRecheckOrderExample();
//            CertificateRecheckOrderExample.Criteria criteria=orderExample.createCriteria();
//            if(orderNum!=null && orderNum.equals("")){
//                criteria.andCertificateRecheckOrderIdEqualTo(orderNum);
//            }
//            criteria.andIsDeleteUserEqualTo(false).andOrderTypeEqualTo(false).andMemberKeyIdEqualTo(memberId);
//            if(businessName!=null && businessName.equals("")){
//                criteria.andBusinessNameLike("%"+businessName+"%");
//            }
//            total=recheckOrderMapper.countByExample(orderExample);
//            orderExample.setOrderByClause("order_begin_time DESC");
//            orderExample.setOffset((currentPage-1)*pageSize);
//            orderExample.setLimit(pageSize);
//            List<CertificateRecheckOrder> orders=recheckOrderMapper.selectByExample(orderExample);
//            for(CertificateRecheckOrder order:orders){
//                JSONObject object=new JSONObject();
//                object.put("orderNum", order.getCertificateRecheckOrderId());
//                object.put("orderType", type);
//                object.put("businessName", order.getBusinessName());
//                object.put("builder", order.getOpManName());
//                object.put("price", order.getOrderPrice());
//                object.put("buildTime", order.getOrderBeginTime());
//                Date endTime=getObjectHelper.getOrderEndTime(type, order.getOrderBeginTime());
//                object.put("endTime", endTime);
//                object.put("payTime", order.getPayTime());
//                if(!order.getClose()){
//                    if(!order.getPaymentState()){
//                        //查看未支付的订单是否过期，失效则置close为1
//                        if(endTime.compareTo(new Date())<0){
//                            order.setClose(true);
//                            recheckOrderMapper.updateByPrimaryKeySelective(order);
//                        }
//                    }
//                }
//                if(order.getClose()){
//                    object.put("status", "订单关闭");
//                }else {
//                    if(order.getPaymentState())
//                        object.put("status", "已支付");
//                    else
//                        object.put("status", "未支付");
//                }
//                list.add(object);
//            }
//        }
        // TODO
        else if(type.equals("member")) {
            MemberSubscriptionOrderExample orderExample=new MemberSubscriptionOrderExample();
            MemberSubscriptionOrderExample.Criteria criteria=orderExample.createCriteria();
            if(orderNum!=null && orderNum.equals("")){
                criteria.andSubscriptionOrderIdEqualTo(orderNum);
            }
            criteria.andIsDeleteUserEqualTo(false).andMemberKeyIdEqualTo(memberId);
            if(businessName!=null && businessName.equals("")){
                criteria.andBusinessNameLike("%"+businessName+"%");
            }
            total=memberSubscriptionOrderMapper.countByExample(orderExample);
            orderExample.setOrderByClause("create_time DESC");
            orderExample.setOffset((currentPage-1)*pageSize);
            orderExample.setLimit(pageSize);
            List<MemberSubscriptionOrder> orders=memberSubscriptionOrderMapper.selectByExample(orderExample);
            for(MemberSubscriptionOrder order:orders){
                JSONObject object=new JSONObject();
                object.put("orderNum", order.getSubscriptionOrderId());
                object.put("orderType", type);
                object.put("businessName", order.getBusinessName());
                object.put("builder", order.getOpManName());
                object.put("price", order.getFee());
                object.put("buildTime", order.getCreateTime());
                Date endTime=getObjectHelper.getOrderEndTime(type, order.getCreateTime());
                object.put("endTime", endTime);
                object.put("payTime", order.getPayTime());
                if(!order.getClose()){
                    if(!order.getPaymentState()){
                        //查看未支付的订单是否过期，失效则置close为1
                        if(endTime.compareTo(new Date())<0){
                            order.setClose(true);
                            memberSubscriptionOrderMapper.updateByPrimaryKeySelective(order);
                        }
                    }
                }
                if(order.getClose()){
                    object.put("status", "订单关闭");
                }else {
                    if(order.getPaymentState())
                        object.put("status", "已支付");
                    else
                        object.put("status", "未支付");
                }
                list.add(object);
            }
        }else {
            result.put("msg", "订单类别参数type有误");
            return result;
        }
        data.put("list", list);
        data.put("total", total);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取"+type+"类订单列表成功！");
        return result;
    }

    @Override
    public JSONObject alipay(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        String serverPort=jsonObject.getString("serverPort");
        String returnUrl=jsonObject.getString("returnUrl");
        String orderCode=jsonObject.getString("orderId");

        Object orderTypeObject=redisUtil.hget("order_no_map", orderCode);
        if(orderTypeObject==null){
            result.put("msg", "订单类型有误");
            return result;
        }
        BigDecimal fee;
        String bussinessName;
        String orderType = (String) orderTypeObject;
        Object order = getObjectHelper.getOrderByOrderCode(orderCode, orderType);
        if (orderType.equals("activity")) {
            ActivityOrder activityOrder = (ActivityOrder) order;
            if(activityOrder.getPaymentState()){
                result.put("msg", "订单已支付，请勿重复操作");
                return result;
            }
            if(getObjectHelper.checkActivityOrderOutOfTime(activityOrder)){
                result.put("msg", "订单已失效，无法进行支付");
                return result;
            }
            // TODO 学生支付订单前需要判断会员是不是已经帮自己报名了
            if(activityOrder.getOrderType()){
                Integer studentId=activityOrder.getStudentId();
                Integer activityId=activityOrder.getActivityId();
                ActivityStudentExample activityStudentExample=new ActivityStudentExample();
                activityStudentExample.createCriteria().andIsInvalidEqualTo(false)
                        .andActivityIdEqualTo(activityId).andStudentIdEqualTo(studentId);
                if(ActivityStudentSQLConn.getActivityOrderByStudentId(studentId, activityId)!=null
                                || activityStudentMapper.countByExample(activityStudentExample)>0){
                    result.put("msg", "无法支付！所属会员已为您报名或有未支付订单");
                    return result;
                }
            }

            fee=activityOrder.getOrderPrice();
            bussinessName=activityOrder.getOpManName()+"_"+activityOrder.getBusinessName();

        } else if (orderType.equals("training")) {
            TrainingOrder trainingOrder = (TrainingOrder) order;
            if(trainingOrder.getPaymentState()){
                result.put("msg", "订单已支付，请勿重复操作");
                return result;
            }
            if(getObjectHelper.checkTrainingOrderOutOfTime(trainingOrder)){
                result.put("msg", "订单已失效，无法进行支付");
                return result;
            }
            // TODO 学生支付订单前需要判断会员是不是已经帮自己报名了
            if(trainingOrder.getOrderType()){
                Integer studentId=trainingOrder.getStudentId();
                Integer trainingId=trainingOrder.getTrainingId();
                TrainingReStudentExample trainingReStudentExample=new TrainingReStudentExample();
                trainingReStudentExample.createCriteria().andIsInvalidEqualTo(false)
                        .andTrainingIdEqualTo(trainingId).andStudentIdEqualTo(studentId);
                if(TrainingStudentSQLConn.getTrainingOrderByStudentId(studentId, trainingId)!=null
                        || trainingReStudentMapper.countByExample(trainingReStudentExample)>0){
                    result.put("msg", "无法支付！所属会员已为您报名或有未支付订单");
                    return result;
                }
            }
            fee=trainingOrder.getOrderPrice();
            bussinessName=trainingOrder.getOpManName()+"_"+trainingOrder.getBusinessName();

        } else if (orderType.equals("cerChange")) {
            CertificateChangeOrder certificateChangeOrder = (CertificateChangeOrder) order;
            if(certificateChangeOrder.getPaymentState()){
                result.put("msg", "订单已支付，请勿重复操作");
                return result;
            }
            if(getObjectHelper.checkCerChangeOrderOutOfTime(certificateChangeOrder)){
                result.put("msg", "订单已失效，无法进行支付");
                return result;
            }
            fee=certificateChangeOrder.getOrderPrice();
            bussinessName=certificateChangeOrder.getOpManName()+"_"+certificateChangeOrder.getBusinessName();

        } else if (orderType.equals("cerRecheck")) {
            CertificateRecheckOrder certificateRecheckOrder = (CertificateRecheckOrder) order;
            if(certificateRecheckOrder.getPaymentState()){
                result.put("msg", "订单已支付，请勿重复操作");
                return result;
            }
            if(getObjectHelper.checkCerReCheckOrderOutOfTime(certificateRecheckOrder)){
                result.put("msg", "订单已失效，无法进行支付");
                return result;
            }
            fee=certificateRecheckOrder.getOrderPrice();
            bussinessName=certificateRecheckOrder.getOpManName()+"_"+certificateRecheckOrder.getBusinessName();

        } else if (orderType.equals("member")) {
            MemberSubscriptionOrder memberSubscriptionOrder = (MemberSubscriptionOrder) order;
            if(memberSubscriptionOrder.getPaymentState()){
                result.put("msg", "订单已支付，请勿重复操作");
                return result;
            }
            if(getObjectHelper.checkMemberSusOrderOutOfTime(memberSubscriptionOrder)){
                result.put("msg", "订单已失效，无法进行支付");
                return result;
            }
            fee=memberSubscriptionOrder.getFee();
            bussinessName=memberSubscriptionOrder.getOpManName()+"_"+memberSubscriptionOrder.getBusinessName();

        } else {
            result.put("msg", "订单类型有误");
            return result;
        }
        AlipayClient client=new DefaultAlipayClient(AlipayConfig.gatewayUrl,
                AlipayConfig.app_id, AlipayConfig.merchant_private_key, AlipayConfig.format,
                AlipayConfig.charset, AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type);
        AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request
        alipayRequest.setReturnUrl(SERVER_HOST+":"+serverPort+returnUrl);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        alipayRequest.setBizContent( "{"  +
                "    \"out_trade_no\":\""+orderCode+"\","  +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
                "    \"total_amount\":"+fee+","  +
                "    \"subject\":\""+bussinessName+"\","  +
                "    \"body\":\""+orderType+"\","  +
                "    \"extend_params\":{"  +
                "    }" +
                "}" ); //填充业务参数
        String form= "" ;
        try  {
            form = client.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        }  catch  (AlipayApiException e) {
            e.printStackTrace();
        }
        result.put("data", form);
        result.put("code", "success");
        result.put("msg", "拉起支付");
        return result;
    }

    @Override
    public void aliPayNotify(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            map.put(name, valueStr);
        }
        //验证签名
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(map, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (com.alipay.api.AlipayApiException e) {
            e.printStackTrace();
            return;
        }
        if (signVerified) {
            // TODO 处理自己的业务逻辑
            String orderCode=map.get("out_trade_no");
            String orderType=map.get("orderType");
            // 确认订单支付成功，同时更新数据库状态
            confirmOrder(orderCode, orderType);
            // TODO 插入支付业务流水表
        }

    }

    private boolean confirmOrder(String orderCode, String orderType){
        Object order = getObjectHelper.getOrderByOrderCode(orderCode, orderType);
        Date nowTime=new Date();
        if (orderType.equals("activity")) {
            ActivityOrder activityOrder = (ActivityOrder) order;
            activityOrder.setPaymentState(true); //更新订单状态
            activityOrder.setPayTime(nowTime);
            activityOrderMapper.updateByPrimaryKeySelective(activityOrder);
            ActivityOrderStudentExample example=new ActivityOrderStudentExample();
            example.createCriteria().andOrderKeyIdEqualTo(activityOrder.getOrderKeyId());
            ActivityOrderStudent activityOrderStudentRecord=new ActivityOrderStudent();
            activityOrderStudentRecord.setIsPaid(true);
            // TODO 将订单内的学生和活动插入关联表
            activityOrderStudentMapper.updateByExampleSelective(activityOrderStudentRecord, example);
            List<ActivityOrderStudent> activityOrderStudents=activityOrderStudentMapper.selectByExample(example);
            for(ActivityOrderStudent activityOrderStudent:activityOrderStudents){
                ActivityStudent activityStudent=new ActivityStudent();
                activityStudent.setApplyNumber(UUIDUtil.getActivityApplyNumber(activityOrder.getActivityId()));
                activityStudent.setStudentId(activityOrderStudent.getStudentId());
                activityStudent.setActivityId(activityOrder.getActivityId());
                activityStudent.setApplyTime(nowTime);
                activityStudentMapper.insertSelective(activityStudent);
            }

        } else if (orderType.equals("training")) {
            TrainingOrder trainingOrder = (TrainingOrder) order;
            trainingOrder.setPaymentState(true); //更新订单状态
            trainingOrder.setPayTime(nowTime);
            trainingOrderMapper.updateByPrimaryKeySelective(trainingOrder);
            // TODO 找出该培训已经发布的考试
            ExamExample examExample=new ExamExample();
            ExamExample.Criteria criteria1=examExample.createCriteria();
            criteria1.andTrainingIdEqualTo(trainingOrder.getTrainingId()).andIsPostedEqualTo(true);
            List<Exam> exams=examMapper.selectByExample(examExample);

            TrainingOrderStudentExample example=new TrainingOrderStudentExample();
            example.createCriteria().andOrderKeyIdEqualTo(trainingOrder.getOrderKeyId());
            TrainingOrderStudent trainingOrderStudentRecord=new TrainingOrderStudent();
            trainingOrderStudentRecord.setIsPaid(true);
            trainingOrderStudentMapper.updateByExampleSelective(trainingOrderStudentRecord, example);
            // TODO 将订单内的学生插入关联表
            List<TrainingOrderStudent> trainingOrderStudents=trainingOrderStudentMapper.selectByExample(example);
            for(TrainingOrderStudent trainingOrderStudent:trainingOrderStudents){
                TrainingReStudent trainingReStudent=new TrainingReStudent();
                trainingReStudent.setApplyId(UUIDUtil.getTrainingApplyNumber(trainingOrder.getTrainingId()));
                trainingReStudent.setBeginTime(nowTime);
                trainingReStudent.setStudentId(trainingOrderStudent.getStudentId());
                trainingReStudent.setTrainingId(trainingOrder.getTrainingId());
                trainingReStudentMapper.insertSelective(trainingReStudent);
                // TODO 将已发布的考试与学生建立联系
                for(Exam exam:exams){
                    ExamReStudent examReStudent=new ExamReStudent();
                    examReStudent.setStudentId(trainingOrderStudent.getStudentId());
                    examReStudent.setExamId(exam.getExamId());
                    examReStudentMapper.insertSelective(examReStudent);
                }
            }

        } else if (orderType.equals("cerChange")) {
            CertificateChangeOrder certificateChangeOrder = (CertificateChangeOrder) order;
            certificateChangeOrder.setPaymentState(true);
            certificateChangeOrder.setPayTime(nowTime);
            changeOrderMapper.updateByPrimaryKeySelective(certificateChangeOrder);
            // TODO 证书修改

        } else if (orderType.equals("cerRecheck")) {
            CertificateRecheckOrder certificateRecheckOrder = (CertificateRecheckOrder) order;
            certificateRecheckOrder.setPaymentState(true);
            certificateRecheckOrder.setPayTime(nowTime);
            recheckOrderMapper.updateByPrimaryKeySelective(certificateRecheckOrder);
            // TODO 证书复审

        } else if (orderType.equals("member")) {
            MemberSubscriptionOrder memberSubscriptionOrder = (MemberSubscriptionOrder) order;
            memberSubscriptionOrder.setPaymentState(true);
            memberSubscriptionOrder.setPayTime(nowTime);
            memberSubscriptionOrderMapper.updateByPrimaryKeySelective(memberSubscriptionOrder);
            Member member=memberMapper.selectByPrimaryKey(memberSubscriptionOrder.getMemberKeyId());
            // TODO 有效期延长一年
            Calendar cal = Calendar.getInstance();
            cal.setTime(member.getVipEndDate());
            cal.add(Calendar.YEAR, 1);
            member.setVipEndDate(cal.getTime());
            memberMapper.updateByPrimaryKeySelective(member);

        } else {
            System.out.println("订单类型错误!");
            return false;
        }
        return true;
    }
}
