package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.util.RedisUtil;
import com.lutayy.campbackend.common.util.StrUtil;
import com.lutayy.campbackend.dao.AssociationTextMapper;
import com.lutayy.campbackend.dao.NewsImgMapper;
import com.lutayy.campbackend.dao.NewsMapper;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.NewsService;
import com.lutayy.campbackend.service.SQLConn.SystemParamManager;
import jodd.io.FileUtil;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {

    @Value("${IMG_HOST}")
    private String IMG_HOST;
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    NewsImgMapper newsImgMapper;
    @Autowired
    AssociationTextMapper associationTextMapper;
    @Autowired
    GetObjectHelper getObjectHelper;

    @Resource
    private RedisUtil redisUtil;


    //管理员修改协会架构/简介/章程
    @Override
    public JSONObject modifyAssociationItem(JSONObject jsonObject, String item) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        AssociationTextExample associationTextExample=new AssociationTextExample();
        associationTextExample.createCriteria().andItemNameEqualTo(item);
        AssociationText associationText=associationTextMapper.selectByExample(associationTextExample).get(0);
        if(associationText==null){
            result.put("msg", "修改失败！系统中无["+item+"]属性");
            return result;
        }
        associationText.setItemContext(jsonObject.getString("content"));
        if(associationTextMapper.updateByPrimaryKeySelective(associationText)>0){
            result.put("code", "success");
            result.put("msg", "更新成功！");
        }else {
            result.put("msg", "更新失败！请重试");
        }
        return result;
    }

    //获得协会架构/简介/章程
    @Override
    public JSONObject getAssociationItem(String item) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        AssociationTextExample associationTextExample=new AssociationTextExample();
        System.out.println(item);
        associationTextExample.createCriteria().andItemNameEqualTo(item);
        AssociationText associationText=associationTextMapper.selectByExample(associationTextExample).get(0);
        if(associationText==null){
            result.put("msg", "修改失败！系统中无["+item+"]属性");
            return result;
        }
        result.put("code", "success");
        result.put("msg", "获取文本成功！");
        result.put("data", associationText.getItemContext());
        return result;
    }

    //新增一条动态
    @Override
    public JSONObject addDynamic(HttpServletRequest request, String title, String desc, String content,
                                 String placeholder, String type, MultipartFile[] imgList) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        News news = new News();
        Integer adminId = getObjectHelper.getIdByRequestAndRole("admin", request);
        if (adminId == null) {
            result.put("msg", "未登录！");
            return result;
        }
        news.setAdminId(adminId);
        news.setContent(content);
        news.setDescription(desc);
        news.setImgPlaceholder(placeholder);
        news.setPostTime(new Date());
        news.setTitle(title);
        news.setType(type);
        newsMapper.insertSelective(news);
        Integer newsId = news.getNewsId();
        String mainPath = "./src/main/resources/static";
        String dirPath;
        if(type.equals("news")){
            dirPath = "/image/news/" + adminId + "/";
        }else if(type.equals("dynamic")){
            dirPath = "/image/dynamic/" + adminId + "/";
        }else {
            dirPath = "/image/notice/" + adminId + "/";
        }
        for (int i = 0; i < imgList.length; i++) {
            String fileName = imgList[i].getOriginalFilename();
            String imgPath = dirPath + fileName + ".jpg";
            File dest = new File(dirPath);
            if (!dest.exists()) {
                dest.mkdirs();
            }
            try {
                FileOutputStream out = new FileOutputStream(mainPath + imgPath);
                out.write(imgList[i].getBytes());
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            NewsImg newsImg = new NewsImg();
            newsImg.setNewsId(newsId);
            newsImg.setImgPath(imgPath);
            newsImg.setImgNewsIndex(i + 1);
            newsImgMapper.insertSelective(newsImg);
        }
        result.put("code", "success");
        result.put("msg", "新增动态成功！");
        return result;
    }

    //删除一条动态
    @Override
    public JSONObject deleteDynamic(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");

        Integer newsId = jsonObject.getInteger("dynamicId");
        News news = newsMapper.selectByPrimaryKey(newsId);
        if (news == null) {
            result.put("msg", "删除失败！系统中不存在该条动态！");
            return result;
        }
        news.setIsInvalid(true);
        newsMapper.updateByPrimaryKey(news);
        NewsImgExample newsImgExample = new NewsImgExample();
        newsImgExample.createCriteria().andNewsIdEqualTo(newsId);
        NewsImg newsImg = new NewsImg();
        newsImg.setIsInvalid(true);
        newsImgMapper.updateByExampleSelective(newsImg, newsImgExample);
        result.put("code", "success");
        result.put("msg", "成功删除动态");
        return result;
    }

    //获取动态列表
    @Override
    public JSONObject getNewsAndDynamicList(Integer pageSize, Integer currentPage,
                                            String title, String dateString) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("code", "fail");
        result.put("data", data);

        NewsExample newsExample = new NewsExample();
        NewsExample.Criteria criteria = newsExample.createCriteria();
        criteria.andIsInvalidEqualTo(false);
        if (title != null) {
            criteria.andTitleLike("%" + title + "%");
        }
        if (dateString != null) {
            Date date;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = simpleDateFormat.parse(dateString);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("msg", "日期格式错误");
                return result;
            }
            Calendar nextDate = Calendar.getInstance();
            nextDate.setTime(date);
            nextDate.add(Calendar.DAY_OF_MONTH, 1);
            criteria.andPostTimeGreaterThanOrEqualTo(date).andPostTimeLessThan(nextDate.getTime());
        }
        long total = newsMapper.countByExample(newsExample);
        newsExample.setOffset(pageSize * (currentPage - 1));
        newsExample.setLimit(pageSize);
        List<News> newsList = newsMapper.selectByExample(newsExample);
        JSONArray list = new JSONArray();
        for (News news : newsList) {
            JSONObject object = new JSONObject();
            object.put("id", news.getNewsId());
            object.put("title", news.getTitle());
            object.put("type", news.getType());
            object.put("time", news.getPostTime());
            list.add(object);
        }
        data.put("list", list);
        data.put("total", total);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "列表获取成功！");
        return result;
    }

    //管理员获取动态详情
    @Override
    public JSONObject getDynamicDetail(Integer newsId) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("code", "fail");
        result.put("data", data);

        News news = newsMapper.selectByPrimaryKey(newsId);
        if (news == null) {
            result.put("msg", "系统中找不到该动态！");
            return result;
        }
        JSONObject info = new JSONObject();
        JSONArray ids = new JSONArray();
        info.put("title", news.getTitle());
        info.put("desc", news.getDescription());
        NewsImgExample newsImgExample = new NewsImgExample();
        newsImgExample.createCriteria().andNewsIdEqualTo(newsId).andIsInvalidEqualTo(false);
        newsImgExample.setOrderByClause("img_news_index ASC");
        List<NewsImg> newsImgs = newsImgMapper.selectByExample(newsImgExample);

        List<String> contentArray = StrUtil.cutStringByPlaceHolder(news.getContent(), news.getImgPlaceholder());

        StringBuilder content = new StringBuilder();
        for (int i = 0; i < contentArray.size() - 1; i++) {
            content.append(contentArray.get(i));
            if (newsImgs.get(i) != null) {
                content.append("<img src=\"" + IMG_HOST + newsImgs.get(i).getImgPath() + "\">");
                ids.add(newsImgs.get(i).getImgId());
            }
        }
        content.append(contentArray.get(contentArray.size() - 1));

        info.put("content", content.toString());
        data.put("info", info);
        data.put("ids", ids);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "内容获取成功！");
        return result;
    }

    //管理员修改动态
    @Override
    public JSONObject modifyDynamic(HttpServletRequest request, Integer newsId, String placeholder, String title, String desc, String content,
                                    JSONArray imgInfos, MultipartFile[] imgList) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");

        Integer adminId = getObjectHelper.getIdByRequestAndRole("admin", request);
        if (adminId == null) {
            result.put("msg", "未登录！");
            return result;
        }

        News news = newsMapper.selectByPrimaryKey(newsId);
        if (news == null) {
            result.put("msg", "系统中找不到该动态！");
            return result;
        }
        news.setImgPlaceholder(placeholder);
        news.setTitle(title);
        news.setDescription(desc);
        news.setContent(content);
        newsMapper.updateByPrimaryKey(news);
        int imgListIndex = 0;
        String mainPath = "./src/main/resources/static";
        String dirPath = "/image/dynamic/" + adminId + "/";
        //将原来新闻的所有图片置为失效
        NewsImgExample newsImgExample = new NewsImgExample();
        newsImgExample.createCriteria().andIsInvalidEqualTo(false).andNewsIdEqualTo(newsId);
        NewsImg newsImg = new NewsImg();
        newsImg.setIsInvalid(true);
        newsImgMapper.updateByExampleSelective(newsImg, newsImgExample);

        for (int i = 0; i < imgInfos.size(); i++) {
            JSONObject imgInfo = imgInfos.getJSONObject(i);
            Integer imgId = imgInfo.getInteger("imgId");
            Integer order = imgInfo.getInteger("order");
            String fileName = imgInfo.getString("name");

            if (imgId != null) {
                NewsImg img = newsImgMapper.selectByPrimaryKey(imgId);
                img.setIsInvalid(false);
                img.setImgNewsIndex(order);
                newsImgMapper.updateByPrimaryKeySelective(img);
            } else {
                String imgPath = dirPath + fileName + ".jpg";
                File dest = new File(dirPath);
                if (!dest.exists()) {
                    dest.mkdirs();
                }
                try {
                    FileOutputStream out = new FileOutputStream(mainPath + imgPath);
                    out.write(imgList[imgListIndex].getBytes());
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                NewsImg img = new NewsImg();
                img.setNewsId(newsId);
                img.setImgPath(imgPath);
                img.setImgNewsIndex(order);
                newsImgMapper.insertSelective(img);
                imgListIndex++;//指向imgList的下一个
            }
        }
        result.put("code", "success");
        result.put("msg", "修改成功！");
        return result;
    }

    //管理员将一条动态加入到轮播图队列（在redis中）
    @Override
    public JSONObject addCarousel(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        Integer newsId = jsonObject.getInteger("dynamicId");
        News news = newsMapper.selectByPrimaryKey(newsId);
        if (news == null) {
            result.put("msg", "添加失败！系统中找不到该动态");
            return result;
        }
        NewsImgExample newsImgExample = new NewsImgExample();
        newsImgExample.createCriteria().andNewsIdEqualTo(newsId).andImgNewsIndexEqualTo(1).andIsInvalidEqualTo(false);
        List<NewsImg> newsImgs = newsImgMapper.selectByExample(newsImgExample);

        JSONObject newsData = new JSONObject();
        if (newsImgs.size() > 0) {
            newsData.put("url", IMG_HOST + newsImgs.get(0).getImgPath());
        }
        newsData.put("id", newsId);
        newsData.put("title", news.getTitle());
        newsData.put("type", news.getType());
        newsData.put("desc", news.getDescription());
        newsData.put("time", news.getPostTime());
        if (redisUtil.hset("carousel_img_list", Integer.toString(newsId), newsData)) {
            result.put("code", "success");
            result.put("msg", "添加成功！");
        } else {
            result.put("msg", "系统繁忙，请重试");
        }
        return result;
    }

    //管理员获取轮播图信息
    @Override
    public JSONObject getCarouselList() {
        JSONObject result = new JSONObject();
        JSONArray data = new JSONArray();
        Map<Object, Object> dataMap = redisUtil.hmget("carousel_img_list");
        for (Map.Entry<Object, Object> entry : dataMap.entrySet()) {
            JSONObject object = (JSONObject) entry.getValue();
            data.add(object);
        }
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取轮播图队列成功！");
        return result;
    }

    //管理员删除一条轮播图
    @Override
    public JSONObject removeCarousel(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        Integer newsId = jsonObject.getInteger("dynamicId");
        News news = newsMapper.selectByPrimaryKey(newsId);
        if (news == null) {
            result.put("msg", "删除失败！系统中找不到该动态");
            return result;
        }
        redisUtil.hdel("carousel_img_list", Integer.toString(newsId));
        result.put("code", "success");
        result.put("msg", "移除成功！");
        return result;
    }

    //获取协会公告/新闻/动态
    @Override
    public JSONObject indexGetNewsList(Integer pageSize, Integer currentPage, String type) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();

        NewsExample newsExample=new NewsExample();
        newsExample.createCriteria().andTypeEqualTo(type);
        long total=newsMapper.countByExample(newsExample);
        newsExample.setOrderByClause("post_time DESC");
        newsExample.setOffset(pageSize*(currentPage-1));
        newsExample.setLimit(pageSize);
        List<News> newsList=newsMapper.selectByExample(newsExample);
        JSONArray list=new JSONArray();
        for(News news:newsList){
            NewsImgExample newsImgExample = new NewsImgExample();
            newsImgExample.createCriteria().andNewsIdEqualTo(news.getNewsId()).andImgNewsIndexEqualTo(1).andIsInvalidEqualTo(false);
            List<NewsImg> newsImgs = newsImgMapper.selectByExample(newsImgExample);
            JSONObject object=new JSONObject();
            object.put("title", news.getTitle());
            object.put("desc", news.getDescription());
            object.put("date", news.getPostTime());
            if (newsImgs.size() > 0) {
                object.put("url", IMG_HOST + newsImgs.get(0).getImgPath());
            }
            object.put("id", news.getNewsId());
            list.add(object);
        }
        data.put("list", list);
        data.put("total", total);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取成功！");
        return result;
    }

    //获取协会公告/新闻/动态详情
    @Override
    public JSONObject indexGetNewsDetail(Integer newsId, String type) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("code", "fail");
        result.put("data", data);

        News news = newsMapper.selectByPrimaryKey(newsId);
        if (news == null) {
            result.put("msg", "系统中找不到该动态！");
            return result;
        }
        JSONObject info = new JSONObject();
        JSONArray ids = new JSONArray();
        info.put("title", news.getTitle());
        info.put("desc", news.getDescription());
        NewsImgExample newsImgExample = new NewsImgExample();
        newsImgExample.createCriteria().andNewsIdEqualTo(newsId).andIsInvalidEqualTo(false);
        newsImgExample.setOrderByClause("img_news_index ASC");
        List<NewsImg> newsImgs = newsImgMapper.selectByExample(newsImgExample);

        List<String> contentArray = StrUtil.cutStringByPlaceHolder(news.getContent(), news.getImgPlaceholder());

        StringBuilder content = new StringBuilder();
        for (int i = 0; i < contentArray.size() - 1; i++) {
            content.append(contentArray.get(i));
            if (newsImgs.get(i) != null) {
                content.append("<img src=\"" + IMG_HOST + newsImgs.get(i).getImgPath() + "\">");
                ids.add(newsImgs.get(i).getImgId());
            }
        }
        content.append(contentArray.get(contentArray.size() - 1));

        info.put("content", content.toString());
        data.put("info", info);
        data.put("ids", ids);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "内容获取成功！");
        return result;
    }
}
