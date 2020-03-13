package com.lutayy.campbackend.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ActivityOrder implements Serializable {
    private Integer orderKeyId;

    private String activityOrderId;

    private Integer activityId;

    private Boolean orderType;

    private Integer memberKeyId;

    private Integer studentId;

    private BigDecimal orderPrice;

    private Date orderBeginTime;

    private Boolean paymentState;

    private Boolean close;

    private static final long serialVersionUID = 1L;

    public Integer getOrderKeyId() {
        return orderKeyId;
    }

    public void setOrderKeyId(Integer orderKeyId) {
        this.orderKeyId = orderKeyId;
    }

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

    public Integer getMemberKeyId() {
        return memberKeyId;
    }

    public void setMemberKeyId(Integer memberKeyId) {
        this.memberKeyId = memberKeyId;
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