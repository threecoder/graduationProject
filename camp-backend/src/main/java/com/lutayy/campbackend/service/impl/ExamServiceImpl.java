package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.config.AuthorityParam;
import com.lutayy.campbackend.common.util.ExcelUtil;
import com.lutayy.campbackend.common.util.RedisUtil;
import com.lutayy.campbackend.dao.*;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.ExamService;
import com.lutayy.campbackend.service.SQLConn.ExamStudentSQLConn;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    MemberReStudentMapper memberReStudentMapper;
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
    @Autowired
    ExamReportOpLogMapper examReportOpLogMapper;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    MessageToAdminMapper messageToAdminMapper;
    @Autowired
    MessageTextMapper messageTextMapper;
    @Autowired
    CertificateMapper certificateMapper;
    @Autowired
    GetObjectHelper getObjectHelper;

    @Resource
    private RedisUtil redisUtil;


    @Override
    public JSONObject getHalfExamList(Integer studentId, Integer pageSize, Integer currentPage) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();

        Student student = studentMapper.selectByPrimaryKey(studentId);
        if (student == null) {
            result.put("code", "fail");
            result.put("data", null);
            result.put("msg", "用户不存在！");
            return result;
        }

        JSONArray exams = ExamStudentSQLConn.getExamByCondition(studentId, "r.score<e.exam_pass and r.remaining_times<3 and r.remaining_times>0");
        if (exams.size() == 0) {
            result.put("code", "fail");
            result.put("data", null);
            result.put("msg", "查询无结果");
            return result;
        }
        data.put("total", exams.size());
        data.put("list", exams.subList((currentPage - 1) * pageSize, currentPage * pageSize<=exams.size()?currentPage * pageSize:exams.size()));
        result.put("code", "success");
        result.put("data", data);
        result.put("msg", "查询成功");
        return result;
    }

    @Override
    public JSONObject getTodoExamList(Integer studentId, Integer pageSize, Integer currentPage) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        Student student = studentMapper.selectByPrimaryKey(studentId);
        if (student == null) {
            result.put("code", "fail");
            result.put("data", null);
            result.put("msg", "用户不存在！");
            return result;
        }
        /**
         * 检查是否有已报名的培训新发布了考试，有的话进行更新
         **/
        TrainingReStudentExample trainingReStudentExample = new TrainingReStudentExample();
        TrainingReStudentExample.Criteria criteria = trainingReStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId);
        List<TrainingReStudent> trainingReStudents = trainingReStudentMapper.selectByExample(trainingReStudentExample);
        if (trainingReStudents.size() != 0) {
            for (TrainingReStudent trainingReStudent : trainingReStudents) {
                ExamExample examExample = new ExamExample();
                ExamExample.Criteria criteria1 = examExample.createCriteria();
                criteria1.andTrainingIdEqualTo(trainingReStudent.getTrainingId()).andIsPostedEqualTo(true);
                List<Exam> exams = examMapper.selectByExample(examExample);
                if (exams.size() != 0) {
                    for (Exam exam : exams) {
                        ExamReStudentExample examReStudentExample = new ExamReStudentExample();
                        ExamReStudentExample.Criteria criteria2 = examReStudentExample.createCriteria();
                        criteria2.andStudentIdEqualTo(studentId).andExamIdEqualTo(exam.getExamId());
                        if (examReStudentMapper.selectByExample(examReStudentExample).size() == 0) {
                            ExamReStudent examReStudent = new ExamReStudent();
                            examReStudent.setExamId(exam.getExamId());
                            examReStudent.setStudentId(studentId);
                            examReStudentMapper.insertSelective(examReStudent);
                        }
                    }
                }
            }
        }

        /** 获取报名的培训中已发布的考试 **/
        JSONArray exams = ExamStudentSQLConn.getExamByCondition(studentId, "r.remaining_times=3");
        for (int i = 0; i < exams.size(); i++) {
            exams.getJSONObject(i).put("grade", null);
        }
        data.put("total", exams.size());
        data.put("list", exams.subList((currentPage - 1) * pageSize, currentPage * pageSize<=exams.size()?currentPage * pageSize:exams.size()));
        result.put("code", "success");
        result.put("data", data);
        result.put("msg", "查询成功");
        return result;
    }

    @Override
    public JSONObject getDoneExamList(Integer studentId, Integer pageSize, Integer currentPage) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        Student student = studentMapper.selectByPrimaryKey(studentId);
        if (student == null) {
            result.put("code", "fail");
            result.put("data", null);
            result.put("msg", "用户不存在！");
            return result;
        }

        JSONArray exams = ExamStudentSQLConn.getExamByCondition(studentId, "(r.remaining_times=0 or r.score>=e.exam_pass)");
        if (exams.size() == 0) {
            result.put("code", "fail");
            result.put("data", null);
            result.put("msg", "查询无结果");
            return result;
        }
        data.put("total", exams.size());
        data.put("list", exams.subList((currentPage - 1) * pageSize, currentPage * pageSize<=exams.size()?currentPage * pageSize:exams.size()));
        result.put("code", "success");
        result.put("data", data);
        result.put("msg", "查询成功");
        return result;
    }

    @Override
    public JSONObject getExamInfo(Integer studentId, Integer examId) {
        JSONObject result = new JSONObject();
        Exam exam = examMapper.selectByPrimaryKey(examId);
        if (exam == null) {
            result.put("code", "fail");
            result.put("data", null);
            result.put("msg", "无法查询到该考试信息!");
            return result;
        }
        Training training = trainingMapper.selectByPrimaryKey(exam.getTrainingId());

        ExamReStudentExample examReStudentExample = new ExamReStudentExample();
        ExamReStudentExample.Criteria criteria0 = examReStudentExample.createCriteria();
        criteria0.andExamIdEqualTo(examId).andStudentIdEqualTo(studentId).andIsInvalidEqualTo(false);
        List<ExamReStudent> examReStudents = examReStudentMapper.selectByExample(examReStudentExample);
        if (examReStudents.size() == 0) {
            result.put("code", "fail");
            result.put("data", null);
            result.put("msg", "当前用户没有参加本门考试!");
            return result;
        }
        ExamReStudent examReStudent = examReStudents.get(0);

        JSONObject data = new JSONObject();
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
        JSONObject result = new JSONObject();

        ExamReQuestionExample examReQuestionExample = new ExamReQuestionExample();
        ExamReQuestionExample.Criteria criteria = examReQuestionExample.createCriteria();
        criteria.andExamIdEqualTo(examId);
        examReQuestionExample.setOrderByClause("question_index ASC");
        List<ExamReQuestion> examReQuestions = examReQuestionMapper.selectByExample(examReQuestionExample);

        JSONArray list = new JSONArray();
        JSONObject examInfo = new JSONObject();
        JSONObject data = new JSONObject();

        Exam exam = examMapper.selectByPrimaryKey(examId);
        if (exam == null) {
            result.put("code", "fail");
            result.put("data", null);
            result.put("msg", "无法查询到该考试信息");
            return result;
        }
        examInfo.put("name", exam.getExamName());
        examInfo.put("timeLength", exam.getExamLengthMin());

        data.put("examInfo", examInfo);
        if (examReQuestions.size() == 0) {
            result.put("code", "fail");
            data.put("list", null);
            result.put("data", data);
            result.put("msg", "暂无题目!");
            return result;
        }

        for (ExamReQuestion examReQuestion : examReQuestions) {
            JSONObject object = new JSONObject();
            object.put("index", examReQuestion.getQuestionIndex());
            Question question = questionMapper.selectByPrimaryKey(examReQuestion.getQuestionId());
            object.put("title", question.getQuestionState());
            JSONArray arr = new JSONArray();
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
    public JSONObject getExamDetail(Integer studentId, Integer examId) {
        JSONObject result = new JSONObject();

        Exam exam = examMapper.selectByPrimaryKey(examId);
        if (exam == null) {
            result.put("code", "fail");
            result.put("data", null);
            result.put("msg", "查询不到该试卷!");
            return result;
        }
        ExamReStudentExample examReStudentExample = new ExamReStudentExample();
        ExamReStudentExample.Criteria criteria = examReStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId).andExamIdEqualTo(examId).andIsInvalidEqualTo(false);
        List<ExamReStudent> examReStudents = examReStudentMapper.selectByExample(examReStudentExample);
        if (examReStudents.size() == 0) {
            result.put("code", "fail");
            result.put("data", null);
            result.put("msg", "当前用户没有参加本门考试!");
            return result;
        }
        ExamReStudent examReStudent = examReStudents.get(0);

        JSONObject data = new JSONObject();
        JSONObject examInfo = new JSONObject();
        examInfo.put("examName", exam.getExamName());
        examInfo.put("date", exam.getExamStartTime());
        examInfo.put("startTime", exam.getExamStartTime());
        examInfo.put("endTime", exam.getExamEndTime());
        examInfo.put("min", exam.getExamLengthMin());
        examInfo.put("grade", examReStudent.getScore());
        data.put("examInfo", examInfo);
        JSONArray questionList = new JSONArray();

        ExamReQuestionExample examReQuestionExample = new ExamReQuestionExample();
        ExamReQuestionExample.Criteria criteria0 = examReQuestionExample.createCriteria();
        criteria0.andExamIdEqualTo(examId);
        examReQuestionExample.setOrderByClause("question_index ASC");
        List<ExamReQuestion> examReQuestions = examReQuestionMapper.selectByExample(examReQuestionExample);
        if (examReQuestions.size() == 0) {
            result.put("code", "fail");
            data.put("questionList", null);
            result.put("data", data);
            result.put("msg", "该试卷无试题!");
            return result;
        }
        for (ExamReQuestion examReQuestion : examReQuestions) {
            JSONObject object = new JSONObject();
            object.put("index", examReQuestion.getQuestionIndex());
            Question question = questionMapper.selectByPrimaryKey(examReQuestion.getQuestionId());
            object.put("title", question.getQuestionState());
            object.put("score", examReQuestion.getScore());
            JSONArray arr = new JSONArray();
            arr.add(question.getChoiceA());
            arr.add(question.getChoiceB());
            arr.add(question.getChoiceC());
            arr.add(question.getChoiceD());
            object.put("arr", arr);
            object.put("type", question.getType());
            JSONArray rightAnswer = new JSONArray();
            rightAnswer.add(question.getRightChoiceOne());
            if (question.getRightChoiceTwo() != null) {
                rightAnswer.add(question.getRightChoiceTwo());
            }
            if (question.getRightChoiceThree() != null) {
                rightAnswer.add(question.getRightChoiceThree());
            }
            if (question.getRightChoiceFour() != null) {
                rightAnswer.add(question.getRightChoiceFour());
            }
            object.put("rightAnswer", rightAnswer);
            JSONArray studentAnswer = new JSONArray();
            ExamQuestionStudentAnswerExample eqsaE = new ExamQuestionStudentAnswerExample();
            eqsaE.createCriteria().andExamIdEqualTo(examId).andQuestionIdEqualTo(question.getQuestionId()).andStudentIdEqualTo(studentId);
            List<ExamQuestionStudentAnswer> examQuestionStudentAnswers = examQuestionStudentAnswerMapper.selectByExample(eqsaE);
            ExamQuestionStudentAnswer examQuestionStudentAnswer;
            if (examQuestionStudentAnswers.size() == 0) {
                object.put("studentAnswer", studentAnswer);
            } else {
                examQuestionStudentAnswer = examQuestionStudentAnswers.get(0);
                studentAnswer.add(examQuestionStudentAnswer.getAnswerOne());
                if (question.getType() == 1) {
                    if (examQuestionStudentAnswer.getAnswerTwo() != null) {
                        studentAnswer.add(examQuestionStudentAnswer.getAnswerTwo());
                    }
                    if (examQuestionStudentAnswer.getAnswerThree() != null) {
                        studentAnswer.add(examQuestionStudentAnswer.getAnswerThree());
                    }
                    if (examQuestionStudentAnswer.getAnswerFour() != null) {
                        studentAnswer.add(examQuestionStudentAnswer.getAnswerFour());
                    }
                }
                object.put("studentAnswer", studentAnswer);
            }
            questionList.add(object);
        }
        data.put("list", questionList);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "查询成功!");
        return result;
    }

    @Override
    public JSONObject submitExam(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        Integer studentId = jsonObject.getInteger("id");
        Student student = studentMapper.selectByPrimaryKey(studentId);
        if (student == null) {
            result.put("code", "error");
            result.put("msg", "当前登录用户信息错误");
            return result;
        }
        Integer examId = jsonObject.getInteger("examId");
        Exam exam = examMapper.selectByPrimaryKey(examId);
        if (exam == null) {
            result.put("code", "fail");
            result.put("msg", "查询不到该试卷!");
            return result;
        }
        JSONArray answer = jsonObject.getJSONArray("answer");

        ExamReQuestionExample examReQuestionExample = new ExamReQuestionExample();
        ExamReQuestionExample.Criteria criteria = examReQuestionExample.createCriteria();
        criteria.andExamIdEqualTo(examId);
        examReQuestionExample.setOrderByClause("question_index ASC");
        List<ExamReQuestion> examReQuestions = examReQuestionMapper.selectByExample(examReQuestionExample);
        if (answer.size() != examReQuestions.size()) {
            result.put("code", "fail");
            result.put("msg", "提交的试题数目与试卷试题数目不一致，提交失败!");
            return result;
        }
        int rightAnswerNum = 0;
        int score = 0;
        for (int i = 0; i < examReQuestions.size(); i++) {
            System.out.println("第" + (i + 1) + "题选了" + answer.get(i));
            Question question = questionMapper.selectByPrimaryKey(examReQuestions.get(i).getQuestionId());
            //查询改题目的正确答案数
            rightAnswerNum = 0;
            List<Byte> rightAnswer = new ArrayList<>();
            if (question.getRightChoiceOne() != null) {
                rightAnswerNum += 1;
                rightAnswer.add(question.getRightChoiceOne());
            }
            if (question.getRightChoiceTwo() != null) {
                rightAnswerNum += 1;
                rightAnswer.add(question.getRightChoiceTwo());
            }
            if (question.getRightChoiceThree() != null) {
                rightAnswerNum += 1;
                rightAnswer.add(question.getRightChoiceThree());
            }
            if (question.getRightChoiceFour() != null) {
                rightAnswerNum += 1;
                rightAnswer.add(question.getRightChoiceFour());
            }
            //保存做题记录
            ExamQuestionStudentAnswer examQuestionStudentAnswer = new ExamQuestionStudentAnswer();
            examQuestionStudentAnswer.setExamId(examId);
            examQuestionStudentAnswer.setQuestionId(question.getQuestionId());
            examQuestionStudentAnswer.setStudentId(student.getStudentId());
            if (answer.get(i) instanceof JSONArray) {
                List<Byte> answerList = new ArrayList<>();
                for (int o = 0; o < ((JSONArray) answer.get(i)).size(); o++) {
                    answerList.add(((JSONArray) answer.get(i)).getByte(o));
                }
                if (compareTwoArray(rightAnswer, answerList)) {
                    score += examReQuestions.get(i).getScore();
                    examQuestionStudentAnswer.setCorrectOrNot(true);
                    System.out.println("第" + (i + 1) + "题正确");
                } else {
                    examQuestionStudentAnswer.setCorrectOrNot(false);
                    System.out.println("第" + (i + 1) + "题选了" + answer.get(i) + "错误,正确答案是" + rightAnswer);
                }
                //保存做题记录
                if (((JSONArray) answer.get(i)).size() > 0) {
                    examQuestionStudentAnswer.setAnswerOne(((JSONArray) answer.get(i)).getByte(0));
                    if (((JSONArray) answer.get(i)).size() > 1) {
                        examQuestionStudentAnswer.setAnswerTwo(((JSONArray) answer.get(i)).getByte(1));
                        if (((JSONArray) answer.get(i)).size() > 2) {
                            examQuestionStudentAnswer.setAnswerThree(((JSONArray) answer.get(i)).getByte(2));
                            if (((JSONArray) answer.get(i)).size() > 3) {
                                examQuestionStudentAnswer.setAnswerFour(((JSONArray) answer.get(i)).getByte(3));
                            }
                        }
                    }
                }
            } else {
                if (answer.getByte(i) == rightAnswer.get(0)) {
                    score += examReQuestions.get(i).getScore();
                    examQuestionStudentAnswer.setCorrectOrNot(true);
                    System.out.println("第" + (i + 1) + "题正确");
                } else {
                    examQuestionStudentAnswer.setCorrectOrNot(false);
                    System.out.println("第" + (i + 1) + "题选了" + answer.get(i) + "错误,正确答案是" + rightAnswer.get(0));
                }
                examQuestionStudentAnswer.setAnswerOne(answer.getByte(i));
            }
            //保存做题记录
            ExamQuestionStudentAnswerExample eqsaE = new ExamQuestionStudentAnswerExample();
            eqsaE.createCriteria().andExamIdEqualTo(examId).andQuestionIdEqualTo(question.getQuestionId()).andStudentIdEqualTo(studentId);
            if (examQuestionStudentAnswerMapper.selectByExample(eqsaE).size() > 0) {
                examQuestionStudentAnswerMapper.updateByExampleSelective(examQuestionStudentAnswer, eqsaE);
            } else {
                examQuestionStudentAnswerMapper.insertSelective(examQuestionStudentAnswer);
            }
        }
        //查找是否已有考试记录
        ExamReStudentExample examReStudentExample = new ExamReStudentExample();
        ExamReStudentExample.Criteria criteria1 = examReStudentExample.createCriteria();
        criteria1.andExamIdEqualTo(examId).andStudentIdEqualTo(student.getStudentId()).andIsInvalidEqualTo(false);
        List<ExamReStudent> examReStudents = examReStudentMapper.selectByExample(examReStudentExample);
        if (examReStudents.size() != 0) {
            ExamReStudent examReStudent = examReStudents.get(0);
            Byte remainingTimes = examReStudent.getRemainingTimes();
            examReStudent.setRemainingTimes((byte) (remainingTimes - 1));
            examReStudent.setScore(score);
            examReStudentMapper.updateByPrimaryKeySelective(examReStudent);
        } else {
            ExamReStudent examReStudent = new ExamReStudent();
            examReStudent.setScore(score);
            examReStudent.setRemainingTimes((byte) 3);
            examReStudent.setExamId(examId);
            examReStudent.setStudentId(student.getStudentId());
            examReStudentMapper.insertSelective(examReStudent);
        }
        result.put("code", "success");
        result.put("msg", "提交试卷成功!");
        return result;
    }

    //比较两个数组是否具有相同的元素，是返回true，否返回false，用于多选题的评分
    private boolean compareTwoArray(List<Byte> A, List<Byte> B) {
        if (A.size() != B.size()) {
            return false;
        }
        Collections.sort(A);
        Collections.sort(B);
        return A.equals(B);
    }

    @Override
    public JSONObject rejoinExam(JSONObject jsonObject) {
        Integer examId = jsonObject.getInteger("examId");
        Integer studentId = jsonObject.getInteger("id");
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        Exam exam = examMapper.selectByPrimaryKey(examId);
        if (exam == null) {
            result.put("msg", "系统中找不到该考试！");
            return result;
        }
        ExamReStudentExample examReStudentExample = new ExamReStudentExample();
        examReStudentExample.createCriteria().andStudentIdEqualTo(studentId).andExamIdEqualTo(examId).andIsInvalidEqualTo(false);
        List<ExamReStudent> examReStudents = examReStudentMapper.selectByExample(examReStudentExample);
        if (examReStudents.size() == 0) {
            result.put("msg", "学生无报名该考试！");
            return result;
        }
        ExamReStudent examReStudent = examReStudents.get(0);
        if (examReStudent.getRemainingTimes() < 1) {
            result.put("msg", "剩余考试次数为0");
            return result;
        }
        if (examReStudent.getInLine()) {
            result.put("msg", "当前考试正在审核队列中，请耐心等待审核");
            return result;
        }
        if (examReStudent.getIsVerify().equals((byte) 2)) {
            result.put("msg", "当前考试已审核通过，无须重考");
            return result;
        }
        examReStudent.setRemainingTimes((byte) (examReStudent.getRemainingTimes() - 1));
        examReStudentMapper.updateByPrimaryKeySelective(examReStudent);
        result.put("code", "success");
        result.put("msg", "重新报名考试成功");
        return result;
    }

    /**
     * 管理员操作
     **/
    @Override
    public JSONObject addNewExam(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        //权限检查
        Integer opAdminId = jsonObject.getInteger("id");
        if (!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.EXAM)) {
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }

        String name = jsonObject.getString("name");
        Short num = jsonObject.getShort("num");
        Short pass = jsonObject.getShort("pass");
        Byte lenMinute = jsonObject.getByte("len");
        int trainingId = jsonObject.getInteger("training");
        Date openTime = jsonObject.getDate("openTime");
        Date closeTime = jsonObject.getDate("closeTime");

        if (trainingMapper.selectByPrimaryKey(trainingId) == null) {
            result.put("code", "fail");
            result.put("msg", "所选培训不存在");
            return result;
        }

        ExamExample examExample=new ExamExample();
        examExample.createCriteria().andTrainingIdEqualTo(trainingId);
        if(examMapper.countByExample(examExample)>0){
            result.put("code", "fail");
            result.put("msg", "创建失败！所选培训已发布考试");
            return result;
        }

        Exam exam = new Exam();
        exam.setExamName(name);
        exam.setExamPass(pass);
        exam.setExamNum(num);
        exam.setExamLengthMin(lenMinute);
        exam.setTrainingId(trainingId);
        exam.setExamStartTime(openTime);
        exam.setExamEndTime(closeTime);
        exam.setIsPosted(false);
        exam.setHaveQuestions(false);
        if (examMapper.insertSelective(exam) > 0) {
            result.put("code", "success");
            result.put("msg", "添加考试成功");
        } else {
            result.put("code", "fail");
            result.put("msg", "添加考试失败,请重试");
        }
        return result;
    }

    @Override
    public JSONObject getPublishedList(Integer currentPage, Integer pageSize) {  //管理员获取已发布考试列表
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();
        currentPage = (currentPage == null || currentPage < 1) ? 1 : currentPage;
        pageSize = (pageSize == null || pageSize < 1) ? 10 : pageSize;
        ExamExample examExample = new ExamExample();
        ExamExample.Criteria criteria = examExample.createCriteria();
        criteria.andIsPostedEqualTo(true); //已发布的考试
        examExample.setOrderByClause("exam_id DESC");
        examExample.setOffset((currentPage - 1) * pageSize);
        examExample.setLimit(pageSize);
        List<Exam> exams = examMapper.selectByExample(examExample);
        data.put("total", exams.size());
        for (Exam exam : exams) {
            JSONObject object = new JSONObject();
            object.put("examId", exam.getExamId());
            object.put("name", exam.getExamName());
            String trainingName = "";
            if (exam.getTrainingId() != null) {
                Training training = trainingMapper.selectByPrimaryKey(exam.getTrainingId());
                if (training != null) {
                    trainingName = training.getTrainingName();
                }
            }
            object.put("belong", trainingName);
            object.put("startTime", exam.getExamStartTime());
            object.put("endTime", exam.getExamEndTime());
            object.put("min", exam.getExamLengthMin());
            int status = 1;
            if (exam.getIsPosted()) status = 2;
            if (exam.getExamStartTime() != null || exam.getExamEndTime() != null) {
                if (exam.getExamStartTime().compareTo(new Date()) == -1 && exam.getExamEndTime().compareTo(new Date()) == 1)
                    status = 3;
                else if (exam.getExamEndTime().compareTo(new Date()) == -1) status = 4;
            }
            //if() status = 5;
            object.put("status", status);
            object.put("pass", exam.getExamPass());
            list.add(object);
        }
        data.put("list", list);
        result.put("code", "success");
        result.put("msg", "查询成功");
        result.put("data", data);
        return result;
    }

    @Override
    public JSONObject getNotPostExam(Integer pageSize, Integer currentPage) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();

        currentPage = (currentPage == null || currentPage < 1) ? 1 : currentPage;
        pageSize = (pageSize == null || pageSize < 1) ? 10 : pageSize;

        ExamExample examExample = new ExamExample();
        ExamExample.Criteria criteria = examExample.createCriteria();
        criteria.andIsPostedEqualTo(false);
        examExample.setOrderByClause("create_time DESC");
        List<Exam> exams = examMapper.selectByExample(examExample);
        int total = exams.size();
        data.put("total", total);
        if (total == 0) {
            data.put("list", null);
            result.put("msg", "暂无未发布的试卷");
            result.put("code", "fail");
            result.put("data", data);
        }
        JSONArray list = new JSONArray();
        int i = 1;//计数
        int sum = 0;//每页数目;
        for (Exam exam : exams) {
            if (i <= pageSize * (currentPage - 1)) {
                i++;
                continue;
            }
            JSONObject object = new JSONObject();
            object.put("examId", exam.getExamId());
            object.put("examName", exam.getExamName());
            object.put("startTime", exam.getExamStartTime());
            object.put("endTime", exam.getExamEndTime());
            object.put("min", exam.getExamLengthMin());
            Training training = trainingMapper.selectByPrimaryKey(exam.getTrainingId());
            object.put("belong", training.getTrainingName());
            object.put("status", 0);
            if (exam.getHaveQuestions() != null) {
                if (exam.getHaveQuestions()) {
                    object.put("status", 1);
                }
            }
            list.add(object);
            sum++;
            if (sum == pageSize) {
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
    public JSONObject getQuestionList(Integer currentPage, Integer pageSize, String keyword, Integer type) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();

        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        if (type == null) {
            type = -1;
        }

        QuestionExample questionExample = new QuestionExample();
        QuestionExample.Criteria criteria = questionExample.createCriteria();
        if (keyword != null && !keyword.equals("")) {
            criteria.andQuestionStateLike("%" + keyword + "%");
        }
        if (type != -1) {
            criteria.andTypeEqualTo(type);
        }
        questionExample.setOrderByClause("question_id DESC");
        List<Question> questions = questionMapper.selectByExample(questionExample);
        int total = questions.size();
        JSONArray list = new JSONArray();

        int i = 1;//计数
        int sum = 0;//每页数目;
        for (Question question : questions) {
            if (i <= pageSize * (currentPage - 1)) {
                i++;
                continue;
            }
            JSONObject object = new JSONObject();
            object.put("questionId", question.getQuestionId());
            object.put("state", question.getQuestionState());
            object.put("type", question.getType());
            List<Byte> answerList = new ArrayList<>();
            answerList.add(question.getRightChoiceOne());
            answerList.add(question.getRightChoiceTwo());
            answerList.add(question.getRightChoiceThree());
            answerList.add(question.getRightChoiceFour());
            String answer = "";
            for (int o = 0; o < 4; o++) {
                if (answerList.get(o) != null) {
                    answer += answerList.get(o);
                }
            }
            answer = answer.replace("1", "A").replace("2", "B").replace("3", "C").replace("4", "D");
            object.put("answer", answer);
            object.put("choiceA", question.getChoiceA());
            object.put("choiceB", question.getChoiceB());
            object.put("choiceC", question.getChoiceC());
            object.put("choiceD", question.getChoiceD());
            list.add(object);
            sum++;
            if (sum == pageSize) {
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

    @Override  //随机出题
    public JSONObject randomFillExam(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        //权限检查
        Integer opAdminId = jsonObject.getInteger("id");
        if (!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.EXAM)) {
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }

        Integer examId = jsonObject.getInteger("examId");
        /**
         * 暂时是从考试对应的培训下的题库随机出题，每道题目的分数=总分(100)/题目数目
         * **/
        Exam exam = examMapper.selectByPrimaryKey(examId);
        if (exam == null) {
            result.put("code", "fail");
            result.put("msg", "系统中找不到当前的考试");
            return result;
        }
        int trainingId = exam.getTrainingId();
        int questionNum = exam.getExamNum();
        ExamReQuestionExample examReQuestionExample = new ExamReQuestionExample();
        ExamReQuestionExample.Criteria criteria0 = examReQuestionExample.createCriteria();
        criteria0.andExamIdEqualTo(examId);
        if (examReQuestionMapper.countByExample(examReQuestionExample) > 0) {
            result.put("code", "fail");
            result.put("msg", "该考试已有试卷，请清空后再尝试");
            return result;
        }
        QuestionExample questionExample = new QuestionExample();
        QuestionExample.Criteria criteria = questionExample.createCriteria();
        QuestionExample.Criteria criteria1 = questionExample.createCriteria();
        criteria.andTrainingIdEqualTo(trainingId);
        criteria1.andTrainingIdIsNull();
        questionExample.or(criteria1);
        List<Question> questions = questionMapper.selectByExample(questionExample);
        if (questions.size() == 0) {
            result.put("code", "fail");
            result.put("msg", "无符合条件的试题可供随机出卷");
            return result;
        }
        String msg = "";
        Collections.shuffle(questions);
        if (questions.size() >= questionNum) {
            questions = questions.subList(0, questionNum);
        } else {
            msg += "题库中符合条件的试题数量小于试卷要求数量，";
        }
        questionNum = questions.size();
        int averageScore = 100 / questionNum;
        int questionIndex = 1;//题目序号
        for (Question question : questions) {
            ExamReQuestion examReQuestion = new ExamReQuestion();
            examReQuestion.setExamId(examId);
            examReQuestion.setQuestionId(question.getQuestionId());
            examReQuestion.setScore(averageScore);
            examReQuestion.setQuestionIndex(questionIndex);
            if (questionIndex == questionNum) {
                examReQuestion.setScore(averageScore + (100 - averageScore * questionNum));
            }
            examReQuestionMapper.insert(examReQuestion);
            questionIndex++;
        }
        exam.setHaveQuestions(true);
        examMapper.updateByPrimaryKeySelective(exam);

        msg += "随机生成试卷成功";
        result.put("code", "success");
        result.put("msg", msg);
        return result;
    }

    @Override  //自行出题
    public JSONObject saveQuestionForExam(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        //权限检查
        Integer opAdminId = jsonObject.getInteger("id");
        if (!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.EXAM)) {
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }

        Integer examId = jsonObject.getInteger("examId");
        JSONArray queList = jsonObject.getJSONArray("queList");
        Exam exam = examMapper.selectByPrimaryKey(examId);
        if (exam == null) {
            result.put("msg", "系统中无该考试");
            return result;
        }
        if (exam.getHaveQuestions()) {
            result.put("msg", "该试卷已有题目，如需改动请清空重新添加");
            return result;
        }
        int examNum = exam.getExamNum();
        if (queList.size() < examNum) {
            result.put("msg", "题目数量不足");
            return result;
        }
        Question question;
        for (int i = 1; i <= examNum; i++) {
            int questionId = queList.getInteger(i - 1);
            question = questionMapper.selectByPrimaryKey(questionId);
            if (question == null)
                continue;
            ExamReQuestion examReQuestion = new ExamReQuestion();
            examReQuestion.setExamId(examId);
            examReQuestion.setQuestionId(questionId);
            examReQuestion.setQuestionIndex(i);
            examReQuestion.setScore(100 / examNum);
            if (i == examNum)
                examReQuestion.setScore(100 - (100 / examNum) * (examNum - 1));
            examReQuestionMapper.insert(examReQuestion);
        }
        exam.setHaveQuestions(true);
        examMapper.updateByPrimaryKeySelective(exam);
        result.put("code", "success");
        result.put("msg", "试题添加成功！");
        return result;
    }

    @Override
    public JSONObject publishExam(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        //权限检查
        Integer opAdminId = jsonObject.getInteger("id");
        if (!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.EXAM)) {
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }

        Integer examId = jsonObject.getInteger("examId");
        Exam exam = examMapper.selectByPrimaryKey(examId);
        if (exam == null) {
            result.put("code", "fail");
            result.put("msg", "系统中找不到当前的考试");
            return result;
        }
        if (!exam.getHaveQuestions()) {
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

    @Override
    public JSONObject closeExam(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        //权限检查
        Integer opAdminId = jsonObject.getInteger("id");
        if (!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.EXAM)) {
            result.put("code", "fail");
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }

        Integer examId = jsonObject.getInteger("examId");
        Exam exam = examMapper.selectByPrimaryKey(examId);
        if (exam == null) {
            result.put("code", "fail");
            result.put("msg", "系统中找不到当前的考试");
            return result;
        }
        exam.setIsPosted(false);
        if (examMapper.updateByPrimaryKeySelective(exam) > 0) {
            result.put("code", "success");
            result.put("msg", "考试撤销发布");
        } else {
            result.put("code", "fail");
            result.put("msg", "系统繁忙，请稍后尝试");
        }
        return result;
    }

    @Override
    public JSONObject adminGetExamInfo(Integer examId) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        Exam exam = examMapper.selectByPrimaryKey(examId);
        if (exam == null) {
            result.put("data", null);
            result.put("msg", "无法查询到该考试信息!");
            return result;
        }

        JSONObject data = new JSONObject();
        Training training = trainingMapper.selectByPrimaryKey(exam.getTrainingId());
        data.put("name", exam.getExamName());
        data.put("num", exam.getExamNum());
        data.put("pass", exam.getExamPass());//及格分数
        data.put("len", exam.getExamLengthMin());
        data.put("startTime", exam.getExamStartTime());
        data.put("endTime", exam.getExamEndTime());
        data.put("training", training == null ? "" : training.getTrainingName());
        data.put("hasQue", exam.getHaveQuestions());
        result.put("code", "success");
        result.put("data", data);
        result.put("msg", "查询成功!");
        return result;
    }

    @Override
    public JSONObject getExamQuestionList(Integer examId) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        Exam exam = examMapper.selectByPrimaryKey(examId);
        if (exam == null) {
            result.put("code", "fail");
            result.put("data", data);
            result.put("msg", "无法查询到该考试信息!");
            return result;
        }
        JSONArray list = new JSONArray();
        ExamReQuestionExample examReQuestionExample = new ExamReQuestionExample();
        examReQuestionExample.createCriteria().andExamIdEqualTo(examId);
        examReQuestionExample.setOrderByClause("question_index ASC");
        List<ExamReQuestion> examReQuestions = examReQuestionMapper.selectByExample(examReQuestionExample);
        for (ExamReQuestion examReQuestion : examReQuestions) {
            list.add(examReQuestion.getQuestionId());
        }
        data.put("list", list);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "试卷试题获取成功");
        return result;
    }

    @Override
    public JSONObject modifyExamInfo(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        //权限检查
        Integer opAdminId = jsonObject.getInteger("id");
        if (!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.EXAM)) {
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }

        Integer examId = jsonObject.getInteger("examId");
