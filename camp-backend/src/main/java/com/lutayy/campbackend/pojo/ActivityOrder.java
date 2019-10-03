package com.lutayy.campbackend.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ActivityOrder implements Serializable {
    private String activityOrderId;

    private Integer activityId;

    private Boolean orderType;

    private String memberId;

    private Integer studentId;

    private BigDecimal orderPrice;

    private Date orderBeginTime;

    private Boolean paymentState;

    private Boolean close;

    private static final long serialVersionUID = 1L;

    public String getActivityOrderId() {
        return activityOrderId;
    }

    public void setActivityOrderId(String activityOrderId) {
        this.activityOrderId = activityOrderId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Boolean getOrderType() {
        return orderType;
    }

    public void setOrderType(Boolean orderType) {
        this.orderType = orderType;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Date getOrderBeginTime() {
        return orderBeginTime;
    }

    public void setOrderBeginTime(Date orderBeginTime) {
        this.orderBeginTime = orderBeginTime;
    }

    public Boolean getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(Boolean paymentState) {
        this.paymentState = paymentState;
    }

    public Boolean getClose() {
        return close;
    }

    public void setClose(Boolean close) {
        this.close = close;
    }
}