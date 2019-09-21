package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.ExamReQuestion;
import com.lutayy.campbackend.pojo.ExamReQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamReQuestionMapper {
    long countByExample(ExamReQuestionExample example);

    int deleteByExample(ExamReQuestionExample example);

    int insert(ExamReQuestion record);

    int insertSelective(ExamReQuestion record);

    List<ExamReQuestion> selectByExample(ExamReQuestionExample example);

    int updateByExampleSelective(@Param("record") ExamReQuestion record, @Param("example") ExamReQuestionExample example);

    int updateByExample(@Param("record") ExamReQuestion record, @Param("example") ExamReQuestionExample example);
}