package com.revengemission.demo.dynamicdatasource.persistence.sqlserver.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by revenge mission on 18-6-17.
 */
public class TagIndex implements Serializable {
    private int id;
    private String tagName;
    private int tagType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getTagType() {
        return tagType;
    }

    public void setTagType(int tagType) {
        this.tagType = tagType;
    }
}
