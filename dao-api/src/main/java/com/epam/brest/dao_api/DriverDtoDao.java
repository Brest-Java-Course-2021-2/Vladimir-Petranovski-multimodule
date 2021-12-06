package com.epam.brest.dao_api;

import com.epam.brest.model.dto.DriverDto;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public interface DriverDtoDao {

    /**
     * Get list of driver Dto.
     *
     * @return list of driver Dto.
     */

    List<DriverDto> findAllDriversWithCountCars();

    /**
     * Get  list of driver from date to date Dto.
     *
     * @return list of driver from date to date Dto.
     */

    List<DriverDto> chooseDriverOnDateRange(String fromDate, String toDate);
}
