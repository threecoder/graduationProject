package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class ActivitySeat implements Serializable {
    private Integer seatId;

    private Integer activityId;

    private Integer realX;

    private Integer realY;

    private Boolean isLock;

    private Boolean isOccupied;

    private Integer fakeX;

    private Integer fakeY;

    private Integer studentnum;

    private static final long serialVersionUID = 1L;

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getRealX() {
        return realX;
    }

    public void setRealX(Integer realX) {
        this.realX = realX;
    }

    public Integer getRealY() {
        return realY;
    }

    public void setRealY(Integer realY) {
        this.realY = realY;
    }

    public Boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(Boolean isLock) {
        this.isLock = isLock;
    }

    public Boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(Boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public Integer getFakeX() {
        return fakeX;
    }

    public void setFakeX(Integer fakeX) {
        this.fakeX = fakeX;
    }

    public Integer getFakeY() {
        return fakeY;
    }

    public void setFakeY(Integer fakeY) {
        this.fakeY = fakeY;
    }

    public Integer getStudentnum() {
        return studentnum;
    }

    public void setStudentnum(Integer studentnum) {
        this.studentnum = studentnum;
    }
}