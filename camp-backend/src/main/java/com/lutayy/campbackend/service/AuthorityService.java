package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONObject;

public interface AuthorityService {
    //管理员修改某个账号权限
    JSONObject modifyAuthority(JSONObject jsonObject);
    //管理员获取某个账号的权限列表
    JSONObject getAuthority(String account);
}
