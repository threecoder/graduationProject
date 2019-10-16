package com.lutayy.campbackend.pojo;

import java.io.Serializable;
import java.util.Date;

public class Exam implements Serializable {
    private Integer examId;

    private String examName;

    private Integer trainingId;

    private Short examNum;

    private Short examPass;

    private Byte examLengthMin;

    private Date examStartTime;

    private Date examEndTime;

    private Boolean isPosted;

    private static final long serialVersionUID = 1L;

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Integer getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Integer trainingId) {
        this.trainingId = trainingId;
    }

    public Short getExamNum() {
        return examNum;
    }

    public void setExamNum(Short examNum) {
        this.examNum = examNum;
    }

    public Short getExamPass() {
        return examPass;
    }

    public void setExamPass(Short examPass) {
        this.examPass = examPass;
    }

    public Byte getExamLengthMin() {
        return examLengthMin;
    }

    public void setExamLengthMin(Byte examLengthMin) {
        this.examLengthMin = examLengthMin;
    }

    public Date getExamStartTime() {
        return examStartTime;
    }

    public void setExamStartTime(Date examStartTime) {
        this.examStartTime = examStartTime;
    }

    public Date getExamEndTime() {
        return examEndTime;
    }

    public void setExamEndTime(Date examEndTime) {
        this.examEndTime = examEndTime;
    }

    public Boolean getIsPosted() {
        return isPosted;
    }

    public void setIsPosted(Boolean isPosted) {
        this.isPosted = isPosted;
    }
}