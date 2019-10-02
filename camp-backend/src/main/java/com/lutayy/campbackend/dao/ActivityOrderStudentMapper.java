package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.ActivityOrderStudent;
import com.lutayy.campbackend.pojo.ActivityOrderStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityOrderStudentMapper {
    long countByExample(ActivityOrderStudentExample example);

    int deleteByExample(ActivityOrderStudentExample example);

    int insert(ActivityOrderStudent record);

    int insertSelective(ActivityOrderStudent record);

    List<ActivityOrderStudent> selectByExample(ActivityOrderStudentExample example);

    int updateByExampleSelective(@Param("record") ActivityOrderStudent record, @Param("example") ActivityOrderStudentExample example);

    int updateByExample(@Param("record") ActivityOrderStudent record, @Param("example") ActivityOrderStudentExample example);
}