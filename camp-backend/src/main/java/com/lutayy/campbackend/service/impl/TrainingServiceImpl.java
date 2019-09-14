package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.lutayy.campbackend.dao.TrainingMapper;
import com.lutayy.campbackend.dao.TrainingReStudentMapper;
import com.lutayy.campbackend.pojo.Training;
import com.lutayy.campbackend.pojo.TrainingExample;
import com.lutayy.campbackend.pojo.TrainingReStudent;
import com.lutayy.campbackend.pojo.TrainingReStudentExample;
import com.lutayy.campbackend.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    TrainingMapper trainingMapper;
    @Autowired
    TrainingReStudentMapper trainingReStudentMapper;

    @Override
    public JSONObject getCourses(JSONObject jsonObject) {
        String keyword=jsonObject.getString("keyWord");
        Date startDate=jsonObject.getDate("startDate");
        Date endDate=jsonObject.getDate("endDate");
        Integer pageSize=jsonObject.getInteger("pageSize");
        Integer currentPage=jsonObject.getInteger("currentPage");
        if(pageSize==null){
            pageSize=10;
        }
        if(currentPage==null){
            currentPage=1;
        }
        TrainingExample trainingExample=new TrainingExample();
        TrainingExample.Criteria criteria=trainingExample.createCriteria();
        criteria.andTrainingNameLike("%"+keyword+"%");
        if(startDate!=null){
            criteria.andTrainingStartTimeGreaterThanOrEqualTo(startDate);
        }
        if(endDate!=null){
            Calendar c = Calendar.getInstance();
            c.setTime(endDate);
            c.add(Calendar.DAY_OF_MONTH, +1);
            criteria.andTrainingEndTimeLessThanOrEqualTo(c.getTime());
        }
        trainingExample.setOrderByClause("post_time DESC");
        List<Training> trainings=trainingMapper.selectByExample(trainingExample);
        int size=trainings.size();
        JSONArray data = new JSONArray();
        int i=1;//计数
        int sum=0;//每页数目;
        for(Training training:trainings){
            if(i<=pageSize*(currentPage-1)){
                i++;
                continue;
            }
            /*获取参与培训的学生数目*/
            TrainingReStudentExample trainingReStudentExample=new TrainingReStudentExample();
            TrainingReStudentExample.Criteria criteria1=trainingReStudentExample.createCriteria();
            criteria1.andTrainingIdEqualTo(training.getTrainingId());
            List<TrainingReStudent> trainingReStudents=trainingReStudentMapper.selectByExample(trainingReStudentExample);
            int studentNums=trainingReStudents.size();

            JSONObject data0=new JSONObject();
            data0.put("title", training.getTrainingName());
            data0.put("abstract",training.getTrainingIntroduce());
            data0.put("viewers",studentNums);
            data0.put("url",training.getTrainingPic());
            data0.put("level",training.getLevel());
            data.add(data0);
            sum++;
            if(sum==pageSize){
                break;
            }
        }
        JSONObject result = new JSONObject();
        result.put("code", "success");
        result.put("data", data);
        result.put("allNum", size);
        result.put("pageSize",sum);
        result.put("currentPage", currentPage);
        return result;
    }

}

