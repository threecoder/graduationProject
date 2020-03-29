package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;

public interface VoteService {

    JSONObject addNewVote(JSONObject jsonObject);

    JSONObject deleteVote(JSONObject jsonObject);

    JSONObject getVoteStatistic(Integer voteId);

    JSONObject getVoteList(String name, Integer type, Integer currentPage, Integer pageSize);

    JSONObject getMemberCanVoteList(String name, Integer isFinish, Integer currentPage, Integer pageSize);

    JSONObject getVoteDetail(Integer voteId);

    JSONObject memberVote(JSONObject jsonObject);

    JSONObject getMemberHasVotedList(String name, Integer isFinish, Integer currentPage, Integer pageSize, int memberKeyId);

    JSONObject getStudentCanVoteList(String name, Integer isFinish, Integer currentPage, Integer pageSize);

    JSONObject studentVote(JSONObject jsonObject);

    JSONObject getStudentHasVotedList(String name, Integer isFinish, Integer currentPage, Integer pageSize, int studentId);

}
