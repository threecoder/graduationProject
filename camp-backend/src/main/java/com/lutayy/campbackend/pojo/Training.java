package com.lutayy.campbackend.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Training implements Serializable,Comparable<Training> {
    private Integer trainingId;

    private String trainingName;

    private String trainingIntroduce;

    private BigDecimal trainingFeeNormal;

    private BigDecimal trainingFeeVip;

    private Date trainingEndTime;

    private Date trainingStartTime;

    private Date postTime;

    private Byte level;

    private String trainingPic;

    private String trainingAddress;

    private String contacts;

    private static final long serialVersionUID = 1L;

    @Override
    public int compareTo(Training training){
        return training.getPostTime().compareTo(this.getPostTime());
    }


    public Integer getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Integer trainingId) {
        this.trainingId = trainingId;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getTrainingIntroduce() {
        return trainingIntroduce;
    }

    public void setTrainingIntroduce(String trainingIntroduce) {
        this.trainingIntroduce = trainingIntroduce;
    }

    public BigDecimal getTrainingFeeNormal() {
        return trainingFeeNormal;
    }

    public void setTrainingFeeNormal(BigDecimal trainingFeeNormal) {
        this.trainingFeeNormal = trainingFeeNormal;
    }

    public BigDecimal getTrainingFeeVip() {
        return trainingFeeVip;
    }

    public void setTrainingFeeVip(BigDecimal trainingFeeVip) {
        this.trainingFeeVip = trainingFeeVip;
    }

    public Date getTrainingEndTime() {
        return trainingEndTime;
    }

    public void setTrainingEndTime(Date trainingEndTime) {
        this.trainingEndTime = trainingEndTime;
    }

    public Date getTrainingStartTime() {
        return trainingStartTime;
    }

    public void setTrainingStartTime(Date trainingStartTime) {
        this.trainingStartTime = trainingStartTime;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public String getTrainingPic() {
        return trainingPic;
    }

    public void setTrainingPic(String trainingPic) {
        this.trainingPic = trainingPic;
    }

    public String getTrainingAddress() {
        return trainingAddress;
    }

    public void setTrainingAddress(String trainingAddress) {
        this.trainingAddress = trainingAddress;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}