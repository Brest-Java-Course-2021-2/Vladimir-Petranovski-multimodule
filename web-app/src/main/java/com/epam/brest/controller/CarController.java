package com.epam.brest.controller;

import com.epam.brest.controller.validator.CarValidator;
import com.epam.brest.model.Car;
import com.epam.brest.service_api.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.epam.brest.logger.ProjectLogger.log;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    private final CarValidator carValidator;

    public CarController(CarService carService, CarValidator carValidator) {
        this.carService = carService;
        this.carValidator = carValidator;
    }

    @GetMapping()
    public String getAllCars(Model model) {
        log.info("Method findAllCars() started of class {}", getClass().getName());
        model.addAttribute("carList", carService.findAllCars());
        return "cars/cars";
    }

    @GetMapping("/new-car")
    public String showFormAddingCar(@ModelAttribute("car") Car car) {
        log.info("Method showFormAddingCar() with car {} started of class {}", car, getClass().getName());
        return "cars/new-car";
    }

    @PostMapping()
    public String saveCar(@ModelAttribute("car") Car car, BindingResult result) {
        log.info("Method saveCar() with car {} started of class {}", car, getClass().getName());

        carValidator.validate(car, result);
        if (result.hasErrors()) {
            return "cars/new-car";
        }
        carService.saveCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/{id}/update-car")
    public String showFormUpdatingCar(@PathVariable("id") Integer id, Model model) {
        log.info("Method showFormUpdatingCar() with id {} started of class {}", id, getClass().getName());
        model.addAttribute("car", carService.findCarById(id));
        return "cars/update-car";
    }

    @PostMapping("/{id}")
    public String updateCar(@ModelAttribute("car") Car car, BindingResult result, @PathVariable("id") Integer id) {
        log.info("Method updateCar() with car {} and id {} started of class {}", car, id, getClass().getName());

        carValidator.validate(car, result);
        if(result.hasErrors()) {
            return "cars/update-car";
        }
        carService.updateCarById(id, car);
        return "redirect:/cars";
    }

    @GetMapping("/{id}/delete-car")
    public String deleteCar(@PathVariable("id") Integer id) {
        log.info("Method deleteCar() with id {} started of class {}", id, getClass().getName());
        carService.deleteCarById(id);
        return "redirect:/cars";
    }
}
