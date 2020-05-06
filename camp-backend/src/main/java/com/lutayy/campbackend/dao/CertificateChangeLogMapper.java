package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.CertificateChangeLog;
import com.lutayy.campbackend.pojo.CertificateChangeLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertificateChangeLogMapper {
    long countByExample(CertificateChangeLogExample example);

    int deleteByExample(CertificateChangeLogExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(CertificateChangeLog record);

    int insertSelective(CertificateChangeLog record);

    List<CertificateChangeLog> selectByExample(CertificateChangeLogExample example);

    CertificateChangeLog selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("record") CertificateChangeLog record, @Param("example") CertificateChangeLogExample example);

    int updateByExample(@Param("record") CertificateChangeLog record, @Param("example") CertificateChangeLogExample example);

    int updateByPrimaryKeySelective(CertificateChangeLog record);

    int updateByPrimaryKey(CertificateChangeLog record);
}