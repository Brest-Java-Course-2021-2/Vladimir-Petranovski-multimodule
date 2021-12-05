package com.epam.brest.service.exception;

import org.springframework.dao.EmptyResultDataAccessException;

public class CarNotFoundException extends EmptyResultDataAccessException {
    public CarNotFoundException(Integer id) {
        super("Driver not found with id: " + id, 1);
    }
}
