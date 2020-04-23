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
}
