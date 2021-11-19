package com.epam.brest.controller;

import com.epam.brest.service_api.dto.DriverDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/drivers")
public class DriverController {

    private final DriverDtoService driverDtoService;

    public DriverController(DriverDtoService driverDtoService) {
        this.driverDtoService = driverDtoService;
    }

    @GetMapping
    public String findAllDrivers(Model model) {
        model.addAttribute("driverList", driverDtoService.findAllDriverWithCountCars());
        return "drivers/drivers";
    }
}
