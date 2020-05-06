package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class CertificateChangeOrderNewMemberName implements Serializable {
    private Integer id;

    private Integer changeOrderKeyId;

    private String newMemberName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChangeOrderKeyId() {
        return changeOrderKeyId;
    }

    public void setChangeOrderKeyId(Integer changeOrderKeyId) {
        this.changeOrderKeyId = changeOrderKeyId;
    }

    public String getNewMemberName() {
        return newMemberName;
    }

    public void setNewMemberName(String newMemberName) {
        this.newMemberName = newMemberName;
    }
}