package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.Training;
import com.lutayy.campbackend.pojo.TrainingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrainingMapper {
    long countByExample(TrainingExample example);

    int deleteByExample(TrainingExample example);

    int deleteByPrimaryKey(Integer trainingId);

    int insert(Training record);

    int insertSelective(Training record);

    List<Training> selectByExample(TrainingExample example);

    Training selectByPrimaryKey(Integer trainingId);

    int updateByExampleSelective(@Param("record") Training record, @Param("example") TrainingExample example);

    int updateByExample(@Param("record") Training record, @Param("example") TrainingExample example);

    int updateByPrimaryKeySelective(Training record);

    int updateByPrimaryKey(Training record);
}