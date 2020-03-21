package com.lutayy.campbackend.pojo;

import java.io.Serializable;
import java.util.Date;

public class MessageText implements Serializable {
    private Integer messageId;

    private Date sendDate;

    private String message;

    private static final long serialVersionUID = 1L;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}