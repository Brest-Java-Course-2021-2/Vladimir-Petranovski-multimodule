package com.epam.brest.model.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class DriverDto {

    /**
     * @serialField driverId Integer.
     */

    private Integer driverId;

    /**
     * @serialField driverName String.
     */

    private String driverName;

    /**
     * @serialField driverDateStartWork Instant.
     */

    private Instant driverDateStartWork;

    /**
     * @serialField driverSalary BigDecimal.
     */

    private BigDecimal driverSalary;

    /**
     * @serialField countOfCarsAssignedToDriver Integer.
     */

    private Integer countOfCarsAssignedToDriver;

    /**
     * @serialField fromDateChoose String.
     */

    private String fromDateChoose;

    /**
     * @serialField toDateChoose String.
     */
    private String toDateChoose;

    /**
     * Constructor without parameters.
     */

    public DriverDto() {
    }

    /**
     * Constructor.
     *
     * @param driverName String.
     * @param driverDateStartWork Instant.
     * @param driverSalary BigDecimal.
     * @param countOfCarsAssignedToDriver Integer.
     */

    public DriverDto(final String driverName, final Instant driverDateStartWork,
                     final BigDecimal driverSalary,
                     final Integer countOfCarsAssignedToDriver) {
        this.driverName = driverName;
        this.driverDateStartWork = driverDateStartWork;
        this.driverSalary = driverSalary;
        this.countOfCarsAssignedToDriver = countOfCarsAssignedToDriver;
    }

    /**
     * Constructor.
     *
     * @param driverId Integer.
     * @param driverName String.
     * @param driverDateStartWork Instant.
     * @param driverSalary BigDecimal.
     * @param countOfCarsAssignedToDriver Integer.
     */

    public DriverDto(final Integer driverId, final String driverName,
                     final Instant driverDateStartWork,
                     final BigDecimal driverSalary,
                     final Integer countOfCarsAssignedToDriver) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverDateStartWork = driverDateStartWork;
        this.driverSalary = driverSalary;
        this.countOfCarsAssignedToDriver = countOfCarsAssignedToDriver;
    }

    /**
     * Constructor.
     *
     * @param driverId Integer.
     * @param driverName String.
     * @param driverDateStartWork Instant.
     * @param driverSalary BigDecimal.
     * @param countOfCarsAssignedToDriver Integer.
     * @param fromDateChoose String.
     * @param toDateChoose String.
     */

    public DriverDto(final Integer driverId, final String driverName,
                     final Instant driverDateStartWork,
                     final BigDecimal driverSalary,
                     final Integer countOfCarsAssignedToDriver,
                     final String fromDateChoose, final String toDateChoose) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverDateStartWork = driverDateStartWork;
        this.driverSalary = driverSalary;
        this.countOfCarsAssignedToDriver = countOfCarsAssignedToDriver;
        this.fromDateChoose = fromDateChoose;
        this.toDateChoose = toDateChoose;
    }

    /**
     * Constructor.
     *
     * @param fromDateChoose String.
     * @param toDateChoose String.
     */

    public DriverDto(final String fromDateChoose,
                     final String toDateChoose) {
        this.fromDateChoose = fromDateChoose;
        this.toDateChoose = toDateChoose;
    }

    /**
     * Getter for driverId.
     *
     * @return driverId.
     */

    public Integer getDriverId() {
        return driverId;
    }

    /**
     * Setter for driverId.
     *
     * @param driverId Integer.
     */

    public void setDriverId(final Integer driverId) {
        this.driverId = driverId;
    }

    /**
     * Getter for driverName.
     *
     * @return driverName.
     */

    public String getDriverName() {
        return driverName;
    }

    /**
     * Setter for driverName.
     *
     * @param driverName String.
     */

    public void setDriverName(final String driverName) {
        this.driverName = driverName;
    }

    /**
     * Getter for driverDateStartWork.
     *
     * @return driverDateStartWork.
     */

    public Instant getDriverDateStartWork() {
        return driverDateStartWork;
    }

    /**
     * Setter for driverDateStartWork.
     *
     * @param driverDateStartWork Instant.
     */

    public void setDriverDateStartWork(final Instant driverDateStartWork) {
        this.driverDateStartWork = driverDateStartWork;
    }

    /**
     * Getter for driverSalary.
     *
     * @return driverSalary.
     */

    public BigDecimal getDriverSalary() {
        return driverSalary;
    }

    /**
     * Setter for driverSalary.
     *
     * @param driverSalary BigDecimal.
     */

    public void setDriverSalary(final BigDecimal driverSalary) {
        this.driverSalary = driverSalary;
    }

    /**
     * Getter for countOfCarsAssignedToDriver.
     *
     * @return countOfCarsAssignedToDriver.
     */

    public Integer getCountOfCarsAssignedToDriver() {
        return countOfCarsAssignedToDriver;
    }

    /**
     * Setter for countOfCarsAssignedToDriver.
     *
     * @param countOfCarsAssignedToDriver Integer.
     */

    public void setCountOfCarsAssignedToDriver(
            final Integer countOfCarsAssignedToDriver) {
        this.countOfCarsAssignedToDriver = countOfCarsAssignedToDriver;
    }

    /**
     * Getter for getFromDateChoose.
     *
     * @return getFromDateChoose.
     */

    public String getFromDateChoose() {
        return fromDateChoose;
    }

    /**
     * Setter for fromDateChoose.
     *
     * @param fromDateChoose String.
     */

    public void setFromDateChoose(final String fromDateChoose) {
        this.fromDateChoose = fromDateChoose;
    }

    /**
     * Getter for getToDateChoose.
     *
     * @return getToDateChoose.
     */

    public String getToDateChoose() {
        return toDateChoose;
    }

    /**
     * Setter for toDateChoose.
     *
     * @param toDateChoose String.
     */

    public void setToDateChoose(final String toDateChoose) {
        this.toDateChoose = toDateChoose;
    }

    @Override
    public String toString() {
        return "DriverDto{"
                + "driverId=" + driverId
                + ", driverName='" + driverName + '\''
                + ", driverDateStartWork=" + driverDateStartWork
                + ", driverSalary=" + driverSalary
                + ", countOfCarsAssignedToDriver=" + countOfCarsAssignedToDriver
                + ", fromDateChoose=" + fromDateChoose
                + ", toDateChoose=" + toDateChoose
                + '}';
    }
}
