package com.epam.brest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MotorDepotController {

    /**
     * Goto motor depot page.
     *
     * @return view name.
     */

    @GetMapping
    public String showMotorDepotPage() {
        return "motor-depot";
    }
}
