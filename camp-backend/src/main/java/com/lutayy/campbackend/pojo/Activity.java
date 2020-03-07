package com.lutayy.campbackend.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Activity implements Serializable {
    private Integer activityId;

    private BigDecimal activityFee;

    private String activityName;

    private String activityIntroduction;

    private Date activityDate;

    private Integer activityLengthMin;

    private String activityAddress;

    private Date openTime;

    private Date closeTime;

    private Date postTime;

    private String contactName;

    private String contactPhone;

    private Integer maxNum;

    private Integer areaWidth;

    private Integer areaLength;

    private static final long serialVersionUID = 1L;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public BigDecimal getActivityFee() {
        return activityFee;
    }

    public void setActivityFee(BigDecimal activityFee) {
        this.activityFee = activityFee;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityIntroduction() {
        return activityIntroduction;
    }

    public void setActivityIntroduction(String activityIntroduction) {
        this.activityIntroduction = activityIntroduction;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public Integer getActivityLengthMin() {
        return activityLengthMin;
    }

    public void setActivityLengthMin(Integer activityLengthMin) {
        this.activityLengthMin = activityLengthMin;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Integer getAreaWidth() {
        return areaWidth;
    }

    public void setAreaWidth(Integer areaWidth) {
        this.areaWidth = areaWidth;
    }

    public Integer getAreaLength() {
        return areaLength;
    }

    public void setAreaLength(Integer areaLength) {
        this.areaLength = areaLength;
    }
}