package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.dao.*;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.ExamService;
import com.lutayy.campbackend.service.SQLConn.ExamStudentSQLConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ExamMapper examMapper;
    @Autowired
    TrainingMapper trainingMapper;
    @Autowired
    ExamReStudentMapper examReStudentMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    ExamReQuestionMapper examReQuestionMapper;


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
        if(exams.size()==0){
            result.put("code", "fail");
            result.put("data",null);
            result.put("msg","查询无结果");
            return result;
        }
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
        if(exams.size()==0){
            result.put("code", "fail");
            result.put("data",null);
            result.put("msg","查询无结果");
            return result;
        }
        for(int i=0;i<exams.size();i++){
            exams.getJSONObject(i).put("grade", null);
        }
        result.put("code", "success");
        result.put("data",exams);
        result.put("msg","查询成功");
        return result;
    }

    @Override
    public JSONObject getExamInfo(JSONObject jsonObject) {
        String idcard=jsonObject.getString("id");
        int examId=jsonObject.getInteger("examId");
        JSONObject result=new JSONObject();

        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        criteria.andStudentIdcardEqualTo(idcard);
        List<Student> students=studentMapper.selectByExample(studentExample);
        Student student=students.get(0);

        Exam exam=examMapper.selectByPrimaryKey(examId);
        if(exam==null){
            result.put("code", "fail");
            result.put("data", null);
            result.put("msg", "无法查询到该考试信息!");
            return result;
        }
        Training training=trainingMapper.selectByPrimaryKey(exam.getTrainingId());

        ExamReStudentExample examReStudentExample=new ExamReStudentExample();
        ExamReStudentExample.Criteria criteria0=examReStudentExample.createCriteria();
        criteria0.andExamIdEqualTo(examId).andStudentIdEqualTo(student.getStudentId());
        List<ExamReStudent> examReStudents=examReStudentMapper.selectByExample(examReStudentExample);
        ExamReStudent examReStudent=examReStudents.get(0);

        JSONObject data=new JSONObject();
        data.put("courseName", training.getTrainingName());
        data.put("examName", exam.getExamName());
        data.put("timeLength", exam.getExamLengthMin());
        data.put("remainTimes", examReStudent.getRemainingTimes());
        data.put("pass", exam.getExamPass());//及格分数
        result.put("code", "success");
        result.put("data", data);
        result.put("msg", "查询成功!");
        return result;
    }

    @Override
    public JSONObject getExamQuestions(JSONObject jsonObject) {
        int examId=jsonObject.getInteger("examId");
        JSONObject result=new JSONObject();

        ExamReQuestionExample examReQuestionExample=new ExamReQuestionExample();
        ExamReQuestionExample.Criteria criteria=examReQuestionExample.createCriteria();
        criteria.andExamIdEqualTo(examId);
        List<ExamReQuestion> examReQuestions=examReQuestionMapper.selectByExample(examReQuestionExample);

        JSONArray list=new JSONArray();
        JSONObject examInfo=new JSONObject();
        JSONObject data=new JSONObject();

        Exam exam=examMapper.selectByPrimaryKey(examId);
        if(exam==null){
            result.put("code", "fail");
            result.put("data", null);
            result.put("msg", "无法查询到该考试信息");
            return result;
        }
        examInfo.put("name", exam.getExamName());
        examInfo.put("timeLength", exam.getExamLengthMin());

        data.put("examInfo", examInfo);
        if(examReQuestions.size()==0){
            result.put("code", "fail");
            data.put("list", null);
            result.put("data", data);
            result.put("msg", "暂无题目!");
            return result;
        }
        int i = 1;
        for(ExamReQuestion examReQuestion:examReQuestions){
            JSONObject object=new JSONObject();
            object.put("index", i++);
            Question question=questionMapper.selectByPrimaryKey(examReQuestion.getQuestionId());
            object.put("title", question.getQuestionState());
            JSONArray arr=new JSONArray();
            arr.add(question.getChoiceA());
            arr.add(question.getChoiceB());
            arr.add(question.getChoiceC());
            arr.add(question.getChoiceD());
            object.put("arr", arr);
            if(!question.getType()){
                object.put("type", 1);
            }else {
                object.put("type", 2);
            }
            list.add(object);
        }
        data.put("list", list);
        result.put("code", "success");
        result.put("data", data);
        result.put("msg", "查询成功!");
        return result;
    }
}
