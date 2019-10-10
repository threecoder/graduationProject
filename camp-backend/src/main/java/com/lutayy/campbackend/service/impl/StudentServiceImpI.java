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
        data.put("position", student.getStudentPosition());
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
        student.setStudentPosition(position);
        student.setStudentPhone(phone);
        if(!idNum.equals(student.getStudentIdcard())){
            student.setStudentIdcard(idNum);
            Cookie cookie=new Cookie("token", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.setContentType("application/json;charset=UTF-8");
            result.put("msg","信息修改成功，请重新登录！");
        }else{
            result.put("msg","信息修改成功！");
        }
        if(studentMapper.updateByPrimaryKeySelective(student)>0){
            result.put("code", "success");
        }else {
            result.put("code", "fail");
            result.put("msg", "系统繁忙，请稍后尝试");
        }
        return result;
    }

    @Override
    public JSONObject setNewPassword(JSONObject jsonObject) {
        return null;
    }
}
