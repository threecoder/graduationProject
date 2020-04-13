package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.ExamReportOpLog;
import com.lutayy.campbackend.pojo.ExamReportOpLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamReportOpLogMapper {
    long countByExample(ExamReportOpLogExample example);

    int deleteByExample(ExamReportOpLogExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(ExamReportOpLog record);

    int insertSelective(ExamReportOpLog record);

    List<ExamReportOpLog> selectByExample(ExamReportOpLogExample example);

    ExamReportOpLog selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("record") ExamReportOpLog record, @Param("example") ExamReportOpLogExample example);

    int updateByExample(@Param("record") ExamReportOpLog record, @Param("example") ExamReportOpLogExample example);

    int updateByPrimaryKeySelective(ExamReportOpLog record);

    int updateByPrimaryKey(ExamReportOpLog record);
}