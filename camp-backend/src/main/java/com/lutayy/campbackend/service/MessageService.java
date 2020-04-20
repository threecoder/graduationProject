package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;

public interface MessageService {
    //查询本账号未读消息数量
    JSONObject getUnReadMsgNum(Integer memberId);
}
