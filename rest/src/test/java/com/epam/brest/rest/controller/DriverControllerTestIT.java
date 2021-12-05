package com.epam.brest.rest.controller;

import com.epam.brest.model.Driver;
import com.epam.brest.model.constant.DriverConstants;
import com.epam.brest.rest.controller.exception.CustomExceptionHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static com.epam.brest.logger.ProjectLogger.log;
import static com.epam.brest.model.constant.DriverConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:rest-test-app-context.xml"})
@Transactional
class DriverControllerTestIT {

    public static final String DRIVERS_ENDPOINT = "/drivers";

    @Autowired
    private DriverController driverController;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    MockMvcDriverService driverService = new MockMvcDriverService();

    private Instant driverDateStartWork;
    private BigDecimal driverSalary;

    private

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(driverController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(customExceptionHandler)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();

        objectMapper = new ObjectMapper();
//        driverService = new MockMvcDriverService();

        driverDateStartWork = Instant.MAX;
        driverSalary = new BigDecimal(250);
    }

    @Test
    void findDriverById() throws Exception {
        log.info("Method findDriverById() started of class {}", getClass().getName());

//        //given
//        Driver driver = new Driver(RandomStringUtils.randomAlphabetic(DRIVER_NAME_SIZE), driverDateStartWork, driverSalary);
//        assertNotNull(driver);
//        Integer id = driverService.saveDriver(driver);
//        assertNotNull(id);
//        //when


    }

    class MockMvcDriverService {

        public List<Driver> findAllDrivers() throws Exception {
            log.info("Method findAllDrivers() started of class {}", getClass().getName());

            MockHttpServletResponse response = mockMvc.perform(
                    MockMvcRequestBuilders.get(DRIVERS_ENDPOINT)
                            .accept(MediaType.APPLICATION_JSON)
            ).andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andReturn().getResponse();
            assertNotNull(response);
            return objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Driver>>() {
            });
        }

        public Driver findDriverById(Integer id) throws Exception {
            log.info("Method findDriverById() with id: {} started of class {}", id, getClass().getName());

            MockHttpServletResponse response = mockMvc.perform(
                    MockMvcRequestBuilders.get(DRIVERS_ENDPOINT + "/" + id)
                            .accept(MediaType.APPLICATION_JSON)
            ).andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andReturn().getResponse();
            assertNotNull(response);
            return objectMapper.readValue(response.getContentAsString(), Driver.class);
        }

        public Integer saveDriver(Driver driver) throws Exception {
            log.info("Method saveDriver() with driver: {} started of class {}", driver, getClass().getName());

            String json = objectMapper.writeValueAsString(driver);
            MockHttpServletResponse response = mockMvc.perform(
                    MockMvcRequestBuilders.post(DRIVERS_ENDPOINT)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk())
                    .andReturn().getResponse();
            assertNotNull(response);
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }
    }
}