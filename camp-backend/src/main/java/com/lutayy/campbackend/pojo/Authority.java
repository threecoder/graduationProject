package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class Authority implements Serializable {
    private Integer authorityId;

    private String authority;

    private static final long serialVersionUID = 1L;

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}