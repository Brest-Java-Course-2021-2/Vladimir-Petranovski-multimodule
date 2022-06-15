package com.epam.brest.service.impl;

import com.epam.brest.dao_api.DriverDao;
import com.epam.brest.model.Driver;
import com.epam.brest.service.exception.DriverNotFoundException;
import com.epam.brest.service_api.DriverService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    public static final Logger LOG = LogManager.getLogger(
            DriverServiceImpl.class);

    /**
     * Field driverDao.
     */

    private final DriverDao driverDao;

    /**
     * Constructor.
     *
     * @param driverDao driverDao.
     */

    public DriverServiceImpl(final DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    /**
     * Find all drivers.
     *
     * @return list of drivers.
     */

    @Override
    @Transactional(readOnly = true)
    public List<Driver> findAllDrivers() {
        LOG.info("Method findAllDrivers() started of class {}",
                getClass().getName());

        return driverDao.findAllDrivers();
    }

    /**
     * Find driver by id.
     *
     * @param id driver Id.
     * @return driver.
     */

    @Override
    @Transactional(readOnly = true)
    public Driver findDriverById(final Integer id) {
        LOG.info("Method findDriverById()"
                + " with id: {} started of class {}",
                id, getClass().getName());
        try {
            return this.driverDao.findDriverById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new DriverNotFoundException(id);
        }
    }

    /**
     * Persist new driver.
     *
     * @param driver driver.
     * @return persisted driver id.
     */

    @Override
    @Transactional
    public Integer saveDriver(final Driver driver) {
        LOG.info("Method saveDriver()"
                + " with driver: {} started of class {}",
                driver, getClass().getName());

        return driverDao.saveDriver(driver);
    }

    /**
     * Update department.
     *
     * @param id driver id.
     * @param driver driver.
     * @return number of updated records in the database.
     */

    @Override
    @Transactional
    public Integer updateDriverById(final Integer id,
                                    final Driver driver) {
        LOG.info("Method updateDriverById()"
                + " with id {} and with driver: {} started of class {}",
                id, driver, getClass().getName());

        return driverDao.updateDriverById(id, driver);
    }

    /**
     * Delete driver.
     *
     * @param id driver id.
     * @return number of updated records in the database.
     */

    @Override
    @Transactional
    public Integer deleteDriverById(final Integer id) {
        LOG.info("Method deleteDriverById()"
                + " with id: {} started of class {}",
                id, getClass().getName());

        return driverDao.deleteDriverById(id);
    }

    /**
     * Count drivers.
     *
     * @return quantity of the drivers.
     */

    @Override
    @Transactional(readOnly = true)
    public Integer count() {
        LOG.info("Method count() started of class {}",
                getClass().getName());

        return driverDao.count();
    }
}
