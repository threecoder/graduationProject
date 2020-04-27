package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.CertificateRecheckOrder;
import com.lutayy.campbackend.pojo.CertificateRecheckOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertificateRecheckOrderMapper {
    long countByExample(CertificateRecheckOrderExample example);

    int deleteByExample(CertificateRecheckOrderExample example);

    int deleteByPrimaryKey(Integer orderKeyId);

    int insert(CertificateRecheckOrder record);

    int insertSelective(CertificateRecheckOrder record);

    List<CertificateRecheckOrder> selectByExample(CertificateRecheckOrderExample example);

    CertificateRecheckOrder selectByPrimaryKey(Integer orderKeyId);

    int updateByExampleSelective(@Param("record") CertificateRecheckOrder record, @Param("example") CertificateRecheckOrderExample example);

    int updateByExample(@Param("record") CertificateRecheckOrder record, @Param("example") CertificateRecheckOrderExample example);

    int updateByPrimaryKeySelective(CertificateRecheckOrder record);

    int updateByPrimaryKey(CertificateRecheckOrder record);
}