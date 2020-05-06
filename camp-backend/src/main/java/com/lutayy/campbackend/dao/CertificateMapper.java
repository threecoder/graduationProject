package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.Certificate;
import com.lutayy.campbackend.pojo.CertificateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertificateMapper {
    long countByExample(CertificateExample example);

    int deleteByExample(CertificateExample example);

    int deleteByPrimaryKey(Integer certificateId);

    int insert(Certificate record);

    int insertSelective(Certificate record);

    List<Certificate> selectByExample(CertificateExample example);

    Certificate selectByPrimaryKey(Integer certificateId);

    int updateByExampleSelective(@Param("record") Certificate record, @Param("example") CertificateExample example);

    int updateByExample(@Param("record") Certificate record, @Param("example") CertificateExample example);

    int updateByPrimaryKeySelective(Certificate record);

    int updateByPrimaryKey(Certificate record);
}