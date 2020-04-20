package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    //查询本账号未读消息数量
    @Override
    public JSONObject getUnReadMsgNum(Integer memberId) {
        return null;
    }
}
