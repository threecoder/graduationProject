package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.AdminReAuthority;
import com.lutayy.campbackend.pojo.AdminReAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminReAuthorityMapper {
    long countByExample(AdminReAuthorityExample example);

    int deleteByExample(AdminReAuthorityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminReAuthority record);

    int insertSelective(AdminReAuthority record);

    List<AdminReAuthority> selectByExample(AdminReAuthorityExample example);

    AdminReAuthority selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminReAuthority record, @Param("example") AdminReAuthorityExample example);

    int updateByExample(@Param("record") AdminReAuthority record, @Param("example") AdminReAuthorityExample example);

    int updateByPrimaryKeySelective(AdminReAuthority record);

    int updateByPrimaryKey(AdminReAuthority record);
}