package com.epam.brest.dao.rowMappers;

import com.epam.brest.model.Driver;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class DriverRowMapper implements RowMapper<Driver> {
    @Override
    public Driver mapRow(ResultSet resultSet, int i) throws SQLException {
        Driver driver = new Driver();
        driver.setDriver_id(resultSet.getInt("driver_id"));
        driver.setName(resultSet.getString("name"));
        driver.setDateStartWork(resultSet.getObject("dateStartWork", Instant.class));
        driver.setSalary(resultSet.getBigDecimal("salary"));
        return null;
    }
}
