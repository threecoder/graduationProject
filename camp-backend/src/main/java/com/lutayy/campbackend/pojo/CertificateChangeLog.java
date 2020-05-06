package com.lutayy.campbackend.pojo;

import java.io.Serializable;
import java.util.Date;

public class CertificateChangeLog implements Serializable {
    private Integer logId;

    private Integer certificateId;

    private String certificateName;

    private Integer cerStudentId;

    private String opDescription;

    private String opUserType;

    private Integer opUserId;

    private String opUserName;

    private Date opTime;

    private String isSuccess;

    private String newMemberName;

    private static final long serialVersionUID = 1L;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public Integer getCerStudentId() {
        return cerStudentId;
    }

    public void setCerStudentId(Integer cerStudentId) {
        this.cerStudentId = cerStudentId;
    }

    public String getOpDescription() {
        return opDescription;
    }

    public void setOpDescription(String opDescription) {
        this.opDescription = opDescription;
    }

    public String getOpUserType() {
        return opUserType;
    }

    public void setOpUserType(String opUserType) {
        this.opUserType = opUserType;
    }

    public Integer getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(Integer opUserId) {
        this.opUserId = opUserId;
    }

    public String getOpUserName() {
        return opUserName;
    }

    public void setOpUserName(String opUserName) {
        this.opUserName = opUserName;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getNewMemberName() {
        return newMemberName;
    }

    public void setNewMemberName(String newMemberName) {
        this.newMemberName = newMemberName;
    }
}