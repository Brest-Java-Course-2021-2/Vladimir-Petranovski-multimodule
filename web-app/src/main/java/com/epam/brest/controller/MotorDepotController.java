package com.epam.brest.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MotorDepotController {

    public static final Logger LOG = LogManager.getLogger(
            MotorDepotController.class);

    /**
     * Goto motor depot page.
     *
     * @return view name.
     */

    @GetMapping
    public String showMotorDepotPage() {
        LOG.info("Method showMotorDepotPage() started in {}",
                getClass().getName());
        return "motor-depot";
    }
}
