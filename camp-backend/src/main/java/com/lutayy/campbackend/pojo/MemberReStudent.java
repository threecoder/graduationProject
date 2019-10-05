package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class MemberReStudent implements Serializable {
    private String memberId;

    private Integer studentId;

    private static final long serialVersionUID = 1L;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}