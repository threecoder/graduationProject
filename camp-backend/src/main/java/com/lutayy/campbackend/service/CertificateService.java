package com.lutayy.campbackend.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface CertificateService {

    //管理员修改某个证书信息(修改背景图)
    JSONObject modifyCerInfo(JSONObject jsonObject);
    //上传背景图
    JSONObject uploadCerBackground(MultipartFile img, String imgName);
    //获取证书背景图片列表
    JSONObject getCerBackgroundImg();
    //管理员获取某个培训证书信息（返回证书背景id）
    JSONObject adminGetCerInfo(Integer trainingId);
    //管理员获取审核记录列表
    JSONObject adminGetOperLogList(Integer adminId, String cerName, String userName, Integer pageSize, Integer currentPage, String type);
    //管理员通过某条记录申请
    JSONObject passOperLog(JSONObject jsonObject);
    //管理员拒绝某个记录申请
    JSONObject rejectOperLog(JSONObject jsonObject);


    //会员获取学员现有证书
    JSONObject memberGetStudentCerList(Integer memberId, String cerName, String trainingName, Integer pageSize, Integer currentPage);
    //会员获取学员的某个证书相关信息
    JSONObject memberGetCerInfo(Integer memberId, String idNum, Integer cerId);


    //学员获取证书文件
    ResponseEntity<byte[]> getCerFile(Integer studentId, Integer cerId);
    //学员获取某个证书相关信息
    JSONObject studentGetCerInfo(Integer studentId, Integer cerId);
    //学员获取现有证书
    JSONObject studentGetStudentCerList(Integer studentId, String cerName, String trainingName, Integer pageSize, Integer currentPage);
    //学员获取自己的过期证书
    JSONObject studentGetOverdueCerList(Integer studentId, String cerName, String trainingName, Integer pageSize, Integer currentPage);
    //学员修改某个证书相关信息
    JSONObject studentModifyCerInfo(JSONObject jsonObject);
    //学员发起证书复审
    JSONObject examineCertificate(JSONObject jsonObject);
    //学员获取本账号上的证书审核记录
    JSONObject studentGetOperList(Integer studentId, String cerName, String userName, Integer pageSize, Integer currentPage, String type);

}
