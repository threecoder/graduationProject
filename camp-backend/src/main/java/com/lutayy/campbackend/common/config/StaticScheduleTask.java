package com.lutayy.campbackend.common.config;


import com.lutayy.campbackend.dao.*;
import com.lutayy.campbackend.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class StaticScheduleTask {

    @Scheduled(cron = "0 0 2 * * ?") //每天凌晨2点开始执行
    private void configureTask(){
        checkCer();
        checkMem();
    }

    @Autowired
    CertificateMapper certificateMapper;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    MessageTextMapper messageTextMapper;
    @Autowired
    MessageToMemberMapper messageToMemberMapper;

    private void checkCer(){
        CertificateExample certificateExample = new CertificateExample();
        certificateExample.createCriteria().andIsInvalidEqualTo(true)
                .andEndTimeLessThanOrEqualTo(new Date());
        Certificate certificate=new Certificate();
        certificate.setIsInvalid(false);
        certificateMapper.updateByExampleSelective(certificate, certificateExample);
        System.out.println("成功更新系统内所有培训证书状态！");
    }

    private void checkMem(){
        Date nowTime = new Date();
        MemberExample memberExample=new MemberExample();
        memberExample.createCriteria().andIsVipEqualTo(true)
                .andVipEndDateLessThanOrEqualTo(nowTime);
        Member memberRecord = new Member();
        memberRecord.setIsVip(false);
        List<Member> members=memberMapper.selectByExample(memberExample);
        if(members.size()>0){
            MessageText messageText=new MessageText();
            messageText.setType("memberRenew");
            messageText.setSendTime(nowTime);
            messageText.setTitle("会员过期通知");
            messageText.setMessage("您的会员于今天过期，如有需要请到线下办理");
            messageTextMapper.insertSelective(messageText);
            for(Member member:members){
                MessageToMember messageToMember=new MessageToMember();
                messageToMember.setMessageId(messageText.getMessageId());
                messageToMember.setAdminId(4);
                messageToMember.setMemberId(member.getMemberKeyId());
                messageToMemberMapper.insertSelective(messageToMember);
            }
        }
        memberMapper.updateByExampleSelective(memberRecord, memberExample);
        System.out.println("成功更新系统内会员状态！");
    }

}
