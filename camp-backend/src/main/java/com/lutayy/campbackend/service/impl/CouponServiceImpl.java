package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.config.AuthorityParam;
import com.lutayy.campbackend.common.util.RedisUtil;
import com.lutayy.campbackend.common.util.UUIDUtil;
import com.lutayy.campbackend.dao.CouponMapper;
import com.lutayy.campbackend.dao.CouponMemberMapper;
import com.lutayy.campbackend.dao.MemberMapper;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.CouponService;
import com.lutayy.campbackend.service.SQLConn.CouponMemberSQLConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    MemberMapper memberMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    CouponMemberMapper couponMemberMapper;
    @Autowired
    GetObjectHelper getObjectHelper;
    @Resource
    private RedisUtil redisUtil;

    //管理员新建优惠券
    @Override
    public JSONObject addCoupon(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        //权限检查
        Integer opAdminId=jsonObject.getInteger("id");
        if(!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.COUPON)){
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }

        String name=jsonObject.getString("name");
        BigDecimal value=jsonObject.getBigDecimal("value");
        Date startTime=jsonObject.getDate("startTime");
        Date endTime=jsonObject.getDate("endTime");
        Coupon coupon=new Coupon();
        coupon.setAmountDiscount(value);
        coupon.setCouponName(name);
        coupon.setStartTime(startTime);
        coupon.setEndTime(endTime);
        coupon.setCreateTime(new Date());
        if(couponMapper.insertSelective(coupon)>0){
            result.put("code", "success");
            result.put("msg", "新增优惠券成功！");
        }else {
            result.put("code", "fail");
            result.put("msg", "新增优惠券失败！系统繁忙，请重试");
        }
        return result;
    }

    @Override
    public JSONObject deleteCoupon(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        //权限检查
        Integer opAdminId=jsonObject.getInteger("id");
        if(!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.COUPON)){
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }

        Integer couponId=jsonObject.getInteger("couponId");
        Coupon coupon=couponMapper.selectByPrimaryKey(couponId);
        if(coupon==null || coupon.getIsInvalid()){
            result.put("msg", "系统中无该优惠券，或已被删除！");
            return result;
        }
        coupon.setIsInvalid(true);
        couponMapper.updateByPrimaryKeySelective(coupon);

        CouponMemberExample couponMemberExample=new CouponMemberExample();
        couponMemberExample.createCriteria().andCouponIdEqualTo(couponId).andStateEqualTo(false).andIsInvalidEqualTo(false);
        CouponMember record=new CouponMember();
        record.setIsInvalid(true);
        couponMemberMapper.updateByExampleSelective(record, couponMemberExample);

        result.put("code", "success");
        result.put("msg", "删除优惠券成功！");
        return result;
    }

    @Override
    public JSONObject getCouponList(Integer pageSize, Integer currentPage, String name,
                                    Double value, String startTime, String endTime) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        JSONObject data=new JSONObject();
        result.put("data", data);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CouponExample couponExample=new CouponExample();
        CouponExample.Criteria criteria=couponExample.createCriteria();
        criteria.andIsInvalidEqualTo(false);
        if(name!=null && !name.equals("")){
            criteria.andCouponNameLike("%"+name+"%");
        }
        if(value!=null){
            criteria.andAmountDiscountEqualTo(BigDecimal.valueOf(value));
        }

        if(endTime!=null){
            try {
                Date endDate=simpleDateFormat.parse(endTime);
//                Calendar cal=Calendar.getInstance();
//                cal.setTime(endDate);
//                cal.add(Calendar.MONTH, 1);
                criteria.andEndTimeGreaterThanOrEqualTo(endDate);
            }catch (Exception e){
                result.put("msg", "时间格式错误，请检查！");
                return result;
            }
        }
        if(startTime!=null){
            try {
                Date startDate=simpleDateFormat.parse(startTime);
//                Calendar cal=Calendar.getInstance();
//                cal.setTime(startDate);
//                cal.add(Calendar.MONTH, 1);
                criteria.andStartTimeLessThanOrEqualTo(startDate);
            }catch (Exception e){
                result.put("msg", "时间格式错误，请检查！");
                return result;
            }
        }
        long total=couponMapper.countByExample(couponExample);
        couponExample.setOffset((currentPage-1)*pageSize);
        couponExample.setLimit(pageSize);
        List<Coupon> coupons=couponMapper.selectByExample(couponExample);
        JSONArray list=new JSONArray();
        for(Coupon coupon:coupons){
            JSONObject object=new JSONObject();
            object.put("id", coupon.getCouponId());
            object.put("name", coupon.getCouponName());
            object.put("buildTime", coupon.getCreateTime());
            object.put("startTime", coupon.getStartTime());
            object.put("endTime", coupon.getEndTime());
            object.put("value", coupon.getAmountDiscount());
            list.add(object);
        }
        data.put("data", list);
        data.put("total", total);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "优惠券列表获取成功！");
        return result;
    }

    //发放优惠券给所有会员(已缴费)
    @Override
    public JSONObject allGrantCoupon(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        //权限检查
        Integer opAdminId=jsonObject.getInteger("id");
        if(!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.COUPON)){
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }

        Integer couponId=jsonObject.getInteger("couponId");
        Coupon coupon=couponMapper.selectByPrimaryKey(couponId);
        if(coupon==null || coupon.getIsInvalid()){
            result.put("msg", "系统中无此优惠券！");
            return result;
        }
        MemberExample memberExample=new MemberExample();
        memberExample.createCriteria().andIsVipEqualTo(true);
        List<Member> members=memberMapper.selectByExample(memberExample);
        for(Member member:members){
            CouponMember couponMember=new CouponMember();
            couponMember.setCouponId(couponId);
            couponMember.setMemberKeyId(member.getMemberKeyId());
            couponMember.setSendTime(new Date());
            String couponCode= UUIDUtil.createCoupenCode(couponId);
            while (redisUtil.sSet("coupon_code_set", couponCode)<=0) {
                couponCode= UUIDUtil.createCoupenCode(couponId);
            }
            couponMember.setCouponCode(couponCode);
            while(couponMemberMapper.insertSelective(couponMember)<=0){}
        }
        result.put("code", "success");
        result.put("msg", "优惠券发放成功！");
        return result;
    }

    //发放优惠券给指定会员（多个）
    @Override
    public JSONObject grantCoupon(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        //权限检查
        Integer opAdminId=jsonObject.getInteger("id");
        if(!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.COUPON)){
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }

        Integer couponId=jsonObject.getInteger("couponId");
        Coupon coupon=couponMapper.selectByPrimaryKey(couponId);
        if(coupon==null || coupon.getIsInvalid()){
            result.put("msg", "系统中无此优惠券！");
            return result;
        }
        JSONArray memberIds=jsonObject.getJSONArray("memberIds");
        for(int i=0;i<memberIds.size();i++){
            Integer memberId=memberIds.getInteger(i);
            Member member=memberMapper.selectByPrimaryKey(memberId);
            if(member==null)
                continue;
            CouponMember couponMember=new CouponMember();
            couponMember.setCouponId(couponId);
            couponMember.setMemberKeyId(member.getMemberKeyId());
            couponMember.setSendTime(new Date());
            String couponCode= UUIDUtil.createCoupenCode(couponId);
            while (redisUtil.sSet("coupon_code_set", couponCode)<=0) {
                couponCode= UUIDUtil.createCoupenCode(couponId);
            }
            couponMember.setCouponCode(couponCode);
            while(couponMemberMapper.insertSelective(couponMember)<=0){}
        }
        result.put("code", "success");
        result.put("msg", "优惠券发放成功！");
        return result;
    }

    //根据优惠券获取相关会员列表
    @Override
    public JSONObject couponGetMemberList(Integer couponId, Integer pageSize, Integer currentPage, Integer memberKeyId, String memberName) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();
        result.put("data", data);
        result.put("code", "fail");
        Coupon coupon=couponMapper.selectByPrimaryKey(couponId);
        if(coupon==null){
            result.put("msg", "该优惠券不存在");
            return result;
        }

        MemberExample memberExample=new MemberExample();
        MemberExample.Criteria criteria=memberExample.createCriteria();
        if(memberKeyId!=null)
            criteria.andMemberKeyIdEqualTo(memberKeyId);
        if(memberName!=null && !memberName.equals(""))
            criteria.andMemberNameLike("%"+memberName+"%");
        long total=memberMapper.countByExample(memberExample);
        memberExample.setOffset((currentPage-1)*pageSize);
        memberExample.setLimit(pageSize);
        List<Member> members=memberMapper.selectByExample(memberExample);
        JSONArray list=new JSONArray();
        for(Member member:members){
            JSONObject object=new JSONObject();
            object.put("id", member.getMemberKeyId());
            object.put("name", member.getMemberName());
            object.put("phone", member.getMemberPhone());
            object.put("email", member.getMemberEmail());
            CouponMemberExample example=new CouponMemberExample();
            example.createCriteria().andIsInvalidEqualTo(false)
                    .andMemberKeyIdEqualTo(member.getMemberKeyId()).andCouponIdEqualTo(couponId);
            object.put("num", couponMemberMapper.countByExample(example));
            list.add(object);
        }

        data.put("total", total);
        data.put("data", list);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取会员列表成功！");
        return result;
    }

    //会员获取自己有的优惠券
    @Override
    public JSONObject memberGetCouponList(Integer memberId, Integer pageSize, Integer currentPage) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();
        JSONArray list=new JSONArray();
        CouponMemberExample couponMemberExample=new CouponMemberExample();
        couponMemberExample.createCriteria().andIsInvalidEqualTo(false).andMemberKeyIdEqualTo(memberId);
        long total=couponMemberMapper.countByExample(couponMemberExample);
        couponMemberExample.setOrderByClause("send_time DESC");
        couponMemberExample.setOffset((currentPage-1)*pageSize);
        couponMemberExample.setLimit(pageSize);
        List<CouponMember> couponMembers=couponMemberMapper.selectByExample(couponMemberExample);
        for(CouponMember couponMember:couponMembers){
            JSONObject object=new JSONObject();
            Coupon coupon=couponMapper.selectByPrimaryKey(couponMember.getCouponId());
            object.put("couponId", couponMember.getId());
            object.put("code", couponMember.getCouponCode());
            object.put("name", coupon.getCouponName());
            object.put("startTime", coupon.getStartTime());
            object.put("endTime", coupon.getEndTime());
            object.put("value", coupon.getAmountDiscount());
            list.add(object);
        }
        data.put("total", total);
        data.put("data", list);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取优惠券列表成功！");
        return result;
    }
}
