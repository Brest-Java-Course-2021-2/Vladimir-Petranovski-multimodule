package com.epam.brest.controller;

import com.epam.brest.logger.ProjectLogger;
import com.epam.brest.service_api.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static com.epam.brest.logger.ProjectLogger.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:app-context-test.xml"})
@Transactional
@Rollback
class DriverControllerTestIT {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private DriverService driverService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void findAllDrivers() throws Exception {
        log.info("Method findAllDrivers() started of class {}", getClass().getName());
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/drivers")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}