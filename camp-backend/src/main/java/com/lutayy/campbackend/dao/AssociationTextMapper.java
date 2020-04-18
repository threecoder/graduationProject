package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.AssociationText;
import com.lutayy.campbackend.pojo.AssociationTextExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssociationTextMapper {
    long countByExample(AssociationTextExample example);

    int deleteByExample(AssociationTextExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AssociationText record);

    int insertSelective(AssociationText record);

    List<AssociationText> selectByExample(AssociationTextExample example);

    AssociationText selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AssociationText record, @Param("example") AssociationTextExample example);

    int updateByExample(@Param("record") AssociationText record, @Param("example") AssociationTextExample example);

    int updateByPrimaryKeySelective(AssociationText record);

    int updateByPrimaryKey(AssociationText record);
}