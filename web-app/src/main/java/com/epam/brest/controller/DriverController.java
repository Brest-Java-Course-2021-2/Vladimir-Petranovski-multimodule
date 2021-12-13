package com.epam.brest.controller;

import com.epam.brest.controller.validator.DriverValidator;
import com.epam.brest.model.Driver;
import com.epam.brest.service_api.DriverService;
import com.epam.brest.service_api.dto.DriverDtoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

import static com.epam.brest.logger.ProjectLogger.LOG;

@Controller
public class DriverController {

    /**
     * Field driverDtoService.
     */

    private final DriverDtoService driverDtoService;

    /**
     * Field driverService.
     */

    private final DriverService driverService;

    /**
     * Field driverValidator.
     */

    private final DriverValidator driverValidator;

    /**
     * Constructor.
     *
     * @param enterDriverDtoService driverDto Service.
     * @param enterDriverService driverService.
     * @param enterDriverValidator driverValidator.
     */

    public DriverController(@Qualifier("driverDtoServiceRest")
                            final DriverDtoService enterDriverDtoService,
                            @Qualifier("driverServiceRest")
                            final DriverService enterDriverService,
                            @Qualifier("driverValidator")
                            final DriverValidator enterDriverValidator) {
        this.driverDtoService = enterDriverDtoService;
        this.driverService = enterDriverService;
        this.driverValidator = enterDriverValidator;
    }

    /**
     * Goto driver's list page.
     *
     * @param model model.
     * @return view name.
     */

    @GetMapping("/drivers_dto")
    public final String findAllDrivers(final Model model) {
        LOG.info("Method findAllDrivers() started of class {}",
                getClass().getName());
//        DriverDto driverDto = new DriverDto();
////        driverDto.setDriverDateStartWork(Instant.now());
//
////        double secondsSinceEpoch = 1.511554592277516E9;
////        long longSeconds = (long) secondsSinceEpoch;
////        long micros = Math.round(
// (secondsSinceEpoch - longSeconds) * 1_000_000);
////        Instant inst = Instant.ofEpochSecond(
// longSeconds).plus(micros , ChronoUnit.MICROS);
//        double secondsSinceEpoch1 = Double.parseDouble(
//        String.valueOf(driverDto.getDriverDateStartWork()));
//        long longSeconds1 = (long) secondsSinceEpoch1;
//        long micros1 = Math.round(
//        (secondsSinceEpoch1 - longSeconds1) * 1_000_000);
//        driverDto.setDriverDateStartWork(
//        Instant.ofEpochSecond(longSeconds1).plus(micros1 ,
//        ChronoUnit.MICROS));
        model.addAttribute("driverList",
                driverDtoService.findAllDriverWithCountCars());
        return "drivers/drivers";
    }

    /**
     * Goto form adding new driver page.
     *
     * @param driver driver.
     * @return view name.
     */

    @GetMapping("/drivers/new-driver")
    public final String showFormAddingDriver(
            @ModelAttribute("driver") final Driver driver) {
        LOG.info("Method showFormAddingDriver() "
                        + "with driver {} started of class {}",
                driver, getClass().getName());
        return "drivers/new-driver";
    }

    /**
     * Goto driver's list after adding new driver page.
     *
     * @param driver driver.
     * @param result for validate.
     * @return view name.
     */

    @PostMapping("/drivers_dto")
    public String saveDriver(@ModelAttribute("driver") final Driver driver,
                             final BindingResult result) {
        LOG.info("Method saveDriver() with driver {} started of class {}",
                driver, getClass().getName());

        driverValidator.validate(driver, result);
        if (result.hasErrors()) {
            return "drivers/new-driver";
        }

        driverService.saveDriver(driver);
        return "redirect:/drivers_dto";
    }

    /**
     * Goto form updating driver page.
     *
     * @param id driver for updating.
     * @param model model.
     * @return view name.
     */

    @GetMapping("/drivers/{id}/update-driver")
    public String showFormForUpdatingDriver(
            @PathVariable("id") final Integer id,
            final Model model) {
        LOG.info("Method showFormForUpdatingDriver()"
                        + " with id {} started of class {}",
                id, getClass().getName());
        model.addAttribute("driver", driverService.findDriverById(id));
        return "drivers/update-driver";
    }

    /**
     * Goto driver's list page after updating driver.
     *
     * @param driver driver.
     * @param result for validate.
     * @param id driver for updating.
     * @return view name.
     */

    @PostMapping("/drivers/{id}")
    public String updateDriver(@ModelAttribute("driver") final Driver driver,
                               final BindingResult result,
                               @PathVariable("id") final Integer id) {
        LOG.info("Method updateDriver() with driver {}"
                        + " and id {} started of class {}",
                driver, id, getClass().getName());

        driverValidator.validate(driver, result);
        if (result.hasErrors()) {
            return "drivers/update-driver";
        }
        driverService.updateDriverById(id, driver);
        return "redirect:/drivers_dto";
    }

    /**
     * Goto driver's list page after deleting driver.
     *
     * @param id driver for deleting.
     * @return view name.
     */

    @GetMapping("/drivers/{id}/delete-driver")
    public String deleteDriver(@PathVariable("id") final Integer id) {
        LOG.info("Method deleteDriver() with id {} started of class {}",
                id, getClass().getName());
        driverService.deleteDriverById(id);
        return "redirect:/drivers_dto";
    }
}
