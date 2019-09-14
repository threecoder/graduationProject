package com.lutayy.campbackend.pojo;

import java.io.Serializable;
import java.util.Date;

public class TrainingReStudent implements Serializable {
    private Integer trainingId;

    private Integer studentId;

    private Date beginTime;

    private static final long serialVersionUID = 1L;

    public Integer getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Integer trainingId) {
        this.trainingId = trainingId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
}