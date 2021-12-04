package com.epam.brest.service.exception;

import org.springframework.dao.EmptyResultDataAccessException;

public class DriverNotFoundException extends EmptyResultDataAccessException {
    public DriverNotFoundException(Integer id) {
        super("Driver not found with id: " + id, 1);
    }
}
