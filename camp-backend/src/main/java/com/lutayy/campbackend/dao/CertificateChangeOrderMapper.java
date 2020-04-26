package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.CertificateChangeOrder;
import com.lutayy.campbackend.pojo.CertificateChangeOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertificateChangeOrderMapper {
    long countByExample(CertificateChangeOrderExample example);

    int deleteByExample(CertificateChangeOrderExample example);

    int deleteByPrimaryKey(Integer orderKeyId);

    int insert(CertificateChangeOrder record);

    int insertSelective(CertificateChangeOrder record);

    List<CertificateChangeOrder> selectByExample(CertificateChangeOrderExample example);

    CertificateChangeOrder selectByPrimaryKey(Integer orderKeyId);

    int updateByExampleSelective(@Param("record") CertificateChangeOrder record, @Param("example") CertificateChangeOrderExample example);

    int updateByExample(@Param("record") CertificateChangeOrder record, @Param("example") CertificateChangeOrderExample example);

    int updateByPrimaryKeySelective(CertificateChangeOrder record);

    int updateByPrimaryKey(CertificateChangeOrder record);
}