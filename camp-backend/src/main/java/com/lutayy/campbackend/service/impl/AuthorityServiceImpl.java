package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.config.AuthorityParam;
import com.lutayy.campbackend.dao.AdminReAuthorityMapper;
import com.lutayy.campbackend.dao.SystemParameterMapper;
import com.lutayy.campbackend.pojo.Admin;
import com.lutayy.campbackend.pojo.AdminReAuthority;
import com.lutayy.campbackend.pojo.SystemParameter;
import com.lutayy.campbackend.service.AuthorityService;
import com.lutayy.campbackend.service.SQLConn.SystemParamManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    GetObjectHelper getObjectHelper;
    @Autowired
    AdminReAuthorityMapper adminReAuthorityMapper;
    @Autowired
    SystemParameterMapper systemParameterMapper;

    //管理员修改某个账号权限
    @Override
    public JSONObject modifyAuthority(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");

        String account = jsonObject.getString("account");
        Admin admin = getObjectHelper.getAdminFromAccount(account);
        if(admin.getPermissionValue()){
            result.put("msg", "无法修改root管理员权限！");
            return result;
        }
        if (admin == null) {
            result.put("msg", "对应的账号不存在，请检查输入是否正确");
            return result;
        }

        int adminId = admin.getAdminId();
        if(jsonObject.getInteger("id").equals(adminId)){
            result.put("msg", "不能修改自身权限！");
            return result;
        }

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

    //管理员获取某个账号的权限列表
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

    //管理员获取系统参数
    @Override
    public JSONObject getSystemParams() {
        JSONObject result=new JSONObject();
        result.put("code", "success");
        result.put("msg", "获取成功！");
        JSONObject data=new JSONObject();
        data.put("studentTrainig", SystemParamManager.getValueByKey("stu_tran_permission").equals("1")?"是":"否");
        data.put("trainingOrderTime", SystemParamManager.getValueByKey("training_order_length"));
        data.put("activityOrderTime", SystemParamManager.getValueByKey("activity_order_length"));
        data.put("certificateOrderTime", SystemParamManager.getValueByKey("certificate_order_length"));
        data.put("memberOrderTime", SystemParamManager.getValueByKey("member_order_length"));
        data.put("memberFee", SystemParamManager.getValueByKey("member_Fee"));
        result.put("data", data);
        return result;
    }

    //管理员修改系统参数
    @Override
    public JSONObject modifySystemParams(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        String studentTrainig;
        Integer trainingOrderTime, activityOrderTime, certificateOrderTime, memberOrderTime;
        BigDecimal newMemberFee;
        try{
            studentTrainig=jsonObject.getString("studentTrainig");
            if(!studentTrainig.equals("是")&&!studentTrainig.equals("否")){
                throw new Exception();
            }
            trainingOrderTime=Integer.valueOf(jsonObject.getString("trainingOrderTime"));
            activityOrderTime=Integer.valueOf(jsonObject.getString("activityOrderTime"));
            certificateOrderTime=Integer.valueOf(jsonObject.getString("certificateOrderTime"));
            memberOrderTime=Integer.valueOf(jsonObject.getString("memberOrderTime"));
            newMemberFee=BigDecimal.valueOf(Double.valueOf(jsonObject.getString("memberFee")));
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", "fail");
            result.put("msg", "属性数据类型有误，请检查");
            return result;
        }

        SystemParameter stuTranPermission=SystemParamManager.getSystemParameterByParaKey("stu_tran_permission");
        stuTranPermission.setParaValue(studentTrainig.equals("是")?"1":"0");
        systemParameterMapper.updateByPrimaryKeySelective(stuTranPermission);

        SystemParameter activityOrderLength=SystemParamManager.getSystemParameterByParaKey("activity_order_length");
        activityOrderLength.setParaValue(activityOrderTime.toString());
        systemParameterMapper.updateByPrimaryKeySelective(activityOrderLength);

        SystemParameter trainingOrderLength=SystemParamManager.getSystemParameterByParaKey("training_order_length");
        trainingOrderLength.setParaValue(trainingOrderTime.toString());
        systemParameterMapper.updateByPrimaryKeySelective(trainingOrderLength);

        SystemParameter certificateOrderLength=SystemParamManager.getSystemParameterByParaKey("certificate_order_length");
        certificateOrderLength.setParaValue(certificateOrderTime.toString());
        systemParameterMapper.updateByPrimaryKeySelective(certificateOrderLength);

        SystemParameter memberOrderLength=SystemParamManager.getSystemParameterByParaKey("member_order_length");
        memberOrderLength.setParaValue(memberOrderTime.toString());
        systemParameterMapper.updateByPrimaryKeySelective(memberOrderLength);

        SystemParameter memberFee=SystemParamManager.getSystemParameterByParaKey("member_fee");
        memberFee.setParaValue(newMemberFee.toString());
        systemParameterMapper.updateByPrimaryKeySelective(stuTranPermission);

        result.put("code", "success");
        result.put("msg", "修改成功！");
        return result;
    }
}
