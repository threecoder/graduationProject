package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.MessageText;
import com.lutayy.campbackend.pojo.MessageTextExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageTextMapper {
    long countByExample(MessageTextExample example);

    int deleteByExample(MessageTextExample example);

    int deleteByPrimaryKey(Integer messageId);

    int insert(MessageText record);

    int insertSelective(MessageText record);

    List<MessageText> selectByExampleWithBLOBs(MessageTextExample example);

    List<MessageText> selectByExample(MessageTextExample example);

    MessageText selectByPrimaryKey(Integer messageId);

    int updateByExampleSelective(@Param("record") MessageText record, @Param("example") MessageTextExample example);

    int updateByExampleWithBLOBs(@Param("record") MessageText record, @Param("example") MessageTextExample example);

    int updateByExample(@Param("record") MessageText record, @Param("example") MessageTextExample example);

    int updateByPrimaryKeySelective(MessageText record);

    int updateByPrimaryKeyWithBLOBs(MessageText record);

    int updateByPrimaryKey(MessageText record);
}