package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.util.ExcelUtil;
import com.lutayy.campbackend.dao.*;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.ExamService;
import com.lutayy.campbackend.service.SQLConn.ExamStudentSQLConn;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ExamMapper examMapper;
    @Autowired
    TrainingMapper trainingMapper;
    @Autowired
    TrainingReStudentMapper trainingReStudentMapper;
    @Autowired
    ExamReStudentMapper examReStudentMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    ExamReQuestionMapper examReQuestionMapper;
    @Autowired
    ExamQuestionStudentAnswerMapper examQuestionStudentAnswerMapper;


    @Override
    public JSONObject getHalfExamList(Integer studentId) {
        JSONObject result=new JSONObject();

        Student student=studentMapper.selectByPrimaryKey(studentId);
        if(student==null){
            result.put("code", "fail");
            result.put("data",null);
            result.put("msg","用户不存在！");
            return result;
        }

        JSONArray exams = ExamStudentSQLConn.getExamByCondition(studentId,"r.score<e.exam_pass and r.remaining_times<3 and r.remaining_times>0");
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
    public JSONObject getTodoExamList(Integer studentId) {
        JSONObject result=new JSONObject();
        if(studentId==-1){
            result.put("code", "fail");
            result.put("data",null);
            result.put("msg","用户不存在！");
            return result;
        }
        /**
         * 检查是否有已报名的培训新发布了考试，有的话进行更新
         **/
        TrainingReStudentExample trainingReStudentExample=new TrainingReStudentExample();
        TrainingReStudentExample.Criteria criteria=trainingReStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId);
        List<TrainingReStudent> trainingReStudents=trainingReStudentMapper.selectByExample(trainingReStudentExample);
        if(trainingReStudents.size()!=0){
            for (TrainingReStudent trainingReStudent:trainingReStudents){
                ExamExample examExample=new ExamExample();
                ExamExample.Criteria criteria1=examExample.createCriteria();
                criteria1.andTrainingIdEqualTo(trainingReStudent.getTrainingId()).andIsPostedEqualTo(true);
                List<Exam> exams=examMapper.selectByExample(examExample);
                if(exams.size()!=0){
                    for(Exam exam:exams){
                        ExamReStudentExample examReStudentExample=new ExamReStudentExample();
                        ExamReStudentExample.Criteria criteria2=examReStudentExample.createCriteria();
                        criteria2.andStudentIdEqualTo(studentId).andExamIdEqualTo(exam.getExamId());
                        if(examReStudentMapper.selectByExample(examReStudentExample).size()==0){
                            ExamReStudent examReStudent=new ExamReStudent();
                            examReStudent.setExamId(exam.getExamId());
                            examReStudent.setStudentId(studentId);
                            examReStudentMapper.insertSelective(examReStudent);
                        }
                    }
                }
            }
        }

        /** 获取报名的培训中已发布的考试 **/
        JSONArray exams = ExamStudentSQLConn.getExamByCondition(studentId,"r.remaining_times=3");
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
    public JSONObject getDoneExamList(Integer studentId){
        JSONObject result=new JSONObject();
        Student student=studentMapper.selectByPrimaryKey(studentId);
        if(student==null){
            result.put("code", "fail");
            result.put("data",null);
            result.put("msg","用户不存在！");
            return result;
        }

        JSONArray exams = ExamStudentSQLConn.getExamByCondition(studentId,"(r.remaining_times=0 or r.score>=e.exam_pass)");
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
    public JSONObject getExamInfo(Integer studentId,Integer examId) {
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
        criteria0.andExamIdEqualTo(examId).andStudentIdEqualTo(studentId).andIsInvalidEqualTo(false);
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
    public JSONObject getExamQuestions(Integer examId) {
        JSONObject result=new JSONObject();

        ExamReQuestionExample examReQuestionExample=new ExamReQuestionExample();
        ExamReQuestionExample.Criteria criteria=examReQuestionExample.createCriteria();
        criteria.andExamIdEqualTo(examId);
        examReQuestionExample.setOrderByClause("question_index ASC");
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
            object.put("type", question.getType());
            list.add(object);
        }
        data.put("list", list);
        result.put("code", "success");
        result.put("data", data);
        result.put("msg", "查询成功!");
        return result;
    }

    @Override
    public JSONObject getExamDetail(Integer studentId,Integer examId) {
        JSONObject result=new JSONObject();

        Exam exam=examMapper.selectByPrimaryKey(examId);
        if(exam==null){
            result.put("code", "fail");
            result.put("data", null);
            result.put("msg", "查询不到该试卷!");
            return result;
        }
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
            object.put("type", question.getType());
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
                if(question.getType()==1){
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

    @Override
    public JSONObject submitExam(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        Integer studentId=jsonObject.getInteger("id");
        Student student=studentMapper.selectByPrimaryKey(studentId);
        if (student==null){
            result.put("code", "error");
            result.put("msg", "当前登录用户信息错误");
            return result;
        }
        Integer examId=jsonObject.getInteger("examId");
        Exam exam=examMapper.selectByPrimaryKey(examId);
        if(exam==null){
            result.put("code", "fail");
            result.put("msg", "查询不到该试卷!");
            return result;
        }
        JSONArray answer=jsonObject.getJSONArray("answer");

        ExamReQuestionExample examReQuestionExample=new ExamReQuestionExample();
        ExamReQuestionExample.Criteria criteria=examReQuestionExample.createCriteria();
        criteria.andExamIdEqualTo(examId);
        examReQuestionExample.setOrderByClause("question_index ASC");
        List<ExamReQuestion> examReQuestions=examReQuestionMapper.selectByExample(examReQuestionExample);
        if(answer.size()!=examReQuestions.size()){
            result.put("code", "fail");
            result.put("msg", "提交的试题数目与试卷试题数目不一致，提交失败!");
            return result;
        }
        int rightAnswerNum=0;
        int score=0;
        for (int i=0;i<examReQuestions.size();i++){
            System.out.println("第"+(i+1)+"题选了"+answer.get(i));
            Question question=questionMapper.selectByPrimaryKey(examReQuestions.get(i).getQuestionId());
            //查询改题目的正确答案数
            rightAnswerNum=0;
            List<Byte> rightAnswer=new ArrayList<>();
            if(question.getRightChoiceOne()!=null){
                rightAnswerNum+=1;
                rightAnswer.add(question.getRightChoiceOne());
            }
            if(question.getRightChoiceTwo()!=null){
                rightAnswerNum+=1;
                rightAnswer.add(question.getRightChoiceTwo());
            }
            if(question.getRightChoiceThree()!=null){
                rightAnswerNum+=1;
                rightAnswer.add(question.getRightChoiceThree());
            }
            if(question.getRightChoiceFour()!=null){
                rightAnswerNum+=1;
                rightAnswer.add(question.getRightChoiceFour());
            }
            //保存做题记录
            ExamQuestionStudentAnswer examQuestionStudentAnswer=new ExamQuestionStudentAnswer();
            examQuestionStudentAnswer.setExamId(examId);
            examQuestionStudentAnswer.setQuestionId(question.getQuestionId());
            examQuestionStudentAnswer.setStudentId(student.getStudentId());
            if(answer.get(i) instanceof JSONArray){
                List<Byte> answerList=new ArrayList<>();
                for(int o=0;o<((JSONArray)answer.get(i)).size();o++){
                    answerList.add(((JSONArray) answer.get(i)).getByte(o));
                }
                if(compareTwoArray(rightAnswer, answerList)){
                    score+=examReQuestions.get(i).getScore();
                    System.out.println("第"+(i+1)+"题正确");
                }else {
                    System.out.println("第"+(i+1)+"题选了"+answer.get(i)+"错误,正确答案是"+rightAnswer);
                }
                //保存做题记录
                examQuestionStudentAnswer.setAnswerOne(((JSONArray) answer.get(i)).getByte(0));
                if(((JSONArray) answer.get(i)).size()>1){
                    examQuestionStudentAnswer.setAnswerTwo(((JSONArray) answer.get(i)).getByte(1));
                    if(((JSONArray) answer.get(i)).size()>2){
                        examQuestionStudentAnswer.setAnswerThree(((JSONArray) answer.get(i)).getByte(2));
                        if(((JSONArray) answer.get(i)).size()>3){
                            examQuestionStudentAnswer.setAnswerFour(((JSONArray) answer.get(i)).getByte(3));
                        }
                    }
                }
            }else {
                if(answer.getByte(i)==rightAnswer.get(0)){
                    score+=examReQuestions.get(i).getScore();
                    System.out.println("第"+(i+1)+"题正确");
                }else {
                    System.out.println("第"+(i+1)+"题选了"+answer.get(i)+"错误,正确答案是"+rightAnswer.get(0));
                }
                examQuestionStudentAnswer.setAnswerOne(answer.getByte(i));
            }
            //保存做题记录
            if(examQuestionStudentAnswerMapper.selectByPrimaryKey(examId, question.getQuestionId(), studentId)!=null){
                examQuestionStudentAnswerMapper.updateByPrimaryKeySelective(examQuestionStudentAnswer);
            }else {
                examQuestionStudentAnswerMapper.insertSelective(examQuestionStudentAnswer);
            }
        }
        //查找是否已有考试记录
        ExamReStudentExample examReStudentExample=new ExamReStudentExample();
        ExamReStudentExample.Criteria criteria1=examReStudentExample.createCriteria();
        criteria1.andExamIdEqualTo(examId).andStudentIdEqualTo(student.getStudentId()).andIsInvalidEqualTo(false);
        List<ExamReStudent> examReStudents=examReStudentMapper.selectByExample(examReStudentExample);
        if(examReStudents.size()!=0){
            ExamReStudent examReStudent=examReStudents.get(0);
            Byte remainingTimes=examReStudent.getRemainingTimes();
            examReStudent.setRemainingTimes((byte)(remainingTimes-1));
            examReStudent.setScore(score);
            examReStudentMapper.updateByPrimaryKeySelective(examReStudent);
        }else {
            ExamReStudent examReStudent=new ExamReStudent();
            examReStudent.setScore(score);
            examReStudent.setRemainingTimes((byte)3);
            examReStudent.setExamId(examId);
            examReStudent.setStudentId(student.getStudentId());
            examReStudentMapper.insertSelective(examReStudent);
        }
        result.put("code", "success");
        result.put("msg", "提交试卷成功!");
        return result;
    }
    //比较两个数组是否具有相同的元素，是返回true，否返回false，用于多选题的评分
    private boolean compareTwoArray(List<Byte> A,List<Byte> B){
        if(A.size()!=B.size()){
            return false;
        }
        Collections.sort(A);
        Collections.sort(B);
        return A.equals(B);
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
        exam.setHaveQuestions(false);
        if(examMapper.insertSelective(exam)>0){
            result.put("code", "success");
            result.put("msg", "添加考试成功");
        }else {
            result.put("code", "fail");
            result.put("msg", "添加考试失败,请重试");
        }
        return result;
    }

    @Override
    public JSONObject getNotPostExam(Integer pageSize, Integer currentPage) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();

        if(pageSize==null){
            pageSize=10;
        }
        if(currentPage==null){
            currentPage=1;
        }

        ExamExample examExample=new ExamExample();
        ExamExample.Criteria criteria=examExample.createCriteria();
        criteria.andIsPostedEqualTo(false);
        examExample.setOrderByClause("create_time DESC");
        List<Exam> exams=examMapper.selectByExample(examExample);
        int total=exams.size();
        data.put("total", total);
        if(total==0){
            data.put("list", null);
            result.put("msg", "暂无未发布的试卷");
            result.put("code", "fail");
            result.put("data", data);
        }
        JSONArray list=new JSONArray();
        int i=1;//计数
        int sum=0;//每页数目;
        for(Exam exam:exams){
            if(i<=pageSize*(currentPage-1)){
                i++;
                continue;
            }
            JSONObject object=new JSONObject();
            object.put("examId", exam.getExamId());
            object.put("examName", exam.getExamName());
            object.put("startTime", exam.getExamStartTime());
            object.put("endTime", exam.getExamEndTime());
            object.put("min", exam.getExamLengthMin());
            Training training=trainingMapper.selectByPrimaryKey(exam.getTrainingId());
            object.put("belong", training.getTrainingName());
            object.put("status", 0);
            if(exam.getHaveQuestions()!=null){
                if (exam.getHaveQuestions()){
                    object.put("status", 1);
                }
            }
            list.add(object);
            sum++;
            if(sum==pageSize){
                break;
            }
        }
        data.put("list", list);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "查找未发布的考试成功");
        return result;
    }

    @Override
    public JSONObject getQuestionList(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();

        Integer currentPage=jsonObject.getInteger("currentPage");
        Integer pageSize=jsonObject.getInteger("pageSize");
        if(pageSize==null){
            pageSize=10;
        }
        if(currentPage==null){
            currentPage=1;
        }
        String keyword=jsonObject.getString("keyword");
        int type=-1;
        if(jsonObject.getInteger("type")!=null){
            type=jsonObject.getInteger("type");
        }

        QuestionExample questionExample=new QuestionExample();
        QuestionExample.Criteria criteria=questionExample.createCriteria();
        if(keyword!=null){
            criteria.andQuestionStateLike("%"+keyword+"%");
        }
        if(type!=-1){
            criteria.andTypeEqualTo(type);
        }
        questionExample.setOrderByClause("question_id DESC");
        List<Question> questions=questionMapper.selectByExample(questionExample);
        int total=questions.size();
        JSONArray list=new JSONArray();

        int i=1;//计数
        int sum=0;//每页数目;
        for(Question question:questions){
            if(i<=pageSize*(currentPage-1)){
                i++;
                continue;
            }
            JSONObject object=new JSONObject();
            object.put("questionId", question.getQuestionId());
            object.put("state", question.getQuestionState());
            object.put("type", question.getType());
            List<Byte> answerList=new ArrayList<>();
            answerList.add(question.getRightChoiceOne());
            answerList.add(question.getRightChoiceTwo());
            answerList.add(question.getRightChoiceThree());
            answerList.add(question.getRightChoiceFour());
            String answer="";
            for(int o=0;o<4;o++){
                if(answerList.get(o)!=null){
                    answer+=answerList.get(o);
                }
            }
            answer=answer.replace("1", "A").replace("2", "B").replace("3", "C").replace("4", "D");
            object.put("answer", answer);
            object.put("choiceA", question.getChoiceA());
            object.put("choiceB", question.getChoiceB());
            object.put("choiceC", question.getChoiceC());
            object.put("choiceD", question.getChoiceD());
            list.add(object);
            sum++;
            if(sum==pageSize){
                break;
            }
        }
        data.put("list", list);
        data.put("total", total);
        data.put("currentPage", currentPage);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取试题成功");
        return result;
    }

    @Override
    public JSONObject randomFillExam(Integer examId) {
        JSONObject result=new JSONObject();
        /**
         * 暂时是从考试对应的培训下的题库随机出题，每道题目的分数=总分(100)/题目数目
         * **/
        Exam exam=examMapper.selectByPrimaryKey(examId);
        if(exam==null){
            result.put("code", "fail");
            result.put("msg", "系统中找不到当前的考试");
            return result;
        }
        int trainingId=exam.getTrainingId();
        int questionNum=exam.getExamNum();
        ExamReQuestionExample examReQuestionExample=new ExamReQuestionExample();
        ExamReQuestionExample.Criteria criteria0=examReQuestionExample.createCriteria();
        criteria0.andExamIdEqualTo(examId);
        if(examReQuestionMapper.selectByExample(examReQuestionExample).size()!=0){
            result.put("code", "fail");
            result.put("msg", "该考试已有试卷，请清空后再尝试");
            return result;
        }
        QuestionExample questionExample=new QuestionExample();
        QuestionExample.Criteria criteria=questionExample.createCriteria();
        QuestionExample.Criteria criteria1=questionExample.createCriteria();
        criteria.andTrainingIdEqualTo(trainingId);
        criteria1.andTrainingIdIsNull();
        questionExample.or(criteria1);
        List<Question> questions=questionMapper.selectByExample(questionExample);
        if(questions.size()==0){
            result.put("code", "fail");
            result.put("msg", "无符合条件的试题可供随机出卷");
            return result;
        }
        String msg="";
        Collections.shuffle(questions);
        if(questions.size()>=questionNum){
            questions=questions.subList(0, questionNum);
        }else {
            msg+="题库中符合条件的试题数量小于试卷要求数量，";
        }
        questionNum=questions.size();
        int averageScore=100/questionNum;
        int questionIndex=1;//题目序号
        for (Question question:questions){
            ExamReQuestion examReQuestion=new ExamReQuestion();
            examReQuestion.setExamId(examId);
            examReQuestion.setQuestionId(question.getQuestionId());
            examReQuestion.setScore(averageScore);
            examReQuestion.setQuestionIndex(questionIndex);
            if(questionIndex==questionNum){
                examReQuestion.setScore(averageScore+(100-averageScore*questionNum));
            }
            examReQuestionMapper.insert(examReQuestion);
            questionIndex++;
        }
        exam.setHaveQuestions(true);
        examMapper.updateByPrimaryKeySelective(exam);

        msg+="随机生成试卷成功";
        result.put("code", "success");
        result.put("msg", msg);
        return result;
    }

    @Override
    public JSONObject publishExam(Integer examId) {
        JSONObject result=new JSONObject();
        Exam exam=examMapper.selectByPrimaryKey(examId);
        if(exam==null){
            result.put("code", "fail");
            result.put("msg", "系统中找不到当前的考试");
            return result;
        }
        if(!exam.getHaveQuestions()){
            result.put("code", "fail");
            result.put("msg", "试卷还未出题，不可发布");
            return result;
        }
        exam.setIsPosted(true);
        examMapper.updateByPrimaryKeySelective(exam);
        result.put("code", "success");
        result.put("msg", "考试发布成功!");
        return result;
    }
}
