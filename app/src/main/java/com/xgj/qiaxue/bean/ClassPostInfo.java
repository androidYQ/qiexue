package com.xgj.qiaxue.bean;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * 职务
 * Created by qi.yang on 2018/5/4 0004.
 */

public class ClassPostInfo implements Serializable{
    private int type;
    private String name;
    private boolean isSelected = false;
    private String value;

    public String getValue() {

        return !TextUtils.isEmpty(value) ? value : name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ClassPostInfo() {
    }

    public ClassPostInfo(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
