package com.epam.brest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Driver implements Serializable {

    private Integer driver_id;
    private String name;
    private Instant dateStartWork;
    private BigDecimal salary;

    public Driver() {
    }

    public Driver(String name, Instant dateStartWork, BigDecimal salary) {
        this.name = name;
        this.dateStartWork = dateStartWork;
        this.salary = salary;
    }

    public Driver(Integer driver_id, String name, Instant dateStartWork, BigDecimal salary) {
        this.driver_id = driver_id;
        this.name = name;
        this.dateStartWork = dateStartWork;
        this.salary = salary;
    }

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

    public Instant getDateStartWork() {
        return dateStartWork;
    }

    public void setDateStartWork(Instant dateStartWork) {
        this.dateStartWork = dateStartWork;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(driver_id, driver.driver_id) && Objects.equals(name, driver.name) && Objects.equals(dateStartWork, driver.dateStartWork) && Objects.equals(salary, driver.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driver_id, name, dateStartWork, salary);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driver_id=" + driver_id +
                ", name='" + name + '\'' +
                ", dateStartWork=" + dateStartWork +
                ", salary=" + salary +
                '}';
    }
}
