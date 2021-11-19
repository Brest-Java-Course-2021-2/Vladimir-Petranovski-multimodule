package com.epam.brest.controller;

import com.epam.brest.service_api.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;

import static com.epam.brest.logger.ProjectLogger.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:app-context-web-test.xml"})
@Transactional
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
    void shouldReturnDriverPage() throws Exception {
        log.info("Method shouldReturnDriverPage() started of class {}", getClass().getName());
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/drivers")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("drivers/drivers"))
                .andExpect(model().attribute("driverList", hasItem(
                        allOf(
                                hasProperty("driverId", is(1)),
                                hasProperty("driverName", is("VASIA")),
                                hasProperty("driverDateStartWork", is(Instant.parse("1998-10-01T12:02:01.8472Z"))),
                                hasProperty("driverSalary", is(BigDecimal.valueOf(500))),
                                hasProperty("countOfCarsAssignedToDriver", is(2))
                        )
                )))
                .andExpect(model().attribute("driverList", hasItem(
                        allOf(
                                hasProperty("driverId", is(2)),
                                hasProperty("driverName", is("VOVA")),
                                hasProperty("driverDateStartWork", is(Instant.parse("2010-10-11T08:30:30.1234Z"))),
                                hasProperty("driverSalary", is(BigDecimal.valueOf(850))),
                                hasProperty("countOfCarsAssignedToDriver", is(0))
                        )
                )))
                .andExpect(model().attribute("driverList", hasItem(
                        allOf(
                                hasProperty("driverId", is(3)),
                                hasProperty("driverName", is("VITALIY")),
                                hasProperty("driverDateStartWork", is(Instant.parse("2005-04-28T14:44:50.5327Z"))),
                                hasProperty("driverSalary", is(BigDecimal.valueOf(650))),
                                hasProperty("countOfCarsAssignedToDriver", is(3))
                        )
                )));
    }

    @Test
    void findAllDrivers() {
    }
}