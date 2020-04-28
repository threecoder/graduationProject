package com.lutayy.campbackend.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NewsController {

    @Autowired
    NewsService newsService;


    @RequestMapping("/admin/modifyFramework")
    @ResponseBody
    public JSONObject modifyFramework(@RequestBody JSONObject jsonObject) {
        return newsService.modifyAssociationItem(jsonObject, "framework");
    }
    @RequestMapping("/admin/modifyConstitution")
    @ResponseBody
    public JSONObject modifyConstitution(@RequestBody JSONObject jsonObject) {
        return newsService.modifyAssociationItem(jsonObject, "constitution");
    }
    @RequestMapping("/admin/modifyBrief")
    @ResponseBody
    public JSONObject modifyBrief(@RequestBody JSONObject jsonObject) {
        return newsService.modifyAssociationItem(jsonObject, "brief");
    }
    @RequestMapping(value = {"/admin/getFrameWork", "/index/getFramework"})
    @ResponseBody
    public Object getFrameWork() {
        return newsService.getAssociationItem("framework");
    }
    @RequestMapping(value = {"/admin/getConsitiution", "/index/getConstitution"})
    @ResponseBody
    public Object getConsitiution() {
        return newsService.getAssociationItem("constitution");
    }
    @RequestMapping(value = {"/admin/getBrief", "/index/getBrief"})
    @ResponseBody
    public Object getBrief() {
        return newsService.getAssociationItem("brief");
    }


    @RequestMapping("/admin/addDynamic")
    @ResponseBody
    public JSONObject addDynamic(HttpServletRequest request,
                                 @RequestParam("title") String title,
                                 @RequestParam("desc") String desc,
                                 @RequestParam("content") String content,
                                 @RequestParam("placeholder") String placeholder,
                                 @RequestParam("type") String type,
                                 @RequestParam("imgList") MultipartFile[] imgList) {
        return newsService.addDynamic(request, title, desc, content, placeholder, type, imgList);
    }

    @RequestMapping("/admin/deleteDynamic")
    @ResponseBody
    public JSONObject deleteDynamic(@RequestBody JSONObject jsonObject) {
        return newsService.deleteDynamic(jsonObject);
    }

    @RequestMapping("/admin/getNewsAndDynamicList")
    @ResponseBody
    public Object getNewsAndDynamicList(@RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("currentPage") Integer currentPage,
                                        @RequestParam(value = "title", required = false) String title,
                                        @RequestParam(value = "date", required = false) String dateString) {
        return newsService.getNewsAndDynamicList(pageSize, currentPage, title, dateString);
    }


    @RequestMapping("/admin/getDynamicDetail")
    @ResponseBody
    public Object getDynamicDetail(@RequestParam("dynamicId") Integer newsId) {
        return newsService.getDynamicDetail(newsId);
    }

    @RequestMapping("/admin/modifyDynamic")
    @ResponseBody
    public JSONObject modifyDynamic(HttpServletRequest request,
                                        @RequestParam("dynamicId") Integer newsId,
                                        @RequestParam("placeholder") String placeholder,
                                        @RequestParam("title") String title,
                                        @RequestParam("desc") String desc,
                                        @RequestParam("content") String content,
                                        @RequestParam("type") String type,
                                        @RequestParam("imgInfo") String imgInfo,
                                        @RequestParam(value = "imgList", required = false) MultipartFile[] imgList) {
        JSONArray imgListJSON = JSONArray.parseArray(imgInfo);
        JSONArray imgInfos = new JSONArray();
        for (int i = 0; i < imgListJSON.size(); i++) {
            System.out.println(111);
            String info = imgListJSON.getString(i);
            imgInfos.add(JSONObject.parseObject(info));
        }
        System.out.println(imgInfos);
        return newsService.modifyDynamic(request, newsId, placeholder, title, desc, type, content, imgInfos, imgList);
    }

    @RequestMapping("/admin/addNotice")
    @ResponseBody
    public JSONObject addNotice(@RequestBody JSONObject jsonObject) {
        return newsService.addNotice(jsonObject);
    }

    @RequestMapping("/admin/getNoticeList")
    @ResponseBody
    public Object getNewsAndNoticeList(@RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("currentPage") Integer currentPage,
                                        @RequestParam(value = "title", required = false) String title,
                                        @RequestParam(value = "date", required = false) String dateString) {
        return newsService.getNewsAndNoticeList(pageSize, currentPage, title, dateString);
    }

    @RequestMapping("/admin/deleteNotice")
    @ResponseBody
    public JSONObject deleteNotice(@RequestBody JSONObject jsonObject) {
        return newsService.deleteNotice(jsonObject);
    }

    @RequestMapping("/admin/modifyNotice")
    @ResponseBody
    public JSONObject modifyNotice(@RequestBody JSONObject jsonObject) {
        return newsService.modifyNotice(jsonObject);
    }

    @RequestMapping("/admin/addCarousel")
    @ResponseBody
    public JSONObject addCarousel(@RequestBody JSONObject jsonObject) {
        return newsService.addCarousel(jsonObject);
    }

    @RequestMapping(value = {"/admin/getCarouselList", "/index/getCarouselList"})
    @ResponseBody
    public Object getCarouselList() {
        return newsService.getCarouselList();
    }

    @RequestMapping("/admin/removeCarousel")
    @ResponseBody
    public JSONObject removeCarousel(@RequestBody JSONObject jsonObject) {
        return newsService.removeCarousel(jsonObject);
    }

    @RequestMapping("/index/getDynamicList")
    @ResponseBody
    public Object indexGetDynamicList(@RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("currentPage") Integer currentPage) {
        return newsService.indexGetNewsList(pageSize, currentPage, "dynamic");
    }
    @RequestMapping("/index/getNewsList")
    @ResponseBody
    public Object indexGetNewsList(@RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("currentPage") Integer currentPage) {
        return newsService.indexGetNewsList(pageSize, currentPage, "news");
    }
    @RequestMapping("/index/getNoticeList")
    @ResponseBody
    public Object indexGetNoticeList(@RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("currentPage") Integer currentPage) {
        return newsService.indexGetNewsList(pageSize, currentPage, "notice");
    }

    @RequestMapping("/index/getDynamicDetail")
    @ResponseBody
    public Object indexGetDynamicDetail(@RequestParam("id") Integer newsId) {
        return newsService.indexGetNewsDetail(newsId, "dynamic");
    }
    @RequestMapping("/index/getNewsDetail")
    @ResponseBody
    public Object indexGetNewsDetail(@RequestParam("id") Integer newsId) {
        return newsService.indexGetNewsDetail(newsId, "news");
    }
    @RequestMapping("/index/getNoticeDetail")
    @ResponseBody
    public Object indexGetNoticeDetail(@RequestParam("id") Integer newsId) {
        return newsService.indexGetNewsDetail(newsId, "notice");
    }
}
