package com.epam.brest.model;

import java.util.Objects;

public class Car {

    private Integer carId;
    private String carModel;
    private Integer driverId;

    public Car() {
    }

    public Car(String carModel) {
        this.carModel = carModel;
    }

    public Car(String carModel, Integer driverId) {
        this.carModel = carModel;
        this.driverId = driverId;
    }

    public Integer getCarId() {
        return carId;
    }

    public Car setCarId(Integer carId) {
        this.carId = carId;
        return this;
    }

    public String getCarModel() {
        return carModel;
    }

    public Car setCarModel(String carModel) {
        this.carModel = carModel;
        return this;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) && Objects.equals(carModel, car.carModel) && Objects.equals(driverId, car.driverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, carModel, driverId);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", carModel='" + carModel + '\'' +
                ", driverId=" + driverId +
                '}';
    }
}
