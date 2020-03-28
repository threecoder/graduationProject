package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.VoteOptionMember;
import com.lutayy.campbackend.pojo.VoteOptionMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteOptionMemberMapper {
    long countByExample(VoteOptionMemberExample example);

    int deleteByExample(VoteOptionMemberExample example);

    int insert(VoteOptionMember record);

    int insertSelective(VoteOptionMember record);

    List<VoteOptionMember> selectByExample(VoteOptionMemberExample example);

    int updateByExampleSelective(@Param("record") VoteOptionMember record, @Param("example") VoteOptionMemberExample example);

    int updateByExample(@Param("record") VoteOptionMember record, @Param("example") VoteOptionMemberExample example);
}