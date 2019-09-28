package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.ActivityOrder;
import com.lutayy.campbackend.pojo.ActivityOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityOrderMapper {
    long countByExample(ActivityOrderExample example);

    int deleteByExample(ActivityOrderExample example);

    int deleteByPrimaryKey(String activityOrderId);

    int insert(ActivityOrder record);

    int insertSelective(ActivityOrder record);

    List<ActivityOrder> selectByExample(ActivityOrderExample example);

    ActivityOrder selectByPrimaryKey(String activityOrderId);

    int updateByExampleSelective(@Param("record") ActivityOrder record, @Param("example") ActivityOrderExample example);

    int updateByExample(@Param("record") ActivityOrder record, @Param("example") ActivityOrderExample example);

    int updateByPrimaryKeySelective(ActivityOrder record);

    int updateByPrimaryKey(ActivityOrder record);
}