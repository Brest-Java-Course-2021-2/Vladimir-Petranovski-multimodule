package com.epam.brest.model.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

public class DriverDto {

    private Integer driverId;
    private String driverName;
    private Instant driverDateStartWork;
    private BigDecimal driverSalary;
    private Integer countOfCarsAssignedToDriver;
    private String fromDateChoose;
    private String toDateChoose;

    public DriverDto() {
    }

    public DriverDto(String driverName, Instant driverDateStartWork, BigDecimal driverSalary, Integer countOfCarsAssignedToDriver) {
        this.driverName = driverName;
        this.driverDateStartWork = driverDateStartWork;
        this.driverSalary = driverSalary;
        this.countOfCarsAssignedToDriver = countOfCarsAssignedToDriver;
    }

    public DriverDto(Integer driverId, String driverName, Instant driverDateStartWork, BigDecimal driverSalary, Integer countOfCarsAssignedToDriver) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverDateStartWork = driverDateStartWork;
        this.driverSalary = driverSalary;
        this.countOfCarsAssignedToDriver = countOfCarsAssignedToDriver;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
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

    public Integer getCountOfCarsAssignedToDriver() {
        return countOfCarsAssignedToDriver;
    }

    public void setCountOfCarsAssignedToDriver(Integer countOfCarsAssignedToDriver) {
        this.countOfCarsAssignedToDriver = countOfCarsAssignedToDriver;
    }

    public String getFromDateChoose() {
        return fromDateChoose;
    }

    public void setFromDateChoose(String fromDateChoose) {
        this.fromDateChoose = fromDateChoose;
    }

    public String getToDateChoose() {
        return toDateChoose;
    }

    public void setToDateChoose(String toDateChoose) {
        this.toDateChoose = toDateChoose;
    }

    @Override
    public String toString() {
        return "DriverDto{" +
                "driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", driverDateStartWork=" + driverDateStartWork +
                ", driverSalary=" + driverSalary +
                ", countOfCarsAssignedToDriver=" + countOfCarsAssignedToDriver +
                ", fromDateChoose=" + fromDateChoose +
                ", toDateChoose=" + toDateChoose +
                '}';
    }
}
