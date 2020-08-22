package com.revengemission.demo.dynamicdatasource.persistence.h2.entity;

import java.io.Serializable;

public class MonthStat implements Serializable {
    private int id;
    private String value;
    private String valueDate;

    public int getId() {
        return id;
    }

    public MonthStat setId(int id) {
        this.id = id;
        return this;
    }

    public String getValue() {
        return value;
    }

    public MonthStat setValue(String value) {
        this.value = value;
        return this;
    }

    public String getValueDate() {
        return valueDate;
    }

    public MonthStat setValueDate(String valueDate) {
        this.valueDate = valueDate;
        return this;
    }
}
