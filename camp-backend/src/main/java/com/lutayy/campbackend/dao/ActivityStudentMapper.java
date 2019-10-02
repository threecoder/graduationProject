package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.ActivityStudent;
import com.lutayy.campbackend.pojo.ActivityStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityStudentMapper {
    long countByExample(ActivityStudentExample example);

    int deleteByExample(ActivityStudentExample example);

    int deleteByPrimaryKey(String applyNumber);

    int insert(ActivityStudent record);

    int insertSelective(ActivityStudent record);

    List<ActivityStudent> selectByExample(ActivityStudentExample example);

    ActivityStudent selectByPrimaryKey(String applyNumber);

    int updateByExampleSelective(@Param("record") ActivityStudent record, @Param("example") ActivityStudentExample example);

    int updateByExample(@Param("record") ActivityStudent record, @Param("example") ActivityStudentExample example);

    int updateByPrimaryKeySelective(ActivityStudent record);

    int updateByPrimaryKey(ActivityStudent record);
}