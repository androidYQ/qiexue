package com.xgj.qiaxue.bean;

import java.io.Serializable;

/**
 * 班级信息
 * Created by qi.yang on 2018/5/7 0007.
 */

public class ClassesInfo implements Serializable{
    private String id;
    private String className;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
