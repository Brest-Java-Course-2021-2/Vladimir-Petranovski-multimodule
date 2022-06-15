package com.epam.brest.rest.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    public static final Logger LOG = LogManager.getLogger(VersionController.class);

    /**
     * Field VERSION.
     */

    private static final String VERSION = "0.0.1";

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
