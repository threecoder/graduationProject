package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.VoteOption;
import com.lutayy.campbackend.pojo.VoteOptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteOptionMapper {
    long countByExample(VoteOptionExample example);

    int deleteByExample(VoteOptionExample example);

    int deleteByPrimaryKey(Integer optionId);

    int insert(VoteOption record);

    int insertSelective(VoteOption record);

    List<VoteOption> selectByExample(VoteOptionExample example);

    VoteOption selectByPrimaryKey(Integer optionId);

    int updateByExampleSelective(@Param("record") VoteOption record, @Param("example") VoteOptionExample example);

    int updateByExample(@Param("record") VoteOption record, @Param("example") VoteOptionExample example);

    int updateByPrimaryKeySelective(VoteOption record);

    int updateByPrimaryKey(VoteOption record);
}