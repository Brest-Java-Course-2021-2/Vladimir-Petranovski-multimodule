package com.epam.brest.controller;

import com.epam.brest.logger.ProjectLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MotorDepotController {

    @GetMapping
    public String showMotorDepotPage() {
        ProjectLogger.log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "motor-depot";
    }
}
