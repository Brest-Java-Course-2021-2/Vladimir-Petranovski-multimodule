package com.epam.brest.controller;

import com.epam.brest.service_api.CarService;
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

import static com.epam.brest.logger.ProjectLogger.log;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:app-context-web-test.xml"})
@Transactional
class CarControllerTestIT {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private CarService carService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void shouldReturnPageCars() throws Exception {
        log.info("Method shouldReturnPageCars() started of class {}", getClass().getName());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("cars/cars"))
                .andExpect(model().attribute("carList", hasItem(
                        allOf(
                                hasProperty("carId", is(1)),
                                hasProperty("carModel", is("GAZ")),
                                hasProperty("driverId", is(1))
                        )
                )))
                .andExpect(model().attribute("carList", hasItem(
                        allOf(
                                hasProperty("carId", is(2)),
                                hasProperty("carModel", is("ZIL")),
                                hasProperty("driverId", is(3))
                        )
                )))
                .andExpect(model().attribute("carList", hasItem(
                        allOf(
                                hasProperty("carId", is(3)),
                                hasProperty("carModel", is("LADA")),
                                hasProperty("driverId", is(3))
                        )
                )))
                .andExpect(model().attribute("carList", hasItem(
                        allOf(
                                hasProperty("carId", is(4)),
                                hasProperty("carModel", is("GIGA")),
                                hasProperty("driverId", is(1))
                        )
                )))
                .andExpect(model().attribute("carList", hasItem(
                        allOf(
                                hasProperty("carId", is(5)),
                                hasProperty("carModel", is("URAL")),
                                hasProperty("driverId", is(3))
                        )
                )));
    }
}