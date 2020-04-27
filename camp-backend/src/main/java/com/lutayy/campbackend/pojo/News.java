package com.lutayy.campbackend.pojo;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {
    private Integer newsId;

    private Integer adminId;

    private String title;

    private String description;

    private String content;

    private String imgPlaceholder;

    private Date postTime;

    private String type;

    private Boolean isInvalid;

    private static final long serialVersionUID = 1L;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgPlaceholder() {
        return imgPlaceholder;
    }

    public void setImgPlaceholder(String imgPlaceholder) {
        this.imgPlaceholder = imgPlaceholder;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsInvalid() {
        return isInvalid;
    }

    public void setIsInvalid(Boolean isInvalid) {
        this.isInvalid = isInvalid;
    }
}