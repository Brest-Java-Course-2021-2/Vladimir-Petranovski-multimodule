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
}
