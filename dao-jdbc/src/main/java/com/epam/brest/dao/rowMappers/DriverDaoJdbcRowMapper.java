package com.epam.brest.dao.rowMappers;

import com.epam.brest.model.Driver;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverDaoJdbcRowMapper implements RowMapper<Driver> {
    @Override
    public Driver mapRow(ResultSet resultSet, int i) throws SQLException {
        Driver driver = new Driver();
        driver.setDriverId(resultSet.getInt("driver_id"));
        driver.setDriverName(resultSet.getString("name"));
        driver.setDriverDateStartWork(resultSet.getTimestamp("dateStartWork").toInstant());
        driver.setDriverSalary(resultSet.getBigDecimal("salary"));
        return driver;
    }
}
