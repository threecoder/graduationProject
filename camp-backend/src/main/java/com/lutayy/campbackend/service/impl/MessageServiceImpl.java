package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.dao.MessageTextMapper;
import com.lutayy.campbackend.dao.MessageToAdminMapper;
import com.lutayy.campbackend.dao.MessageToMemberMapper;
import com.lutayy.campbackend.dao.MessageToStudentMapper;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageToMemberMapper messageToMemberMapper;
    @Autowired
    MessageToStudentMapper messageToStudentMapper;
    @Autowired
    MessageToAdminMapper messageToAdminMapper;
    @Autowired
    MessageTextMapper messageTextMapper;

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

    //根据消息ID获取消息详情
    @Override
    public JSONObject getMsgDetail(Integer msgID, String role) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();
        result.put("code", "fail");
        result.put("msg", "无该条消息记录");
        result.put("data", data);

        if(role.equals("member")) {
            MessageToMember messageToMember=messageToMemberMapper.selectByPrimaryKey(msgID);
            if(messageToMember==null)
                return result;
            data.put("id", messageToMember.getId());
            MessageText messageText=messageTextMapper.selectByPrimaryKey(messageToMember.getMessageId());
            data.put("type", messageText.getType());
            data.put("time", messageToMember.getSendTime());
            data.put("title", messageText.getTitle());
            data.put("msg", messageText.getMessage());
            data.put("read", messageToMember.getStatus()?"已读":"未读");
        }else if(role.equals("student")) {
            MessageToStudent messageToStudent=messageToStudentMapper.selectByPrimaryKey(msgID);
            if(messageToStudent==null)
                return result;
            data.put("id", messageToStudent.getId());
            MessageText messageText=messageTextMapper.selectByPrimaryKey(messageToStudent.getMessageId());
            data.put("type", messageText.getType());
            data.put("time", messageToStudent.getSendTime());
            data.put("title", messageText.getTitle());
            data.put("msg", messageText.getMessage());
            data.put("read", messageToStudent.getStatus()?"已读":"未读");
        }else {
            MessageToAdmin messageToAdmin=messageToAdminMapper.selectByPrimaryKey(msgID);
            if(messageToAdmin==null)
                return result;
            data.put("id", messageToAdmin.getId());
            MessageText messageText=messageTextMapper.selectByPrimaryKey(messageToAdmin.getMessageId());
            data.put("type", messageText.getType());
            data.put("time", messageToAdmin.getSendTime());
            data.put("title", messageText.getTitle());
            data.put("msg", messageText.getMessage());
            data.put("read", messageToAdmin.getStatus()?"已读":"未读");
        }
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "消息获取成功！");
        return result;
    }

    //根据消息ID删除本账号消息
    @Override
    public JSONObject deleteMsg(JSONObject jsonObject, String role) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        result.put("msg", "无该条消息记录");

        Integer messageToRoleId=jsonObject.getInteger("msgID");
        if(role.equals("member")) {
            MessageToMember messageToMember=messageToMemberMapper.selectByPrimaryKey(messageToRoleId);
            if(messageToMember==null)
                return result;
            messageToMember.setIsInvalid(true);
            messageToMemberMapper.updateByPrimaryKeySelective(messageToMember);
        }else if(role.equals("student")) {
            MessageToStudent messageToStudent=messageToStudentMapper.selectByPrimaryKey(messageToRoleId);
            if(messageToStudent==null)
                return result;
            messageToStudent.setIsInvalid(true);
            messageToStudentMapper.updateByPrimaryKeySelective(messageToStudent);
        }else {
            MessageToAdmin messageToAdmin=messageToAdminMapper.selectByPrimaryKey(messageToRoleId);
            if(messageToAdmin==null)
                return result;
            messageToAdmin.setIsInvalid(true);
            messageToAdminMapper.updateByPrimaryKeySelective(messageToAdmin);
        }
        result.put("code", "success");
        result.put("msg", "成功删除该动态！");
        return result;
    }

    //获取本账号消息列表
    @Override
    public JSONObject getMsgList(Integer pageSize, Integer currentPage, Integer roleId, String role) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();
        JSONArray list=new JSONArray();
        long totalNum;
        if(role.equals("member")) {
            MessageToMemberExample example = new MessageToMemberExample();
            example.createCriteria().andMemberIdEqualTo(roleId).andIsInvalidEqualTo(false).andStatusEqualTo(false);
            totalNum=messageToMemberMapper.countByExample(example);
            example.setOrderByClause("status ASC, send_time DESC");
            List<MessageToMember> messageToMemberList=messageToMemberMapper.selectByExample(example);
            for(MessageToMember messageToMember:messageToMemberList){
                JSONObject object=new JSONObject();
                object.put("id", messageToMember.getId());
                MessageText messageText=messageTextMapper.selectByPrimaryKey(messageToMember.getMessageId());
                object.put("type", messageText.getType());
                object.put("time", messageToMember.getSendTime());
                object.put("title", messageText.getTitle());
                object.put("shortMsg", messageText.getMessage().substring(0, 8));
                object.put("read", messageToMember.getStatus()?"已读":"未读");
                list.add(object);
            }
        }else if(role.equals("student")) {
            MessageToStudentExample example=new MessageToStudentExample();
            example.createCriteria().andStudentIdEqualTo(roleId).andIsInvalidEqualTo(false).andStatusEqualTo(false);
            totalNum=messageToStudentMapper.countByExample(example);
            example.setOrderByClause("status ASC, send_time DESC");
            List<MessageToStudent> messageToStudentList=messageToStudentMapper.selectByExample(example);
            for(MessageToStudent messageToStudent:messageToStudentList){
                JSONObject object=new JSONObject();
                object.put("id", messageToStudent.getId());
                MessageText messageText=messageTextMapper.selectByPrimaryKey(messageToStudent.getMessageId());
                object.put("type", messageText.getType());
                object.put("time", messageToStudent.getSendTime());
                object.put("title", messageText.getTitle());
                object.put("shortMsg", messageText.getMessage().substring(0, 8));
                object.put("read", messageToStudent.getStatus()?"已读":"未读");
                list.add(object);
            }
        }else {
            MessageToAdminExample example=new MessageToAdminExample();
            example.createCriteria().andReceiveAdminIdEqualTo(roleId).andIsInvalidEqualTo(false).andStatusEqualTo(false);
            totalNum=messageToAdminMapper.countByExample(example);
            example.setOrderByClause("status ASC, send_time DESC");
            List<MessageToAdmin> messageToAdminList=messageToAdminMapper.selectByExample(example);
            for(MessageToAdmin messageToAdmin:messageToAdminList){
                JSONObject object=new JSONObject();
                object.put("id", messageToAdmin.getId());
                MessageText messageText=messageTextMapper.selectByPrimaryKey(messageToAdmin.getMessageId());
                object.put("type", messageText.getType());
                object.put("time", messageToAdmin.getSendTime());
                object.put("title", messageText.getTitle());
                object.put("shortMsg", messageText.getMessage().substring(0, 8));
                object.put("read", messageToAdmin.getStatus()?"已读":"未读");
                list.add(object);
            }
        }
        result.put("code", "success");
        result.put("msg", "获取消息列表成功！");
        data.put("data", list);
        data.put("total", totalNum);
        result.put("data", data);
        return result;
    }
}
