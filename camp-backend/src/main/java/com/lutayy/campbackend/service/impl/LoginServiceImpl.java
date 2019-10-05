package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.util.JwtUtil;
import com.lutayy.campbackend.common.util.Md5;
import com.lutayy.campbackend.dao.MemberMapper;
import com.lutayy.campbackend.dao.StudentMapper;
import com.lutayy.campbackend.pojo.Member;
import com.lutayy.campbackend.pojo.MemberExample;
import com.lutayy.campbackend.pojo.Student;
import com.lutayy.campbackend.pojo.StudentExample;
import com.lutayy.campbackend.pojo.request.TokenRequest;
import com.lutayy.campbackend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    MemberMapper memberMapper;

    @Value("${SECRET_KEY}")
    private String SECRET_KEY;

    @Override
    public JSONObject login(JSONObject jsonObject, HttpServletResponse response){
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();

        String password = jsonObject.getString("password");
        //手机号码或身份证号登录
        String id = jsonObject.getString("username");
        if(id.substring(0, 3).equals("mb_")){
            Member member=memberMapper.selectByPrimaryKey(id);
            if(member==null){
                result.put("code","fail");
                result.put("msg","账号不存在！");
                result.put("data", null);
                return result;
            }
            if(!password.equals(Md5.digest(member.getMemberPassword()))){
                result.put("code","fail");
                result.put("msg","密码错误！");
                result.put("data", null);
                return result;
            }
            TokenRequest tokenRequest=new TokenRequest();
            tokenRequest.setName(id);
            tokenRequest.setRole("member");
            String token = JwtUtil.sign(tokenRequest, 30*60*1000, SECRET_KEY);
            Cookie cookie=new Cookie("token", token);
            //cookie.setMaxAge(3600);
            cookie.setPath("/");
            response.addCookie(cookie);
            data.put("name", member.getMemberName());
            data.put("type", 1);
        }else{
            StudentExample studentExample=new StudentExample();
            StudentExample.Criteria criteria=studentExample.createCriteria();
            criteria.andStudentIdcardEqualTo(id);
            List<Student> students=studentMapper.selectByExample(studentExample);
            if(students.size()==0){
                StudentExample studentExample0=new StudentExample();
                StudentExample.Criteria criteria0=studentExample0.createCriteria();
                criteria0.andStudentPhoneEqualTo(id);
                students=studentMapper.selectByExample(studentExample0);
            }

            if(students.size()==0){
                result.put("code","fail");
                result.put("msg","账号不存在！");
                result.put("data", null);
                return result;
            }
            Student student=students.get(0);
            if(!password.equals(Md5.digest(student.getStudentPassword()))){
                result.put("code","fail");
                result.put("msg","密码错误！");
                result.put("data", null);
                return result;
            }
            TokenRequest tokenRequest=new TokenRequest();
            tokenRequest.setName(student.getStudentIdcard());//身份证号码作为标识
            tokenRequest.setRole("student");
            String token = JwtUtil.sign(tokenRequest, 30*60*1000, SECRET_KEY);
            Cookie cookie=new Cookie("token", token);
            //cookie.setMaxAge(3600);
            cookie.setPath("/");
            response.addCookie(cookie);
            data.put("name", student.getStudentName());
            data.put("type", 0);
        }
        result.put("data", data);
        result.put("code","success");
        result.put("msg","登陆成功！");
        return  result;
    }


    @Override
    public JSONObject logout(HttpServletResponse response) {
        JSONObject result=new JSONObject();

        Cookie cookie=new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setContentType("application/json;charset=UTF-8");
        result.put("code", "success");
        result.put("msg","登出成功!");
        return result;
    }
}
