package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.CertificateImage;
import com.lutayy.campbackend.pojo.CertificateImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertificateImageMapper {
    long countByExample(CertificateImageExample example);

    int deleteByExample(CertificateImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CertificateImage record);

    int insertSelective(CertificateImage record);

    List<CertificateImage> selectByExample(CertificateImageExample example);

    CertificateImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CertificateImage record, @Param("example") CertificateImageExample example);

    int updateByExample(@Param("record") CertificateImage record, @Param("example") CertificateImageExample example);

    int updateByPrimaryKeySelective(CertificateImage record);

    int updateByPrimaryKey(CertificateImage record);
}