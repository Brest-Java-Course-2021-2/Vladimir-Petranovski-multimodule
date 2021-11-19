package com.epam.brest.controller;

import com.epam.brest.service_api.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public String getAllCars(Model model) {
        model.addAttribute("carList", carService.findAllCars());
        return "cars/cars";
    }
}
