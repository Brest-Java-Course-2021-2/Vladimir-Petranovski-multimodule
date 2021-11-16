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
        return null;
    }
}
