package com.epam.brest.service.impl;

import com.epam.brest.dao_api.DriverDao;
import com.epam.brest.model.Driver;
import com.epam.brest.service.exception.DriverNotFoundException;
import com.epam.brest.service_api.DriverService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.epam.brest.logger.ProjectLogger.log;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverDao driverDao;

    public DriverServiceImpl(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Driver> findAllDrivers() {
        log.info("Method findAllDrivers() started of class {}", getClass().getName());

        return driverDao.findAllDrivers();
    }

    @Override
    @Transactional(readOnly = true)
    public Driver findDriverById(Integer id) {
        log.info("Method findDriverById() with id: {} started of class {}", id, getClass().getName());
        try {
            return this.driverDao.findDriverById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new DriverNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public Integer saveDriver(Driver driver) {
        log.info("Method saveDriver() with driver: {} started of class {}", driver, getClass().getName());

        return driverDao.saveDriver(driver);
    }

    @Override
    @Transactional
    public Integer updateDriverById(Integer id, Driver driver) {
        log.info("Method updateDriverById() with id {} and with driver: {} started of class {}", id, driver, getClass().getName());

        return driverDao.updateDriverById(id, driver);
    }

    @Override
    @Transactional
    public Integer deleteDriverById(Integer id) {
        log.info("Method deleteDriverById() with id: {} started of class {}", id, getClass().getName());

        return driverDao.deleteDriverById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer count() {
        log.info("Method count() started of class {}", getClass().getName());

        return driverDao.count();
    }
}
