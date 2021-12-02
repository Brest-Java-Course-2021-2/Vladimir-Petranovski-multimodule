package com.epam.brest.service.impl.dto;

import com.epam.brest.dao_api.DriverDtoDao;
import com.epam.brest.model.Driver;
import com.epam.brest.model.dto.DriverDto;
import com.epam.brest.service_api.dto.DriverDtoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class DriverDtoServiceImpl implements DriverDtoService {

    private final DriverDtoDao driverDtoDao;

    public DriverDtoServiceImpl(DriverDtoDao driverDtoDao) {
        this.driverDtoDao = driverDtoDao;
    }

    @Override
    public List<DriverDto> findAllDriverWithCountCars() {
        return driverDtoDao.findAllDriversWithCountCars();
    }

    @Override
    public List<DriverDto> chooseDriverOnDateRange(String fromDate, String toDate) {
        return driverDtoDao.chooseDriverOnDateRange(fromDate, toDate);
    }


}
