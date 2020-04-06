package com.lutayy.campbackend.pojo;

import java.io.Serializable;
import java.util.Date;

public class MessageText implements Serializable {
    private Integer messageId;

    private String message;

    private Date sendTime;

    private static final long serialVersionUID = 1L;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}