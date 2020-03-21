package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.dao.VoteMapper;
import com.lutayy.campbackend.dao.VoteOptionMapper;
import com.lutayy.campbackend.dao.VoteStudentMemberMapper;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteMapper voteMapper;
    @Autowired
    VoteOptionMapper voteOptionMapper;
    @Autowired
    VoteStudentMemberMapper voteStudentMemberMapper;

    @Override
    public JSONObject addNewVote(JSONObject jsonObject) {
        String voteContent = jsonObject.getString("name");
        Byte voteType = jsonObject.getByte("type");
        Byte optionalNum = jsonObject.getByte("num");
        JSONArray options = jsonObject.getJSONArray("options");
        Date endTime = jsonObject.getDate("time");
        JSONObject result = new JSONObject();

        VoteExample voteExample=new VoteExample();
        VoteExample.Criteria criteria=voteExample.createCriteria();
        criteria.andVoteContentEqualTo(voteContent);
        if(voteMapper.selectByExample(voteExample).size()>0){
            result.put("code", "fail");
            result.put("msg", "新建失败，已有同名投票");
            return result;
        }

        Vote vote = new Vote();
        vote.setVoteContent(voteContent);
        vote.setVoteType(voteType);
        vote.setOptionalSum((byte) options.size());
        vote.setEndTime(endTime);
        vote.setPostTime(new Date());
        vote.setOptionalNum(optionalNum);
        if (voteMapper.insertSelective(vote) < 1) {
            result.put("code", "fail");
            result.put("msg", "系统繁忙，请稍后重试");
            return result;
        }
        for (int i = 0; i < options.size(); i++) {
            String optionContent = options.getString(i);
            VoteOption voteOption=new VoteOption();
            voteOption.setOptionText(optionContent);
            voteOption.setVoteId(vote.getVoteId());
            voteOptionMapper.insertSelective(voteOption);
        }
        result.put("code", "success");
        result.put("msg", "新建投票成功！");
        return result;
    }

    @Override
    public JSONObject deleteVote(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        Integer voteId=jsonObject.getInteger("voteId");
        if(voteMapper.selectByPrimaryKey(voteId)==null){
            result.put("code", "fail");
            result.put("msg", "系统无此投票！");
            return result;
        }

        //删除“投票-学员-会员”表的内容
        VoteStudentMemberExample voteStudentMemberExample=new VoteStudentMemberExample();
        VoteStudentMemberExample.Criteria criteria=voteStudentMemberExample.createCriteria();
        criteria.andVoteIdEqualTo(voteId);
        voteStudentMemberMapper.deleteByExample(voteStudentMemberExample);

        //删除“投票-选项”表的内容
        VoteOptionExample voteOptionExample=new VoteOptionExample();
        VoteOptionExample.Criteria criteria1=voteOptionExample.createCriteria();
        criteria1.andVoteIdEqualTo(voteId);
        voteOptionMapper.deleteByExample(voteOptionExample);

        //删除“投票”表的内容
        voteMapper.deleteByPrimaryKey(voteId);

        result.put("code", "success");
        result.put("msg", "删除成功！");
        return result;
    }
}
