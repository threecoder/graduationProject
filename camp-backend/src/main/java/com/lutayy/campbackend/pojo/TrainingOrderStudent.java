package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class TrainingOrderStudent implements Serializable {
    private String trainingOrderId;

    private Integer studentId;

    private Boolean isPaid;

    private static final long serialVersionUID = 1L;

    public String getTrainingOrderId() {
        return trainingOrderId;
    }

    public void setTrainingOrderId(String trainingOrderId) {
        this.trainingOrderId = trainingOrderId;
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