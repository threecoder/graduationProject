package com.lutayy.campbackend.service.SQLConn;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.dao.ExamMapper;
import com.lutayy.campbackend.dao.TrainingMapper;
import com.lutayy.campbackend.pojo.Exam;
import com.lutayy.campbackend.pojo.Training;
import com.lutayy.campbackend.pojo.TrainingExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ExamStudentSQLConn {

    private static final String URL="jdbc:mysql://127.0.0.1:3306/association?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String Name="root";
    private static final String Pwd="123456";

    @Autowired
    TrainingMapper trainingMapper;

    private static ExamStudentSQLConn examStudentSQLConn;



    @PostConstruct
    public void init(){
        examStudentSQLConn=this;
        examStudentSQLConn.trainingMapper=this.trainingMapper;
    }

    public static JSONArray getExamByCondition(int userKey,String condition){
        JSONArray exams=new JSONArray();
        Connection conn=null;
        Statement statement=null;
        try{
            conn= DriverManager.getConnection(URL,Name,Pwd);
            statement=conn.createStatement();
            String sql="select e.exam_id,e.exam_name,e.training_id,e.exam_start_time,e.exam_end_time,e.exam_length_min,r.score from exam e, student s,exam_re_student r " +
                    "where e.exam_id = r.exam_id and s.student_id = r.student_id and r.is_invalid=0 and s.student_id ="+userKey;
            if(!condition.equals("")){
                sql+=" and "+condition;
            }
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                int examId=rs.getInt("exam_id");
                int trainingId=rs.getInt("training_id");
                String examName=rs.getString("exam_name");
                String examStartTime=rs.getString("exam_start_time");
                String examEndTime=rs.getString("exam_end_time");
                byte examLengthMin=rs.getByte("exam_length_min");
                int score=rs.getInt("score");
                JSONObject exam=new JSONObject();
                exam.put("examName",examName);
                exam.put("startTime",examStartTime);
                exam.put("endTime", examEndTime);
                exam.put("min", examLengthMin);
                exam.put("grade", score);
                exam.put("examId", examId);
                Training training=examStudentSQLConn.trainingMapper.selectByPrimaryKey(trainingId);
                exam.put("belong",training.getTrainingName());
                exams.add(exam);
            }
            return exams;

        } catch (SQLException e){
            e.printStackTrace();
            return exams;
        }


    }
}
