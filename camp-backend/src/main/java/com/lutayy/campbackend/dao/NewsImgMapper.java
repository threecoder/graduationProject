package com.lutayy.campbackend.dao;

import com.lutayy.campbackend.pojo.NewsImg;
import com.lutayy.campbackend.pojo.NewsImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsImgMapper {
    long countByExample(NewsImgExample example);

    int deleteByExample(NewsImgExample example);

    int deleteByPrimaryKey(Integer imgId);

    int insert(NewsImg record);

    int insertSelective(NewsImg record);

    List<NewsImg> selectByExample(NewsImgExample example);

    NewsImg selectByPrimaryKey(Integer imgId);

    int updateByExampleSelective(@Param("record") NewsImg record, @Param("example") NewsImgExample example);

    int updateByExample(@Param("record") NewsImg record, @Param("example") NewsImgExample example);

    int updateByPrimaryKeySelective(NewsImg record);

    int updateByPrimaryKey(NewsImg record);
}