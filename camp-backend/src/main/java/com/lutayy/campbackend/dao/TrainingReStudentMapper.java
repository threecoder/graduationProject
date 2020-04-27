package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.TrainingReStudent;
import com.lutayy.campbackend.pojo.TrainingReStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrainingReStudentMapper {
    long countByExample(TrainingReStudentExample example);

    int deleteByExample(TrainingReStudentExample example);

    int deleteByPrimaryKey(Integer reId);

    int insert(TrainingReStudent record);

    int insertSelective(TrainingReStudent record);

    List<TrainingReStudent> selectByExample(TrainingReStudentExample example);

    TrainingReStudent selectByPrimaryKey(Integer reId);

    int updateByExampleSelective(@Param("record") TrainingReStudent record, @Param("example") TrainingReStudentExample example);

    int updateByExample(@Param("record") TrainingReStudent record, @Param("example") TrainingReStudentExample example);

    int updateByPrimaryKeySelective(TrainingReStudent record);

    int updateByPrimaryKey(TrainingReStudent record);
}