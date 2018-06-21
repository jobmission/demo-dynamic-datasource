package com.revengemission.demo.dynamicdatasource.persistence.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DayStat implements Serializable {
    private Long id;

    private Date valueTime;

    private BigDecimal value;

    private int version;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getValueTime() {
        return valueTime;
    }

    public void setValueTime(Date valueTime) {
        this.valueTime = valueTime;
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