package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface NewsService {
    //管理员修改协会架构/简介/章程
    JSONObject modifyAssociationItem(JSONObject jsonObject, String item);
    //获得协会架构/简介/章程
    JSONObject getAssociationItem(String item);

    //新增一条动态
    JSONObject addDynamic(HttpServletRequest request, String title, String desc, String content, String placeholder, MultipartFile[] imgList);
    //删除一条动态
    JSONObject deleteDynamic(JSONObject jsonObject);
    //获取动态列表
    JSONObject getNewsAndDynamicList(Integer pageSize, Integer currentPage, String title, String dateString);
    //管理员获取动态详情
    JSONObject getDynamicDetail(Integer newsId);
    //管理员修改动态
    JSONObject modifyDynamic(HttpServletRequest request, Integer newsId, String placeholder, String title, String desc, String content, JSONArray imgInfos, MultipartFile[] imgList);
    //管理员将一条动态加入到轮播图队列
    JSONObject addCarousel(JSONObject jsonObject);
    //管理员/首页获取轮播图队列信息
    JSONObject getCarouselList();
    //管理员删除一条轮播图
    JSONObject removeCarousel(JSONObject jsonObject);
}
