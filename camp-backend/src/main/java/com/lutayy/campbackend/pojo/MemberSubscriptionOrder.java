package com.lutayy.campbackend.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MemberSubscriptionOrder implements Serializable {
    private Integer orderKeyId;

    private String subscriptionOrderId;

    private Integer memberKeyId;

    private Date createTime;

    private BigDecimal fee;

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

    public String getSubscriptionOrderId() {
        return subscriptionOrderId;
    }

    public void setSubscriptionOrderId(String subscriptionOrderId) {
        this.subscriptionOrderId = subscriptionOrderId;
    }

    public Integer getMemberKeyId() {
        return memberKeyId;
    }

    public void setMemberKeyId(Integer memberKeyId) {
        this.memberKeyId = memberKeyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
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