package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class VoteOptionStudent implements Serializable {
    private Integer voteId;

    private Integer optionId;

    private Integer studentId;

    private static final long serialVersionUID = 1L;

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}