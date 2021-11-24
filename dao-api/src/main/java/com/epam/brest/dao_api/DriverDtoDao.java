package com.epam.brest.dao_api;

import com.epam.brest.model.dto.DriverDto;

import java.util.List;

public interface DriverDtoDao {

    /**
     * Get list of driver Dto.
     *
     * @return list of driver Dto.
     */

    List<DriverDto> findAllDriversWithCountCars();
}
