package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.VoteStudentMember;
import com.lutayy.campbackend.pojo.VoteStudentMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteStudentMemberMapper {
    long countByExample(VoteStudentMemberExample example);

    int deleteByExample(VoteStudentMemberExample example);

    int insert(VoteStudentMember record);

    int insertSelective(VoteStudentMember record);

    List<VoteStudentMember> selectByExample(VoteStudentMemberExample example);

    int updateByExampleSelective(@Param("record") VoteStudentMember record, @Param("example") VoteStudentMemberExample example);

    int updateByExample(@Param("record") VoteStudentMember record, @Param("example") VoteStudentMemberExample example);
}