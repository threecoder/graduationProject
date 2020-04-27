package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;

public interface MessageService {
    //查询本账号未读消息数量
    JSONObject getUnReadMsgNum(Integer memberId, String role);
    //标记某条信息为已读
    JSONObject signAsRead(JSONObject jsonObject, String role);
    //根据消息ID获取消息详情
    JSONObject getMsgDetail(Integer msgID, String role);
    //根据消息ID删除本账号消息
    JSONObject deleteMsg(JSONObject jsonObject, String role);
    //获取本账号消息列表
    JSONObject getMsgList(Integer pageSize, Integer currentPage, Integer roleId, String role);
    //管理员发送站内信
    JSONObject sendMsg(JSONObject jsonObject);
    //管理员提醒所有会员报名指定培训
    JSONObject promptAllMemberEnrollTraining(JSONObject jsonObject);
    //管理员提醒所有未报名学员报名培训
    JSONObject promptAllStudentEnrollTraining(JSONObject jsonObject);
    //管理员提醒部分会员报名培训
    JSONObject promptMemberEnrollTraining(JSONObject jsonObject);
    //管理员提醒部分学员报名培训
    JSONObject promptStudentEnrollTraining(JSONObject jsonObject);
}
