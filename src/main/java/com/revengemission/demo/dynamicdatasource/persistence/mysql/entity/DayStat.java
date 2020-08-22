package com.revengemission.demo.dynamicdatasource.persistence.mysql.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class DayStat implements Serializable {
    private Long id;

    private String valueTime;

    private BigDecimal value;

    private int version;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValueTime() {
        return valueTime;
    }

    public void setValueTime(String valueTime) {
        this.valueTime = valueTime == null ? null : valueTime.trim();
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}