package com.lutayy.campbackend.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.util.JwtUtil;
import com.lutayy.campbackend.common.util.Md5;
import com.lutayy.campbackend.dao.StudentMapper;
import com.lutayy.campbackend.pojo.Student;
import com.lutayy.campbackend.pojo.StudentExample;
import com.lutayy.campbackend.pojo.request.TokenRequest;
import com.lutayy.campbackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("dnfslaAWRFaWR561wa&%==_+")
    private String SECRET_KEY;

    @Override
    public  JSONObject login(JSONObject jsonObject, HttpServletResponse response){
        String password = jsonObject.getString("password");
        //手机号码或身份证号登录
        String phoneoridcard = jsonObject.getString("username");

        JSONObject result =new JSONObject();

        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        criteria.andStudentIdcardEqualTo(phoneoridcard);
        List<Student> students=studentMapper.selectByExample(studentExample);
        if(students.size()==0){
            StudentExample studentExample0=new StudentExample();
            StudentExample.Criteria criteria0=studentExample0.createCriteria();
            criteria0.andStudentPhoneEqualTo(phoneoridcard);
            students=studentMapper.selectByExample(studentExample0);
        }

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
        TokenRequest tokenRequest=new TokenRequest();
        tokenRequest.setName(student.getStudentIdcard());
        String token = JwtUtil.sign(tokenRequest, 30*60*1000, SECRET_KEY);
        Cookie cookie=new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);
        result.put("code","success");
        result.put("msg","登陆成功！");
        return  result;
    }
}
