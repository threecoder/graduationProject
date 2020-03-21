package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.MessageToMember;
import com.lutayy.campbackend.pojo.MessageToMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageToMemberMapper {
    long countByExample(MessageToMemberExample example);

    int deleteByExample(MessageToMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MessageToMember record);

    int insertSelective(MessageToMember record);

    List<MessageToMember> selectByExample(MessageToMemberExample example);

    MessageToMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MessageToMember record, @Param("example") MessageToMemberExample example);

    int updateByExample(@Param("record") MessageToMember record, @Param("example") MessageToMemberExample example);

    int updateByPrimaryKeySelective(MessageToMember record);

    int updateByPrimaryKey(MessageToMember record);
}