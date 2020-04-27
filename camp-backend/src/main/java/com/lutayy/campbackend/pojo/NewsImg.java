package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class NewsImg implements Serializable {
    private Integer imgId;

    private String imgPath;

    private Integer newsId;

    private Integer imgNewsIndex;

    private Boolean isInvalid;

    private static final long serialVersionUID = 1L;

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getImgNewsIndex() {
        return imgNewsIndex;
    }

    public void setImgNewsIndex(Integer imgNewsIndex) {
        this.imgNewsIndex = imgNewsIndex;
    }

    public Boolean getIsInvalid() {
        return isInvalid;
    }

    public void setIsInvalid(Boolean isInvalid) {
        this.isInvalid = isInvalid;
    }
}