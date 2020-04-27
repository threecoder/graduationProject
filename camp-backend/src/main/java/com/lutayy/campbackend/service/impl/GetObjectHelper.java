package com.lutayy.campbackend.service.impl;

import com.lutayy.campbackend.common.util.JwtUtil;
import com.lutayy.campbackend.dao.*;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.pojo.request.TokenRequest;
import com.lutayy.campbackend.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Component
public class GetObjectHelper {

    @Value("${SECRET_KEY}")
    private String SECRET_KEY;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    MemberReStudentMapper memberReStudentMapper;
    @Autowired
    AdminReAuthorityMapper adminReAuthorityMapper;
    @Autowired
    ExamReStudentMapper examReStudentMapper;
    @Autowired
    TrainingReStudentMapper trainingReStudentMapper;
    @Autowired
    ActivityStudentMapper activityStudentMapper;
    @Autowired
    ActivitySeatMapper activitySeatMapper;
    @Autowired
    SystemParameterMapper systemParameterMapper;
    @Autowired
    ActivityOrderMapper activityOrderMapper;
    @Autowired
    TrainingOrderMapper trainingOrderMapper;
    @Autowired
    CertificateChangeOrderMapper changeOrderMapper;
    @Autowired
    CertificateRecheckOrderMapper recheckOrderMapper;
    @Autowired
    MemberSubscriptionOrderMapper memberSubscriptionOrderMapper;

