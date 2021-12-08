package com.epam.brest.rest.controller;

import com.epam.brest.model.Car;
import com.epam.brest.model.Driver;
import com.epam.brest.model.constant.DriverConstants;
import com.epam.brest.rest.controller.exception.CustomExceptionHandler;
import com.epam.brest.rest.controller.exception.ErrorResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
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
import static com.epam.brest.model.constant.CarConstants.CAR_MODEL_SIZE;
import static com.epam.brest.model.constant.DriverConstants.*;
import static com.epam.brest.rest.controller.exception.CustomExceptionHandler.DRIVER_NOT_FOUND;
import static com.epam.brest.rest.controller.exception.CustomExceptionHandler.VALIDATION_ERROR;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
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

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(driverController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(customExceptionHandler)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();

        objectMapper = new ObjectMapper().registerModule(new JSR310Module());

        driverDateStartWork = Instant.MIN;
        driverSalary = new BigDecimal(250);
    }

    @Test
    void findAllDrivers() throws Exception {
        log.info("Method findAllDrivers() started of class {}", getClass().getName());
        //given
        Driver driver = new Driver(RandomStringUtils.randomAlphabetic(DRIVER_NAME_SIZE), driverDateStartWork, driverSalary);
        assertNotNull(driver);
        Integer id = driverService.saveDriver(driver);
        assertNotNull(id);
        //when
        List<Driver> drivers = driverService.findAllDrivers();
        //then
        assertNotNull(drivers);
        assertTrue(drivers.size() > 0);
    }

    @Test
    public void shouldSaveDriver() throws Exception {
        log.info("Method shouldSaveDriver() started of class {}", getClass().getName());

        Driver driver = new Driver(RandomStringUtils.randomAlphabetic(DRIVER_NAME_SIZE), driverDateStartWork, driverSalary);
        assertNotNull(driver);
        Integer id = driverService.saveDriver(driver);
        assertNotNull(id);
    }

    @Test
    void findDriverById() throws Exception {
        log.info("Method findDriverById() started of class {}", getClass().getName());
        //given
        Driver driver = new Driver(RandomStringUtils.randomAlphabetic(DRIVER_NAME_SIZE), driverDateStartWork, driverSalary);
        assertNotNull(driver);
        Integer id = driverService.saveDriver(driver);
        assertNotNull(id);
        // when
        Driver driverSrc = driverService.findDriverById(id);
        // then
        assertNotNull(driverSrc);
        assertEquals(driverSrc.getDriverId(), id);
        assertEquals(driver.getDriverName(), driverSrc.getDriverName());
    }

    @Test
    public void shouldUpdateDriver() throws Exception {
        log.info("Method shouldUpdateDriver() started of class {}", getClass().getName());

        // given
        Driver driver = new Driver(RandomStringUtils.randomAlphabetic(DRIVER_NAME_SIZE), driverDateStartWork, driverSalary);
        Integer id = driverService.saveDriver(driver);
        assertNotNull(id);

        Driver driverSrc = driverService.findDriverById(id);
        assertNotNull(driverSrc);

        driverSrc.setDriverName(RandomStringUtils.randomAlphabetic(DRIVER_NAME_SIZE));

        // when
        int result = driverService.updateDriver(id, driverSrc);

        // then
        assertTrue(1 == result);

        Driver driverDst = driverService.findDriverById(id);
        assertNotNull(driverDst);
        assertEquals(driverDst.getDriverId(), id);
        assertEquals(driverSrc.getDriverName(), driverDst.getDriverName());

    }

    @Test
    public void shouldDeleteDriver() throws Exception {
        log.info("Method shouldDeleteDriver() started of class {}", getClass().getName());

        // given
        Driver driver = new Driver(RandomStringUtils.randomAlphabetic(DRIVER_NAME_SIZE), driverDateStartWork, driverSalary);
        Integer id = driverService.saveDriver(driver);
        assertNotNull(id);

        List<Driver> drivers = driverService.findAllDrivers();
        assertNotNull(drivers);

        // when
        int result = driverService.deleteDriver(id);

        // then
        assertTrue(1 == result);

        List<Driver> currentDrivers = driverService.findAllDrivers();
        assertNotNull(currentDrivers);

        assertTrue(drivers.size() - 1 == currentDrivers.size());
    }

    @Test
    public void shouldReturnDriverNotFoundError() throws Exception {
        log.info("Method shouldReturnDriverNotFoundError() started of class {}", getClass().getName());

        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.get(DRIVERS_ENDPOINT + "/999999")
                                .accept(MediaType.APPLICATION_JSON)
                        ).andExpect(status().isNotFound())
                        .andReturn().getResponse();
        assertNotNull(response);
        ErrorResponse errorResponse = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);
        assertNotNull(errorResponse);
        assertEquals(errorResponse.getMessage(), DRIVER_NOT_FOUND);
    }

    @Test
    public void shouldFailOnCreateDriverWithDuplicateName() throws Exception {
        log.info("Method shouldFailOnCreateDriverWithDuplicateName() started of class {}", getClass().getName());

        Driver driver = new Driver(RandomStringUtils.randomAlphabetic(DRIVER_NAME_SIZE), driverDateStartWork, driverSalary);
        Integer id = driverService.saveDriver(driver);
        assertNotNull(id);

        Driver driverDst = new Driver(driver.getDriverName(), driverDateStartWork, driverSalary);

        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.post(DRIVERS_ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(driverDst))
                                .accept(MediaType.APPLICATION_JSON)
                        ).andExpect(status().isUnprocessableEntity())
                        .andReturn().getResponse();

        assertNotNull(response);
        ErrorResponse errorResponse = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);
        assertNotNull(errorResponse);
        assertEquals(errorResponse.getMessage(), VALIDATION_ERROR);
    }

    class MockMvcDriverService {

        public List<Driver> findAllDrivers() throws Exception {
            log.info("Method findAllDrivers() started of class {}", getClass().getName());

            MockHttpServletResponse response = mockMvc.perform(
                            MockMvcRequestBuilders.get(DRIVERS_ENDPOINT)
                                    .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
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
                    ).andExpect(status().isOk())
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

        public Integer updateDriver(Integer id, Driver driver) throws Exception {
            log.info("Method updateDriver() with driver: {} and with id: {} started of class {}", driver, id, getClass().getName());

            MockHttpServletResponse response =
                    mockMvc.perform(MockMvcRequestBuilders.patch(new StringBuilder(DRIVERS_ENDPOINT).append("/")
                                            .append(id).toString())
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(driver))
                                    .accept(MediaType.APPLICATION_JSON)
                            ).andExpect(status().isOk())
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }

        public Integer deleteDriver(Integer id) throws Exception {
            log.info("Method deleteDriver() with id: {} started of class {}", id, getClass().getName());

            MockHttpServletResponse response = mockMvc.perform(
                            MockMvcRequestBuilders.delete(new StringBuilder(DRIVERS_ENDPOINT).append("/")
                                            .append(id).append("/delete-driver").toString())
                                    .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }
    }
}