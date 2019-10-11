package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.TrainingOrder;
import com.lutayy.campbackend.pojo.TrainingOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrainingOrderMapper {
    long countByExample(TrainingOrderExample example);

    int deleteByExample(TrainingOrderExample example);

    int deleteByPrimaryKey(String trainingOrderId);

    int insert(TrainingOrder record);

    int insertSelective(TrainingOrder record);

    List<TrainingOrder> selectByExample(TrainingOrderExample example);

    TrainingOrder selectByPrimaryKey(String trainingOrderId);

    int updateByExampleSelective(@Param("record") TrainingOrder record, @Param("example") TrainingOrderExample example);

    int updateByExample(@Param("record") TrainingOrder record, @Param("example") TrainingOrderExample example);

    int updateByPrimaryKeySelective(TrainingOrder record);

    int updateByPrimaryKey(TrainingOrder record);
}