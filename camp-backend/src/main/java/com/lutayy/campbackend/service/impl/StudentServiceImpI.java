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

    @Value("${SECRET_KEY}")
    private String SECRET_KEY;

    @Override
    public JSONObject login(JSONObject jsonObject, HttpServletResponse response){
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
        tokenRequest.setName(student.getStudentIdcard());//身份证号码作为标识
        String token = JwtUtil.sign(tokenRequest, 30*60*1000, SECRET_KEY);
        Cookie cookie=new Cookie("token", token);
        //cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);
        result.put("code","success");
        result.put("msg","登陆成功！");
        return  result;
    }

    @Override
    public JSONObject getUserInfo(String idcard) {
        JSONObject result=new JSONObject();

        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        criteria.andStudentIdcardEqualTo(idcard);
        List<Student> students=studentMapper.selectByExample(studentExample);
        if(students.size()==0){
            result.put("code", "fail");
            result.put("data","");
            result.put("msg","用户不存在！");
            return result;
        }
        Student student=students.get(0);
        JSONObject data=new JSONObject();
        data.put("idNum", student.getStudentIdcard());
        data.put("phone", student.getStudentPhone());
        data.put("name",student.getStudentName());
        data.put("email",student.getStudentEmail());
        data.put("position", student.getStudentPositon());
        data.put("province", student.getStudentProvince());
        data.put("city", student.getStudentCity());
        data.put("area", student.getStudentArea());
        data.put("zone", student.getStudentAddress());
        result.put("code","success");
        result.put("msg", "查询成功！");
        result.put("data", data);
        return result;
    }

    @Override
    public JSONObject setUserInfo(JSONObject jsonObject,HttpServletResponse response) {
        String idNum=jsonObject.getString("idNum");
        String phone=jsonObject.getString("phone");
        String name=jsonObject.getString("name");
        String email=jsonObject.getString("email");
        String position=jsonObject.getString("position");
        String province=jsonObject.getString("province");
        String city=jsonObject.getString("city");
        String area=jsonObject.getString("area");
        String address=jsonObject.getString("zone");

        JSONObject result=new JSONObject();

        String idcard=jsonObject.getString("id");
        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        criteria.andStudentIdcardEqualTo(idcard);
        List<Student> students=studentMapper.selectByExample(studentExample);
        if(students.size()==0){
            result.put("code", "fail");
            result.put("data","");
            result.put("msg","用户不存在！");
            return result;
        }
        Student student=students.get(0);

        StudentExample studentExample0=new StudentExample();
        StudentExample.Criteria criteria0=studentExample0.createCriteria();
        criteria0.andStudentPhoneEqualTo(phone);
        List<Student> students0=studentMapper.selectByExample(studentExample0);
        if(students0.size()!=0){
            if(!students0.get(0).getStudentId().equals(student.getStudentId())){
                result.put("code", "fail");
                result.put("msg","该手机号码已有其他账户！");
                return result;
            }
        }
        StudentExample studentExample1=new StudentExample();
        StudentExample.Criteria criteria1=studentExample1.createCriteria();
        criteria1.andStudentIdcardEqualTo(idNum);
        List<Student> students1=studentMapper.selectByExample(studentExample1);
        if(students1.size()!=0){
            if(!students1.get(0).getStudentId().equals(student.getStudentId())){
                result.put("code", "fail");
                result.put("msg","该身份证已有其他账户！");
                return result;
            }
        }
        student.setStudentAddress(address);
        student.setStudentCity(city);
        student.setStudentArea(area);
        student.setStudentProvince(province);
        student.setStudentEmail(email);
        student.setStudentName(name);
        student.setStudentPositon(position);
        student.setStudentPhone(phone);
        if(!idNum.equals(student.getStudentIdcard())){
            student.setStudentIdcard(idNum);
            Cookie cookie=new Cookie("token", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.setContentType("application/json;charset=UTF-8");
            result.put("code", "success");
            result.put("msg","信息修改成功，请重新登录！");
        }else{
            result.put("code", "success");
            result.put("msg","信息修改成功！");
        }
        studentMapper.updateByPrimaryKey(student);
        return result;
    }

    @Override
    public JSONObject setNewPassword(JSONObject jsonObject) {
        return null;
    }
}
