package com.lutayy.campbackend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.common.config.AuthorityParam;
import com.lutayy.campbackend.common.util.OrderIdGenerator;
import com.lutayy.campbackend.common.util.PdfUtil;
import com.lutayy.campbackend.common.util.RedisUtil;
import com.lutayy.campbackend.common.util.UUIDUtil;
import com.lutayy.campbackend.dao.*;
import com.lutayy.campbackend.pojo.*;
import com.lutayy.campbackend.service.CertificateService;
import com.lutayy.campbackend.service.SQLConn.CertificateSQLConn;
import com.lutayy.campbackend.service.SQLConn.SystemParamManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    MemberMapper memberMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    MemberReStudentMapper memberReStudentMapper;
    @Autowired
    TrainingMapper trainingMapper;
    @Autowired
    CertificateMapper certificateMapper;
    @Autowired
    CertificateImageMapper certificateImageMapper;
    @Autowired
    AssociationTextMapper associationTextMapper;
    @Autowired
    CertificateChangeLogMapper changeLogMapper;
    @Autowired
    CertificateRecheckLogMapper recheckLogMapper;
    @Autowired
    CertificateChangeOrderMapper changeOrderMapper;
    @Autowired
    CertificateRecheckOrderMapper recheckOrderMapper;
    @Autowired
    CertificateChangeOrderNewMemberNameMapper certificateChangeOrderNewMemberNameMapper;
    @Autowired
    MessageTextMapper messageTextMapper;
    @Autowired
    MessageToStudentMapper messageToStudentMapper;

    @Autowired
    GetObjectHelper getObjectHelper;
    @Resource
    private RedisUtil redisUtil;

    @Value("${IMG_HOST}")
    private String IMG_HOST;

    /** 管理员操作 **/
    //管理员修改某个证书信息(修改背景图)
    @Override
    public JSONObject modifyCerInfo(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        //权限检查
        Integer opAdminId = jsonObject.getInteger("id");
        if (!getObjectHelper.checkAdminIfHasAuthority(opAdminId, AuthorityParam.CERTIFICATE)) {
            result.put("msg", "操作失败！当前用户无该操作权限");
            return result;
        }
        Integer trainingId=jsonObject.getInteger("trainingId");
        Training training=trainingMapper.selectByPrimaryKey(trainingId);
        if(training==null){
            result.put("msg", "系统中找不到该培训！");
            return result;
        }
        Integer imgId=jsonObject.getInteger("imgId");
        CertificateImage certificateImage=certificateImageMapper.selectByPrimaryKey(imgId);
        if(certificateImage==null){
            result.put("msg", "系统中找不到该背景图片！");
            return result;
        }
        training.setCerImgId(imgId);
        trainingMapper.updateByPrimaryKeySelective(training);
        result.put("code", "success");
        result.put("msg", "培训证书背景修改成功！");
        return result;
    }

    //上传背景图
    @Override
    public JSONObject uploadCerBackground(MultipartFile img, String imgName) {
        JSONObject result=new JSONObject();
        String mainPath = "./src/main/resources/static";
        String dirPath="/image/certificate_background";
        String imgPath=dirPath+"/"+UUIDUtil.getMemberUUID().substring(3)+ new Date().getTime()+".jpg";
        File dest=new File(mainPath+dirPath);
        if (!dest.exists()) {
            dest.mkdirs();
        }
        try{
            FileOutputStream out = new FileOutputStream(mainPath + imgPath);
            out.write(img.getBytes());
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        CertificateImage certificateImage=new CertificateImage();
        certificateImage.setImgName(imgName);
        certificateImage.setImgPath(imgPath);
        certificateImageMapper.insertSelective(certificateImage);
        result.put("code", "success");
        result.put("msg", "上传新背景成功！");
        return result;
    }

    //获取证书背景图片列表
    @Override
    public JSONObject getCerBackgroundImg() {
        JSONObject result=new JSONObject();
        String mainPath = "./src/main/resources/static";

        CertificateImageExample example=new CertificateImageExample();
        example.createCriteria().andIsInvalidEqualTo(false);
        List<CertificateImage> images=certificateImageMapper.selectByExample(example);
        JSONArray list=new JSONArray();
        for(CertificateImage image:images){
            JSONObject object=new JSONObject();
            object.put("url", IMG_HOST+image.getImgPath());
            object.put("name", image.getImgName());
            object.put("imgId", image.getImageId());
            list.add(object);
        }
        result.put("data", list);
        result.put("code", "success");
        result.put("msg", "获取证书背景列表成功！");
        return result;
    }

    //管理员获取某个培训证书信息（返回证书背景id）
    @Override
    public JSONObject adminGetCerInfo(Integer trainingId) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        Training training=trainingMapper.selectByPrimaryKey(trainingId);
        if(training==null){
            result.put("msg", "系统中找不到该培训！");
            return result;
        }
        result.put("code", "success");
        result.put("msg", "获取证书背景成功！");
        result.put("data", training.getCerImgId());
        return result;
    }

    //管理员获取审核记录列表
    @Override
    public JSONObject adminGetOperLogList(Integer adminId, String cerName, String userName,
                                          Integer pageSize, Integer currentPage, String type) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();
        long total=0;
        JSONArray list=new JSONArray();
        if(type.equals("modify")){
            CertificateChangeLogExample changeLogExample=new CertificateChangeLogExample();
            CertificateChangeLogExample.Criteria criteria = changeLogExample.createCriteria();
            if(cerName!=null && !cerName.equals("")){
                criteria.andCertificateNameLike("%"+cerName+"%");
            }
            if(userName!=null && !userName.equals("")){
                criteria.andOpUserNameLike("%"+userName+"%");
            }
            total=changeLogMapper.countByExample(changeLogExample);
            changeLogExample.setOffset((currentPage-1)*pageSize);
            changeLogExample.setLimit(pageSize);
            List<CertificateChangeLog> changeLogs=changeLogMapper.selectByExample(changeLogExample);
            for(CertificateChangeLog changeLog:changeLogs){
                JSONObject object=new JSONObject();
                object.put("operId", changeLog.getLogId());
                object.put("userName", changeLog.getOpUserName());
                object.put("userType", changeLog.getOpUserType());
                object.put("cerName", changeLog.getCertificateName());
                object.put("desc", changeLog.getOpDescription());
                object.put("operTime", changeLog.getOpTime());
                object.put("success", changeLog.getIsSuccess());
                list.add(object);
            }
        }
        else if(type.equals("recheck")){
            CertificateRecheckLogExample recheckLogExample=new CertificateRecheckLogExample();
            CertificateRecheckLogExample.Criteria criteria = recheckLogExample.createCriteria();
            if(cerName!=null && !cerName.equals("")){
                criteria.andCertificateNameLike("%"+cerName+"%");
            }
            if(userName!=null && !userName.equals("")){
                criteria.andOpUserNameLike("%"+userName+"%");
            }
            total=recheckLogMapper.countByExample(recheckLogExample);
            recheckLogExample.setOffset((currentPage-1)*pageSize);
            recheckLogExample.setLimit(pageSize);
            List<CertificateRecheckLog> recheckLogs=recheckLogMapper.selectByExample(recheckLogExample);
            for(CertificateRecheckLog recheckLog:recheckLogs){
                JSONObject object=new JSONObject();
                object.put("userName", recheckLog.getOpUserName());
                object.put("userType", recheckLog.getOpUserType());
                object.put("cerName", recheckLog.getCertificateName());
                object.put("desc", recheckLog.getOpDescription());
                object.put("operTime", recheckLog.getOpTime());
                object.put("success", recheckLog.getIsSuccess());
                list.add(object);
            }
        }
        else {
            result.put("msg", "类型错误");
            return result;
        }
        data.put("list", list);
        data.put("data", total);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取证书复审申请记录成功！");
        return result;
    }

    //管理员通过某条记录申请
    @Override
    public JSONObject passOperLog(JSONObject jsonObject) {
        Integer operId=jsonObject.getInteger("operId");
        String type=jsonObject.getString("type");
        Integer adminId=jsonObject.getInteger("id");
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        if(type!=null && type.equals("modify")){
            CertificateChangeLog changeLog=changeLogMapper.selectByPrimaryKey(operId);
            if(changeLog==null){
                result.put("msg", "系统中无该申请记录");
                return result;
            }
            if(!changeLog.getIsSuccess().equals("wait")){
                result.put("msg", "该记录已被确认，请勿重复操作");
                return result;
            }
            String newMemberName=changeLog.getNewMemberName();
            // TODO 更新申请记录状态为success
            changeLog.setIsSuccess("success");
            changeLogMapper.updateByPrimaryKeySelective(changeLog);
            // TODO 将证书移出审核队列
            Certificate certificate=certificateMapper.selectByPrimaryKey(changeLog.getCertificateId());
            certificate.setInChangeLine(false);
            certificateMapper.updateByPrimaryKeySelective(certificate);
            // TODO 新建订单
            CertificateChangeOrder changeOrder=new CertificateChangeOrder();
            String orderId = OrderIdGenerator.getUniqueId();
            while (!redisUtil.hset("order_no_map", orderId, "cerChange")) {
                orderId = OrderIdGenerator.getUniqueId();
            }
            changeOrder.setCertificateChangeOrderId(orderId);
            changeOrder.setCertificateId(changeLog.getCertificateId());
            changeOrder.setStudentId(changeLog.getCerStudentId());
            changeOrder.setOrderType(true);
            String fee= SystemParamManager.getValueByKey("certificate_change_fee");
            changeOrder.setOrderPrice(BigDecimal.valueOf(Double.valueOf(fee==null?"9999999.99":fee)));
            changeOrder.setOrderBeginTime(new Date());
            if(changeLog.getOpUserType().equals("student")) {
                changeOrder.setOpManName(studentMapper.selectByPrimaryKey(changeLog.getOpUserId()).getStudentName());
            }
            changeOrder.setBusinessName("证书更改申请:"+changeLog.getOpDescription());
            changeOrderMapper.insertSelective(changeOrder);
            CertificateChangeOrderNewMemberName certificateChangeOrderNewMemberName=new CertificateChangeOrderNewMemberName();
            certificateChangeOrderNewMemberName.setChangeOrderKeyId(changeOrder.getOrderKeyId());
            certificateChangeOrderNewMemberName.setNewMemberName(newMemberName);
            // 订单-新会员名
            certificateChangeOrderNewMemberNameMapper.insertSelective(certificateChangeOrderNewMemberName);
            // TODO 插入新通知
            Date nowTime=new Date();
            MessageText messageText=new MessageText();
            messageText.setType("cerChange");
            messageText.setTitle("您的"+changeLog.getCertificateName()+"校正申请记录有新的状态");
            messageText.setMessage("申请通过！已自动生成订单，可到订单中心中查看并支付");
            messageText.setSendTime(nowTime);
            messageTextMapper.insertSelective(messageText);
            MessageToStudent messageToStudent=new MessageToStudent();
            messageToStudent.setMessageId(messageText.getMessageId());
            messageToStudent.setStudentId(changeLog.getOpUserId());
            messageToStudent.setAdminId(adminId);
            messageToStudent.setSendTime(nowTime);
            messageToStudentMapper.insertSelective(messageToStudent);
        }
        else if(type!=null && type.equals("recheck")){
            CertificateRecheckLog recheckLog=recheckLogMapper.selectByPrimaryKey(operId);
            if(recheckLog==null){
                result.put("msg", "系统中无该申请记录");
                return result;
            }
            if(!recheckLog.getIsSuccess().equals("wait")){
                result.put("msg", "该记录已被确认，请勿重复操作");
                return result;
            }
            // TODO 更新申请记录状态为success
            recheckLog.setIsSuccess("success");
            recheckLogMapper.updateByPrimaryKeySelective(recheckLog);
            // TODO 将证书移出审核队列
            Certificate certificate=certificateMapper.selectByPrimaryKey(recheckLog.getCertificateId());
            certificate.setInRecheckLine(false);
            certificateMapper.updateByPrimaryKeySelective(certificate);
            // TODO 新建订单
            String orderId = OrderIdGenerator.getUniqueId();
            while (!redisUtil.hset("order_no_map", orderId, "cerRecheck")) {
                orderId = OrderIdGenerator.getUniqueId();
            }
            CertificateRecheckOrder recheckOrder=new CertificateRecheckOrder();
            recheckOrder.setCertificateRecheckOrderId(orderId);
            recheckOrder.setCertificateId(recheckLog.getCertificateId());
            recheckOrder.setStudentId(recheckLog.getCerStudentId());
            String fee= SystemParamManager.getValueByKey("certificate_recheck_fee");
            recheckOrder.setOrderPrice(BigDecimal.valueOf(Double.valueOf(fee==null?"9999999.99":fee)));
            recheckOrder.setOrderBeginTime(new Date());
            if(recheckLog.getOpUserType().equals("student")) {
                recheckOrder.setOpManName(studentMapper.selectByPrimaryKey(recheckLog.getOpUserId()).getStudentName());
            }
            recheckOrder.setBusinessName("证书复审申请");
            recheckOrderMapper.insertSelective(recheckOrder);
            // TODO 插入新通知
            Date nowTime=new Date();
            MessageText messageText=new MessageText();
            messageText.setType("cerRecheck");
            messageText.setTitle("您的"+recheckLog.getCertificateName()+"复审申请记录有新的状态");
            messageText.setMessage("申请通过！已自动生成订单，可到订单中心中查看并支付");
            messageText.setSendTime(nowTime);
            messageTextMapper.insertSelective(messageText);
            MessageToStudent messageToStudent=new MessageToStudent();
            messageToStudent.setMessageId(messageText.getMessageId());
            messageToStudent.setStudentId(recheckLog.getOpUserId());
            messageToStudent.setAdminId(adminId);
            messageToStudent.setSendTime(nowTime);
            messageToStudentMapper.insertSelective(messageToStudent);
        } else {
            result.put("msg", "类型错误");
            return result;
        }
        result.put("code", "success");
        result.put("msg", "操作成功！生成订单成功");
        return result;
    }

    //管理员拒绝某个记录申请
    @Override
    public JSONObject rejectOperLog(JSONObject jsonObject) {
        Integer operId=jsonObject.getInteger("operId");
        String type=jsonObject.getString("type");
        Integer adminId=jsonObject.getInteger("id");

        JSONObject result=new JSONObject();
        result.put("code", "fail");
        if(type!=null && type.equals("modify")){
            CertificateChangeLog changeLog=changeLogMapper.selectByPrimaryKey(operId);
            if(changeLog==null){
                result.put("msg", "系统中无该申请记录");
                return result;
            }
            if(!changeLog.getIsSuccess().equals("wait")){
                result.put("msg", "该记录已被确认，请勿重复操作");
                return result;
            }
            // TODO 更新申请记录状态为fail
            changeLog.setIsSuccess("fail");
            changeLogMapper.updateByPrimaryKeySelective(changeLog);
            // TODO 将证书移出审核队列
            Certificate certificate=certificateMapper.selectByPrimaryKey(changeLog.getCertificateId());
            certificate.setInChangeLine(false);
            certificateMapper.updateByPrimaryKeySelective(certificate);
            // TODO 插入新通知
            Date nowTime=new Date();
            MessageText messageText=new MessageText();
            messageText.setType("cerChange");
            messageText.setTitle("您的"+changeLog.getCertificateName()+"校正申请记录有新的状态");
            messageText.setMessage("申请结果为不通过，如有问题请联系相关工作人员");
            messageText.setSendTime(nowTime);
            messageTextMapper.insertSelective(messageText);
            MessageToStudent messageToStudent=new MessageToStudent();
            messageToStudent.setMessageId(messageText.getMessageId());
            messageToStudent.setStudentId(changeLog.getOpUserId());
            messageToStudent.setAdminId(adminId);
            messageToStudent.setSendTime(nowTime);
            messageToStudentMapper.insertSelective(messageToStudent);
        }
        else if(type!=null && type.equals("recheck")){
            CertificateRecheckLog recheckLog=recheckLogMapper.selectByPrimaryKey(operId);
            if(recheckLog==null){
                result.put("msg", "系统中无该申请记录");
                return result;
            }
            if(!recheckLog.getIsSuccess().equals("wait")){
                result.put("msg", "该记录已被确认，请勿重复操作");
                return result;
            }
            // TODO 更新申请记录状态为fail
            recheckLog.setIsSuccess("fail");
            recheckLogMapper.updateByPrimaryKeySelective(recheckLog);
            // TODO 将证书移出审核队列
            Certificate certificate=certificateMapper.selectByPrimaryKey(recheckLog.getCertificateId());
            certificate.setInRecheckLine(false);
            certificateMapper.updateByPrimaryKeySelective(certificate);
            // TODO 插入新通知
            Date nowTime=new Date();
            MessageText messageText=new MessageText();
            messageText.setType("cerRecheck");
            messageText.setTitle("您的"+recheckLog.getCertificateName()+"复审申请记录有新的状态");
            messageText.setMessage("申请结果为不通过，如有问题请联系相关工作人员");
            messageText.setSendTime(nowTime);
            messageTextMapper.insertSelective(messageText);
            MessageToStudent messageToStudent=new MessageToStudent();
            messageToStudent.setMessageId(messageText.getMessageId());
            messageToStudent.setStudentId(recheckLog.getOpUserId());
            messageToStudent.setAdminId(adminId);
            messageToStudent.setSendTime(nowTime);
            messageToStudentMapper.insertSelective(messageToStudent);
        } else {
            result.put("msg", "类型错误");
            return result;
        }
        result.put("code", "success");
        result.put("msg", "操作成功！");
        return result;
    }


    /** 会员操作 **/

    //会员获取学员现有证书
    @Override
    public JSONObject memberGetStudentCerList(Integer memberId, String cerName,
                                        String trainingName, Integer pageSize, Integer currentPage) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();

        int total= CertificateSQLConn.memberCountCerficateId(memberId, cerName, trainingName);
        List<Integer> idList=CertificateSQLConn.memberGetCerficateId(memberId, cerName, trainingName, pageSize, currentPage);
        JSONArray list=new JSONArray();
        for(int i=0;i<idList.size();i++){
            JSONObject object=new JSONObject();
            Certificate certificate=certificateMapper.selectByPrimaryKey(idList.get(i));
            object.put("cerId", certificate.getCertificateId());
            object.put("cerName", certificate.getCertificateName());
            Training training=trainingMapper.selectByPrimaryKey(certificate.getTrainingId());
            object.put("trainingName", training.getTrainingName());
            object.put("memberName", memberMapper.selectByPrimaryKey(memberId).getMemberName());
            object.put("effectiveLength", countDateSubDate(certificate.getEndTime(), certificate.getCreateTime()));
            object.put("beginTime", certificate.getCreateTime());
            object.put("endTime", certificate.getEndTime());
            if((certificate.getEndTime().getTime()-new Date().getTime())>(1000 * 60 * 60 * 24 * 90)){
                object.put("isEndding", false);
            }else {
                object.put("isEndding", true);
            }
            list.add(object);
        }
        data.put("data", list);
        data.put("total", total);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取名下学员证书列表成功！");
        return result;
    }
    private String countDateSubDate(Date d1, Date d2) { //计算两个时间相减，返回“X天X小时X分”
        long diff = d1.getTime() - d2.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
        return  (days + "天" + hours + "小时" + minutes + "分钟");
    }

    //会员获取学员的某个证书相关信息
    @Override
    public JSONObject memberGetCerInfo(Integer memberId, String idNum, Integer cerId) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        Student student=getObjectHelper.getStudentFromIdCard(idNum);
        if(student==null){
            result.put("msg", "操作失败！系统中无该名学员");
            return result;
        }
        MemberReStudent memberReStudent=getObjectHelper.getMemberReStudentByStudentId(student.getStudentId());
        if(memberReStudent==null || !memberReStudent.getMemberKeyId().equals(memberId)){
            result.put("msg", "操作失败！该学员没有挂靠在当前会员名下");
            return result;
        }
        Certificate certificate=certificateMapper.selectByPrimaryKey(cerId);
        if(certificate==null){
            result.put("msg", "操作失败！系统中无该证书");
            return result;
        }
        if(!certificate.getStudentId().equals(student.getStudentId())){
            result.put("msg", "操作失败！身份证号与证书所属学员不一致");
            return result;
        }
        result.put("code", "success");
        result.put("msg", "操作成功！");
        result.put("data", null);
        return null;
    }

    /** 学员操作 **/

    //学员获取证书文件
    @Override
    public ResponseEntity<byte[]> getCerFile(Integer studentId, Integer cerId) {
        ResponseEntity<byte[]> response = null;
        Certificate certificate=certificateMapper.selectByPrimaryKey(cerId);
        if(certificate==null)
            return response;
        if(!certificate.getStudentId().equals(studentId))
            return response;
        Student student=studentMapper.selectByPrimaryKey(studentId);
        Training training=trainingMapper.selectByPrimaryKey(certificate.getTrainingId());
        TrainingReStudent trainingReStudent=getObjectHelper.getTrainingReStudentByIds(training.getTrainingId(), studentId);

        String mainPath = "./src/main/resources/templates";
        String temPath;
        String outPath = mainPath + "/certificate/training_cer/" + training.getTrainingId() + "_"+studentId+"_training_cer.pdf";
        String backImgPath = "./src/main/resources/static" +
                    certificateImageMapper.selectByPrimaryKey(training.getCerImgId()).getImgPath();
        Map<String, String> contentMap = new HashMap<>();
        // TODO 参数设置
        contentMap.put("student_name", student.getStudentName());
        contentMap.put("idcard",
                student.getStudentIdcard().substring(0, 5)+"*********"+student.getStudentIdcard().substring(14));
        contentMap.put("enter_year", String.format("%tY",trainingReStudent.getBeginTime()));
        contentMap.put("enter_month", String.format("%tm",trainingReStudent.getBeginTime()));
        contentMap.put("traning_name", "\""+training.getTrainingName()+"\"");
        contentMap.put("pass_year", String.format("%tY",trainingReStudent.getFinishTime()));
        contentMap.put("pass_month", String.format("%tm",trainingReStudent.getFinishTime()));
        contentMap.put("pass_date", String.format("%td",trainingReStudent.getFinishTime()));
        contentMap.put("end_year", String.format("%tY",certificate.getEndTime()));
        contentMap.put("end_month", String.format("%tm",certificate.getEndTime()));
        contentMap.put("end_date", String.format("%td",certificate.getEndTime()));
        AssociationTextExample associationTextExample = new AssociationTextExample();
        associationTextExample.createCriteria().andItemNameEqualTo("association_name");
        AssociationText associationText = associationTextMapper.selectByExample(associationTextExample).get(0);
        contentMap.put("association_name", associationText.getItemContext());
        contentMap.put("issue_year", String.format("%tY",certificate.getCreateTime()));
        contentMap.put("issue_month", String.format("%tm",certificate.getCreateTime()));
        contentMap.put("issue_date", String.format("%td",certificate.getCreateTime()));
        if(certificate.getMemberName()!=null){
            temPath = mainPath + "/certificate/certificate_template_with_member.pdf";
            contentMap.put("member_name", certificate.getMemberName());
        }else {
            temPath = mainPath + "/certificate/certificate_template_without_member.pdf";
        }
        if(!PdfUtil.writePdf(contentMap, temPath, outPath, backImgPath))
            return null;
        File file = new File(outPath);
        InputStream in;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            HttpHeaders headers = new HttpHeaders();
            String fileName = training.getTrainingName()+"_培训证书.pdf";
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
        file.delete();
        return response;
    }

    //学员获取某个证书相关信息
    @Override
    public JSONObject studentGetCerInfo(Integer studentId, Integer cerId) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        Certificate certificate=certificateMapper.selectByPrimaryKey(cerId);
        if(certificate==null){
            result.put("msg", "系统中找不到该证书");
            return result;
        }
        if(!certificate.getStudentId().equals(studentId)){
            result.put("msg", "该证书不属于当前用户");
            return result;
        }
        MemberReStudent memberReStudent=getObjectHelper.getMemberReStudentByStudentId(certificate.getStudentId());
        if(memberReStudent==null){
            result.put("msg", "当前用户无挂靠会员");
            return result;
        }
        result.put("code", "success");
        result.put("msg", "获得证书信息成功！");
        result.put("data", memberReStudent.getMemberKeyId());
        return result;
    }

    //学员获取现有证书
    @Override
    public JSONObject studentGetStudentCerList(Integer studentId, String cerName,
                                               String trainingName, Integer pageSize, Integer currentPage) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();

        int total= CertificateSQLConn.studentCountCerficateId(studentId, cerName, trainingName);
        List<Integer> idList=CertificateSQLConn.studentGetCerficateId(studentId, cerName, trainingName, pageSize, currentPage);
        JSONArray list=new JSONArray();
        for(int i=0;i<idList.size();i++){
            JSONObject object=new JSONObject();
            Certificate certificate=certificateMapper.selectByPrimaryKey(idList.get(i));
            object.put("cerId", certificate.getCertificateId());
            object.put("cerName", certificate.getCertificateName());
            Training training=trainingMapper.selectByPrimaryKey(certificate.getTrainingId());
            object.put("trainingName", training.getTrainingName());
            object.put("memberName", certificate.getMemberName());
            object.put("effectiveLength", countDateSubDate(certificate.getEndTime(), certificate.getCreateTime()));
            object.put("beginTime", certificate.getCreateTime());
            object.put("endTime", certificate.getEndTime());
            if((certificate.getEndTime().getTime()-new Date().getTime())>(1000 * 60 * 60 * 24 * 90)){
                object.put("isEndding", false);
            }else {
                object.put("isEndding", true);
            }
            list.add(object);
        }
        data.put("data", list);
        data.put("total", total);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取证书列表成功！");
        return result;
    }

    //学员获取自己的过期证书
    @Override
    public JSONObject studentGetOverdueCerList(Integer studentId, String cerName,
                                               String trainingName, Integer pageSize, Integer currentPage) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();

        int total= CertificateSQLConn.studentCountOverDueCerficateId(studentId, cerName, trainingName);
        List<Integer> idList=CertificateSQLConn.studentGetOverDueCerficateId(studentId, cerName, trainingName, pageSize, currentPage);
        JSONArray list=new JSONArray();
        for(int i=0;i<idList.size();i++){
            JSONObject object=new JSONObject();
            Certificate certificate=certificateMapper.selectByPrimaryKey(idList.get(i));
            object.put("cerId", certificate.getCertificateId());
            object.put("cerName", certificate.getCertificateName());
            Training training=trainingMapper.selectByPrimaryKey(certificate.getTrainingId());
            object.put("trainingName", training.getTrainingName());
            object.put("memberName", certificate.getMemberName());
            object.put("effectiveLength", countDateSubDate(certificate.getEndTime(), certificate.getCreateTime()));
            object.put("beginTime", certificate.getCreateTime());
            object.put("endTime", certificate.getEndTime());
            if((new Date().getTime()-certificate.getEndTime().getTime())>(1000 * 60 * 60 * 24 * 365)){
                object.put("canRecheck", false);
            }else {
                object.put("canRecheck", true);
            }
            list.add(object);
        }
        data.put("data", list);
        data.put("total", total);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取无效证书列表成功！");
        return result;
    }

    //学员修改某个证书相关信息
    @Override
    public JSONObject studentModifyCerInfo(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        Integer cerId=jsonObject.getInteger("cerId");
        Integer studentId=jsonObject.getInteger("id");
        Certificate certificate=certificateMapper.selectByPrimaryKey(cerId);
        if(!certificate.getStudentId().equals(studentId)){
            result.put("msg", "该证书不属于当前用户！");
            return result;
        }
        if(certificate==null){
            result.put("msg", "系统中找不到该证书");
            return result;
        }
        if(certificate.getInChangeLine()){
            result.put("msg", "该证书修改正在等待管理员审核，请勿重复申请");
            return result;
        }
        CertificateChangeLog log=new CertificateChangeLog();//新的修改申请纪录
        log.setCertificateName(certificate.getCertificateName());
        log.setOpUserName(studentMapper.selectByPrimaryKey(studentId).getStudentName());
        log.setCerStudentId(studentId);
        log.setCertificateId(cerId);
        log.setOpTime(new Date());
        log.setOpUserType("student");
        log.setOpUserId(studentId);
        MemberReStudent memberReStudent=getObjectHelper.getMemberReStudentByStudentId(studentId);
        result.put("msg", "学员的挂靠状态无更改，无法申请变更");
        if(memberReStudent==null && certificate.getMemberName()==null){
            log.setOpDescription("由无会员状态变更为无会员状态");
            log.setIsSuccess("fail");
            return result;
        }
        if(memberReStudent!=null){
            Member member=memberMapper.selectByPrimaryKey(memberReStudent.getMemberKeyId());
            log.setOpDescription("所属会员由"+certificate.getMemberName()+"变更为"+member.getMemberName());
            log.setNewMemberName(member.getMemberName());
            if(member.getMemberName().equals(certificate.getMemberName())) {
                log.setIsSuccess("fail");
                return result;
            }
            log.setIsSuccess("wait");
        }else {
            log.setNewMemberName(null);
            log.setOpDescription("所属会员由"+certificate.getMemberName()+"变更为无会员状态");
            log.setIsSuccess("wait");
        }
        certificate.setInChangeLine(true);
        certificateMapper.updateByPrimaryKeySelective(certificate);
        // 插入记录
        changeLogMapper.insertSelective(log);
        result.put("code", "success");
        result.put("msg", "成功提交校正申请！请耐心等待管理员审核");
        return result;
    }

    //学员发起证书复审
    @Override
    public JSONObject examineCertificate(JSONObject jsonObject) {
        JSONObject result=new JSONObject();
        result.put("code", "fail");
        Integer cerId=jsonObject.getInteger("cerId");
        Integer studentId=jsonObject.getInteger("id");
        Certificate certificate=certificateMapper.selectByPrimaryKey(cerId);
        if(certificate==null){
            result.put("msg", "系统中找不到该证书");
            return result;
        }
        if(!certificate.getStudentId().equals(studentId)){
            result.put("msg", "该证书不属于当前用户！");
            return result;
        }
        if(certificate.getInRecheckLine()){
            result.put("msg", "该证书复审正在等待管理员审核，请勿重复申请");
            return result;
        }
        CertificateRecheckLog log=new CertificateRecheckLog();//新纪录
        log.setCertificateName(certificate.getCertificateName());
        log.setCerStudentId(certificate.getStudentId());
        log.setOpUserName(studentMapper.selectByPrimaryKey(studentId).getStudentName());
        log.setCertificateId(cerId);
        log.setOpTime(new Date());
        log.setOpUserType("student");
        log.setOpUserId(studentId);
        log.setOpDescription("延长证书有效期");
        log.setIsSuccess("wait");
        certificate.setInRecheckLine(true);
        certificateMapper.updateByPrimaryKeySelective(certificate);
        // 插入记录
        recheckLogMapper.insertSelective(log);
        result.put("code", "success");
        result.put("msg", "成功提交延期申请！请耐心等待管理员审核");
        return result;
    }

    //学员获取本账号上的证书审核记录
    @Override
    public JSONObject studentGetOperList(Integer studentId, String cerName, String userName,
                                         Integer pageSize, Integer currentPage, String type) {
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();
        long total=0;
        JSONArray list=new JSONArray();
        if(type.equals("modify")){
            CertificateChangeLogExample changeLogExample=new CertificateChangeLogExample();
            CertificateChangeLogExample.Criteria criteria = changeLogExample.createCriteria();
            criteria.andCerStudentIdEqualTo(studentId);
            if(cerName!=null && !cerName.equals("")){
                criteria.andCertificateNameLike("%"+cerName+"%");
            }
            if(userName!=null && !userName.equals("")){
                criteria.andOpUserNameLike("%"+userName+"%");
            }
            total=changeLogMapper.countByExample(changeLogExample);
            changeLogExample.setOffset((currentPage-1)*pageSize);
            changeLogExample.setLimit(pageSize);
            List<CertificateChangeLog> changeLogs=changeLogMapper.selectByExample(changeLogExample);
            for(CertificateChangeLog changeLog:changeLogs){
                JSONObject object=new JSONObject();
                object.put("userName", changeLog.getOpUserName());
                object.put("userType", changeLog.getOpUserType());
                object.put("cerName", changeLog.getCertificateName());
                object.put("desc", changeLog.getOpDescription());
                object.put("operTime", changeLog.getOpTime());
                object.put("success", changeLog.getIsSuccess());
                list.add(object);
            }
        }
        if(type.equals("recheck")){
            CertificateRecheckLogExample recheckLogExample=new CertificateRecheckLogExample();
            CertificateRecheckLogExample.Criteria criteria = recheckLogExample.createCriteria();
            criteria.andCerStudentIdEqualTo(studentId);
            if(cerName!=null && !cerName.equals("")){
                criteria.andCertificateNameLike("%"+cerName+"%");
            }
            if(userName!=null && !userName.equals("")){
                criteria.andOpUserNameLike("%"+userName+"%");
            }
            total=recheckLogMapper.countByExample(recheckLogExample);
            recheckLogExample.setOffset((currentPage-1)*pageSize);
            recheckLogExample.setLimit(pageSize);
            List<CertificateRecheckLog> recheckLogs=recheckLogMapper.selectByExample(recheckLogExample);
            for(CertificateRecheckLog recheckLog:recheckLogs){
                JSONObject object=new JSONObject();
                object.put("userName", recheckLog.getOpUserName());
                object.put("userType", recheckLog.getOpUserType());
                object.put("cerName", recheckLog.getCertificateName());
                object.put("desc", recheckLog.getOpDescription());
                object.put("operTime", recheckLog.getOpTime());
                object.put("success", recheckLog.getIsSuccess());
                list.add(object);
            }
        }
        data.put("list", list);
        data.put("data", total);
        result.put("data", data);
        result.put("code", "success");
        result.put("msg", "获取证书复审申请记录成功！");
        return result;
    }
}
