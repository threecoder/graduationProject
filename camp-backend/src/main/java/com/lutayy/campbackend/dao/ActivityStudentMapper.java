package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.ActivityStudent;
import com.lutayy.campbackend.pojo.ActivityStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityStudentMapper {
    long countByExample(ActivityStudentExample example);

    int deleteByExample(ActivityStudentExample example);

    int insert(ActivityStudent record);

    int insertSelective(ActivityStudent record);

    List<ActivityStudent> selectByExample(ActivityStudentExample example);

    int updateByExampleSelective(@Param("record") ActivityStudent record, @Param("example") ActivityStudentExample example);

    int updateByExample(@Param("record") ActivityStudent record, @Param("example") ActivityStudentExample example);
}