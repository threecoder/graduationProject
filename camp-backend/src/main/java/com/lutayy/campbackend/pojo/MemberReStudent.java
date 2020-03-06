package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class MemberReStudent implements Serializable {
    private Integer studentId;

    private Integer memberKeyId;

    private static final long serialVersionUID = 1L;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getMemberKeyId() {
        return memberKeyId;
    }

    public void setMemberKeyId(Integer memberKeyId) {
        this.memberKeyId = memberKeyId;
    }
}