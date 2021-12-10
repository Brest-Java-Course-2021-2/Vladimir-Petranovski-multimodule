package com.epam.brest.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Driver {

    private Integer driverId;
    private String driverName;
    private Instant driverDateStartWork;
    private BigDecimal driverSalary;

    public Driver() {
    }

    public Driver(String driverName) {
        this.driverName = driverName;
    }

    public Driver(String driverName, Instant driverDateStartWork, BigDecimal driverSalary) {
        this.driverName = driverName;
        this.driverDateStartWork = driverDateStartWork;
        this.driverSalary = driverSalary;
    }

    public Driver(Integer driverId, String driverName, Instant driverDateStartWork, BigDecimal driverSalary) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverDateStartWork = driverDateStartWork;
        this.driverSalary = driverSalary;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public Driver setDriverId(Integer driverId) {
        this.driverId = driverId;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

    public Driver setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public Instant getDriverDateStartWork() {
        return driverDateStartWork;
    }

    public void setDriverDateStartWork(Instant driverDateStartWork) {
        this.driverDateStartWork = driverDateStartWork;
    }

    public BigDecimal getDriverSalary() {
        return driverSalary;
    }

    public void setDriverSalary(BigDecimal driverSalary) {
        this.driverSalary = driverSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(driverId, driver.driverId) && Objects.equals(driverName, driver.driverName) && Objects.equals(driverDateStartWork, driver.driverDateStartWork) && Objects.equals(driverSalary, driver.driverSalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverId, driverName, driverDateStartWork, driverSalary);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", driverDateStartWork=" + driverDateStartWork +
                ", driverSalary=" + driverSalary +
                '}';
    }
}
