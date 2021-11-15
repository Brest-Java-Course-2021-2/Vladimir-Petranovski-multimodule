package com.epam.brest.model.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class DriverDto {
    private Integer driver_id;
    private String name;
    private Instant dateStartWork;
    private BigDecimal salary;
    private Integer countOfCarsAssignedToDriver;

    public DriverDto() {
    }

    public DriverDto(String name, Instant dateStartWork, BigDecimal salary, Integer countOfCarsAssignedToDriver) {
        this.name = name;
        this.dateStartWork = dateStartWork;
        this.salary = salary;
        this.countOfCarsAssignedToDriver = countOfCarsAssignedToDriver;
    }

    public DriverDto(Integer driver_id, String name, Instant dateStartWork, BigDecimal salary, Integer countOfCarsAssignedToDriver) {
        this.driver_id = driver_id;
        this.name = name;
        this.dateStartWork = dateStartWork;
        this.salary = salary;
        this.countOfCarsAssignedToDriver = countOfCarsAssignedToDriver;
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

    public Integer getCountOfCarsAssignedToDriver() {
        return countOfCarsAssignedToDriver;
    }

    public void setCountOfCarsAssignedToDriver(Integer countOfCarsAssignedToDriver) {
        this.countOfCarsAssignedToDriver = countOfCarsAssignedToDriver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverDto driverDto = (DriverDto) o;
        return Objects.equals(driver_id, driverDto.driver_id) && Objects.equals(name, driverDto.name) && Objects.equals(dateStartWork, driverDto.dateStartWork) && Objects.equals(salary, driverDto.salary) && Objects.equals(countOfCarsAssignedToDriver, driverDto.countOfCarsAssignedToDriver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driver_id, name, dateStartWork, salary, countOfCarsAssignedToDriver);
    }

    @Override
    public String toString() {
        return "DriverDto{" +
                "driver_id=" + driver_id +
                ", name='" + name + '\'' +
                ", dateStartWork=" + dateStartWork +
                ", salary=" + salary +
                ", countOfCarsAssignedToDriver=" + countOfCarsAssignedToDriver +
                '}';
    }
}
