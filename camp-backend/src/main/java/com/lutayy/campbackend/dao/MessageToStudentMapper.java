package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.MessageToStudent;
import com.lutayy.campbackend.pojo.MessageToStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageToStudentMapper {
    long countByExample(MessageToStudentExample example);

    int deleteByExample(MessageToStudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MessageToStudent record);

    int insertSelective(MessageToStudent record);

    List<MessageToStudent> selectByExample(MessageToStudentExample example);

    MessageToStudent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MessageToStudent record, @Param("example") MessageToStudentExample example);

    int updateByExample(@Param("record") MessageToStudent record, @Param("example") MessageToStudentExample example);

    int updateByPrimaryKeySelective(MessageToStudent record);

    int updateByPrimaryKey(MessageToStudent record);
}