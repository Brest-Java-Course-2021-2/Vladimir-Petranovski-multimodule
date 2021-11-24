package com.epam.brest.controller;

import com.epam.brest.model.Driver;
import com.epam.brest.service_api.DriverService;
import com.epam.brest.service_api.dto.DriverDtoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.epam.brest.logger.ProjectLogger.log;

@Controller
@RequestMapping("/drivers")
public class DriverController {

    private final DriverDtoService driverDtoService;

    private final DriverService driverService;

    public DriverController(DriverDtoService driverDtoService, DriverService driverService) {
        this.driverDtoService = driverDtoService;
        this.driverService = driverService;
    }

    @GetMapping()
    public final String findAllDrivers(Model model) {
        model.addAttribute("driverList", driverDtoService.findAllDriverWithCountCars());
        return "drivers/drivers";
    }

    @GetMapping("/new-driver")
    public final String showFormAddingDriver(@ModelAttribute("driver") Driver driver) {
        log.info("Method showFormAddingDriver() with driver {} started of class {}", driver, getClass().getName());
        return "drivers/new-driver";
    }

    @PostMapping()
    public String saveDriver(@ModelAttribute("driver") Driver driver) {
        log.info("Method saveDriver() with driver {} started of class {}", driver, getClass().getName());
        driverService.saveDriver(driver);
        return "redirect:/drivers";
    }

    @GetMapping("/{id}/update-driver")
    public String showFormForUpdatingDriver(@PathVariable("id") Integer id, Model model) {
        log.info("Method showFormForUpdatingDriver() with id {} started of class {}", id, getClass().getName());
        model.addAttribute("driver", driverService.findDriverById(id));
        return "drivers/update-driver";
    }

    @PostMapping("/{id}")
    public String updateDriver(@ModelAttribute("driver") Driver driver, @PathVariable("id") Integer id) {
        log.info("Method updateDriver() with driver {} and id {} started of class {}", driver, id, getClass().getName());
        driverService.updateDriverById(id, driver);
        return "redirect:/drivers";
    }

    @GetMapping("/{id}/delete-driver")
    public String deleteDriver(@PathVariable("id") Integer id) {
        log.info("Method deleteDriver() with id {} started of class {}", id, getClass().getName());
        driverService.deleteDriverById(id);
        return "redirect:/drivers";
    }
}