//        Integer trainingId=jsonObject.getInteger("training");
        String examName = jsonObject.getString("name");
        Short examNum = jsonObject.getShort("num");
        Byte examMinLength = jsonObject.getByte("len");
        Short examPass = jsonObject.getShort("pass");
        Date examStartTime = jsonObject.getDate("openTime");
        Date examEndTime = jsonObject.getDate("closeTime");
        Exam exam = examMapper.selectByPrimaryKey(examId);
        System.out.println(examId);
        if (exam == null) {
            result.put("code", "fail");
            result.put("msg", "该考试不存在");
            return result;
        }
        if (!exam.getExamNum().equals(examNum) && exam.getHaveQuestions()) {
            result.put("code", "fail");
            result.put("msg", "已为试卷添加试题，不可修改试题数量！");
            return result;
        }
        exam.setExamNum(examNum);
        exam.setExamStartTime(examStartTime);
        exam.setExamEndTime(examEndTime);
        exam.setExamLengthMin(examMinLength);
        exam.setExamPass(examPass);
        exam.setExamName(examName);
        if (examMapper.updateByPrimaryKeySelective(exam) > 0) {
            result.put("code", "success");
            result.put("msg", "考试信息修改成功！");
        } else {
            result.put("code", "fail");
            result.put("msg", "系统繁忙，请稍后尝试");
        }
        return result;
    }

    //管理员获取已经作答的学生情况
    @Override
    public JSONObject getFinishedStudentList(Integer examId) {
        JSONObject result = new JSONObject();
        JSONArray list = new JSONArray();
        Exam exam = examMapper.selectByPrimaryKey(examId);
        if (exam == null) {
            result.put("code", "fail");
            result.put("msg", "系统中已无该场考试！");
            result.put("data", list);
            return result;
        }
        ExamReStudentExample examReStudentExample = new ExamReStudentExample();
        examReStudentExample.createCriteria().andExamIdEqualTo(examId);
        examReStudentExample.setOrderByClause("is_invalid ASC");
        List<ExamReStudent> examReStudents = examReStudentMapper.selectByExample(examReStudentExample);
        for (ExamReStudent examReStudent : examReStudents) {
            Student student = studentMapper.selectByPrimaryKey(examReStudent.getStudentId());
            JSONObject object = new JSONObject();
            object.put("idNum", student.getStudentIdcard());
            object.put("name", student.getStudentName());
            object.put("grade", examReStudent.getScore());
            object.put("times", examReStudent.getRemainingTimes());
            object.put("member", student.getCompany());
            if (examReStudent.getIsInvalid())
                object.put("isInvalid", "该场考试已作废");
            else
                object.put("isInvalid", "该场考试有效");
            list.add(object);
        }
        result.put("code", "success");
        result.put("msg", "查询列表成功");
        result.put("data", list);
        return result;
    }

    //管理员获取待审核成绩列表
    @Override
    public JSONObject getGradeList(Integer examId) {
        JSONObject result = new JSONObject();
        JSONArray list = new JSONArray();
        Exam exam = examMapper.selectByPrimaryKey(examId);
        if (exam == null) {
            result.put("code", "fail");
            result.put("msg", "系统中已无该场考试！");
            result.put("data", list);
            return result;
        }
        int passScore = exam.getExamPass();
        ExamReStudentExample examReStudentExample = new ExamReStudentExample();
        examReStudentExample.createCriteria().andExamIdEqualTo(examId).andIsInvalidEqualTo(false).
                andIsVerifyEqualTo((byte) 0).andInLineEqualTo(false).andScoreGreaterThanOrEqualTo(passScore);
        List<ExamReStudent> examReStudents = examReStudentMapper.selectByExample(examReStudentExample);
        for (ExamReStudent examReStudent : examReStudents) {
            JSONObject object = new JSONObject();
            object.put("id", examReStudent.getReportId());
            Student student = studentMapper.selectByPrimaryKey(examReStudent.getStudentId());
            object.put("name", student.getStudentName());
            object.put("idNum", student.getStudentIdcard());
            object.put("member", student.getCompany());
            object.put("times", 3 - examReStudent.getRemainingTimes());
            object.put("grade", examReStudent.getScore());
            list.add(object);
        }
        result.put("code", "success");
        result.put("msg", "获取待审核成绩列表成功！");
        result.put("data", list);
        return result;
    }

    //管理员提交审核成绩请求
    @Override
    public JSONObject submitGradeList(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        //权限检查
        Integer opAdminId = jsonObject.getInteger("id");
        if (!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.EXAM)) {
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }

        Admin opAdmin = adminMapper.selectByPrimaryKey(opAdminId);
        result.put("code", "fail");

        Integer adminId = jsonObject.getInteger("checker");
        Admin admin = adminMapper.selectByPrimaryKey(adminId);
        if (admin == null) {
            result.put("msg", "对应的账号不存在，请检查输入是否正确");
            return result;
        }
        JSONArray reportIds = jsonObject.getJSONArray("ids");
        int totalNum = 0;
        String sendReportStr = "";
        for (int i = 0; i < reportIds.size(); i++) {
            int reportId = reportIds.getIntValue(i);
            ExamReStudent report = examReStudentMapper.selectByPrimaryKey(reportId);
            if (report == null || report.getIsInvalid() || report.getInLine() || report.getIsVerify().equals(2)) {
                continue;
            }
            sendReportStr += reportId;
            if (i != reportIds.size() - 1)
                sendReportStr += "、";
            totalNum++;
            Student student = studentMapper.selectByPrimaryKey(report.getStudentId());
            Exam exam = examMapper.selectByPrimaryKey(report.getExamId());
            Training training = trainingMapper.selectByPrimaryKey(exam.getTrainingId());
            ExamReportOpLog reportOpLog = new ExamReportOpLog();
            reportOpLog.setAdminId(opAdminId);
            reportOpLog.setExamId(report.getExamId());
            reportOpLog.setReportId(reportId);
            reportOpLog.setOpDescription("将成绩单" + reportId + "放入管理员ID:" + adminId + "的审核队列中");
            reportOpLog.setOpTime(new Date());
            reportOpLog.setAdminName(opAdmin == null ? "" : opAdmin.getAdminName());
            reportOpLog.setStudentName(student.getStudentName());

            //存放要审核的成绩单
            JSONObject reportObject = new JSONObject();
            reportObject.put("idNum", student.getStudentIdcard());
            reportObject.put("name", student.getStudentName());
            reportObject.put("member", student.getCompany());
            reportObject.put("grade", report.getScore());
            reportObject.put("times", 3 - report.getRemainingTimes());
            reportObject.put("id", reportId);
            reportObject.put("trainingName", training.getTrainingName());
            reportObject.put("examId", report.getExamId());

            if (redisUtil.hset("admin" + adminId + "_OpList", Integer.toString(reportId), reportObject)) {

                report.setInLine(true);
                examReStudentMapper.updateByPrimaryKeySelective(report);
                reportOpLog.setIsSuccess(true);
            } else {
                reportOpLog.setIsSuccess(false);
            }
            examReportOpLogMapper.insert(reportOpLog);
        }
        // TODO 发送站内信给对应的管理员
        MessageText messageText = new MessageText();
        messageText.setSendTime(new Date());
        messageText.setType("checkGrade");
        messageText.setMessage("审核队列更新，新增" + totalNum + "条审核请求！");
        messageText.setTitle("审核队列更新，新增" + totalNum + "条审核请求！分别是：" + sendReportStr);
        messageTextMapper.insertSelective(messageText);
        MessageToAdmin messageToAdmin = new MessageToAdmin();
        messageToAdmin.setReceiveAdminId(adminId);
        messageToAdmin.setAdminId(opAdminId);
        messageToAdmin.setWhoSend((byte) 0);
        messageToAdmin.setSendTime(new Date());
        messageToAdmin.setMessageId(messageText.getMessageId());
        messageToAdminMapper.insertSelective(messageToAdmin);

        result.put("code", "success");
        result.put("msg", "提交审核申请成功！");
        return result;
    }

    //管理员获取本账号待审核成绩记录
    @Override
    public JSONObject getWaitingGradeList(Integer adminId, Integer pageSize, Integer currentPage,
                                          String trainingName, String idNum, String studentName) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();

        Map<Object, Object> dataMap = redisUtil.hmget("admin" + adminId + "_OpList");
        List<JSONObject> dataList = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : dataMap.entrySet()) {
            JSONObject object = (JSONObject) entry.getValue();
            if (trainingName != null && !trainingName.equals(object.getString("trainingName")))
                continue;
            if (idNum != null && !idNum.equals(object.getString("idNum")))
                continue;
            if (studentName != null && !studentName.equals(object.getString("name")))
                continue;
            dataList.add(object);
        }
        data.put("total", dataList.size());
        int begin = pageSize * (currentPage - 1);
        int end = pageSize * currentPage;
        for (int i = 0; i < dataList.size(); i++) {
            if (i >= begin && i < end) {
                list.add(dataList.get(i));
            }
        }
        data.put("data", list);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取待审核成绩单成功！");
        return result;
    }

    //管理员批量通过成绩记录（并颁发证书）
    @Override
    public JSONObject approvalManyRecords(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");

        Integer adminId = jsonObject.getInteger("id");
        Admin admin = adminMapper.selectByPrimaryKey(adminId);
        JSONArray reportIds = jsonObject.getJSONArray("ids");
        boolean flag = false;
        String noticeMsg = "，其中成绩单:";
        for (int i = 0; i < reportIds.size(); i++) {
            ExamReStudent report = examReStudentMapper.selectByPrimaryKey(reportIds.getIntValue(i));
            if (report == null) {
                flag = true;
                noticeMsg += (reportIds.getIntValue(i) + ";");
                continue;
            }
            Student student = studentMapper.selectByPrimaryKey(report.getStudentId());
            ExamReportOpLog reportOpLog = new ExamReportOpLog();
            reportOpLog.setAdminId(adminId);
            reportOpLog.setExamId(report.getExamId());
            reportOpLog.setReportId(report.getReportId());
            reportOpLog.setOpDescription("通过成绩单" + report.getReportId() + "，并移出队列");
            reportOpLog.setOpTime(new Date());
            reportOpLog.setAdminName(admin.getAdminName());
            reportOpLog.setStudentName(student.getStudentName());
            reportOpLog.setIsPass(true);

            report.setInLine(false);
            report.setIsVerify((byte) 2);
            if (examReStudentMapper.updateByPrimaryKeySelective(report) <= 0) {
                reportOpLog.setIsSuccess(false);
            } else {
                reportOpLog.setIsSuccess(true);
                redisUtil.hdel("admin" + adminId + "_OpList", report.getReportId().toString());
            }
            examReportOpLogMapper.insert(reportOpLog);
            // TODO 更新培训完成状态
            Exam exam=examMapper.selectByPrimaryKey(report.getExamId());
            TrainingReStudent trainingReStudent=getObjectHelper.getTrainingReStudentByIds(exam.getTrainingId(), report.getStudentId());
            trainingReStudent.setIsDone(true);
            trainingReStudent.setFinishTime(new Date());
            trainingReStudentMapper.updateByPrimaryKeySelective(trainingReStudent);
            // TODO 颁发证书（往证书表插入数据）
            Date nowDate=new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(nowDate);
            cal.add(Calendar.YEAR, 1); //证书有效期为一年
            Certificate certificate=new Certificate();
            certificate.setCreateTime(nowDate);
            certificate.setEndTime(cal.getTime());
            certificate.setStudentId(student.getStudentId());
            Training training=trainingMapper.selectByPrimaryKey(exam.getTrainingId());
            certificate.setCertificateName(training.getTrainingName()+"培训证书_"+student.getStudentName());
            certificate.setTrainingId(exam.getTrainingId());
            certificate.setStudentName(student.getStudentName());
            //检查学员是否有挂靠会员
            MemberReStudentExample example=new MemberReStudentExample();
            example.createCriteria().andStudentIdEqualTo(student.getStudentId());
            List<MemberReStudent> memberReStudents=memberReStudentMapper.selectByExample(example);
            if(memberReStudents.size()>0)
                certificate.setMemberName(memberMapper.selectByPrimaryKey(memberReStudents.get(0).getMemberKeyId()).getMemberName());
            certificateMapper.insertSelective(certificate);
        }
        String msg = "通过目标成绩单！同时颁发证书成功";
        if (flag) {
            msg += (noticeMsg + "的记录不存在");
        }
        result.put("code", "success");
        result.put("msg", msg);
        return result;
    }

    //拒绝单条成绩记录
    @Override
    public JSONObject refuseSingleRecord(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        Integer reportId = jsonObject.getInteger("reportId");
        ExamReStudent report = examReStudentMapper.selectByPrimaryKey(reportId);
        if (report == null) {
            result.put("msg", "该成绩记录不存在");
            return result;
        }
        Student student = studentMapper.selectByPrimaryKey(report.getStudentId());
        Integer adminId = jsonObject.getInteger("id");
        Admin admin = adminMapper.selectByPrimaryKey(adminId);
        String tip = jsonObject.getString("tip");

        ExamReportOpLog reportOpLog = new ExamReportOpLog();
        reportOpLog.setAdminId(adminId);
        reportOpLog.setExamId(report.getExamId());
        reportOpLog.setReportId(reportId);
        reportOpLog.setOpDescription("成绩单" + reportId + "审核不通过,已打回,拒绝理由为:" + tip);
        reportOpLog.setOpTime(new Date());
        reportOpLog.setAdminName(admin.getAdminName());
        reportOpLog.setStudentName(student.getStudentName());
        reportOpLog.setIsPass(false);

        report.setIsVerify((byte) 1);
        report.setInLine(false);
        report.setNotPassTimes(report.getNotPassTimes() + 1);
        report.setNotPassReason(tip);
        if (examReStudentMapper.updateByPrimaryKeySelective(report) <= 0) {
            reportOpLog.setIsSuccess(false);
        } else {
            reportOpLog.setIsSuccess(true);
            redisUtil.hdel("admin" + adminId + "_OpList", report.getReportId().toString());
        }
        examReportOpLogMapper.insert(reportOpLog);
        result.put("code", "success");
        result.put("msg", "操作成功！已将成绩单打回！");
        return result;
    }

    //拒绝多条成绩记录
    @Override
    public JSONObject refuseManyRecords(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        Admin admin = adminMapper.selectByPrimaryKey(jsonObject.getInteger("id"));
        JSONArray reportIds = jsonObject.getJSONArray("ids");
        String tip = jsonObject.getString("tip");

        boolean flag = false;
        String noticeMsg = "，其中成绩单:";
        for (int i = 0; i < reportIds.size(); i++) {
            ExamReStudent report = examReStudentMapper.selectByPrimaryKey(reportIds.getIntValue(i));
            if (report == null) {
                flag = true;
                noticeMsg += (reportIds.getIntValue(i) + ";");
                continue;
            }
            Student student = studentMapper.selectByPrimaryKey(report.getStudentId());
            ExamReportOpLog reportOpLog = new ExamReportOpLog();
            reportOpLog.setAdminId(admin.getAdminId());
            reportOpLog.setExamId(report.getExamId());
            reportOpLog.setReportId(report.getReportId());
            reportOpLog.setOpDescription("成绩单" + report.getReportId() + "审核不通过,已打回,拒绝理由为:" + tip);
            reportOpLog.setOpTime(new Date());
            reportOpLog.setAdminName(admin.getAdminName());
            reportOpLog.setStudentName(student.getStudentName());
            reportOpLog.setIsPass(false);

            report.setInLine(false);
            report.setIsVerify((byte) 1);
            report.setNotPassReason(tip);
            report.setNotPassTimes(report.getNotPassTimes() + 1);
            if (examReStudentMapper.updateByPrimaryKeySelective(report) <= 0) {
                reportOpLog.setIsSuccess(false);
            } else {
                reportOpLog.setIsSuccess(true);
                redisUtil.hdel("admin" + admin.getAdminId() + "_OpList", report.getReportId().toString());
            }
            examReportOpLogMapper.insert(reportOpLog);
        }
        String msg = "操作成功！已将成绩单打回";
        if (flag) {
            msg += (noticeMsg + "的记录不存在");
        }
        result.put("code", "success");
        result.put("msg", msg);
        return result;
    }

    //获取某次考试的审核记录
    @Override
    public JSONObject getCheckRecordList(Integer examId, Integer pageSize, Integer currentPage, String checker, String studentName, Integer isPass) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("code", "fail");
        result.put("data", data);

        Exam exam = examMapper.selectByPrimaryKey(examId);
        if (exam == null) {
            result.put("msg", "所查询的考试不存在！");
            return result;
        }
        ExamReportOpLogExample logExample = new ExamReportOpLogExample();
        ExamReportOpLogExample.Criteria criteria = logExample.createCriteria();
        criteria.andExamIdEqualTo(examId);
        if (checker != null) criteria.andAdminNameLike("%" + checker + "%");
        if (studentName != null) criteria.andStudentNameLike("%" + studentName + "%");
        if (isPass != null) criteria.andIsPassEqualTo(isPass.equals(1));
        long total = examReportOpLogMapper.countByExample(logExample);
        data.put("total", total);
        logExample.setOffset(pageSize * (currentPage - 1));
        logExample.setLimit(pageSize);
        List<ExamReportOpLog> examReportOpLogs = examReportOpLogMapper.selectByExample(logExample);
        JSONArray list = new JSONArray();
        for (ExamReportOpLog log : examReportOpLogs) {
            JSONObject object = new JSONObject();
            object.put("name", log.getStudentName());
            ExamReStudent report = examReStudentMapper.selectByPrimaryKey(log.getReportId());
            Student student = studentMapper.selectByPrimaryKey(report.getStudentId());
            object.put("idNum", student.getStudentIdcard());
            object.put("member", student.getCompany());
            object.put("grade", report.getScore());
            object.put("checker", log.getAdminName());
            if (log.getIsPass() == null) {
                object.put("pass", "加入队列");
            } else if (log.getIsPass()) {
                object.put("pass", "通过");
            } else {
                object.put("pass", "不通过");
            }
            object.put("tip", report.getNotPassReason());
            object.put("checkTime", log.getOpTime());
            list.add(object);
        }
        data.put("data", list);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "记录获取成功");
        return result;
    }

    @Override
    public ResponseEntity<byte[]> getGradeTemplate(HttpServletRequest request) {
        String fileName = "exam_template.xlsx";
//        ServletContext servletContext=request.getServletContext();
//        String path=servletContext.getRealPath("/WEB-INF/templates/"+fileName);
        String path = "./src/main/resources/templates/exam_template.xlsx";
        File file = new File(path);
        InputStream in;
        ResponseEntity<byte[]> response = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            HttpHeaders headers = new HttpHeaders();
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
            headers.add("Content-Disposition", "attachment;filename=" + fileName);
            HttpStatus statusCode = HttpStatus.OK;
            response = new ResponseEntity<byte[]>(bytes, headers, statusCode);
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public JSONObject uploadGradeOfExam(MultipartFile file) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        if (file == null || file.isEmpty()) {
            result.put("msg", "文件不能为空");
            return result;
        }
        String fileName = file.getOriginalFilename().toLowerCase();
        if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
            result.put("msg", "文件格式错误");
            return result;
        }
        InputStream in;
        try {
            in = file.getInputStream();
            Map<String, List<Map<String, String>>> map = ExcelUtil.readXls(in);
            if (map.isEmpty()) {
                result.put("msg", "上传文件数据为空");
                return result;
            }
            Set<String> excelSheets = map.keySet();
            int rowIndex = 1;
            int totalCount = 0;
            int wrongExamCount = 0;//考试信息错误
            String wrongExamTip = "";
            int wrongStudentCount = 0;//学员信息错误
            String wrongStudentTip = "";
            int nonExamReStudentCount = 0;//学生没有报名该场考试
            String nonReTip = "";
            int outOfExamTimesCount = 0;//考试次数超限
            String outTip = "";
            int errorCount = 0;
            String errorTip = "";
            for (String excelSheet : excelSheets) {
                List<Map<String, String>> list = map.get(excelSheet);
                totalCount = totalCount + list.size();
                for (Map<String, String> row : list) {
                    rowIndex++;
                    // TODO 检查排除异常的行
                    Integer examId;
                    String examName;
                    String studentName;
                    String studentIdCard;
                    Integer examScore;
                    try {
                        examId = Integer.valueOf(row.get("试卷编号"));
                        examName = row.get("考试名称");
                        studentName = row.get("学员姓名");
                        studentIdCard = row.get("学员身份证");
                        examScore = Integer.valueOf(row.get("考试分数"));
                    } catch (Exception e) {
                        errorCount++;
                        errorTip += (rowIndex + ",");
                        continue;
                    }
                    Exam exam = examMapper.selectByPrimaryKey(examId);
                    if (exam == null || !exam.getExamName().equals(examName)) {
                        wrongExamCount++;
                        wrongExamTip += (rowIndex + ",");
                        continue;
                    }
                    Student student = getObjectHelper.getStudentFromIdCard(studentIdCard);
                    if (student == null || !student.getStudentName().equals(studentName)) {
                        wrongStudentCount++;
                        wrongStudentTip += (rowIndex + ",");
                        continue;
                    }
                    ExamReStudent report = getObjectHelper.getReportByExamIdAndStudentId(examId, student.getStudentId());
                    TrainingReStudent trainingReStudent = getObjectHelper.getTrainingReStudentByIds(exam.getTrainingId(), student.getStudentId());

                    if ((report == null || report.getIsInvalid()) && (trainingReStudent == null || trainingReStudent.getIsInvalid())) {
                        nonExamReStudentCount++;
                        nonReTip += (rowIndex + ",");
                        continue;
                    }
                    if (report != null && (report.getScore() >= exam.getExamPass() || report.getRemainingTimes() < 1)) {
                        outOfExamTimesCount++;
                        outTip += (rowIndex + ",");
                        continue;
                    }
                    // TODO 批量添加
                    if (report != null) {
                        report.setScore(examScore);
                        report.setRemainingTimes((byte) (report.getRemainingTimes() - 1));
                        examReStudentMapper.updateByPrimaryKeySelective(report);
                    } else if (trainingReStudent != null) {
                        ExamReStudent newReport = new ExamReStudent();
                        newReport.setScore(examScore);
                        newReport.setRemainingTimes((byte) 2);
                        newReport.setExamId(examId);
                        newReport.setStudentId(student.getStudentId());
                        examReStudentMapper.insertSelective(newReport);
                    }
                }
            }
            String msg = "尝试导入" + totalCount + "个成绩单，" + (totalCount - wrongExamCount - wrongStudentCount - nonExamReStudentCount - outOfExamTimesCount - errorCount) + "个导入并更新成功。 ";
            if (errorCount > 0) {
                msg += ("  有" + errorCount + "行存在数据类型错误-行号是:" + errorTip);
            }
            if (wrongExamCount > 0) {
                msg += ("  有" + wrongExamCount + "行考试信息错误-行号是:" + wrongExamTip);
            }
            if (wrongStudentCount > 0) {
                msg += ("  有" + wrongStudentCount + "行学员信息错误-行号是:" + wrongStudentTip);
            }
            if (nonExamReStudentCount > 0) {
                msg += ("  有" + nonExamReStudentCount + "行学生无报名该考试-行号是:" + nonReTip);
            }
            if (outOfExamTimesCount > 0) {
                msg += ("  有" + outOfExamTimesCount + "行无法再进行考试(已及格/次数超限)-行号是:" + outTip);
            }
            result.put("code", "success");
            result.put("msg", msg);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "导入异常，请检查格式");
        }
        return result;
    }

    //管理员修改考试成绩
    @Override
    public JSONObject modifyGrade(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("code", "fail");
        //权限检查
        Integer opAdminId = jsonObject.getInteger("id");
        if (!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.GRADE)) {
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }

        Integer examId = jsonObject.getInteger("examId");
        String idNum = jsonObject.getString("idNum");
        Integer score = jsonObject.getInteger("grade");
        Student student = getObjectHelper.getStudentFromIdCard(idNum);
        if (student == null) {
            result.put("msg", "系统中找不到该学员");
            return result;
        }
        ExamReStudent report = getObjectHelper.getReportByExamIdAndStudentId(examId, student.getStudentId());
        if (report == null) {
            result.put("msg", "学员没有报名该场考试");
            return result;
        }
        report.setScore(score);
        if (examReStudentMapper.updateByPrimaryKeySelective(report) > 0) {
            result.put("msg", "修改成功！");
            result.put("code", "success");
        } else {
            result.put("msg", "系统繁忙，请重试");
        }
        return result;
    }

}
