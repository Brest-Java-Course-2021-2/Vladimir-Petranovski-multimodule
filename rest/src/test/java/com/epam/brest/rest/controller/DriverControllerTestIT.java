package com.epam.brest.rest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:rest-test-app-context.xml"})
@Transactional
class DriverControllerTestIT {

    public static final String DRIVERS_ENDPOINT = "/drivers";

    @Autowired
    private DriverController driverController;



    @BeforeEach
    void setUp() {
    }

    @Test
    void findDriverById() {
    }
}