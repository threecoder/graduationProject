package com.lutayy.campbackend.service.SQLConn;


import com.lutayy.campbackend.dao.SystemParameterMapper;
import com.lutayy.campbackend.pojo.SystemParameter;
import com.lutayy.campbackend.pojo.SystemParameterExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class SystemParamManager {

    @Autowired
    SystemParameterMapper systemParameterMapper;

    private static SystemParamManager systemParamManager;

    @PostConstruct
    public void init(){
        systemParamManager=this;
        systemParamManager.systemParameterMapper=this.systemParameterMapper;
    }

    public static String getValueByKey(String key){
        SystemParameterExample systemParameterExample=new SystemParameterExample();
        SystemParameterExample.Criteria criteria=systemParameterExample.createCriteria();
        criteria.andParaKeyEqualTo(key).andFlagEqualTo(true);
        SystemParameter systemParameter=systemParamManager.systemParameterMapper.selectByExample(systemParameterExample).get(0);
        if(systemParameter==null){
            return null;
        }
        return systemParameter.getParaValue();
    }

    public static SystemParameter getSystemParameterByParaKey(String key){
        SystemParameterExample systemParameterExample=new SystemParameterExample();
        SystemParameterExample.Criteria criteria=systemParameterExample.createCriteria();
        criteria.andParaKeyEqualTo(key).andFlagEqualTo(true);
        SystemParameter systemParameter=systemParamManager.systemParameterMapper.selectByExample(systemParameterExample).get(0);
        return systemParameter;
    }
}
