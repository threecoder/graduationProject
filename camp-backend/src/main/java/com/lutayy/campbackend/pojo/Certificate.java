package com.lutayy.campbackend.pojo;

import java.io.Serializable;
import java.util.Date;

public class Certificate implements Serializable {
    private Integer certificateId;

    private String certificateName;

    private Integer studentId;

    private Integer trainingId;

    private Date createTime;

    private Date endTime;

    private String certificatePath;

    private Boolean inChangeLine;

    private Boolean inRecheckLine;

    private String studentName;

    private String memberName;

    private Boolean isInvalid;

    private static final long serialVersionUID = 1L;

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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Integer trainingId) {
        this.trainingId = trainingId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCertificatePath() {
        return certificatePath;
    }

    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }

    public Boolean getInChangeLine() {
        return inChangeLine;
    }

    public void setInChangeLine(Boolean inChangeLine) {
        this.inChangeLine = inChangeLine;
    }

    public Boolean getInRecheckLine() {
        return inRecheckLine;
    }

    public void setInRecheckLine(Boolean inRecheckLine) {
        this.inRecheckLine = inRecheckLine;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Boolean getIsInvalid() {
        return isInvalid;
    }

    public void setIsInvalid(Boolean isInvalid) {
        this.isInvalid = isInvalid;
    }
}