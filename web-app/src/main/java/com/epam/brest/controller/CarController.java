package com.epam.brest.controller;

import com.epam.brest.controller.validator.CarValidator;
import com.epam.brest.model.Car;
import com.epam.brest.service_api.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

    public static final Logger LOG = LogManager.getLogger(
            CarController.class);

    public static final String CARS_CARS = "cars/cars";

    public static final String CARS_NEW_CARS = "cars/new-car";

    public static final String CARS_UPDATE_CARS = "cars/update-car";

    public static final String REDIRECT_CARS = "redirect:/cars";

    /**
     * Field carService.
     */

    private final CarService carService;

    /**
     * Field carValidator.
     */

    private final CarValidator carValidator;

    /**
     * Constructor.
     *
     * @param carService carService.
     * @param  carValidator carValidator.
     *
     */

    public CarController(final CarService carService,
                         final CarValidator carValidator) {
        this.carService = carService;
        this.carValidator = carValidator;
    }

    /**
     * Goto car's list page.
     *
     * @param model model.
     * @return view name.
     */

    @GetMapping()
    public String getAllCars(final Model model) {
        LOG.info("Method findAllCars() started of class {}",
                getClass().getName());
        model.addAttribute("carList", carService.findAllCars());
        return CARS_CARS;
    }

    /**
     * Goto form adding new car page.
     *
     * @param car car.
     * @return view name.
     */

    @GetMapping("/new-car")
    public String showFormAddingCar(@ModelAttribute("car") final Car car) {
        LOG.info("Method showFormAddingCar() with car {} started of class {}",
                car, getClass().getName());
        return CARS_NEW_CARS;
    }

    /**
     * Goto car's list after adding new car page.
     *
     * @param car car.
     * @param result for validate.
     * @return view name.
     */

    @PostMapping()
    public String saveCar(@ModelAttribute("car") final Car car,
                          final BindingResult result) {
        LOG.info("Method saveCar() with car {} started of class {}",
                car, getClass().getName());

        carValidator.validate(car, result);
        if (result.hasErrors()) {
            return CARS_NEW_CARS;
        }
        carService.saveCar(car);
        return "redirect:/cars";
    }

    /**
     * Goto form updating car page.
     *
     * @param id car for updating.
     * @param model model.
     * @return view name.
     */

    @GetMapping("/{id}/update-car")
    public String showFormUpdatingCar(@PathVariable("id") final Integer id,
                                      final Model model) {
        LOG.info("Method showFormUpdatingCar() with id {} started of class {}",
                id, getClass().getName());
        model.addAttribute("car", carService.findCarById(id));
        return CARS_UPDATE_CARS;
    }

    /**
     * Goto car's list page after updating car.
     *
     * @param car car.
     * @param result for validate.
     * @param id car for updating.
     * @return view name.
     */

    @PostMapping("/{id}")
    public String updateCar(@ModelAttribute("car") final Car car,
                            final BindingResult result,
                            @PathVariable("id") final Integer id) {
        LOG.info("Method updateCar() with car {} and id {} started of class {}",
                car, id, getClass().getName());

        carValidator.validate(car, result);
        if (result.hasErrors()) {
            return CARS_UPDATE_CARS;
        }
        carService.updateCarById(id, car);
        return REDIRECT_CARS;
    }

    /**
     * Goto car's list page after deleting car.
     *
     * @param id car for deleting.
     * @return view name.
     */

    @GetMapping("/{id}/delete-car")
    public String deleteCar(@PathVariable("id") final Integer id) {
        LOG.info("Method deleteCar() with id {} started of class {}",
                id, getClass().getName());
        carService.deleteCarById(id);
        return REDIRECT_CARS;
    }
}
