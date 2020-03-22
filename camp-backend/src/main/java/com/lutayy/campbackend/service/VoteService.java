package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;

public interface VoteService {

    JSONObject addNewVote(JSONObject jsonObject);

    JSONObject deleteVote(JSONObject jsonObject);

    JSONObject getMemberCanVoteList(String name, Integer isFinish, Integer currentPage, Integer pageSize);

    JSONObject getVoteDetail(Integer voteId);
}
