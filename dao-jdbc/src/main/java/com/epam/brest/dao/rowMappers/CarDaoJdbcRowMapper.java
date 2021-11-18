package com.epam.brest.dao.rowMappers;

import com.epam.brest.model.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDaoJdbcRowMapper implements RowMapper<Car> {
    @Override
    public Car mapRow(ResultSet resultSet, int i) throws SQLException {
        Car car = new Car();
        car.setCarId(resultSet.getInt("car_id"));
        car.setCarModel(resultSet.getString("model"));
        car.setDriverId(resultSet.getInt("driver_id"));
        return car;
    }
}
