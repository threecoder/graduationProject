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
    @RequestMapping("/admin/getFrameWork")
    @ResponseBody
    public Object getFrameWork() {
        return newsService.getAssociationItem("framework");
    }
    @RequestMapping("/admin/getConsitiution")
    @ResponseBody
    public Object getConsitiution() {
        return newsService.getAssociationItem("constitution");
    }
    @RequestMapping("/admin/getBrief")
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
                                 @RequestParam("imgList") MultipartFile[] imgList) {
        return newsService.addDynamic(request, title, desc, content, placeholder, imgList);
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
                                        @RequestParam("imgInfo") String[] imgInfo,
                                        @RequestParam(value = "imgList", required = false) MultipartFile[] imgList) {
        JSONArray imgInfos=new JSONArray();
        for(String info:imgInfo){
            imgInfos.add(JSONObject.parseObject(info));
        }
        return newsService.modifyDynamic(request, newsId, placeholder, title, desc, content, imgInfos, imgList);
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
}
