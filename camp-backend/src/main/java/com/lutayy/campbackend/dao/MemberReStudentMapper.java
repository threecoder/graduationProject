package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.MemberReStudent;
import com.lutayy.campbackend.pojo.MemberReStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberReStudentMapper {
    long countByExample(MemberReStudentExample example);

    int deleteByExample(MemberReStudentExample example);

    int insert(MemberReStudent record);

    int insertSelective(MemberReStudent record);

    List<MemberReStudent> selectByExample(MemberReStudentExample example);

    int updateByExampleSelective(@Param("record") MemberReStudent record, @Param("example") MemberReStudentExample example);

    int updateByExample(@Param("record") MemberReStudent record, @Param("example") MemberReStudentExample example);
}