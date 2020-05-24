package com.lutayy.campbackend.controller;


import com.alibaba.fastjson.JSONObject;
import com.lutayy.campbackend.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CertificateController {

    @Autowired
    CertificateService certificateService;

    /**
     * 管理员操作
     **/
    @RequestMapping("/admin/modifyCerInfo")
    @ResponseBody
    public JSONObject modifyCerInfo(@RequestBody JSONObject jsonObject) {
        return certificateService.modifyCerInfo(jsonObject);
    }

    @RequestMapping("/admin/uploadCerBackground")
    @ResponseBody
    public JSONObject uploadCerBackground(@RequestParam("file") MultipartFile img,
                                          @RequestParam("name") String imgName) {
        return certificateService.uploadCerBackground(img, imgName);
    }

    @RequestMapping("/admin/getCerBackgroundImg")
    @ResponseBody
    public Object getCerBackgroundImg() {
        return certificateService.getCerBackgroundImg();
    }

    @RequestMapping("/admin/getCerInfo")
    @ResponseBody
    public Object adminGetCerInfo(@RequestParam("trainingId") Integer trainingId) {
        return certificateService.adminGetCerInfo(trainingId);
    }

    @RequestMapping("/admin/getOperLogList")
    @ResponseBody
    public Object adminGetOperLogList(@RequestParam("id") Integer adminId,
                                     @RequestParam(value = "cerName", required = false) String cerName,
                                     @RequestParam(value = "userName", required = false) String userName,
                                     @RequestParam("pageSize") Integer pageSize,
                                     @RequestParam("currentPage") Integer currentPage,
                                     @RequestParam("type") String type) {
        return certificateService.adminGetOperLogList(adminId, cerName, userName, pageSize, currentPage, type);
    }

    @RequestMapping("/admin/passOperLog")
    @ResponseBody
    public JSONObject passOperLog(@RequestBody JSONObject jsonObject) {
        return certificateService.passOperLog(jsonObject);
    }

    @RequestMapping("/admin/rejectOperLog")
    @ResponseBody
    public JSONObject rejectOperLog(@RequestBody JSONObject jsonObject) {
        return certificateService.rejectOperLog(jsonObject);
    }


    /**
     * 会员操作
     **/
    @RequestMapping("/member/getStudentCerList")
    @ResponseBody
    public Object memberGetStudentCerList(@RequestParam("id") Integer memberId,
                                          @RequestParam(value = "cerName", required = false) String cerName,
                                          @RequestParam(value = "idNum", required = false) String idNum,
                                          @RequestParam(value = "trainingName", required = false) String trainingName,
                                          @RequestParam("pageSize") Integer pageSize,
                                          @RequestParam("currentPage") Integer currentPage) {
        return certificateService.memberGetStudentCerList(memberId, idNum, cerName, trainingName, pageSize, currentPage);
    }

    @RequestMapping("/member/getCerInfo")
    @ResponseBody
    public Object memberGetCerInfo(@RequestParam("id") Integer memberId,
                                   @RequestParam("idNum") String idNum,
                                   @RequestParam("cerId") Integer cerId) {
        return certificateService.memberGetCerInfo(memberId, idNum, cerId);
    }


    /**
     * 学员操作
     **/
    @RequestMapping("/student/getCerFile")
    @ResponseBody
    public ResponseEntity<byte[]> getCerFile(@RequestParam("id") Integer studentId,
                                             @RequestParam("cerId") Integer cerId) {
        return certificateService.getCerFile(studentId, cerId);
    }

    @RequestMapping("/student/getCerInfo")
    @ResponseBody
    public Object studentGetCerInfo(@RequestParam("id") Integer studentId,
                                    @RequestParam("cerId") Integer cerId) {
        return certificateService.studentGetCerInfo(studentId, cerId);
    }

    @RequestMapping("/student/getCertificateList")
    @ResponseBody
    public Object studentGetStudentCerList(@RequestParam("id") Integer studentId,
                                           @RequestParam(value = "cerName", required = false) String cerName,
                                           @RequestParam(value = "trainingName", required = false) String trainingName,
                                           @RequestParam("pageSize") Integer pageSize,
                                           @RequestParam("currentPage") Integer currentPage) {
        return certificateService.studentGetStudentCerList(studentId, cerName, trainingName, pageSize, currentPage);
    }


    @RequestMapping("/student/getOverdueCerList")
    @ResponseBody
    public Object studentGetOverdueCerList(@RequestParam("id") Integer studentId,
                                           @RequestParam(value = "cerName", required = false) String cerName,
                                           @RequestParam(value = "trainingName", required = false) String trainingName,
                                           @RequestParam("pageSize") Integer pageSize,
                                           @RequestParam("currentPage") Integer currentPage) {
        return certificateService.studentGetOverdueCerList(studentId, cerName, trainingName, pageSize, currentPage);
    }

    @RequestMapping("/student/modifyCer")
    @ResponseBody
    public JSONObject studentModifyCerInfo(@RequestBody JSONObject jsonObject) {
        return certificateService.studentModifyCerInfo(jsonObject);
    }

    @RequestMapping("/student/examineCertificate")
    @ResponseBody
    public JSONObject examineCertificate(@RequestBody JSONObject jsonObject) {
        return certificateService.examineCertificate(jsonObject);
    }

    @RequestMapping("/student/getOperList")
    @ResponseBody
    public Object studentGetOperList(@RequestParam("id") Integer studentId,
                                     @RequestParam(value = "cerName", required = false) String cerName,
                                     @RequestParam(value = "userName", required = false) String userName,
                                     @RequestParam("pageSize") Integer pageSize,
                                     @RequestParam("currentPage") Integer currentPage,
                                     @RequestParam("type") String type) {
        return certificateService.studentGetOperList(studentId, cerName, userName, pageSize, currentPage, type);
    }
}
