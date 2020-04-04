package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class Admin implements Serializable {
    private Integer adminId;

    private String adminAccount;

    private String adminPassword;

    private Boolean permissionValue;

    private Boolean isLocked;

    private String adminName;

    private static final long serialVersionUID = 1L;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Boolean getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(Boolean permissionValue) {
        this.permissionValue = permissionValue;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}