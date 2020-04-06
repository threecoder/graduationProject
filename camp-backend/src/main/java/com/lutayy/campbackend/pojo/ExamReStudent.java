package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class ExamReStudent implements Serializable {
    private Integer reportId;

    private Integer examId;

    private Integer studentId;

    private Integer score;

    private Byte remainingTimes;

    private Boolean isInvalid;

    private Byte isVerify;

    private Boolean inLine;

    private String notPassReason;

    private Integer notPassTimes;

    private static final long serialVersionUID = 1L;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Byte getRemainingTimes() {
        return remainingTimes;
    }

    public void setRemainingTimes(Byte remainingTimes) {
        this.remainingTimes = remainingTimes;
    }

    public Boolean getIsInvalid() {
        return isInvalid;
    }

    public void setIsInvalid(Boolean isInvalid) {
        this.isInvalid = isInvalid;
    }

    public Byte getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Byte isVerify) {
        this.isVerify = isVerify;
    }

    public Boolean getInLine() {
        return inLine;
    }

    public void setInLine(Boolean inLine) {
        this.inLine = inLine;
    }

    public String getNotPassReason() {
        return notPassReason;
    }

    public void setNotPassReason(String notPassReason) {
        this.notPassReason = notPassReason;
    }

    public Integer getNotPassTimes() {
        return notPassTimes;
    }

    public void setNotPassTimes(Integer notPassTimes) {
        this.notPassTimes = notPassTimes;
    }
}