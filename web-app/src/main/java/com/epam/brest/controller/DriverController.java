package com.epam.brest.controller;

import com.epam.brest.controller.validator.DriverValidator;
import com.epam.brest.model.Driver;
import com.epam.brest.service_api.DriverService;
import com.epam.brest.service_api.dto.DriverDtoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.epam.brest.logger.ProjectLogger.log;

@Controller
@RequestMapping("/drivers")
public class DriverController {

    private final DriverDtoService driverDtoService;

    private final DriverService driverService;

    private final DriverValidator driverValidator;

    public DriverController(DriverDtoService driverDtoService, DriverService driverService, DriverValidator driverValidator) {
        this.driverDtoService = driverDtoService;
        this.driverService = driverService;
        this.driverValidator = driverValidator;
    }

    @GetMapping()
    public final String findAllDrivers(Model model) {
        log.info("Method findAllDrivers() started of class {}", getClass().getName());
        model.addAttribute("driverList", driverDtoService.findAllDriverWithCountCars());
        return "drivers/drivers";
    }

    @GetMapping("/new-driver")
    public final String showFormAddingDriver(@ModelAttribute("driver") Driver driver) {
        log.info("Method showFormAddingDriver() with driver {} started of class {}", driver, getClass().getName());
        return "drivers/new-driver";
    }

    @PostMapping()
    public String saveDriver(@ModelAttribute("driver") Driver driver, BindingResult result) {
        log.info("Method saveDriver() with driver {} started of class {}", driver, getClass().getName());

        driverValidator.validate(driver, result);
        if (result.hasErrors()) {
            return "drivers/new-driver";
        }

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
    public String updateDriver(@ModelAttribute("driver") Driver driver, BindingResult result, @PathVariable("id") Integer id) {
        log.info("Method updateDriver() with driver {} and id {} started of class {}", driver, id, getClass().getName());

        driverValidator.validate(driver, result);
        if (result.hasErrors()) {
            return "drivers/update-driver";
        }
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
