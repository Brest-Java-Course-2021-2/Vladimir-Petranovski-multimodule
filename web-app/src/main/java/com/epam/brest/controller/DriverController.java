package com.epam.brest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/drivers")
public class DriverController {

    @GetMapping
    public String findAllDrivers() {
        return "drivers/drivers";
    }
}
