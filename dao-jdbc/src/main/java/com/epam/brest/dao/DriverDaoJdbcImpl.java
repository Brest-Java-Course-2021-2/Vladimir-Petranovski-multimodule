package com.epam.brest.dao;

import com.epam.brest.dao.rowMappers.DriverDaoJdbcRowMapper;
import com.epam.brest.dao_api.DriverDao;
import com.epam.brest.model.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.epam.brest.dao.Queries.*;

@Component
public class DriverDaoJdbcImpl implements DriverDao {

    public static final Logger LOG = LogManager.getLogger(DriverDaoJdbcImpl.class);

    /**
     * Field namedParameterJdbcTemplate.
     */

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Constructor.
     *
     * @param namedParameterJdbcTemplate namedParameterJdbcTemplate.
     */

    public DriverDaoJdbcImpl(
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Find all drivers.
     *
     * @return list of drivers.
     */

    @Override
    public List<Driver> findAllDrivers() {
        LOG.info("Method findAllDrivers() of class {} started",
                getClass().getName());
        return namedParameterJdbcTemplate.query(
                DRIVER_FIND_ALL, new DriverDaoJdbcRowMapper());
    }

    /**
     * Find driver by Id.
     *
     * @param id driver Id.
     * @return driver.
     */

    @Override
    public Driver findDriverById(final Integer id) {
        LOG.info("Method findDriverById(with id={})"
                + " of class {} started", id, getClass().getName());

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("driverId", id);
        return namedParameterJdbcTemplate.queryForObject(
                DRIVER_FIND_BY_ID, sqlParameterSource, new DriverDaoJdbcRowMapper());
    }

    /**
     * Persist new driver.
     *
     * @param driver driver.
     * @return persisted driver id.
     */

    @Override
    public Integer saveDriver(final Driver driver) {
        LOG.info("Method saveDriver(with driver {}) of class {} started",
                driver, getClass().getName());

        if (!isDriverUnique(driver.getDriverName())) {
            LOG.warn("Driver with the same name {} already exists.",
                    driver.getDriverName());
            throw new IllegalArgumentException(
                    "Driver with the same name already exists in DB.");
        }
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource()
                        .addValue("driverName",
                                driver.getDriverName())
                        .addValue("driverDateStartWork",
                                driver.getDriverDateStartWork())
                        .addValue("driverSalary", driver.getDriverSalary());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(DRIVER_SAVE, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    /**
     * Update department.
     *
     * @param id driver id.
     * @param driver driver.
     * @return number of updated records in the database.
     */

    @Override
    public Integer updateDriverById(final Integer id,
                                    final Driver driver) {
        LOG.info("Method updateDriverById(with id={},"
                + " with update to driver {}) of class {} started",
                id, driver, getClass().getName());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("driverId", id)
                .addValue("driverName",
                        driver.getDriverName())
                .addValue("driverDateStartWork",
                        driver.getDriverDateStartWork())
                .addValue("driverSalary", driver.getDriverSalary());
        return namedParameterJdbcTemplate.update(
                DRIVER_UPDATE_BY_ID, sqlParameterSource);
    }

    /**
     * Delete driver.
     *
     * @param id driver id.
     * @return number of updated records in the database.
     */

    @Override
    public Integer deleteDriverById(final Integer id) {
        LOG.info("Method deleteDriverById( with id={}) of class {} started",
                id, getClass().getName());

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("driverId", id);
        return namedParameterJdbcTemplate.update(DRIVER_DELETE_BY_ID,
                sqlParameterSource);
    }

    /**
     * Get count of records.
     *
     * @return count of records.
     */

    @Override
    public Integer count() {
        LOG.info("Method count() of class {} started",
                getClass().getName());

        return namedParameterJdbcTemplate.queryForObject(
                DRIVER_COUNT, new MapSqlParameterSource(), Integer.class);
    }

    /**
     * Get boolean value.
     *
     * @return boolean value.
     */

    private boolean isDriverUnique(final String driverName) {
        LOG.info("Method isDriverUnique() with driver's name: {}"
                + " of class {} started", driverName, getClass().getName());

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("driverName", driverName);
        return namedParameterJdbcTemplate.queryForObject(
                DRIVER_CHECK_UNIQUE_NAME, sqlParameterSource, Integer.class) == 0;
    }
}
