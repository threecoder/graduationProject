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
    @Autowired
    GetObjectHelper getObjectHelper;


    @Override
    public JSONObject getUserInfo(Integer studentId) {
        JSONObject result=new JSONObject();

        Student student=studentMapper.selectByPrimaryKey(studentId);
        if(student==null){
            result.put("code", "fail");
            result.put("data","");
            result.put("msg","用户不存在！");
            return result;
        }
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
        data.put("sex", student.getStudentSex());
        data.put("company", student.getCompany());
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
        String sex=jsonObject.getString("sex");

        JSONObject result=new JSONObject();

        Integer studentId=jsonObject.getInteger("id");
        Student student=studentMapper.selectByPrimaryKey(studentId);
        if(student==null){
            result.put("code", "fail");
            result.put("msg","用户不存在！");
            return result;
        }

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
        if(sex!=null)
            student.setStudentSex(sex);
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
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        String oldPassword = jsonObject.getString("oldPassword");
        String newPassword = jsonObject.getString("newPassword");
        if (oldPassword.equals(newPassword)) {
            result.put("msg", "旧密码与新密码不能相同！");
            return result;
        }
        Integer studentId = jsonObject.getInteger("id");
        Student student = studentMapper.selectByPrimaryKey(studentId);
        if (student == null) {
            result.put("msg", "系统中无该用户！");
            return result;
        }
        if (!student.getStudentPassword().equals(oldPassword)) {
            result.put("msg", "旧密码错误！");
        } else {
            student.setStudentPassword(newPassword);
            if (studentMapper.updateByPrimaryKeySelective(student) > 0) {
                result.put("code", "success");
                result.put("msg", "密码更新成功！");
            } else {
                result.put("msg", "密码更新失败！请重试");
            }
        }
        return result;
    }
}
