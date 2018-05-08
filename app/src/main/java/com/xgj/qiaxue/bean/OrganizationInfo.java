package com.xgj.qiaxue.bean;

import java.io.Serializable;

/**
 * Created by qi.yang on 2018/5/4 0004.
 */

public class OrganizationInfo implements Serializable {
    private String id;
    private String name;
    private boolean isSelected = false;

    public OrganizationInfo() {
    }

    public OrganizationInfo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
