package com.lutayy.campbackend.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CertificateChangeOrder implements Serializable {
    private Integer orderKeyId;

    private String certificateChangeOrderId;

    private Integer certificateId;

    private Integer studentId;

    private Boolean orderType;

    private Integer memberKeyId;

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

    public String getCertificateChangeOrderId() {
        return certificateChangeOrderId;
    }

    public void setCertificateChangeOrderId(String certificateChangeOrderId) {
        this.certificateChangeOrderId = certificateChangeOrderId;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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