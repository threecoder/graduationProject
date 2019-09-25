package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.ExamReQuestion;
import com.lutayy.campbackend.pojo.ExamReQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamReQuestionMapper {
    long countByExample(ExamReQuestionExample example);

    int deleteByExample(ExamReQuestionExample example);

    int deleteByPrimaryKey(@Param("examId") Integer examId, @Param("questionId") Integer questionId);

    int insert(ExamReQuestion record);

    int insertSelective(ExamReQuestion record);

    List<ExamReQuestion> selectByExample(ExamReQuestionExample example);

    ExamReQuestion selectByPrimaryKey(@Param("examId") Integer examId, @Param("questionId") Integer questionId);

    int updateByExampleSelective(@Param("record") ExamReQuestion record, @Param("example") ExamReQuestionExample example);

    int updateByExample(@Param("record") ExamReQuestion record, @Param("example") ExamReQuestionExample example);

    int updateByPrimaryKeySelective(ExamReQuestion record);

    int updateByPrimaryKey(ExamReQuestion record);
}