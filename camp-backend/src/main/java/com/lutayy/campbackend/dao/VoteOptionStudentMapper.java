package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.VoteOptionStudent;
import com.lutayy.campbackend.pojo.VoteOptionStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteOptionStudentMapper {
    long countByExample(VoteOptionStudentExample example);

    int deleteByExample(VoteOptionStudentExample example);

    int insert(VoteOptionStudent record);

    int insertSelective(VoteOptionStudent record);

    List<VoteOptionStudent> selectByExample(VoteOptionStudentExample example);

    int updateByExampleSelective(@Param("record") VoteOptionStudent record, @Param("example") VoteOptionStudentExample example);

    int updateByExample(@Param("record") VoteOptionStudent record, @Param("example") VoteOptionStudentExample example);
}