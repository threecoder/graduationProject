package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.Question;
import com.lutayy.campbackend.pojo.QuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionMapper {
    long countByExample(QuestionExample example);

    int deleteByExample(QuestionExample example);

    int deleteByPrimaryKey(Integer questionId);

    int insert(Question record);

    int insertSelective(Question record);

    List<Question> selectByExample(QuestionExample example);

    Question selectByPrimaryKey(Integer questionId);

    int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByExample(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);
}