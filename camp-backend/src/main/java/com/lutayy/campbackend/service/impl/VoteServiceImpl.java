package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.dao.VoteMapper;
import com.lutayy.campbackend.dao.VoteOptionMapper;
import com.lutayy.campbackend.dao.VoteOptionMemberMapper;
import com.lutayy.campbackend.dao.VoteOptionStudentMapper;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteMapper voteMapper;
    @Autowired
    VoteOptionMapper voteOptionMapper;
    @Autowired
    VoteOptionMemberMapper voteOptionMemberMapper;
    @Autowired
    VoteOptionStudentMapper voteOptionStudentMapper;

    //管理员
    @Override
    public JSONObject addNewVote(JSONObject jsonObject) {
        String voteContent = jsonObject.getString("name");
        Byte voteType = jsonObject.getByte("type");
        Byte optionalNum = jsonObject.getByte("num");
        JSONArray options = jsonObject.getJSONArray("options");
        Date endTime = jsonObject.getDate("time");
        JSONObject result = new JSONObject();

        VoteExample voteExample = new VoteExample();
        VoteExample.Criteria criteria = voteExample.createCriteria();
        criteria.andVoteContentEqualTo(voteContent);
        if (voteMapper.selectByExample(voteExample).size() > 0) {
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
            VoteOption voteOption = new VoteOption();
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
        JSONObject result = new JSONObject();
        Integer voteId = jsonObject.getInteger("voteId");
        if (voteMapper.selectByPrimaryKey(voteId) == null) {
            result.put("code", "fail");
            result.put("msg", "系统无此投票！");
            return result;
        }

        //删除“投票-学员-会员”表的内容
        VoteOptionMemberExample voteOptionMemberExample = new VoteOptionMemberExample();
        voteOptionMemberExample.createCriteria().andVoteIdEqualTo(voteId);
        voteOptionMemberMapper.deleteByExample(voteOptionMemberExample);
        VoteOptionStudentExample voteOptionStudentExample=new VoteOptionStudentExample();
        voteOptionStudentExample.createCriteria().andVoteIdEqualTo(voteId);
        voteOptionStudentMapper.deleteByExample(voteOptionStudentExample);

        //删除“投票-选项”表的内容
        VoteOptionExample voteOptionExample = new VoteOptionExample();
        VoteOptionExample.Criteria criteria1 = voteOptionExample.createCriteria();
        criteria1.andVoteIdEqualTo(voteId);
        voteOptionMapper.deleteByExample(voteOptionExample);

        //删除“投票”表的内容
        voteMapper.deleteByPrimaryKey(voteId);

        result.put("code", "success");
        result.put("msg", "删除成功！");
        return result;
    }

    @Override
    public JSONObject getVoteStatistic(Integer voteId) {
        JSONObject result = new JSONObject();
        Vote vote = voteMapper.selectByPrimaryKey(voteId);
        JSONObject data = new JSONObject();
        if (vote == null) {
            result.put("code", "fail");
            result.put("msg", "系统中找不到该投票项目");
            result.put("data", data);
            return result;
        }
        data.put("name", vote.getVoteContent());
        data.put("type", vote.getOptionalNum() > 1 ? "多选" : "单选");
        data.put("num", vote.getOptionalSum());
        JSONArray options = new JSONArray();

        VoteOptionExample voteOptionExample = new VoteOptionExample();
        VoteOptionExample.Criteria criteria = voteOptionExample.createCriteria();
        criteria.andVoteIdEqualTo(voteId);
        List<VoteOption> voteOptions = voteOptionMapper.selectByExample(voteOptionExample);
        //计算投票总参与数
        long sum = 0;
        if (vote.getVoteType().equals((byte) 0) || vote.getVoteType().equals((byte) 2)) {
            VoteOptionMemberExample voteOptionMemberExample = new VoteOptionMemberExample();
            voteOptionMemberExample.createCriteria().andVoteIdEqualTo(voteId);
            sum += voteOptionMemberMapper.countByExample(voteOptionMemberExample);
        }
        if (vote.getVoteType().equals((byte) 1) || vote.getVoteType().equals((byte) 2)) {
            VoteOptionStudentExample voteOptionStudentExample = new VoteOptionStudentExample();
            voteOptionStudentExample.createCriteria().andVoteIdEqualTo(voteId);
            sum += voteOptionStudentMapper.countByExample(voteOptionStudentExample);
        }
        for (VoteOption voteOption : voteOptions) {
            JSONObject object = new JSONObject();
            object.put("text", voteOption.getOptionText());
            long num = 0;
            if (vote.getVoteType().equals((byte) 0) || vote.getVoteType().equals((byte) 2)) {
                VoteOptionMemberExample voteOptionMemberExample = new VoteOptionMemberExample();
                voteOptionMemberExample.createCriteria().andVoteIdEqualTo(voteId).andOptionIdEqualTo(voteOption.getOptionId());
                num += voteOptionMemberMapper.countByExample(voteOptionMemberExample);
            }
            if (vote.getVoteType().equals((byte) 1) || vote.getVoteType().equals((byte) 2)) {
                VoteOptionStudentExample voteOptionStudentExample = new VoteOptionStudentExample();
                voteOptionStudentExample.createCriteria().andVoteIdEqualTo(voteId).andOptionIdEqualTo(voteOption.getOptionId());
                num += voteOptionStudentMapper.countByExample(voteOptionStudentExample);
            }
            object.put("per", sum == 0 ? 0 : (int) ((float) num / sum * 100));
            options.add(object);
        }
        data.put("options", options);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "成功获取投票信息");
        return result;
    }

    @Override
    public JSONObject getVoteList(String name, Integer type, Integer currentPage, Integer pageSize) {
        JSONObject result = new JSONObject();
        VoteExample voteExample = new VoteExample();
        VoteExample.Criteria criteria = voteExample.createCriteria();
        if (name != null) {
            criteria.andVoteContentLike("%" + name + "%");
        }
        if (type != null) {
            criteria.andVoteTypeEqualTo(type.byteValue());
        }
        long sum = voteMapper.countByExample(voteExample);
        voteExample.setOffset((currentPage - 1) * pageSize);
        voteExample.setLimit(pageSize);
        List<Vote> votes = voteMapper.selectByExample(voteExample);
        JSONObject data = new JSONObject();
        data.put("total", sum);
        JSONArray list = new JSONArray();
        for (Vote vote : votes) {
            JSONObject object = new JSONObject();
            object.put("id", vote.getVoteId());
            object.put("name", vote.getVoteContent());
            object.put("type", vote.getOptionalNum() !=null && vote.getOptionalNum() > 1 ? "多选" : "单选");
            object.put("sum", vote.getOptionalSum());
            object.put("num", vote.getOptionalNum());
            list.add(object);
        }
        data.put("list", list);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "查询成功！");
        return result;
    }

    //会员
    @Override
    public JSONObject getMemberCanVoteList(String name, Integer isFinish, Integer currentPage, Integer pageSize) {
        JSONObject result = new JSONObject();
        VoteExample voteExample = new VoteExample();
        VoteExample.Criteria criteria = voteExample.createCriteria();
        VoteExample.Criteria criteria1 = voteExample.createCriteria();
        criteria.andVoteTypeEqualTo((byte)2);
        criteria1.andVoteTypeEqualTo((byte)0);
        if (name != null) {
            criteria.andVoteContentLike("%" + name + "%");
            criteria1.andVoteContentLike("%" + name + "%");
        }
        if (isFinish != null) {
            Date date = new Date();
            if (isFinish.equals(0)) {
                criteria.andEndTimeGreaterThan(date);
                criteria1.andEndTimeGreaterThan(date);
            } else {
                criteria.andEndTimeLessThan(date);
                criteria1.andEndTimeLessThan(date);
            }
        }
        voteExample.or(criteria1);
        long sum = voteMapper.countByExample(voteExample);
        voteExample.setOffset((currentPage - 1) * pageSize);
        voteExample.setLimit(pageSize);
        List<Vote> votes = voteMapper.selectByExample(voteExample);
        JSONObject data = new JSONObject();
        data.put("total", sum);
        JSONArray list = new JSONArray();
        for (Vote vote : votes) {
            JSONObject object = new JSONObject();
            object.put("id", vote.getVoteId());
            object.put("name", vote.getVoteContent());
            object.put("type", vote.getOptionalNum() !=null && vote.getOptionalNum() > 1 ? "多选" : "单选");
            object.put("time", vote.getEndTime());
            list.add(object);
        }
        data.put("list", list);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "查询成功！");
        return result;
    }

    @Override
    public JSONObject getVoteDetail(Integer voteId) {
        JSONObject result = new JSONObject();
        Vote vote = voteMapper.selectByPrimaryKey(voteId);
        JSONObject data = new JSONObject();
        if (vote == null) {
            result.put("code", "fail");
            result.put("msg", "系统中找不到该投票项目");
            result.put("data", data);
            return result;
        }
        data.put("name", vote.getVoteContent());
        data.put("date", vote.getEndTime());
        data.put("type", vote.getOptionalNum() > 0 ? "多选" : "单选");
        data.put("num", vote.getOptionalSum());
        JSONArray options = new JSONArray();

        VoteOptionExample voteOptionExample = new VoteOptionExample();
        VoteOptionExample.Criteria criteria = voteOptionExample.createCriteria();
        criteria.andVoteIdEqualTo(voteId);
        List<VoteOption> voteOptions = voteOptionMapper.selectByExample(voteOptionExample);
        for (VoteOption voteOption : voteOptions) {
            options.add(voteOption.getOptionText());
        }
        data.put("options", options);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "成功获取投票信息");
        return result;
    }

    @Override
    public JSONObject memberVote(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        int voteId = jsonObject.getInteger("voteId");
        VoteOptionExample voteOptionExample = new VoteOptionExample();
        voteOptionExample.createCriteria().andVoteIdEqualTo(voteId);
        List<VoteOption> voteOptions = voteOptionMapper.selectByExample(voteOptionExample);

        JSONArray array = jsonObject.getJSONArray("data");
        if (array.size() > voteOptions.size()) {
            result.put("code", "fail");
            result.put("msg", "投票选项已变更，请刷新页面");
            return result;
        }
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = array.getJSONObject(i);
            int index = object.getInteger("index");
            VoteOptionMember voteOptionMember = new VoteOptionMember();
            voteOptionMember.setMemberKeyId(jsonObject.getInteger("id"));
            if (voteOptions.get(index - 1) == null) {
                result.put("code", "fail");
                result.put("msg", "该投票下无第" + index + "个选项");
                return result;
            }
            voteOptionMember.setOptionId(voteOptions.get(index - 1).getOptionId());
            voteOptionMember.setVoteId(voteId);
            VoteOptionMemberExample voteOptionMemberExample = new VoteOptionMemberExample();
            voteOptionMemberExample.createCriteria().andOptionIdEqualTo(voteOptions.get(index - 1).getOptionId()).andVoteIdEqualTo(voteId).andMemberKeyIdEqualTo(jsonObject.getInteger("id"));
            if (voteOptionMemberMapper.countByExample(voteOptionMemberExample) == 0) {
                voteOptionMemberMapper.insert(voteOptionMember);
            }
        }
        result.put("code", "success");
        result.put("msg", "投票已提交！");
        return result;
    }

    @Override
    public JSONObject getMemberHasVotedList(String name, Integer isFinish, Integer currentPage, Integer pageSize) {
        return null;
    }

    //学员
    @Override
    public JSONObject getStudentCanVoteList(String name, Integer isFinish, Integer currentPage, Integer pageSize) {
        JSONObject result = new JSONObject();
        VoteExample voteExample = new VoteExample();
        VoteExample.Criteria criteria = voteExample.createCriteria();
        VoteExample.Criteria criteria1 = voteExample.createCriteria();
        criteria.andVoteTypeEqualTo((byte)2);
        criteria1.andVoteTypeEqualTo((byte)1);
        if (name != null) {
            criteria.andVoteContentLike("%" + name + "%");
            criteria1.andVoteContentLike("%" + name + "%");
        }
        if (isFinish != null) {
            Date date = new Date();
            if (isFinish.equals(0)) {
                criteria.andEndTimeGreaterThan(date);
                criteria1.andEndTimeGreaterThan(date);
            } else {
                criteria.andEndTimeLessThan(date);
                criteria1.andEndTimeLessThan(date);
            }
        }
        voteExample.or(criteria1);
        long sum = voteMapper.countByExample(voteExample);
        voteExample.setOffset((currentPage - 1) * pageSize);
        voteExample.setLimit(pageSize);
        List<Vote> votes = voteMapper.selectByExample(voteExample);
        JSONObject data = new JSONObject();
        data.put("total", sum);
        JSONArray list = new JSONArray();
        for (Vote vote : votes) {
            JSONObject object = new JSONObject();
            object.put("id", vote.getVoteId());
            object.put("name", vote.getVoteContent());
            object.put("type", vote.getOptionalNum() !=null && vote.getOptionalNum() > 1 ? "多选" : "单选");
            object.put("time", vote.getEndTime());
            list.add(object);
        }
        data.put("list", list);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "查询成功！");
        return result;
    }

    @Override
    public JSONObject studentVote(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        int voteId = jsonObject.getInteger("voteId");
        VoteOptionExample voteOptionExample = new VoteOptionExample();
        voteOptionExample.createCriteria().andVoteIdEqualTo(voteId);
        List<VoteOption> voteOptions = voteOptionMapper.selectByExample(voteOptionExample);

        JSONArray array = jsonObject.getJSONArray("data");
        if (array.size() > voteOptions.size()) {
            result.put("code", "fail");
            result.put("msg", "投票选项已变更，请刷新页面");
            return result;
        }
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = array.getJSONObject(i);
            int index = object.getInteger("index");
            VoteOptionStudent voteOptionStudent = new VoteOptionStudent();
            voteOptionStudent.setStudentId(jsonObject.getInteger("id"));
            if (voteOptions.get(index - 1) == null) {
                result.put("code", "fail");
                result.put("msg", "该投票下无第" + index + "个选项");
                return result;
            }
            voteOptionStudent.setOptionId(voteOptions.get(index - 1).getOptionId());
            voteOptionStudent.setVoteId(voteId);
            VoteOptionStudentExample voteOptionStudentExample = new VoteOptionStudentExample();
            voteOptionStudentExample.createCriteria().andOptionIdEqualTo(voteOptions.get(index - 1).getOptionId()).andVoteIdEqualTo(voteId).andVoteIdEqualTo(jsonObject.getInteger("id"));
            if (voteOptionStudentMapper.countByExample(voteOptionStudentExample) == 0) {
                voteOptionStudentMapper.insert(voteOptionStudent);
            }
        }
        result.put("code", "success");
        result.put("msg", "投票已提交！");
        return result;
    }
}
