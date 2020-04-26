package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.CouponMember;
import com.lutayy.campbackend.pojo.CouponMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponMemberMapper {
    long countByExample(CouponMemberExample example);

    int deleteByExample(CouponMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponMember record);

    int insertSelective(CouponMember record);

    List<CouponMember> selectByExample(CouponMemberExample example);

    CouponMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponMember record, @Param("example") CouponMemberExample example);

    int updateByExample(@Param("record") CouponMember record, @Param("example") CouponMemberExample example);

    int updateByPrimaryKeySelective(CouponMember record);

    int updateByPrimaryKey(CouponMember record);
}