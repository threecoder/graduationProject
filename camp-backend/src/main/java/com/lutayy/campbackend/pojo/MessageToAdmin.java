package com.lutayy.campbackend.pojo;

import java.io.Serializable;
import java.util.Date;

public class MessageToAdmin implements Serializable {
    private Integer id;

    private Integer receiveAdminId;

    private Integer adminId;

    private Integer memberId;

    private Integer studentId;

    private Byte whoSend;

    private Integer messageId;

    private Date sendTime;

    private Boolean isInvalid;

    private Boolean status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReceiveAdminId() {
        return receiveAdminId;
    }

    public void setReceiveAdminId(Integer receiveAdminId) {
        this.receiveAdminId = receiveAdminId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Byte getWhoSend() {
        return whoSend;
    }

    public void setWhoSend(Byte whoSend) {
        this.whoSend = whoSend;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Boolean getIsInvalid() {
        return isInvalid;
    }

    public void setIsInvalid(Boolean isInvalid) {
        this.isInvalid = isInvalid;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}