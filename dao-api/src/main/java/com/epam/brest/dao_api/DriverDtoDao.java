package com.epam.brest.dao_api;

import com.epam.brest.model.dto.DriverDto;

import java.util.List;

public interface DriverDtoDao {

    List<DriverDto> findAllDriverWithCountCars();
}
