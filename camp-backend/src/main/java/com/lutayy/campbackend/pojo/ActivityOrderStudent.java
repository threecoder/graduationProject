package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class ActivityOrderStudent implements Serializable {
    private String activityOrderId;

    private Integer studentId;

    private Boolean isPaid;

    private static final long serialVersionUID = 1L;

    public String getActivityOrderId() {
        return activityOrderId;
    }

    public void setActivityOrderId(String activityOrderId) {
        this.activityOrderId = activityOrderId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }
}