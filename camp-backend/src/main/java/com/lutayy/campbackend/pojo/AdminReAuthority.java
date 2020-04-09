package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class AdminReAuthority implements Serializable {
    private Integer id;

    private Integer adminId;

    private Integer authorityId;

    private String authorityName;

    private Boolean hasOrNot;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public Boolean getHasOrNot() {
        return hasOrNot;
    }

    public void setHasOrNot(Boolean hasOrNot) {
        this.hasOrNot = hasOrNot;
    }
}