    /** 由管理员account获得会员对象 **/
    public Admin getAdminFromAccount(String account) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andAdminAccountEqualTo(account);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins.size() > 0)
            return admins.get(0);
        else
            return null;
    }

    /** 获取管理员-权限的值 **/
    public AdminReAuthority getAdminReAuthority(Integer adminId, Integer authorityId) {
        AdminReAuthorityExample adminReAuthorityExample = new AdminReAuthorityExample();
        adminReAuthorityExample.createCriteria().andAdminIdEqualTo(adminId).andAuthorityIdEqualTo(authorityId);
        List<AdminReAuthority> adminReAuthorities = adminReAuthorityMapper.selectByExample(adminReAuthorityExample);
        if (adminReAuthorities.size() > 0)
            return adminReAuthorities.get(0);
        else
            return null;
    }

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

    /** 由会员名获得会员对象 **/
    public Member getMemberFromMemberName(String memberName){
        MemberExample memberExample=new MemberExample();
        MemberExample.Criteria criteria=memberExample.createCriteria();
        criteria.andMemberNameEqualTo(memberName);
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

    /** 获取成绩单 **/
    public ExamReStudent getReportByExamIdAndStudentId(Integer examId,Integer studentId) {
        ExamReStudentExample reportExample = new ExamReStudentExample();
        reportExample.createCriteria().andExamIdEqualTo(examId).andStudentIdEqualTo(studentId);
        List<ExamReStudent> reports = examReStudentMapper.selectByExample(reportExample);
        if (reports.size() == 0) {
            return null;
        } else {
            return reports.get(0);
        }
    }

    /** 获取培训Re学员 **/
    public TrainingReStudent getTrainingReStudentByIds(Integer trainingId, Integer studentId) {
        TrainingReStudentExample trainingReStudentExample = new TrainingReStudentExample();
        trainingReStudentExample.createCriteria().andStudentIdEqualTo(studentId).andTrainingIdEqualTo(trainingId);
        List<TrainingReStudent> trainingReStudents = trainingReStudentMapper.selectByExample(trainingReStudentExample);
        if (trainingReStudents.size() == 0) {
            return null;
        } else {
            return trainingReStudents.get(0);
        }
    }

    /** 获取活动Re学员 **/
    public ActivityStudent getActivityReStudentByIds(Integer activityId, Integer studentId) {
        ActivityStudentExample activityStudentExample = new ActivityStudentExample();
        activityStudentExample.createCriteria().andActivityIdEqualTo(activityId).andStudentIdEqualTo(studentId);
        List<ActivityStudent> activityStudents = activityStudentMapper.selectByExample(activityStudentExample);
        if (activityStudents.size() == 0) {
            return null;
        } else {
            return activityStudents.get(0);
        }
    }

    /** 由活动id和真实坐标获得座位对象 **/
    public ActivitySeat getSeatByActivityIdAndXY(Integer activityId, Integer row, Integer col) {
        ActivitySeatExample activitySeatExample = new ActivitySeatExample();
        activitySeatExample.createCriteria().andActivityIdEqualTo(activityId).andRealYEqualTo(row).andRealXEqualTo(col);
        List<ActivitySeat> activitySeats = activitySeatMapper.selectByExample(activitySeatExample);
        if (activitySeats.size() == 0) {
            return null;
        } else {
            return activitySeats.get(0);
        }
    }

    /** 从请求中获取用户id **/
    public Integer getIdByRequestAndRole(String role, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = null;
        if(cookies==null){
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("token")) {
                token = cookies[i].getValue();
                break;
            }
        }
        if (token == null || token.equals("")){
            return null;
        }
        TokenRequest tokenRequest = JwtUtil.unsign(token, TokenRequest.class, SECRET_KEY);
        if(tokenRequest==null){
            return null;
        }
        if (tokenRequest.getRole() == null || !tokenRequest.getRole().equals(role)){
            return null;
        }
        return tokenRequest.getId();
    }

    //获取系统参数
    public Integer getSystemParaByParaKey(String paraKey){
        SystemParameterExample systemParameterExample=new SystemParameterExample();
        systemParameterExample.createCriteria().andFlagEqualTo(true).andParaKeyEqualTo(paraKey);
        List<SystemParameter> parameters=systemParameterMapper.selectByExample(systemParameterExample);
        if(parameters.size()==0)
            return null;
        try {
            Integer value = Integer.valueOf(parameters.get(0).getParaValue());
            return value;
        }catch (Exception e){
            return null;
        }
    }

    //由订单号获取订单
    public Object getOrderByOrderCode(String orderCode, String orderType){
        if(orderType.equals("activity")){
            ActivityOrderExample example=new ActivityOrderExample();
            example.createCriteria().andActivityOrderIdEqualTo(orderCode);
            List<ActivityOrder> orders=activityOrderMapper.selectByExample(example);
            if(orders.size()==0)
                return null;
            return orders.get(0);
        }
        else if(orderType.equals("training")){
            TrainingOrderExample example=new TrainingOrderExample();
            example.createCriteria().andTrainingOrderIdEqualTo(orderCode);
            List<TrainingOrder> orders=trainingOrderMapper.selectByExample(example);
            if(orders.size()==0)
                return null;
            return orders.get(0);
        }
        else if(orderType.equals("cerChange")){
            CertificateChangeOrderExample example=new CertificateChangeOrderExample();
            example.createCriteria().andCertificateChangeOrderIdEqualTo(orderCode);
            List<CertificateChangeOrder> orders=changeOrderMapper.selectByExample(example);
            if(orders.size()==0)
                return null;
            return orders.get(0);
        }
        else if(orderType.equals("cerRecheck")){
            CertificateRecheckOrderExample example=new CertificateRecheckOrderExample();
            example.createCriteria().andCertificateRecheckOrderIdEqualTo(orderCode);
            List<CertificateRecheckOrder> orders=recheckOrderMapper.selectByExample(example);
            if(orders.size()==0)
                return null;
            return orders.get(0);
        }
        else if(orderType.equals("member")){
            MemberSubscriptionOrderExample example=new MemberSubscriptionOrderExample();
            example.createCriteria().andSubscriptionOrderIdEqualTo(orderCode);
            List<MemberSubscriptionOrder> orders=memberSubscriptionOrderMapper.selectByExample(example);
            if(orders.size()==0)
                return null;
            return orders.get(0);
        }
        else
            return null;
    }

    //获取订单结束时间
    public Date getOrderEndTime(String orderType, Date startTime){
        String paraKey;
        if(orderType.equals("activity"))
            paraKey="activity_order_length";
        else if(orderType.equals("training"))
            paraKey="training_order_length";
        else if(orderType.equals("cerChange"))
            paraKey="certificate_order_length";
        else if(orderType.equals("cerRecheck"))
            paraKey="certificate_order_length";
        else if(orderType.equals("member"))
            paraKey="member_order_length";
        else
            return null;
        Calendar cal=Calendar.getInstance();
        cal.setTime(startTime);
        cal.add(Calendar.MINUTE, getSystemParaByParaKey(paraKey));
        return cal.getTime();
    }

    //检查活动订单是否过期
    public boolean checkActivityOrderOutOfTime(ActivityOrder activityOrder){
        Date endTime=getOrderEndTime("activity", activityOrder.getOrderBeginTime());
        if(activityOrder.getClose())
            return true;
        if(!activityOrder.getClose()){
            if(!activityOrder.getPaymentState()){
                //查看未支付的订单是否过期，失效则置close为1
                if(endTime.compareTo(new Date())<0){
                    activityOrder.setClose(true);
                    activityOrderMapper.updateByPrimaryKeySelective(activityOrder);
                    return true;
                }
            }
        }
        return false;
    }

    //检查活动订单是否过期
    public boolean checkTrainingOrderOutOfTime(TrainingOrder trainingOrder){
        Date endTime=getOrderEndTime("training", trainingOrder.getOrderBeginTime());
        if(trainingOrder.getClose())
            return true;
        if(!trainingOrder.getClose()){
            if(!trainingOrder.getPaymentState()){
                //查看未支付的订单是否过期，失效则置close为1
                if(endTime.compareTo(new Date())<0){
                    trainingOrder.setClose(true);
                    trainingOrderMapper.updateByPrimaryKeySelective(trainingOrder);
                    return true;
                }
            }
        }
        return false;
    }

    //
    public boolean checkCerChangeOrderOutOfTime(CertificateChangeOrder certificateChangeOrder){
        Date endTime=getOrderEndTime("cerRecheck", certificateChangeOrder.getOrderBeginTime());
        if(certificateChangeOrder.getClose())
            return true;
        if(!certificateChangeOrder.getClose()){
            if(!certificateChangeOrder.getPaymentState()){
                //查看未支付的订单是否过期，失效则置close为1
                if(endTime.compareTo(new Date())<0){
                    certificateChangeOrder.setClose(true);
                    changeOrderMapper.updateByPrimaryKeySelective(certificateChangeOrder);
                    return true;
                }
            }
        }
        return false;
    }

    //
    public boolean checkCerReCheckOrderOutOfTime(CertificateRecheckOrder certificateRecheckOrder){
        Date endTime=getOrderEndTime("cerChange", certificateRecheckOrder.getOrderBeginTime());
        if(certificateRecheckOrder.getClose())
            return true;
        if(!certificateRecheckOrder.getClose()){
            if(!certificateRecheckOrder.getPaymentState()){
                //查看未支付的订单是否过期，失效则置close为1
                if(endTime.compareTo(new Date())<0){
                    certificateRecheckOrder.setClose(true);
                    recheckOrderMapper.updateByPrimaryKeySelective(certificateRecheckOrder);
                    return true;
                }
            }
        }
        return false;
    }

    //
    public boolean checkMemberSusOrderOutOfTime(MemberSubscriptionOrder memberSubscriptionOrder){
        Date endTime=getOrderEndTime("cerChange", memberSubscriptionOrder.getCreateTime());
        if(memberSubscriptionOrder.getClose())
            return true;
        if(!memberSubscriptionOrder.getClose()){
            if(!memberSubscriptionOrder.getPaymentState()){
                //查看未支付的订单是否过期，失效则置close为1
                if(endTime.compareTo(new Date())<0){
                    memberSubscriptionOrder.setClose(true);
                    memberSubscriptionOrderMapper.updateByPrimaryKeySelective(memberSubscriptionOrder);
                    return true;
                }
            }
        }
        return false;
    }
}
