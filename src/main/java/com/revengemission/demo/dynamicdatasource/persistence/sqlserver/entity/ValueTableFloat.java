package com.revengemission.demo.dynamicdatasource.persistence.sqlserver.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by revenge mission on 18-6-17.
 */
public class ValueTableFloat implements Serializable {
    private int id;
    private String value;
    private Date valueTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getValueTime() {
        return valueTime;
    }

    public void setValueTime(Date valueTime) {
        this.valueTime = valueTime;
    }
}
