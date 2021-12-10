package com.epam.brest.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.epam.brest.logger.ProjectLogger.LOG;

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
        LOG.info("Method version() started of class {}",
            getClass().getName());
        return VERSION;
    }
}
