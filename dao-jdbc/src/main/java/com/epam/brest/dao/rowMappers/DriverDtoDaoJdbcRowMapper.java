package com.epam.brest.dao.rowMappers;

import com.epam.brest.model.dto.DriverDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverDtoDaoJdbcRowMapper implements RowMapper<DriverDto> {
    @Override
    public DriverDto mapRow(ResultSet resultSet, int i) throws SQLException {
        DriverDto driver = new DriverDto();
        driver.setDriverId(resultSet.getInt("driver_id"));
        driver.setDriverName(resultSet.getString("name"));
        driver.setDriverDateStartWork(resultSet.getTimestamp("dateStartWork").toInstant());
        driver.setDriverSalary(resultSet.getBigDecimal("salary"));
        driver.setCountOfCarsAssignedToDriver(resultSet.getInt("countOfCarsAssignedToDriver"));
        return driver;
    }
}
