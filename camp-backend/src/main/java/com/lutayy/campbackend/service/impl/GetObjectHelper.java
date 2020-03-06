package com.lutayy.campbackend.service.impl;

import com.lutayy.campbackend.dao.MemberMapper;
import com.lutayy.campbackend.dao.MemberReStudentMapper;
import com.lutayy.campbackend.dao.StudentMapper;
import com.lutayy.campbackend.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GetObjectHelper {
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    MemberReStudentMapper memberReStudentMapper;

    /** 由会员UUID获得会员对象 **/
    public Member getMemberFromUUID(String memberId){
        MemberExample memberExample=new MemberExample();
        MemberExample.Criteria criteria=memberExample.createCriteria();
        criteria.andMemberIdEqualTo(memberId);
        List<Member> members=memberMapper.selectByExample(memberExample);
        if(members.size()>0){
            return members.get(0);
        }else {
            return null;
        }
    }

    /** 由身份证获得学员对象 **/
    public Student getStudentFromIdCard(String idcard){
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

    /** 由手机号码获得学员对象 **/
    public Student getStudentFromPhone(String phone){
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

    /** 检查学员是否已存在绑定关系 **/
    public MemberReStudent getMemberReStudentByStudentId(int studentId){
        MemberReStudentExample memberReStudentExample=new MemberReStudentExample();
        MemberReStudentExample.Criteria criteria=memberReStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId);
        List<MemberReStudent> memberReStudents=memberReStudentMapper.selectByExample(memberReStudentExample);
        if(memberReStudents.size()==0){
            return null;
        }else {
            return memberReStudents.get(0);
        }
    }
}
