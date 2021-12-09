package com.epam.brest.service.impl.dto;

import com.epam.brest.dao_api.dto.DriverDtoDao;
import com.epam.brest.model.dto.DriverDto;
import com.epam.brest.service_api.dto.DriverDtoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverDtoServiceImpl implements DriverDtoService {

    /**
     * Field driverDtoDao.
     */

    private final DriverDtoDao driverDtoDao;

    /**
     * Constructor.
     *
     * @param driverDtoDao driverDtoDao.
     */

    public DriverDtoServiceImpl(final DriverDtoDao driverDtoDao) {
        this.driverDtoDao = driverDtoDao;
    }

    /**
     * Find driver's list Dto.
     *
     * @return driver's list Dto.
     */

    @Override
    @Transactional(readOnly = true)
    public List<DriverDto> findAllDriverWithCountCars() {
        return driverDtoDao.findAllDriversWithCountCars();
    }
}
