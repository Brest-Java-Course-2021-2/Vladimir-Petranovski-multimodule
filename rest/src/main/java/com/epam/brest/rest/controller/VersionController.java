package com.epam.brest.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    /**
     * Field VERSION.
     */

    private final static String VERSION = "0.0.1";

    /**
     * Goto version's page.
     *
     * @return local variable VERSION.
     */

    @GetMapping(value = "/version")
    public String version() {
        return VERSION;
    }
}
