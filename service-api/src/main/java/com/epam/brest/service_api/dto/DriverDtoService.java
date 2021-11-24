package com.epam.brest.service_api.dto;

import com.epam.brest.model.dto.DriverDto;

import java.util.List;

public interface DriverDtoService {

    /**
     * Get list of driver Dto.
     *
     * @return list of driver Dto.
     */

    List<DriverDto> findAllDriverWithCountCars();
}
