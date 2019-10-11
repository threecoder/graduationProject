package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.TrainingOrderStudent;
import com.lutayy.campbackend.pojo.TrainingOrderStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrainingOrderStudentMapper {
    long countByExample(TrainingOrderStudentExample example);

    int deleteByExample(TrainingOrderStudentExample example);

    int insert(TrainingOrderStudent record);

    int insertSelective(TrainingOrderStudent record);

    List<TrainingOrderStudent> selectByExample(TrainingOrderStudentExample example);

    int updateByExampleSelective(@Param("record") TrainingOrderStudent record, @Param("example") TrainingOrderStudentExample example);

    int updateByExample(@Param("record") TrainingOrderStudent record, @Param("example") TrainingOrderStudentExample example);
}