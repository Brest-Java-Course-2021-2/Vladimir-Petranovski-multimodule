package com.epam.brest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Driver implements Serializable {

    private Integer driver_id;
    private String name;
    private Date dateStartWork;
    private BigDecimal salary;

    public Integer getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Integer driver_id) {
        this.driver_id = driver_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateStartWork() {
        return dateStartWork;
    }

    public void setDateStartWork(Date dateStartWork) {
        this.dateStartWork = dateStartWork;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
