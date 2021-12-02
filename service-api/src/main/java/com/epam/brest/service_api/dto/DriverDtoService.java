package com.epam.brest.service_api.dto;

import com.epam.brest.model.Driver;
import com.epam.brest.model.dto.DriverDto;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public interface DriverDtoService {

    /**
     * Get list of driver Dto.
     *
     * @return list of driver Dto.
     */

    List<DriverDto> findAllDriverWithCountCars();

    /**
     * Get  list of driver from date to date Dto.
     *
     * @return list of driver from date to date Dto.
     */

    List<DriverDto> chooseDriverOnDateRange(String fromDate, String toDate);


}
