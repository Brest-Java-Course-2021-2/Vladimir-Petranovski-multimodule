package com.epam.brest.service.impl;

import com.epam.brest.dao_api.DriverDao;
import com.epam.brest.model.Driver;
import com.epam.brest.service_api.DriverService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private final DriverDao driverDao;

    public DriverServiceImpl(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    @Override
    public List<Driver> findAllDrivers() {
        return driverDao.findAllDrivers();
    }

    @Override
    public Driver findDriverById(Integer id) {
        return driverDao.findDriverById(id);
    }

    @Override
    public Integer saveDriver(Driver driver) {
        return driverDao.saveDriver(driver);
    }

    @Override
    public Integer updateDriverById(Integer id, Driver driver) {
        return driverDao.updateDriverById(id, driver);
    }

    @Override
    public Integer deleteDriverById(Integer id) {
        return driverDao.deleteDriverById(id);
    }

    @Override
    public Integer count() {
        return driverDao.count();
    }
}
