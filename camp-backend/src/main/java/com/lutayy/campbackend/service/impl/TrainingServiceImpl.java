package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.lutayy.campbackend.common.util.OrderIdGenerator;
import com.lutayy.campbackend.dao.*;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.SQLConn.TrainingStudentSQLConn;
import com.lutayy.campbackend.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TrainingOrderMapper trainingOrderMapper;
    @Autowired
    TrainingOrderStudentMapper trainingOrderStudentMapper;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    private Student getStudentByIdcard(String idcard){
        StudentExample studentExample=new StudentExample();
        StudentExample.Criteria criteria=studentExample.createCriteria();
        criteria.andStudentIdcardEqualTo(idcard);
        List<Student> students=studentMapper.selectByExample(studentExample);
        if(students.size()==0){
            return null;
        }
        return students.get(0);
    }
    /**
     *  学员培训报名记录查询
     *  有有效报名记录返回1，无则返回0
     **/
    private int checkStudentTraining(int studentId,int trainingId){
        TrainingReStudentExample trainingReStudentExample=new TrainingReStudentExample();
        TrainingReStudentExample.Criteria criteria=trainingReStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(studentId).andTrainingIdEqualTo(trainingId);
        List<TrainingReStudent> trainingReStudents=trainingReStudentMapper.selectByExample(trainingReStudentExample);
        if(trainingReStudents.size()==0){
            return 0;
        }else {
            return 1;
        }
    }

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

        TrainingOrderExample trainingOrderExample=new TrainingOrderExample();
        TrainingOrderExample.Criteria criteria=trainingOrderExample.createCriteria();
        criteria.andOrderTypeEqualTo(false).andMemberIdEqualTo(id).andCloseEqualTo(false).andPaymentStateEqualTo(false);
        trainingOrderExample.setOrderByClause("order_begin_time DESC");
        List<TrainingOrder> trainingOrders=trainingOrderMapper.selectByExample(trainingOrderExample);

        List<Integer> trainingIds=TrainingStudentSQLConn.getTrainingIdByMemberId(id);

        if(trainingIds.size()==0 && trainingOrders.size()==0){
            result.put("code", "fail");
            result.put("msg", "暂无已报名培训");
            result.put("data", null);
        }
        List<Training> trainings=new ArrayList<Training>();
        //此处可以使排列倒过来，使排在前面的是最新报名的培训
        for(int i=0;i<trainingOrders.size();i++){
            trainings.add(trainingMapper.selectByPrimaryKey(trainingOrders.get(i).getTrainingId()));
        }
        for(int i=trainingIds.size()-1;i>=0;i--){
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
        int num=0;
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
            if(num<trainingOrders.size()){
                object.put("status", "有订单未支付");
            } else {
                object.put("status", "已支付");
            }
            object.put("contacts", training.getContacts());
            data.add(object);
            num+=1;
        }
        result.put("code", "success");
        result.put("msg", "成功获取已报名的培训");
        result.put("data", data);
        return result;
    }

    @Override
    public JSONObject memberJoinTraining(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        String memberId=jsonObject.getString("id");
        System.out.println(memberId);
        int trainingId=jsonObject.getInteger("trainingId");
        JSONArray idNums=jsonObject.getJSONArray("idNums");
        Training training=trainingMapper.selectByPrimaryKey(trainingId);
        if(training==null){
            result.put("code", "fail");
            result.put("msg", "系统中没有该培训");
            return result;
        }

        String orderId= OrderIdGenerator.getUniqueId();
        //订单号生成并查重（如非高并发系统基本上可以省略）
        while(trainingOrderMapper.selectByPrimaryKey(orderId)!=null){
            orderId=OrderIdGenerator.getUniqueId();
        }
        /**
         * 由会员报名，对应的订单，还需插入“订单—学生”表
         **/
        TrainingOrder trainingOrder=new TrainingOrder();
        trainingOrder.setTrainingOrderId(orderId);
        trainingOrder.setTrainingId(trainingId);
        trainingOrder.setOrderType(false);//"0"即会员提交的订单
        trainingOrder.setMemberId(memberId);
        trainingOrder.setOrderBeginTime(new Date());
        trainingOrder.setClose(false);
        trainingOrder.setPaymentState(false);
        trainingOrderMapper.insertSelective(trainingOrder);
        int isVip=0;
        Member member=memberMapper.selectByPrimaryKey(memberId);
        if(member!=null){
            if(member.getIsVip()==true){
                isVip=1;
            }
        }
        BigDecimal fee = isVip==0 ? training.getTrainingFeeNormal() : training.getTrainingFeeVip();

        int existTotalCount=0;
        String existTagTip="";
        int existOrderTotalCount=0;
        String existOrderTagTip="";
        for(int i=0;i<idNums.size();i++){
            Student student=getStudentByIdcard(idNums.getString(i));
            if(checkStudentTraining(student.getStudentId(), trainingId)!=0){
                existTotalCount+=1;
                existTagTip+=student.getStudentName()+"("+idNums.getString(i)+") ";
                continue;
            }
            String checkOrderId=TrainingStudentSQLConn.getTrainingOrderByStudentId(student.getStudentId(),trainingId);
//            System.out.println(checkOrderId);
            if(checkOrderId!=null){
                existOrderTotalCount+=1;
                existOrderTagTip+=student.getStudentName()+"(订单"+checkOrderId+") ";
                continue;
            }
            //插入“订单—学生”表
            TrainingOrderStudent trainingOrderStudent=new TrainingOrderStudent();
            trainingOrderStudent.setTrainingOrderId(orderId);
            trainingOrderStudent.setStudentId(student.getStudentId());
            trainingOrderStudentMapper.insertSelective(trainingOrderStudent);
        }
        BigDecimal successNums = new BigDecimal(idNums.size() - existTotalCount - existOrderTotalCount);
//        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        trainingOrder.setOrderPrice(fee.multiply(successNums));
        trainingOrderMapper.updateByPrimaryKeySelective(trainingOrder);
        String msg="尝试为"+idNums.size()+"名学员报名； 成功"+(idNums.size()-existTotalCount-existOrderTotalCount)+"名； ";
        result.put("code", "success");
        if(existTotalCount>0){
            msg+="剔除重复报名"+existTotalCount+"名,分别是:["+existTagTip+"] ";
        }
        if(existOrderTotalCount>0){
            msg+="剔除已存在于该培训未支付订单中的学员"+existOrderTotalCount+"名,分别是:["+existOrderTagTip+"] ";
        }
        if(idNums.size() - existTotalCount - existOrderTotalCount < 1){
            result.put("code", "fail");
            result.put("data",null);
            //提交的名单没有报名成功的，删除订单
            trainingOrderMapper.deleteByPrimaryKey(orderId);
        }else {
            result.put("code", "success");
            result.put("data", orderId);
            msg+="订单生成成功！订单号："+orderId;
        }
        result.put("msg", msg);
        return result;
    }


}

