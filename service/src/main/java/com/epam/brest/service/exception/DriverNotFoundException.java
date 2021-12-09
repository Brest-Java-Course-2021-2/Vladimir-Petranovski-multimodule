package com.epam.brest.service.exception;

import org.springframework.dao.EmptyResultDataAccessException;

public class DriverNotFoundException extends EmptyResultDataAccessException {

    /**
     * Constructor.
     *
     * @param id driver.
     */

    public DriverNotFoundException(final Integer id) {
        super("Driver not found with id: " + id, 1);
    }
}
