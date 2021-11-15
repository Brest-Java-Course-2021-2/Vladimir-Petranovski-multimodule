package com.epam.brest.dao.rowMappers;

import com.epam.brest.model.dto.DriverDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverDtoDaoJdbcRowMapper implements RowMapper<DriverDto> {
    @Override
    public DriverDto mapRow(ResultSet resultSet, int i) throws SQLException {
        DriverDto driver = new DriverDto();
        driver.setDriver_id(resultSet.getInt("driver_id"));
        driver.setName(resultSet.getString("name"));
        driver.setDateStartWork(resultSet.getTimestamp("dateStartWork").toInstant());
        driver.setSalary(resultSet.getBigDecimal("salary"));
        driver.setCountOfCarsAssignedToDriver(resultSet.getInt("countOfCarsAssignedToDriver"));
        return driver;
    }
}
