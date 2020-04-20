package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.dao.MessageToAdminMapper;
import com.lutayy.campbackend.dao.MessageToMemberMapper;
import com.lutayy.campbackend.dao.MessageToStudentMapper;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageToMemberMapper messageToMemberMapper;
    @Autowired
    MessageToStudentMapper messageToStudentMapper;
    @Autowired
    MessageToAdminMapper messageToAdminMapper;

    //查询本账号未读消息数量
    @Override
    public JSONObject getUnReadMsgNum(Integer id, String role) {
        JSONObject result=new JSONObject();
        long unReadNum;
        if(role.equals("member")) {
            MessageToMemberExample example = new MessageToMemberExample();
            example.createCriteria().andMemberIdEqualTo(id).andIsInvalidEqualTo(false).andStatusEqualTo(false);
            unReadNum = messageToMemberMapper.countByExample(example);
        }else if(role.equals("student")) {
            MessageToStudentExample example=new MessageToStudentExample();
            example.createCriteria().andStudentIdEqualTo(id).andIsInvalidEqualTo(false).andStatusEqualTo(false);
            unReadNum=messageToStudentMapper.countByExample(example);
        }else {
            MessageToAdminExample example=new MessageToAdminExample();
            example.createCriteria().andReceiveAdminIdEqualTo(id).andIsInvalidEqualTo(false).andStatusEqualTo(false);
            unReadNum=messageToAdminMapper.countByExample(example);
        }
        result.put("code", "success");
        result.put("msg", "查询未读数目成功！");
        result.put("data", unReadNum);
        return result;
    }

    //标记某条信息为已读
    @Override
    public JSONObject signAsRead(JSONObject jsonObject, String role) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        result.put("msg", "无该条消息记录");

        Integer messageToRoleId=jsonObject.getInteger("msgID");
        if(role.equals("member")) {
            MessageToMember messageToMember=messageToMemberMapper.selectByPrimaryKey(messageToRoleId);
            if(messageToMember==null)
                return result;
            messageToMember.setStatus(true);
            messageToMemberMapper.updateByPrimaryKeySelective(messageToMember);
        }else if(role.equals("student")) {
            MessageToStudent messageToStudent=messageToStudentMapper.selectByPrimaryKey(messageToRoleId);
            if(messageToStudent==null)
                return result;
            messageToStudent.setStatus(true);
            messageToStudentMapper.updateByPrimaryKeySelective(messageToStudent);
        }else {
            MessageToAdmin messageToAdmin=messageToAdminMapper.selectByPrimaryKey(messageToRoleId);
            if(messageToAdmin==null)
                return result;
            messageToAdmin.setStatus(true);
            messageToAdminMapper.updateByPrimaryKeySelective(messageToAdmin);
        }
        result.put("code", "success");
        result.put("msg", "成功标记为已读");
        return result;
    }
}
