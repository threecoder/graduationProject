package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.config.AuthorityParam;
import com.lutayy.campbackend.dao.AdminReAuthorityMapper;
import com.lutayy.campbackend.pojo.Admin;
import com.lutayy.campbackend.pojo.AdminReAuthority;
import com.lutayy.campbackend.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    GetObjectHelper getObjectHelper;
    @Autowired
    AdminReAuthorityMapper adminReAuthorityMapper;

    @Override
    public JSONObject modifyAuthority(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");

        String account = jsonObject.getString("account");
        Admin admin = getObjectHelper.getAdminFromAccount(account);
        if (admin == null) {
            result.put("msg", "对应的账号不存在，请检查输入是否正确");
            return result;
        }
        int adminId = admin.getAdminId();

        JSONObject authority = jsonObject.getJSONObject("authority");
        boolean memberAu = authority.getBooleanValue("member");
        boolean studentAu = authority.getBooleanValue("student");
        boolean trainingAu = authority.getBooleanValue("training");
        boolean examAu = authority.getBooleanValue("exam");
        boolean gradeAu = authority.getBooleanValue("grade");
        boolean certificateAu = authority.getBooleanValue("certificate");
        boolean activityAu = authority.getBooleanValue("activity");
        boolean voteAu = authority.getBooleanValue("vote");
        boolean indexAu = authority.getBooleanValue("index");
        boolean systemAu = authority.getBooleanValue("system");
        boolean orderAu = authority.getBooleanValue("order");
        boolean couponAu = authority.getBooleanValue("coupon");
        boolean[] authorityList = {false, memberAu, studentAu, trainingAu, examAu, gradeAu, certificateAu, activityAu, voteAu, indexAu, systemAu, orderAu, couponAu};

        for (int i = 1; i <= 12; i++) {
            AdminReAuthority adminReAuthority = getObjectHelper.getAdminReAuthority(adminId, i);
            if (adminReAuthority == null) {
                adminReAuthority = new AdminReAuthority();
                adminReAuthority.setAdminId(adminId);
                adminReAuthority.setAuthorityId(i);
                adminReAuthority.setHasOrNot(authorityList[i]);
                adminReAuthority.setAuthorityName(AuthorityParam.authorityList.get(i));
                adminReAuthorityMapper.insert(adminReAuthority);
            } else {
                adminReAuthority.setHasOrNot(authorityList[i]);
                adminReAuthorityMapper.updateByPrimaryKey(adminReAuthority);
            }
        }
        result.put("code", "success");
        result.put("msg", "账号" + account + "的权限更新成功！");
        return result;
    }

    @Override
    public JSONObject getAuthority(String account) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();

        Admin admin = getObjectHelper.getAdminFromAccount(account);
        if (admin == null) {
            result.put("msg", "对应的账号不存在，请检查输入是否正确");
            return result;
        }
        int adminId = admin.getAdminId();

        for (int i = 1; i <= 12; i++) {
            AdminReAuthority adminReAuthority = getObjectHelper.getAdminReAuthority(adminId, i);
            if (adminReAuthority == null) {
                data.put(AuthorityParam.authorityList.get(i), false);
            } else {
                if(adminReAuthority.getHasOrNot()==null || !adminReAuthority.getHasOrNot()){
                    data.put(AuthorityParam.authorityList.get(i), false);
                }else {
                    data.put(AuthorityParam.authorityList.get(i), true);
                }
            }
        }
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "权限查询成功！");
        return result;
    }
}
