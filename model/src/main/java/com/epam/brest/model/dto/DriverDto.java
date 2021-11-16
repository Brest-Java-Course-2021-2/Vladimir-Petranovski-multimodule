package com.epam.brest.model.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class DriverDto {
    private Integer driverId;
    private String driverName;
    private Instant driverDateStartWork;
    private BigDecimal driverSalary;
    private Integer countOfCarsAssignedToDriver;

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

    public void setDriverId(final Integer driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(final String driverName) {
        this.driverName = driverName;
    }

    public Instant getDriverDateStartWork() {
        return driverDateStartWork;
    }

    public void setDriverDateStartWork(final Instant driverDateStartWork) {
        this.driverDateStartWork = driverDateStartWork;
    }

    public BigDecimal getDriverSalary() {
        return driverSalary;
    }

    public void setDriverSalary(final BigDecimal driverSalary) {
        this.driverSalary = driverSalary;
    }

    public Integer getCountOfCarsAssignedToDriver() {
        return countOfCarsAssignedToDriver;
    }

    public void setCountOfCarsAssignedToDriver(final Integer countOfCarsAssignedToDriver) {
        this.countOfCarsAssignedToDriver = countOfCarsAssignedToDriver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverDto driverDto = (DriverDto) o;
        return Objects.equals(driverId, driverDto.driverId) && Objects.equals(driverName, driverDto.driverName) && Objects.equals(driverDateStartWork, driverDto.driverDateStartWork) && Objects.equals(driverSalary, driverDto.driverSalary) && Objects.equals(countOfCarsAssignedToDriver, driverDto.countOfCarsAssignedToDriver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverId, driverName, driverDateStartWork, driverSalary, countOfCarsAssignedToDriver);
    }

    @Override
    public String toString() {
        return "DriverDto{" +
                "driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", driverDateStartWork=" + driverDateStartWork +
                ", driverSalary=" + driverSalary +
                ", countOfCarsAssignedToDriver=" + countOfCarsAssignedToDriver +
                '}';
    }
}
