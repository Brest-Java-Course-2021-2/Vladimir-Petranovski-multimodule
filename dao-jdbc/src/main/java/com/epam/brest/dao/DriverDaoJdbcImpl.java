package com.epam.brest.dao;

import com.epam.brest.dao.rowMappers.DriverDaoJdbcRowMapper;
import com.epam.brest.dao_api.DriverDao;
import com.epam.brest.model.Driver;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import static com.epam.brest.dao.Queries.*;
import static com.epam.brest.logger.ProjectLogger.*;

public class DriverDaoJdbcImpl implements DriverDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DriverDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Driver> findAllDrivers() {
        log.info("Method findAll() of class {} started", getClass().getName());
        return namedParameterJdbcTemplate.query(DRIVER_FIND_ALL, new DriverDaoJdbcRowMapper());
    }

    @Override
    public Driver findDriverById(Integer id) {
        log.info("Method findById(with id={}) of class {} started", id, getClass().getName());
        Map<String, Integer> params = new HashMap<>();
        params.put("driverId", id);
        return namedParameterJdbcTemplate.queryForObject(DRIVER_FIND_BY_ID, params, new DriverDaoJdbcRowMapper());
    }

    @Override
    public void saveDriver(Driver driver) {
        log.info("Method save(with driver {}) of class {} started", driver, getClass().getName());
        if (!findAllNameDrivers().contains(driver.getDriverName().toUpperCase())) {
            Map<String, Object> params = new HashMap<>();
            params.put("driverName", driver.getDriverName());
            params.put("driverDateStartWork", driver.getDriverDateStartWork());
            params.put("driverSalary", driver.getDriverSalary());
            namedParameterJdbcTemplate.execute(DRIVER_SAVE, params, new PreparedStatementCallback<Integer>() {
                @Override
                public Integer doInPreparedStatement(PreparedStatement ps)
                        throws SQLException, DataAccessException {
                    return ps.executeUpdate();
                }
            });
        } else {
            throw new IllegalArgumentException("Name must be unique");
        }
    }

    @Override
    public void updateDriverById(Integer id, Driver updateToDriver) {
        log.info("Method update(with id={}, with update to driver {}) of class {} started",id, updateToDriver, getClass().getName());
        Map<String, Object> params = new HashMap<>();
        params.put("driverId", id);
        params.put("driverName", updateToDriver.getDriverName());
        params.put("driverDateStartWork", updateToDriver.getDriverDateStartWork());
        params.put("driverSalary", updateToDriver.getDriverSalary());
        namedParameterJdbcTemplate.update(DRIVER_UPDATE_BY_ID, params);
    }

    @Override
    public void deleteDriverById(Integer id) {
        log.info("Method delete( with id={}) of class {} started", id, getClass().getName());
        Map<String, Integer> param = new HashMap<>();
        param.put("driverId", id);
        namedParameterJdbcTemplate.update(DRIVER_DELETE_BY_ID, param);
    }

    @Override
    public Integer count() {
        return namedParameterJdbcTemplate.queryForObject(DRIVER_COUNT, new MapSqlParameterSource(), Integer.class);
    }

    private List<String> findAllNameDrivers() {
        log.info("Method findAllNameDrivers() of class {} started", getClass().getName());
        return namedParameterJdbcTemplate.queryForList(DRIVER_FIND_ALL_NAME, Collections.emptyMap(), String.class);
    }
}
