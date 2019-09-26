package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.ExamReStudent;
import com.lutayy.campbackend.pojo.ExamReStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamReStudentMapper {
    long countByExample(ExamReStudentExample example);

    int deleteByExample(ExamReStudentExample example);

    int insert(ExamReStudent record);

    int insertSelective(ExamReStudent record);

    List<ExamReStudent> selectByExample(ExamReStudentExample example);

    int updateByExampleSelective(@Param("record") ExamReStudent record, @Param("example") ExamReStudentExample example);

    int updateByExample(@Param("record") ExamReStudent record, @Param("example") ExamReStudentExample example);
}