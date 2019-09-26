package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class ExamReStudent implements Serializable {
    private Integer examId;

    private Integer studentId;

    private Integer score;

    private Byte remainingTimes;

    private Boolean isInvalid;

    private static final long serialVersionUID = 1L;

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
}