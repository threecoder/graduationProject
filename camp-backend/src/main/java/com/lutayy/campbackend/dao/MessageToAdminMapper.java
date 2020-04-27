package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.MessageToAdmin;
import com.lutayy.campbackend.pojo.MessageToAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageToAdminMapper {
    long countByExample(MessageToAdminExample example);

    int deleteByExample(MessageToAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MessageToAdmin record);

    int insertSelective(MessageToAdmin record);

    List<MessageToAdmin> selectByExample(MessageToAdminExample example);

    MessageToAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MessageToAdmin record, @Param("example") MessageToAdminExample example);

    int updateByExample(@Param("record") MessageToAdmin record, @Param("example") MessageToAdminExample example);

    int updateByPrimaryKeySelective(MessageToAdmin record);

    int updateByPrimaryKey(MessageToAdmin record);
}