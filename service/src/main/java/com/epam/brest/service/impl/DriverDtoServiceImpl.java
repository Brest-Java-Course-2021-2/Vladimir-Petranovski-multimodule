package com.epam.brest.service.impl;

import com.epam.brest.dao_api.DriverDtoDao;
import com.epam.brest.model.dto.DriverDto;
import com.epam.brest.service_api.DriverDtoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DriverDtoServiceImpl implements DriverDtoService {

    private final DriverDtoDao driverDtoDao;

    public DriverDtoServiceImpl(DriverDtoDao driverDtoDao) {
        this.driverDtoDao = driverDtoDao;
    }

    @Override
    public List<DriverDto> findAllWithCountCars() {
        return driverDtoDao.findWithCountCars();
    }
}
