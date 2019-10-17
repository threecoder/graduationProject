package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.util.ExcelUtil;
import com.lutayy.campbackend.dao.*;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.ExamService;
import com.lutayy.campbackend.service.SQLConn.ExamStudentSQLConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    ExamQuestionStudentAnswerMapper examQuestionStudentAnswerMapper;

    /** 由身份证获得学员Id **/
    private int getStudentId(String idcard){
        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        criteria.andStudentIdcardEqualTo(idcard);
        List<Student> students=studentMapper.selectByExample(studentExample);
        if(students.size()==0){
            return -1;
        }
        Student student=students.get(0);
        return student.getStudentId();
    }


    @Override
    public JSONObject getHalfExamList(String idcard) {
        JSONObject result=new JSONObject();

        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        criteria.andStudentIdcardEqualTo(idcard);
        List<Student> students=studentMapper.selectByExample(studentExample);
        if(students.size()==0){
            result.put("code", "fail");
            result.put("data",null);
            result.put("msg","用户不存在！");
            return result;
        }
        Student student=students.get(0);
        int userKey=student.getStudentId();

        JSONArray exams = ExamStudentSQLConn.getExamByCondition(userKey,"r.score<e.exam_pass and r.remaining_times<3 and r.remaining_times>0");
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
            result.put("data",null);
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
    public JSONObject getDoneExamList(String idcard){
        JSONObject result=new JSONObject();

        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        criteria.andStudentIdcardEqualTo(idcard);
        List<Student> students=studentMapper.selectByExample(studentExample);
        if(students.size()==0){
            result.put("code", "fail");
            result.put("data",null);
            result.put("msg","用户不存在！");
            return result;
        }
        Student student=students.get(0);
        int userKey=student.getStudentId();

        JSONArray exams = ExamStudentSQLConn.getExamByCondition(userKey,"(r.remaining_times=0 or r.score>=e.exam_pass)");
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
    public JSONObject getExamInfo(JSONObject jsonObject) {
        String idcard=jsonObject.getString("id");
        int examId=jsonObject.getInteger("examId");
        JSONObject result=new JSONObject();

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
        criteria0.andExamIdEqualTo(examId).andStudentIdEqualTo(getStudentId(idcard)).andIsInvalidEqualTo(false);
        List<ExamReStudent> examReStudents=examReStudentMapper.selectByExample(examReStudentExample);
        if(examReStudents.size()==0){
            result.put("code", "fail");
            result.put("data", null);
            result.put("msg", "当前用户没有参加本门考试!");
            return result;
        }
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

        for(ExamReQuestion examReQuestion:examReQuestions){
            JSONObject object=new JSONObject();
            object.put("index", examReQuestion.getQuestionIndex());
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

    @Override
    public JSONObject getExamDetail(JSONObject jsonObject) {
        String idcard=jsonObject.getString("id");
        int examId=jsonObject.getInteger("examId");
        JSONObject result=new JSONObject();

        Exam exam=examMapper.selectByPrimaryKey(examId);
        if(exam==null){
            result.put("code", "fail");
            result.put("data", null);
            result.put("msg", "查询不到该试卷!");
            return result;
        }
        int studentId=getStudentId(idcard);
        ExamReStudentExample examReStudentExample=new ExamReStudentExample();
        ExamReStudentExample.Criteria criteria=examReStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId).andExamIdEqualTo(examId).andIsInvalidEqualTo(false);
        List<ExamReStudent> examReStudents=examReStudentMapper.selectByExample(examReStudentExample);
        if(examReStudents.size()==0){
            result.put("code", "fail");
            result.put("data", null);
            result.put("msg", "当前用户没有参加本门考试!");
            return result;
        }
        ExamReStudent examReStudent=examReStudents.get(0);

        JSONObject data=new JSONObject();
        JSONObject examInfo=new JSONObject();
        examInfo.put("examName", exam.getExamName());
        examInfo.put("date", exam.getExamStartTime());
        examInfo.put("startTime", exam.getExamStartTime());
        examInfo.put("endTime", exam.getExamEndTime());
        examInfo.put("min", exam.getExamLengthMin());
        examInfo.put("grade", examReStudent.getScore());
        data.put("examInfo", examInfo);
        JSONArray questionList=new JSONArray();

        ExamReQuestionExample examReQuestionExample=new ExamReQuestionExample();
        ExamReQuestionExample.Criteria criteria0=examReQuestionExample.createCriteria();
        criteria0.andExamIdEqualTo(examId);
        List<ExamReQuestion> examReQuestions=examReQuestionMapper.selectByExample(examReQuestionExample);
        if(examReQuestions.size()==0){
            result.put("code", "fail");
            data.put("questionList", null);
            result.put("data", data);
            result.put("msg", "该试卷无试题!");
            return result;
        }
        for(ExamReQuestion examReQuestion:examReQuestions){
            JSONObject object=new JSONObject();
            object.put("index", examReQuestion.getQuestionIndex());
            Question question=questionMapper.selectByPrimaryKey(examReQuestion.getQuestionId());
            object.put("title", question.getQuestionState());
            object.put("score", examReQuestion.getScore());
            JSONArray arr=new JSONArray();
            arr.add(question.getChoiceA());
            arr.add(question.getChoiceB());
            arr.add(question.getChoiceC());
            arr.add(question.getChoiceD());
            object.put("arr", arr);
            boolean type=question.getType();
            if(!type){
                object.put("type", 1);
            }else {
                object.put("type", 2);
            }
            JSONArray rightAnswer=new JSONArray();
            rightAnswer.add(question.getRightChoiceOne());
            if(question.getRightChoiceTwo()!=null){rightAnswer.add(question.getRightChoiceTwo());}
            if(question.getRightChoiceThree()!=null){rightAnswer.add(question.getRightChoiceThree());}
            if(question.getRightChoiceFour()!=null){rightAnswer.add(question.getRightChoiceFour());}
            object.put("rightAnswer", rightAnswer);
            JSONArray studentAnswer=new JSONArray();
            ExamQuestionStudentAnswer examQuestionStudentAnswer=examQuestionStudentAnswerMapper.selectByPrimaryKey(examId, question.getQuestionId(),studentId);
            if(examQuestionStudentAnswer==null){
                object.put("studentAnswer", null);
            }else{
                studentAnswer.add(examQuestionStudentAnswer.getAnswerOne());
                if(type){
                    if(examQuestionStudentAnswer.getAnswerTwo()!=null){studentAnswer.add(examQuestionStudentAnswer.getAnswerTwo());}
                    if(examQuestionStudentAnswer.getAnswerThree()!=null){studentAnswer.add(examQuestionStudentAnswer.getAnswerThree());}
                    if(examQuestionStudentAnswer.getAnswerFour()!=null){studentAnswer.add(examQuestionStudentAnswer.getAnswerFour());}
                }
                object.put("studentAnswer", studentAnswer);
            }
            questionList.add(object);
        }
        data.put("questionList", questionList);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "查询成功!");

        return result;
    }

    /**
     * 管理员操作
     * **/
    @Override
    public JSONObject addNewExam(JSONObject jsonObject) {
        JSONObject result=new JSONObject();

        String name=jsonObject.getString("name");
        Short num=jsonObject.getShort("num");
        Short pass=jsonObject.getShort("pass");
        Byte lenMinute=jsonObject.getByte("len");
        int trainingId=jsonObject.getInteger("training");
        Date openTime=jsonObject.getDate("openTime");
        Date closeTime=jsonObject.getDate("closeTime");

        if(trainingMapper.selectByPrimaryKey(trainingId)==null){
            result.put("code", "fail");
            result.put("msg", "所选培训不存在");
            return result;
        }

        Exam exam=new Exam();
        exam.setExamName(name);
        exam.setExamPass(pass);
        exam.setExamNum(num);
        exam.setExamLengthMin(lenMinute);
        exam.setTrainingId(trainingId);
        exam.setExamStartTime(openTime);
        exam.setExamEndTime(closeTime);
        exam.setIsPosted(false);
        if(examMapper.insert(exam)>0){
            result.put("code", "success");
            result.put("msg", "添加考试成功");
        }else {
            result.put("code", "fail");
            result.put("msg", "添加考试失败,请重试");
        }
        return result;
    }
}
