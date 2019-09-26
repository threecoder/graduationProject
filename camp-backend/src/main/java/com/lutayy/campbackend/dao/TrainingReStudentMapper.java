package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.TrainingReStudent;
import com.lutayy.campbackend.pojo.TrainingReStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrainingReStudentMapper {
    long countByExample(TrainingReStudentExample example);

    int deleteByExample(TrainingReStudentExample example);

    int insert(TrainingReStudent record);

    int insertSelective(TrainingReStudent record);

    List<TrainingReStudent> selectByExample(TrainingReStudentExample example);

    int updateByExampleSelective(@Param("record") TrainingReStudent record, @Param("example") TrainingReStudentExample example);

    int updateByExample(@Param("record") TrainingReStudent record, @Param("example") TrainingReStudentExample example);
}