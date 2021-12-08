package com.epam.brest.dao;

import com.epam.brest.dao.rowMappers.DriverDaoJdbcRowMapper;
import com.epam.brest.dao_api.DriverDao;
import com.epam.brest.model.Driver;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.epam.brest.dao.Queries.*;
import static com.epam.brest.logger.ProjectLogger.log;

@Component
public class DriverDaoJdbcImpl implements DriverDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DriverDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Driver> findAllDrivers() {
        log.info("Method findAllDrivers() of class {} started", getClass().getName());
        return namedParameterJdbcTemplate.query(DRIVER_FIND_ALL, new DriverDaoJdbcRowMapper());
    }

    @Override
    public Driver findDriverById(Integer id) {
        log.info("Method findDriverById(with id={}) of class {} started", id, getClass().getName());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("driverId", id);
        return namedParameterJdbcTemplate.queryForObject(DRIVER_FIND_BY_ID, sqlParameterSource, new DriverDaoJdbcRowMapper());
    }

    @Override
    public Integer saveDriver(Driver driver) {
        log.info("Method saveDriver(with driver {}) of class {} started", driver, getClass().getName());

        if (!isDriverUnique(driver.getDriverName())) {
            log.warn("Driver with the same name {} already exists.", driver.getDriverName());
            throw new IllegalArgumentException("Driver with the same name already exists in DB.");
        }
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource()
                        .addValue("driverName", driver.getDriverName())
                        .addValue("driverDateStartWork", driver.getDriverDateStartWork())
                        .addValue("driverSalary", driver.getDriverSalary());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(DRIVER_SAVE, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer updateDriverById(Integer id, Driver updateToDriver) {
        log.info("Method updateDriverById(with id={}, with update to driver {}) of class {} started", id, updateToDriver, getClass().getName());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("driverId", id)
                .addValue("driverName", updateToDriver.getDriverName())
                .addValue("driverDateStartWork", updateToDriver.getDriverDateStartWork())
                .addValue("driverSalary", updateToDriver.getDriverSalary());
        return namedParameterJdbcTemplate.update(DRIVER_UPDATE_BY_ID, sqlParameterSource);
    }

    /**
     * Delete driver by id.
     */

    @Override
    public Integer deleteDriverById(Integer id) {
        log.info("Method deleteDriverById( with id={}) of class {} started", id, getClass().getName());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("driverId", id);
        return namedParameterJdbcTemplate.update(DRIVER_DELETE_BY_ID, sqlParameterSource);
    }

    /**
     * Get count of records.
     *
     * @return count of records.
     */

    @Override
    public Integer count() {
        log.info("Method count() of class {} started", getClass().getName());

        return namedParameterJdbcTemplate.queryForObject(DRIVER_COUNT, new MapSqlParameterSource(), Integer.class);
    }

    /**
     * Get boolean value.
     *
     * @return boolean value.
     */

    private boolean isDriverUnique(String driverName) {
        log.info("Method isDriverUnique() with driver's name: {} of class {} started", driverName, getClass().getName());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("driverName", driverName);
        return namedParameterJdbcTemplate.queryForObject(DRIVER_CHECK_UNIQUE_NAME, sqlParameterSource, Integer.class) == 0;
    }
}
