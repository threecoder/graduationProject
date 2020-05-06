package com.lutayy.campbackend.pojo;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private Integer studentId;

    private String studentPassword;

    private String studentIdcard;

    private String studentPhone;

    private String studentName;

    private String studentSex;

    private String studentEmail;

    private String studentPosition;

    private String studentCountry;

    private String studentProvince;

    private String studentCity;

    private String studentArea;

    private String studentAddress;

    private String company;

    private Date enterTime;

    private Boolean hasOrg;

    private static final long serialVersionUID = 1L;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentIdcard() {
        return studentIdcard;
    }

    public void setStudentIdcard(String studentIdcard) {
        this.studentIdcard = studentIdcard;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPosition() {
        return studentPosition;
    }

    public void setStudentPosition(String studentPosition) {
        this.studentPosition = studentPosition;
    }

    public String getStudentCountry() {
        return studentCountry;
    }

    public void setStudentCountry(String studentCountry) {
        this.studentCountry = studentCountry;
    }

    public String getStudentProvince() {
        return studentProvince;
    }

    public void setStudentProvince(String studentProvince) {
        this.studentProvince = studentProvince;
    }

    public String getStudentCity() {
        return studentCity;
    }

    public void setStudentCity(String studentCity) {
        this.studentCity = studentCity;
    }

    public String getStudentArea() {
        return studentArea;
    }

    public void setStudentArea(String studentArea) {
        this.studentArea = studentArea;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public Boolean getHasOrg() {
        return hasOrg;
    }

    public void setHasOrg(Boolean hasOrg) {
        this.hasOrg = hasOrg;
    }
}