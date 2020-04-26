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
    JSONObject addDynamic(HttpServletRequest request, String title, String desc, String content, String placeholder, String type, MultipartFile[] imgList);
    //删除一条动态
    JSONObject deleteDynamic(JSONObject jsonObject);
    //获取动态列表
    JSONObject getNewsAndDynamicList(Integer pageSize, Integer currentPage, String title, String dateString);
    //管理员获取动态详情
    JSONObject getDynamicDetail(Integer newsId);
    //管理员修改动态
    JSONObject modifyDynamic(HttpServletRequest request, Integer newsId, String placeholder, String title, String desc, String type, String content, JSONArray imgInfos, MultipartFile[] imgList);

    //管理员新增一条公告
    JSONObject addNotice(Integer adminId, String title, String desc, String content);
    //获取公告列表
    JSONObject getNewsAndNoticeList(Integer pageSize, Integer currentPage, String title, String dateString);
    //删除一条公告
    JSONObject deleteNotice(JSONObject jsonObject);
    //修改一条公告
    JSONObject modifyNotice(Integer newsId, String title, String desc, String content);

    //管理员将一条动态加入到轮播图队列
    JSONObject addCarousel(JSONObject jsonObject);
    //管理员/首页获取轮播图队列信息
    JSONObject getCarouselList();
    //管理员删除一条轮播图
    JSONObject removeCarousel(JSONObject jsonObject);
    //获取协会公告/新闻/动态列表
    JSONObject indexGetNewsList(Integer pageSize, Integer currentPage, String type);
    //获取协会公告/新闻/动态详情
    JSONObject indexGetNewsDetail(Integer newsId, String type);
}
