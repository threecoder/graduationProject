package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.MemberSubscriptionOrder;
import com.lutayy.campbackend.pojo.MemberSubscriptionOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberSubscriptionOrderMapper {
    long countByExample(MemberSubscriptionOrderExample example);

    int deleteByExample(MemberSubscriptionOrderExample example);

    int deleteByPrimaryKey(Integer orderKeyId);

    int insert(MemberSubscriptionOrder record);

    int insertSelective(MemberSubscriptionOrder record);

    List<MemberSubscriptionOrder> selectByExample(MemberSubscriptionOrderExample example);

    MemberSubscriptionOrder selectByPrimaryKey(Integer orderKeyId);

    int updateByExampleSelective(@Param("record") MemberSubscriptionOrder record, @Param("example") MemberSubscriptionOrderExample example);

    int updateByExample(@Param("record") MemberSubscriptionOrder record, @Param("example") MemberSubscriptionOrderExample example);

    int updateByPrimaryKeySelective(MemberSubscriptionOrder record);

    int updateByPrimaryKey(MemberSubscriptionOrder record);
}