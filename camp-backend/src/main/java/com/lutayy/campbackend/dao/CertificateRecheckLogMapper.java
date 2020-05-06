package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.CertificateRecheckLog;
import com.lutayy.campbackend.pojo.CertificateRecheckLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertificateRecheckLogMapper {
    long countByExample(CertificateRecheckLogExample example);

    int deleteByExample(CertificateRecheckLogExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(CertificateRecheckLog record);

    int insertSelective(CertificateRecheckLog record);

    List<CertificateRecheckLog> selectByExample(CertificateRecheckLogExample example);

    CertificateRecheckLog selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("record") CertificateRecheckLog record, @Param("example") CertificateRecheckLogExample example);

    int updateByExample(@Param("record") CertificateRecheckLog record, @Param("example") CertificateRecheckLogExample example);

    int updateByPrimaryKeySelective(CertificateRecheckLog record);

    int updateByPrimaryKey(CertificateRecheckLog record);
}