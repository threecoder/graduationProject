package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.lutayy.campbackend.dao.MemberMapper;
import com.lutayy.campbackend.dao.MemberReStudentMapper;
import com.lutayy.campbackend.dao.StudentMapper;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    MemberReStudentMapper memberReStudentMapper;

    /** 由身份证获得学员Id **/
    private Student getStudentIdFromIdCard(String idcard){
        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        criteria.andStudentIdcardEqualTo(idcard);
        List<Student> students=studentMapper.selectByExample(studentExample);
        if(students.size()==0){
            return null;
        }
        Student student=students.get(0);
        return student;
    }
    /** 由手机号码获得学员Id **/
    private Student getStudentIdFromPhone(String phone){
        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        criteria.andStudentPhoneEqualTo(phone);
        List<Student> students=studentMapper.selectByExample(studentExample);
        if(students.size()==0){
            return null;
        }
        Student student=students.get(0);
        return student;
    }

    @Override
    public Object getUserInfo(String id) {
        JSONObject result=new JSONObject();
        Member member=memberMapper.selectByPrimaryKey(id);
        if(member==null){
            result.put("code", "fail");
            result.put("msg","用户不存在！");
            return result;
        }
        JSONObject data=new JSONObject();
        data.put("phone", member.getMemberPhone());
        data.put("name", member.getMemberName());
        data.put("email", member.getMemberEmail());
        data.put("province", member.getMemberProvince());
        data.put("city", member.getMemberCity());
        data.put("area",member.getMemberArea());
        data.put("zone", member.getMemberAddress());
        if(!member.getIsVip()){
            data.put("memberFlag", "否");
        }else{
            data.put("memberFlag", "是");
        }
        data.put("memberBeginDate", member.getVipBeginDate());
        data.put("memberEndDate", member.getVipEndDate());
        data.put("telephone", member.getMemberTel());

        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "查询成功!");
        return result;
    }

    @Override
    public JSONObject setUserInfo(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        String id=jsonObject.getString("id");

        String phone=jsonObject.getString("phone");
        String name=jsonObject.getString("name");
        String email=jsonObject.getString("email");
        String province=jsonObject.getString("province");
        String city=jsonObject.getString("city");
        String area=jsonObject.getString("area");//区镇
        String zone=jsonObject.getString("zone");

        Member member=memberMapper.selectByPrimaryKey(id);
        if(member==null){
            result.put("code", "fail");
            result.put("msg","用户不存在！");
            return result;
        }
        member.setMemberPhone(phone);
        member.setMemberName(name);
        member.setMemberEmail(email);
        member.setMemberProvince(province);
        member.setMemberCity(city);
        member.setMemberArea(area);
        member.setMemberAddress(zone);
        if(memberMapper.updateByPrimaryKeySelective(member)>0){
            result.put("code", "success");
            result.put("msg","信息修改成功");
        }else{
            result.put("code", "fail");
            result.put("msg", "系统繁忙，请稍后尝试");
        }
        return result;
    }

    @Override
    public JSONObject setNewPassword(JSONObject jsonObject) {
        return null;
    }

    @Override
    public JSONObject rechargeVIP(String id) {
        JSONObject result=new JSONObject();
        Member member=memberMapper.selectByPrimaryKey(id);
        if(member==null){
            result.put("code", "fail");
            result.put("msg","用户不存在!");
            return result;
        }
        if(!member.getIsVip()){
            result.put("code", "fail");
            result.put("msg","当前用户非协会会员,如需办理会员,请联系工作人员");
            return result;
        }
        return result;
    }

    @Override
    public JSONObject importSingleStudent(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        String memberId=jsonObject.getString("id");
        String idNum=jsonObject.getString("idNum");
        String phone=jsonObject.getString("phone");
        String name=jsonObject.getString("name");

        Member member=memberMapper.selectByPrimaryKey(memberId);
        if(member==null){
            result.put("code", "fail");
            result.put("msg","用户不存在!");
            return result;
        }

        Student student=getStudentIdFromIdCard(idNum);
        if(student==null){
            Student newStudent=new Student();
            newStudent.setStudentName(name);
            newStudent.setStudentIdcard(idNum);
            String msg;
            //手机号已存在于系统中，则置为空，需对应学员自行设置
            if(getStudentIdFromPhone(phone)==null){
                newStudent.setStudentPhone(phone);
                msg="";
            }else{
                msg="该手机号已存在,请让学员自行更改； ";
            }
            if(studentMapper.insertSelective(newStudent)>0){
                MemberReStudent memberReStudent=new MemberReStudent();
                memberReStudent.setMemberId(memberId);
                int studentId=getStudentIdFromIdCard(idNum).getStudentId();
                memberReStudent.setStudentId(studentId);
                if(memberReStudentMapper.insert(memberReStudent)>0){
                    result.put("code", "success");
                    result.put("msg",msg+"导入并绑定成功!");
                    return result;
                }else {
                    result.put("code", "success");
                    result.put("msg",msg+"导入成功,但绑定失败!");
                    return result;
                }
            }else {
                result.put("code", "fail");
                result.put("msg","系统繁忙,请稍后尝试");
                return result;
            }
        }
        if(student.getStudentPhone()!=null){
            if(!student.getStudentName().equals(name)||!student.getStudentPhone().equals(phone)){
                result.put("code", "fail");
                result.put("msg", "账号绑定失败,所填身份证、姓名或手机号码不一致");
                return result;
            }
        }else {
            if(!student.getStudentName().equals(name)) {
                result.put("code", "fail");
                result.put("msg", "账号绑定失败,所填身份证、姓名不一致");
                return result;
            }
        }


        MemberReStudentExample memberReStudentExample=new MemberReStudentExample();
        MemberReStudentExample.Criteria criteria=memberReStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(student.getStudentId());
        List<MemberReStudent> memberReStudents=memberReStudentMapper.selectByExample(memberReStudentExample);
        if(memberReStudents.size()>0){
            result.put("code", "fail");
            result.put("msg", "绑定失败!系统内已有该账号且有已有挂靠关系");
            return result;
        }
        MemberReStudent memberReStudent=new MemberReStudent();
        memberReStudent.setMemberId(memberId);
        memberReStudent.setStudentId(student.getStudentId());
        if(memberReStudentMapper.insert(memberReStudent)>0){
            result.put("code", "success");
            result.put("msg","账号已存在; 绑定成功!");
            return result;
        }else {
            result.put("code", "fail");
            result.put("msg","绑定失败,请检查与该学员的挂靠关系或稍后再试");
            return result;
        }
    }

    @Override
    public JSONObject deleteOneStudent(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        String memberId=jsonObject.getString("id");
        String idNum=jsonObject.getString("idNum");
        String phone=jsonObject.getString("phone");

        Member member=memberMapper.selectByPrimaryKey(memberId);
        if(member==null){
            result.put("code", "fail");
            result.put("msg","用户不存在!");
            return result;
        }
        Student student=getStudentIdFromIdCard(idNum);
        if(student==null){
            result.put("code", "fail");
            result.put("msg","该账号不存在!");
            return result;
        }
        if(student.getStudentPhone()!=null){
            if(!student.getStudentPhone().equals(phone)){
                result.put("code", "fail");
                result.put("msg", "操作失败,所填身份证和手机号码不一致");
                return result;
            }
        }

        MemberReStudentExample memberReStudentExample=new MemberReStudentExample();
        MemberReStudentExample.Criteria criteria=memberReStudentExample.createCriteria();
        criteria.andMemberIdEqualTo(memberId).andStudentIdEqualTo(student.getStudentId());
        List<MemberReStudent> memberReStudents=memberReStudentMapper.selectByExample(memberReStudentExample);
        if(memberReStudents.size()==0){
            result.put("code", "fail");
            result.put("msg","该学员不挂靠在名下");
            return result;
        }
        MemberReStudent memberReStudent=memberReStudents.get(0);
        if(memberReStudentMapper.deleteByExample(memberReStudentExample)>0){
            result.put("code", "success");
            result.put("msg","成功解除与该学员的挂靠关系!");
        }else{
            result.put("code", "fail");
            result.put("msg","操作失败,请稍后再试");
        }
        return result;
    }
}
