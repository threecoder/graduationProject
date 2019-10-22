package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.ExamReStudent;
import com.lutayy.campbackend.pojo.ExamReStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamReStudentMapper {
    long countByExample(ExamReStudentExample example);

    int deleteByExample(ExamReStudentExample example);

    int deleteByPrimaryKey(Integer reportId);

    int insert(ExamReStudent record);

    int insertSelective(ExamReStudent record);

    List<ExamReStudent> selectByExample(ExamReStudentExample example);

    ExamReStudent selectByPrimaryKey(Integer reportId);

    int updateByExampleSelective(@Param("record") ExamReStudent record, @Param("example") ExamReStudentExample example);

    int updateByExample(@Param("record") ExamReStudent record, @Param("example") ExamReStudentExample example);

    int updateByPrimaryKeySelective(ExamReStudent record);

    int updateByPrimaryKey(ExamReStudent record);
}