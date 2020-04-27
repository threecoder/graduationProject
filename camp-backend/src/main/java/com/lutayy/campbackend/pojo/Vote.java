package com.lutayy.campbackend.pojo;

import java.io.Serializable;
import java.util.Date;

public class Vote implements Serializable {
    private Integer voteId;

    private String voteContent;

    private Date endTime;

    private Byte voteType;

    private Byte optionalSum;

    private Byte optionalNum;

    private Date postTime;

    private static final long serialVersionUID = 1L;

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public String getVoteContent() {
        return voteContent;
    }

    public void setVoteContent(String voteContent) {
        this.voteContent = voteContent;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Byte getVoteType() {
        return voteType;
    }

    public void setVoteType(Byte voteType) {
        this.voteType = voteType;
    }

    public Byte getOptionalSum() {
        return optionalSum;
    }

    public void setOptionalSum(Byte optionalSum) {
        this.optionalSum = optionalSum;
    }

    public Byte getOptionalNum() {
        return optionalNum;
    }

    public void setOptionalNum(Byte optionalNum) {
        this.optionalNum = optionalNum;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }
}