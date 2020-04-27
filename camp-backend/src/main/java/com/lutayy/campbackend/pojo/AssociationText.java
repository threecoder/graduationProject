package com.lutayy.campbackend.pojo;

import java.io.Serializable;

public class AssociationText implements Serializable {
    private Integer id;

    private String itemName;

    private String itemContext;

    private Boolean isInvalid;

    private String description;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemContext() {
        return itemContext;
    }

    public void setItemContext(String itemContext) {
        this.itemContext = itemContext;
    }

    public Boolean getIsInvalid() {
        return isInvalid;
    }

    public void setIsInvalid(Boolean isInvalid) {
        this.isInvalid = isInvalid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}