package com.epam.brest.model;

import java.util.Objects;

public class Car {

    /**
     * @serialField carId Integer.
     */

    private Integer carId;

    /**
     * @serialField carModel String.
     */

    private String carModel;

    /**
     * @serialField driverId Integer.
     */

    private Integer driverId;

    /**
     * Constructor without parameters.
     */

    public Car() {
    }

    /**
     * Constructor.
     *
     * @param carModel String.
     */

    public Car(final String carModel) {
        this.carModel = carModel;
    }

    /**
     * Constructor.
     *
     * @param carModel String.
     * @param driverId Integer.
     */

    public Car(final String carModel, final Integer driverId) {
        this.carModel = carModel;
        this.driverId = driverId;
    }

    /**
     * Getter for carId.
     *
     * @return carId.
     */

    public Integer getCarId() {
        return carId;
    }

    /**
     * Setter for carId.
     * @param carId Integer.
     */

    public Car setCarId(final Integer carId) {
        this.carId = carId;
        return this;
    }

    /**
     * Getter for carModel.
     *
     * @return carModel.
     */

    public String getCarModel() {
        return carModel;
    }

    /**
     * Setter for carModel.
     * @param carModel String.
     */

    public Car setCarModel(final String carModel) {
        this.carModel = carModel;
        return this;
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
     * @param driverId Integer.
     */

    public void setDriverId(final Integer driverId) {
        this.driverId = driverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) && Objects.equals(
                carModel, car.carModel) && Objects.equals(
                        driverId, car.driverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, carModel, driverId);
    }

    @Override
    public String toString() {
        return "Car{"
                + "carId=" + carId
                + ", carModel='" + carModel + '\''
                + ", driverId=" + driverId
                + '}';
    }
}
