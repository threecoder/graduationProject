package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.ExamQuestionStudentAnswer;
import com.lutayy.campbackend.pojo.ExamQuestionStudentAnswerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamQuestionStudentAnswerMapper {
    long countByExample(ExamQuestionStudentAnswerExample example);

    int deleteByExample(ExamQuestionStudentAnswerExample example);

    int deleteByPrimaryKey(@Param("examId") Integer examId, @Param("questionId") Integer questionId, @Param("studentId") Integer studentId);

    int insert(ExamQuestionStudentAnswer record);

    int insertSelective(ExamQuestionStudentAnswer record);

    List<ExamQuestionStudentAnswer> selectByExample(ExamQuestionStudentAnswerExample example);

    ExamQuestionStudentAnswer selectByPrimaryKey(@Param("examId") Integer examId, @Param("questionId") Integer questionId, @Param("studentId") Integer studentId);

    int updateByExampleSelective(@Param("record") ExamQuestionStudentAnswer record, @Param("example") ExamQuestionStudentAnswerExample example);

    int updateByExample(@Param("record") ExamQuestionStudentAnswer record, @Param("example") ExamQuestionStudentAnswerExample example);

    int updateByPrimaryKeySelective(ExamQuestionStudentAnswer record);

    int updateByPrimaryKey(ExamQuestionStudentAnswer record);
}