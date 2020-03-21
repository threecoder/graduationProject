package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class VoteOption implements Serializable {
    private Integer optionId;

    private Integer voteId;

    private String optionText;

    private static final long serialVersionUID = 1L;

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
}