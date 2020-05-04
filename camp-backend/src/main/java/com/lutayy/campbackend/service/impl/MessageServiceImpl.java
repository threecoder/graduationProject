package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.dao.*;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.plugin2.message.Message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    TrainingMapper trainingMapper;
    @Autowired
    TrainingReStudentMapper trainingReStudentMapper;
    @Autowired
    MessageToMemberMapper messageToMemberMapper;
    @Autowired
    MessageToStudentMapper messageToStudentMapper;
    @Autowired
    MessageToAdminMapper messageToAdminMapper;
    @Autowired
    MessageTextMapper messageTextMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    GetObjectHelper getObjectHelper;

    //查询本账号未读消息数量
    @Override
    public JSONObject getUnReadMsgNum(Integer id, String role) {
        JSONObject result = new JSONObject();
        long unReadNum;
        if (role.equals("member")) {
            MessageToMemberExample example = new MessageToMemberExample();
            example.createCriteria().andMemberIdEqualTo(id).andIsInvalidEqualTo(false).andStatusEqualTo(false);
            unReadNum = messageToMemberMapper.countByExample(example);
        } else if (role.equals("student")) {
            MessageToStudentExample example = new MessageToStudentExample();
            example.createCriteria().andStudentIdEqualTo(id).andIsInvalidEqualTo(false).andStatusEqualTo(false);
            unReadNum = messageToStudentMapper.countByExample(example);
        } else {
            MessageToAdminExample example = new MessageToAdminExample();
            example.createCriteria().andReceiveAdminIdEqualTo(id).andIsInvalidEqualTo(false).andStatusEqualTo(false);
            unReadNum = messageToAdminMapper.countByExample(example);
        }
        result.put("code", "success");
        result.put("msg", "查询未读数目成功！");
        result.put("data", unReadNum);
        return result;
    }

    //标记某条信息为已读
    @Override
    public JSONObject signAsRead(JSONObject jsonObject, String role) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        result.put("msg", "无该条消息记录");

        Integer messageToRoleId = jsonObject.getInteger("msgID");
        if (role.equals("member")) {
            MessageToMember messageToMember = messageToMemberMapper.selectByPrimaryKey(messageToRoleId);
            if (messageToMember == null)
                return result;
            messageToMember.setStatus(true);
            messageToMemberMapper.updateByPrimaryKeySelective(messageToMember);
        } else if (role.equals("student")) {
            MessageToStudent messageToStudent = messageToStudentMapper.selectByPrimaryKey(messageToRoleId);
            if (messageToStudent == null)
                return result;
            messageToStudent.setStatus(true);
            messageToStudentMapper.updateByPrimaryKeySelective(messageToStudent);
        } else {
            MessageToAdmin messageToAdmin = messageToAdminMapper.selectByPrimaryKey(messageToRoleId);
            if (messageToAdmin == null)
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
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("code", "fail");
        result.put("msg", "无该条消息记录");
        result.put("data", data);

        if (role.equals("member")) {
            MessageToMember messageToMember = messageToMemberMapper.selectByPrimaryKey(msgID);
            if (messageToMember == null)
                return result;
            data.put("id", messageToMember.getId());
            MessageText messageText = messageTextMapper.selectByPrimaryKey(messageToMember.getMessageId());
            data.put("type", messageText.getType());
            data.put("time", messageToMember.getSendTime());
            data.put("title", messageText.getTitle());
            data.put("msg", messageText.getMessage());
            data.put("read", messageToMember.getStatus() ? "已读" : "未读");
        } else if (role.equals("student")) {
            MessageToStudent messageToStudent = messageToStudentMapper.selectByPrimaryKey(msgID);
            if (messageToStudent == null)
                return result;
            data.put("id", messageToStudent.getId());
            MessageText messageText = messageTextMapper.selectByPrimaryKey(messageToStudent.getMessageId());
            data.put("type", messageText.getType());
            data.put("time", messageToStudent.getSendTime());
            data.put("title", messageText.getTitle());
            data.put("msg", messageText.getMessage());
            data.put("read", messageToStudent.getStatus() ? "已读" : "未读");
        } else {
            MessageToAdmin messageToAdmin = messageToAdminMapper.selectByPrimaryKey(msgID);
            if (messageToAdmin == null)
                return result;
            data.put("id", messageToAdmin.getId());
            MessageText messageText = messageTextMapper.selectByPrimaryKey(messageToAdmin.getMessageId());
            data.put("type", messageText.getType());
            data.put("time", messageToAdmin.getSendTime());
            data.put("title", messageText.getTitle());
            data.put("msg", messageText.getMessage());
            data.put("read", messageToAdmin.getStatus() ? "已读" : "未读");
        }
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "消息获取成功！");
        return result;
    }

    //根据消息ID删除本账号消息
    @Override
    public JSONObject deleteMsg(JSONObject jsonObject, String role) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        result.put("msg", "无该条消息记录");

        Integer messageToRoleId = jsonObject.getInteger("msgID");
        if (role.equals("member")) {
            MessageToMember messageToMember = messageToMemberMapper.selectByPrimaryKey(messageToRoleId);
            if (messageToMember == null)
                return result;
            messageToMember.setIsInvalid(true);
            messageToMemberMapper.updateByPrimaryKeySelective(messageToMember);
        } else if (role.equals("student")) {
            MessageToStudent messageToStudent = messageToStudentMapper.selectByPrimaryKey(messageToRoleId);
            if (messageToStudent == null)
                return result;
            messageToStudent.setIsInvalid(true);
            messageToStudentMapper.updateByPrimaryKeySelective(messageToStudent);
        } else {
            MessageToAdmin messageToAdmin = messageToAdminMapper.selectByPrimaryKey(messageToRoleId);
            if (messageToAdmin == null)
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
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();
        long totalNum;
        if (role.equals("member")) {
            MessageToMemberExample example = new MessageToMemberExample();
            example.createCriteria().andMemberIdEqualTo(roleId).andIsInvalidEqualTo(false);
            totalNum = messageToMemberMapper.countByExample(example);
            example.setOrderByClause("status ASC, send_time DESC");
            List<MessageToMember> messageToMemberList = messageToMemberMapper.selectByExample(example);
            for (MessageToMember messageToMember : messageToMemberList) {
                JSONObject object = new JSONObject();
                object.put("id", messageToMember.getId());
                MessageText messageText = messageTextMapper.selectByPrimaryKey(messageToMember.getMessageId());
                object.put("type", messageText.getType());
                object.put("time", messageToMember.getSendTime());
                object.put("title", messageText.getTitle());
                String msg=messageText.getMessage();
                object.put("shortMsg", msg.length()>8?msg.substring(0, 8)+"...":msg);
                object.put("read", messageToMember.getStatus() ? "已读" : "未读");
                list.add(object);
            }
        } else if (role.equals("student")) {
            MessageToStudentExample example = new MessageToStudentExample();
            example.createCriteria().andStudentIdEqualTo(roleId).andIsInvalidEqualTo(false);
            totalNum = messageToStudentMapper.countByExample(example);
            example.setOrderByClause("status ASC, send_time DESC");
            List<MessageToStudent> messageToStudentList = messageToStudentMapper.selectByExample(example);
            for (MessageToStudent messageToStudent : messageToStudentList) {
                JSONObject object = new JSONObject();
                object.put("id", messageToStudent.getId());
                MessageText messageText = messageTextMapper.selectByPrimaryKey(messageToStudent.getMessageId());
                object.put("type", messageText.getType());
                object.put("time", messageToStudent.getSendTime());
                object.put("title", messageText.getTitle());
                String msg = messageText.getMessage();
                object.put("shortMsg", msg.length()>8?msg.substring(0, 8)+"...":msg);
                object.put("read", messageToStudent.getStatus() ? "已读" : "未读");
                list.add(object);
            }
        } else {
            MessageToAdminExample example = new MessageToAdminExample();
            example.createCriteria().andReceiveAdminIdEqualTo(roleId).andIsInvalidEqualTo(false);
            totalNum = messageToAdminMapper.countByExample(example);
            example.setOrderByClause("status ASC, send_time DESC");
            List<MessageToAdmin> messageToAdminList = messageToAdminMapper.selectByExample(example);
            for (MessageToAdmin messageToAdmin : messageToAdminList) {
                JSONObject object = new JSONObject();
                object.put("id", messageToAdmin.getId());
                MessageText messageText = messageTextMapper.selectByPrimaryKey(messageToAdmin.getMessageId());
                object.put("type", messageText.getType());
                object.put("time", messageToAdmin.getSendTime());
                object.put("title", messageText.getTitle());
                object.put("shortMsg", messageText.getMessage().substring(0, 8));
                object.put("read", messageToAdmin.getStatus() ? "已读" : "未读");
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

    //管理员发送站内信
    @Override
    public JSONObject sendMsg(JSONObject jsonObject) {
        JSONObject result = new JSONObject();

        Integer adminId=jsonObject.getInteger("id");
        String title = jsonObject.getString("title");
        String messageType = jsonObject.getString("type");
        String recipientType = jsonObject.getString("recipientType");
        String msgContent = jsonObject.getString("msg");
        JSONArray studentIdcards = jsonObject.getJSONArray("student");
        JSONArray memberIds = jsonObject.getJSONArray("member");
        MessageText messageText = new MessageText();
        messageText.setTitle(title);
        messageText.setMessage(msgContent);
        Date nowTime=new Date();
        messageText.setSendTime(nowTime);
        messageText.setType(messageType);
        messageTextMapper.insertSelective(messageText);
        int messageTextId=messageText.getMessageId();
        if(recipientType.equals("all") || recipientType.equals("allStudent")){
            StudentExample studentExample=new StudentExample();
            List<Student> students=studentMapper.selectByExample(studentExample);
            for(Student student:students){
                MessageToStudent messageToStudent=new MessageToStudent();
                messageToStudent.setAdminId(adminId);
                messageToStudent.setSendTime(nowTime);
                messageToStudent.setStudentId(student.getStudentId());
                messageToStudent.setMessageId(messageTextId);
                messageToStudentMapper.insertSelective(messageToStudent);
            }
        }
        if(recipientType.equals("all") || recipientType.equals("allMember")){
            MemberExample memberExample=new MemberExample();
            List<Member> members=memberMapper.selectByExample(memberExample);
            for(Member member:members){
                MessageToMember messageToMember=new MessageToMember();
                messageToMember.setAdminId(adminId);
                messageToMember.setSendTime(nowTime);
                messageToMember.setMessageId(messageTextId);
                messageToMember.setMemberId(member.getMemberKeyId());
                messageToMemberMapper.insertSelective(messageToMember);
            }
        }
        if(recipientType.equals("partStudent")){
            for(int i=0;i<studentIdcards.size();i++){
                Student student=getObjectHelper.getStudentFromIdCard(studentIdcards.getString(i));
                if(student==null)
                    continue;
                MessageToStudent messageToStudent=new MessageToStudent();
                messageToStudent.setAdminId(adminId);
                messageToStudent.setSendTime(nowTime);
                messageToStudent.setStudentId(student.getStudentId());
                messageToStudent.setMessageId(messageTextId);
                messageToStudentMapper.insertSelective(messageToStudent);
            }
        }
        if(recipientType.equals("partMember")){
            for(int i=0;i<memberIds.size();i++){
                Member member=memberMapper.selectByPrimaryKey(memberIds.getInteger(i));
                if(member==null)
                    continue;
                MessageToMember messageToMember=new MessageToMember();
                messageToMember.setAdminId(adminId);
                messageToMember.setSendTime(nowTime);
                messageToMember.setMessageId(messageTextId);
                messageToMember.setMemberId(member.getMemberKeyId());
                messageToMemberMapper.insertSelective(messageToMember);
            }
        }
        result.put("code", "success");
        result.put("msg", "成功发送站内信！");
        return result;
    }

    //管理员提醒会员续费
    @Override
    public JSONObject remindRenew(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        Integer memberId=jsonObject.getInteger("memberId");
        Member member=memberMapper.selectByPrimaryKey(memberId);
        if(member==null){
            result.put("msg", "系统中找不到该会员");
            return result;
        }
        Date nowTime = new Date();
        MessageText messageText=new MessageText();
        messageText.setTitle("***续费提醒***");
        messageText.setSendTime(nowTime);
        messageText.setMessage("您的会员将于"+member.getVipEndDate()+"过期，请及时续费。");
        messageText.setType("memberSubscription");
        messageTextMapper.insertSelective(messageText);
        MessageToMember messageToMember=new MessageToMember();
        messageToMember.setMemberId(memberId);
        messageToMember.setMessageId(messageText.getMessageId());
        messageToMember.setSendTime(nowTime);
        messageToMember.setAdminId(jsonObject.getInteger("id"));
        messageToMemberMapper.insertSelective(messageToMember);
        result.put("code", "success");
        result.put("msg", "成功发送续费通知！");
        return result;
    }

    //管理员提醒所有会员报名指定培训
    @Override
    public JSONObject promptAllMemberEnrollTraining(JSONObject jsonObject) {
        JSONObject result=new JSONObject();

        Integer adminId=jsonObject.getInteger("id");
        result.put("code", "fail");
        Integer trainingId=jsonObject.getInteger("trainingId");
        Training training=trainingMapper.selectByPrimaryKey(trainingId);
        if(training==null){
            result.put("msg", "发送通知失败，该培训不存在");
            return result;
        }
        Date nowTime=new Date();
        MessageText messageText=new MessageText();
        messageText.setType("newTraining");
        messageText.setTitle("新培训发布："+training.getTrainingName());
        messageText.setSendTime(nowTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        messageText.setMessage("新培训\""+training.getTrainingName()+"\"已发布，现在可报名，培训将于"+simpleDateFormat.format(training.getTrainingStartTime())+"开始");
        messageTextMapper.insertSelective(messageText);
        int messageTextId=messageText.getMessageId();
        MemberExample memberExample=new MemberExample();
        List<Member> members=memberMapper.selectByExample(memberExample);
        for(Member member:members){
            MessageToMember messageToMember=new MessageToMember();
            messageToMember.setAdminId(adminId);
            messageToMember.setSendTime(nowTime);
            messageToMember.setMessageId(messageTextId);
            messageToMember.setMemberId(member.getMemberKeyId());
            messageToMemberMapper.insertSelective(messageToMember);
        }
        result.put("code", "success");
        result.put("msg", "消息发送成功!");
        return result;
    }

    //管理员提醒所有未报名学员报名培训
    @Override
    public JSONObject promptAllStudentEnrollTraining(JSONObject jsonObject) {
        JSONObject result=new JSONObject();

        Integer adminId=jsonObject.getInteger("id");
        result.put("code", "fail");
        Integer trainingId=jsonObject.getInteger("trainingId");
        Training training=trainingMapper.selectByPrimaryKey(trainingId);
        if(training==null){
            result.put("msg", "发送通知失败，该培训不存在");
            return result;
        }
        Date nowTime=new Date();
        MessageText messageText=new MessageText();
        messageText.setType("newTraining");
        messageText.setTitle("新培训发布："+training.getTrainingName());
        messageText.setSendTime(nowTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        messageText.setMessage("新培训\""+training.getTrainingName()+"\"已发布，现在可报名，培训将于"+simpleDateFormat.format(training.getTrainingStartTime())+"开始");
        messageTextMapper.insertSelective(messageText);
        int messageTextId=messageText.getMessageId();

        TrainingReStudentExample trainingReStudentExample=new TrainingReStudentExample();
        trainingReStudentExample.createCriteria().andTrainingIdEqualTo(trainingId);
        List<TrainingReStudent> trainingReStudents=trainingReStudentMapper.selectByExample(trainingReStudentExample);
        List<Integer> studentIds=new ArrayList<>();
        for(TrainingReStudent trainingReStudent:trainingReStudents){
            studentIds.add(trainingReStudent.getStudentId());
        }
        StudentExample studentExample=new StudentExample();
        studentExample.createCriteria().andStudentIdNotIn(studentIds);
        List<Student> students=studentMapper.selectByExample(studentExample);
        for(Student student:students){
            MessageToStudent messageToStudent=new MessageToStudent();
            messageToStudent.setAdminId(adminId);
            messageToStudent.setSendTime(nowTime);
            messageToStudent.setStudentId(student.getStudentId());
            messageToStudent.setMessageId(messageTextId);
            messageToStudentMapper.insertSelective(messageToStudent);
        }
        result.put("code", "success");
        result.put("msg", "消息发送成功!");
        return result;
    }

    //管理员提醒部分会员报名培训
    @Override
    public JSONObject promptMemberEnrollTraining(JSONObject jsonObject) {
        JSONObject result=new JSONObject();

        JSONArray memberIds=jsonObject.getJSONArray("ids");
        Integer adminId=jsonObject.getInteger("id");
        result.put("code", "fail");
        Integer trainingId=jsonObject.getInteger("trainingId");
        Training training=trainingMapper.selectByPrimaryKey(trainingId);
        if(training==null){
            result.put("msg", "发送通知失败，该培训不存在");
            return result;
        }
        Date nowTime=new Date();
        MessageText messageText=new MessageText();
        messageText.setType("newTraining");
        messageText.setTitle("新培训发布："+training.getTrainingName());
        messageText.setSendTime(nowTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        messageText.setMessage("新培训\""+training.getTrainingName()+"\"已发布，现在可报名，培训将于"+simpleDateFormat.format(training.getTrainingStartTime())+"开始");
        messageTextMapper.insertSelective(messageText);
        int messageTextId=messageText.getMessageId();
        for(int i=0;i<memberIds.size();i++){
            Member member=memberMapper.selectByPrimaryKey(memberIds.getInteger(i));
            if(member==null)
                continue;
            MessageToMember messageToMember=new MessageToMember();
            messageToMember.setAdminId(adminId);
            messageToMember.setSendTime(nowTime);
            messageToMember.setMessageId(messageTextId);
            messageToMember.setMemberId(member.getMemberKeyId());
            messageToMemberMapper.insertSelective(messageToMember);
        }
        result.put("code", "success");
        result.put("msg", "消息发送成功!");
        return result;
    }

    //管理员提醒部分学员报名培训
    @Override
    public JSONObject promptStudentEnrollTraining(JSONObject jsonObject) {
        JSONObject result=new JSONObject();

        JSONArray studentIdcards=jsonObject.getJSONArray("idNums");
        Integer adminId=jsonObject.getInteger("id");
        result.put("code", "fail");
        Integer trainingId=jsonObject.getInteger("trainingId");
        Training training=trainingMapper.selectByPrimaryKey(trainingId);
        if(training==null){
            result.put("msg", "发送通知失败，该培训不存在");
            return result;
        }
        Date nowTime=new Date();
        MessageText messageText=new MessageText();
        messageText.setType("newTraining");
        messageText.setTitle("新培训发布："+training.getTrainingName());
        messageText.setSendTime(nowTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        messageText.setMessage("新培训\""+training.getTrainingName()+"\"已发布，现在可报名，培训将于"+simpleDateFormat.format(training.getTrainingStartTime())+"开始");
        messageTextMapper.insertSelective(messageText);
        int messageTextId=messageText.getMessageId();

        for(int i=0;i<studentIdcards.size();i++){
            Student student=getObjectHelper.getStudentFromIdCard(studentIdcards.getString(i));
            if(student==null)
                continue;
            MessageToStudent messageToStudent=new MessageToStudent();
            messageToStudent.setAdminId(adminId);
            messageToStudent.setSendTime(nowTime);
            messageToStudent.setStudentId(student.getStudentId());
            messageToStudent.setMessageId(messageTextId);
            messageToStudentMapper.insertSelective(messageToStudent);
        }

        result.put("code", "success");
        result.put("msg", "消息发送成功!");
        return result;
    }
}
