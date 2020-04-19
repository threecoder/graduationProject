package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.ActivitySeat;
import com.lutayy.campbackend.pojo.ActivitySeatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivitySeatMapper {
    long countByExample(ActivitySeatExample example);

    int deleteByExample(ActivitySeatExample example);

    int deleteByPrimaryKey(Integer seatId);

    int insert(ActivitySeat record);

    int insertSelective(ActivitySeat record);

    List<ActivitySeat> selectByExample(ActivitySeatExample example);

    ActivitySeat selectByPrimaryKey(Integer seatId);

    int updateByExampleSelective(@Param("record") ActivitySeat record, @Param("example") ActivitySeatExample example);

    int updateByExample(@Param("record") ActivitySeat record, @Param("example") ActivitySeatExample example);

    int updateByPrimaryKeySelective(ActivitySeat record);

    int updateByPrimaryKey(ActivitySeat record);
}