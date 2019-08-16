package com.lutayy.campbackend.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.util.Md5;
import com.lutayy.campbackend.dao.StudentMapper;
import com.lutayy.campbackend.pojo.Student;
import com.lutayy.campbackend.pojo.StudentExample;
import com.lutayy.campbackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

@Service
public class StudentServiceImpI implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public  JSONObject login(JSONObject jsonObject, HttpServletResponse response){
        String password = jsonObject.getString("password");
        //手机号码或身份证号登录
        String phoneoridcard = jsonObject.getString("username");

        JSONObject result =new JSONObject();

        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        if(phoneoridcard.length()>14){
            criteria.andStudentIdcardEqualTo(phoneoridcard);
        }
        else {
            criteria.andStudentPhoneEqualTo(phoneoridcard);
        }
        List<Student> students=studentMapper.selectByExample(studentExample);
        if(students.size()==0){
            result.put("code","fail");
            result.put("msg","账号不存在！");
            return result;
        }
        Student student=students.get(0);
        if(!password.equals(Md5.digest(student.getStudentPassword()))){
            result.put("code","fail");
            result.put("msg","密码错误！");
            return result;
        }
        Cookie cookie=new Cookie("phone", phoneoridcard);
        cookie.setPath("/");
        cookie.setMaxAge(7200);
        response.addCookie(cookie);
        result.put("code","success");
        result.put("msg","登陆成功！");
        return  result;
    }

    @Override
    public JSONObject getall(){
        StudentExample studentExample=new StudentExample();
        List<Student> students=studentMapper.selectByExample(studentExample);
        Iterator<Student> studentIterator=students.iterator();
        JSONArray all=new JSONArray();
        while(studentIterator.hasNext()){
            Student student=studentIterator.next();
            JSONObject data=new JSONObject();
            data.put("id",student.getStudentId());
            data.put("name",student.getStudentName());
            data.put("idcard",student.getStudentIdcard());
            all.add(data);
        }
        JSONObject result=new JSONObject();
        result.put("all",all);
        return result;
    }
}
