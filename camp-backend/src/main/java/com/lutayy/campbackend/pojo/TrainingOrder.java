package com.lutayy.campbackend.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TrainingOrder implements Serializable {
    private Integer orderKeyId;

    private String trainingOrderId;

    private Integer trainingId;

    private Boolean orderType;

    private Integer memberKeyId;

    private Integer studentId;

    private BigDecimal orderPrice;

    private Date orderBeginTime;

    private Boolean paymentState;

    private Boolean close;

    private Date payTime;

    private Boolean isDeleteUser;

    private Boolean isDeleteAdmin;

    private String opManName;

    private String businessName;

    private static final long serialVersionUID = 1L;

    public Integer getOrderKeyId() {
        return orderKeyId;
    }

    public void setOrderKeyId(Integer orderKeyId) {
        this.orderKeyId = orderKeyId;
    }

    public String getTrainingOrderId() {
        return trainingOrderId;
    }

    public void setTrainingOrderId(String trainingOrderId) {
        this.trainingOrderId = trainingOrderId;
    }

    public Integer getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Integer trainingId) {
        this.trainingId = trainingId;
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

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Boolean getIsDeleteUser() {
        return isDeleteUser;
    }

    public void setIsDeleteUser(Boolean isDeleteUser) {
        this.isDeleteUser = isDeleteUser;
    }

    public Boolean getIsDeleteAdmin() {
        return isDeleteAdmin;
    }

    public void setIsDeleteAdmin(Boolean isDeleteAdmin) {
        this.isDeleteAdmin = isDeleteAdmin;
    }

    public String getOpManName() {
        return opManName;
    }

    public void setOpManName(String opManName) {
        this.opManName = opManName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}