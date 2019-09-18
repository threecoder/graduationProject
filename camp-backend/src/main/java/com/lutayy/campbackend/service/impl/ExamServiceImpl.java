package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.dao.StudentMapper;
import com.lutayy.campbackend.pojo.Exam;
import com.lutayy.campbackend.pojo.Student;
import com.lutayy.campbackend.pojo.StudentExample;
import com.lutayy.campbackend.service.ExamService;
import com.lutayy.campbackend.service.SQLConn.ExamStudentSQLConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    StudentMapper studentMapper;


    @Override
    public JSONObject getHalfExamList(String idcard) {
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
        int userKey=student.getStudentId();

        JSONArray exams = ExamStudentSQLConn.getExamByCondition(userKey,"r.score<60 and r.remaining_times<3");
        result.put("code", "success");
        result.put("data",exams);
        result.put("msg","查询成功");
        return result;
    }

    @Override
    public JSONObject getTodoExamList(String idcard) {
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
        int userKey=student.getStudentId();

        JSONArray exams = ExamStudentSQLConn.getExamByCondition(userKey,"r.remaining_times=3");
        for(int i=0;i<exams.size();i++){
            exams.getJSONObject(i).put("grade", null);
        }
        result.put("code", "success");
        result.put("data",exams);
        result.put("msg","查询成功");
        return result;
    }
}
