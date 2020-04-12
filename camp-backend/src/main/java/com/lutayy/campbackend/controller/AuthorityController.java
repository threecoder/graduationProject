package com.lutayy.campbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;

    @RequestMapping("/admin/modifyAuthority")
    @ResponseBody
    public JSONObject modifyAuthority(@RequestBody JSONObject jsonObject) {
        return authorityService.modifyAuthority(jsonObject);
    }

    @RequestMapping("/admin/getAuthority")
    @ResponseBody
    public Object getAuthority(@RequestParam("account") String account) {
        return authorityService.getAuthority(account);
    }


}
