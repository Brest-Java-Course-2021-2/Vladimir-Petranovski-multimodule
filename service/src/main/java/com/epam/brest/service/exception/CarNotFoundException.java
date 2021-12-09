package com.epam.brest.service.exception;

import org.springframework.dao.EmptyResultDataAccessException;

public class CarNotFoundException extends EmptyResultDataAccessException {

    /**
     * Constructor.
     *
     * @param id car.
     */

    public CarNotFoundException(final Integer id) {
        super("Car not found with id: " + id, 1);
    }
}
