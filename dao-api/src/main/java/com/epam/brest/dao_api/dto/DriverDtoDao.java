package com.epam.brest.dao_api.dto;

import com.epam.brest.model.dto.DriverDto;

import java.util.List;

public interface DriverDtoDao {

    /**
     * Find all cars Dto.
     *
     * @return list of cars Dto.
     */

    List<DriverDto> findAllDriversWithCountCars();

    /**
     * Get  list of driver from date to date Dto.
     *
     * @return list of driver from date to date Dto.
     */

    List<DriverDto> chooseDriverOnDateRange(final String fromDate,
                                            final String toDate);
}
