package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class VoteOptionMember implements Serializable {
    private Integer voteId;

    private Integer optionId;

    private Integer memberKeyId;

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

    public Integer getMemberKeyId() {
        return memberKeyId;
    }

    public void setMemberKeyId(Integer memberKeyId) {
        this.memberKeyId = memberKeyId;
    }
}