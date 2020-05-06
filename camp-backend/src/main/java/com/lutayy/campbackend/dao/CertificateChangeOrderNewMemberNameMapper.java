package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.CertificateChangeOrderNewMemberName;
import com.lutayy.campbackend.pojo.CertificateChangeOrderNewMemberNameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertificateChangeOrderNewMemberNameMapper {
    long countByExample(CertificateChangeOrderNewMemberNameExample example);

    int deleteByExample(CertificateChangeOrderNewMemberNameExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CertificateChangeOrderNewMemberName record);

    int insertSelective(CertificateChangeOrderNewMemberName record);

    List<CertificateChangeOrderNewMemberName> selectByExample(CertificateChangeOrderNewMemberNameExample example);

    CertificateChangeOrderNewMemberName selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CertificateChangeOrderNewMemberName record, @Param("example") CertificateChangeOrderNewMemberNameExample example);

    int updateByExample(@Param("record") CertificateChangeOrderNewMemberName record, @Param("example") CertificateChangeOrderNewMemberNameExample example);

    int updateByPrimaryKeySelective(CertificateChangeOrderNewMemberName record);

    int updateByPrimaryKey(CertificateChangeOrderNewMemberName record);
}