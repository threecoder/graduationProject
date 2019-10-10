package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.lutayy.campbackend.dao.MemberMapper;
import com.lutayy.campbackend.dao.TrainingMapper;
import com.lutayy.campbackend.dao.TrainingReStudentMapper;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.SQLConn.TrainingStudentSQLConn;
import com.lutayy.campbackend.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    TrainingMapper trainingMapper;
    @Autowired
    TrainingReStudentMapper trainingReStudentMapper;
    @Autowired
    MemberMapper memberMapper;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    /** 学员中心接口 **/
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
        JSONArray list = new JSONArray();
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
            list.add(data0);
            sum++;
            if(sum==pageSize){
                break;
            }
        }
        JSONObject result = new JSONObject();
        JSONObject data=new JSONObject();
        data.put("list", list);
        data.put("allNum", size);
        data.put("pageSize",sum);
        data.put("currentPage", currentPage);
        result.put("data", data);
        if(size==0){
            result.put("code","fail");
            result.put("msg","无结果");
        }else {
            result.put("code", "success");
            result.put("msg", "查询成功!");
        }
        return result;
    }

    /** 会员中心接口 **/
    @Override
    public JSONObject getJoinableTraining(String id) {
        JSONObject result=new JSONObject();

        TrainingExample trainingExample=new TrainingExample();
        TrainingExample.Criteria criteria=trainingExample.createCriteria();
        criteria.andTrainingStartTimeLessThan(new Date()).andTrainingEndTimeGreaterThan(new Date());
        List<Training> trainings=trainingMapper.selectByExample(trainingExample);
        if(trainings.size()==0){
            result.put("code", "fail");
            result.put("msg", "无可报名培训");
            result.put("data", null);
            return result;
        }
        int isVip=0;
        Member member=memberMapper.selectByPrimaryKey(id);
        if(member!=null){
            if(member.getIsVip()==true){
                isVip=1;
            }
        }
        JSONArray data=new JSONArray();
        for(Training training:trainings){
            JSONObject object=new JSONObject();
            object.put("id", training.getTrainingId());
            object.put("name", training.getTrainingName());
            object.put("date", simpleDateFormat.format(training.getTrainingStartTime())+"----"+simpleDateFormat.format(training.getTrainingEndTime()));
            object.put("address", training.getTrainingAddress());
            JSONArray introduction=new JSONArray();
            introduction.add(training.getTrainingIntroduce());
            object.put("introduction", introduction);
            if(isVip==0){
                object.put("fee", training.getTrainingFeeNormal());
            }else {
                object.put("fee", training.getTrainingFeeVip());
            }
            object.put("contacts", training.getContacts());
            data.add(object);
        }
        result.put("code", "success");
        result.put("msg", "获取可报名培训成功");
        result.put("data", data);
        return result;
    }

    @Override
    public JSONObject getMemberSignedTraining(String id) {
        JSONObject result=new JSONObject();
        List<Integer> trainingIds=TrainingStudentSQLConn.getTrainingIdByMemberId(id);
        if(trainingIds.size()==0){
            result.put("code", "fail");
            result.put("msg", "暂无学员报名培训");
            result.put("data", null);
        }
        List<Training> trainings=new ArrayList<Training>();
        for(int i=0;i<trainingIds.size();i++){
            trainings.add(trainingMapper.selectByPrimaryKey(trainingIds.get(i)));
        }
        Collections.sort(trainings);
        int isVip=0;
        Member member=memberMapper.selectByPrimaryKey(id);
        if(member!=null){
            if(member.getIsVip()==true){
                isVip=1;
            }
        }
        JSONArray data=new JSONArray();
        for(Training training:trainings){
            JSONObject object=new JSONObject();
            object.put("id", training.getTrainingId());
            object.put("name", training.getTrainingName());
            object.put("date", simpleDateFormat.format(training.getTrainingStartTime())+"----"+simpleDateFormat.format(training.getTrainingEndTime()));
            object.put("address", training.getTrainingAddress());
            JSONArray introduction=new JSONArray();
            introduction.add(training.getTrainingIntroduce());
            object.put("introduction", introduction);
            if(isVip==0){
                object.put("fee", training.getTrainingFeeNormal());
            }else {
                object.put("fee", training.getTrainingFeeVip());
            }
            object.put("contacts", training.getContacts());
            data.add(object);
        }
        result.put("code", "success");
        result.put("msg", "获取可报名培训成功");
        result.put("data", data);
        return result;
    }
}

