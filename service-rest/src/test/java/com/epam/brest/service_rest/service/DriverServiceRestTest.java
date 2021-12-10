package com.epam.brest.service_rest.service;

import com.epam.brest.model.Driver;
import com.epam.brest.model.dto.DriverDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.epam.brest.logger.ProjectLogger.LOG;
import static com.epam.brest.model.constant.DriverConstants.DRIVER_NAME_SIZE;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:service-rest-test-app-context.xml"})
class DriverServiceRestTest {

    public static final String DRIVERS_URL = "http://localhost:8088/drivers";

    @Autowired
    private RestTemplate restTemplate;

    private DriverServiceRest driverServiceRest;

    private MockRestServiceServer mockRestServiceServer;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        driverServiceRest = new DriverServiceRest(DRIVERS_URL, restTemplate);
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
        objectMapper = new ObjectMapper().registerModule(new JSR310Module());
    }

    @Test
    void shouldFindAllDrivers() throws URISyntaxException, JsonProcessingException {
        LOG.info("Method shouldFindAllDrivers() started {}",
                getClass().getName());
        // given
        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(DRIVERS_URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(Arrays.asList(create(0), create(1))))
                );
        // when
        List<Driver> drivers = driverServiceRest.findAllDrivers();
        // then
        mockRestServiceServer.verify();
        assertNotNull(drivers);
        assertTrue(drivers.size() > 0);
    }

    @Test
    void shouldFindDriverById() throws URISyntaxException, JsonProcessingException {
        LOG.info("Method shouldFindDriverById() started {}",
                getClass().getName());
        // given
        Integer id = 1;
        Driver driver = new Driver()
                .setDriverId(id)
                .setDriverName(RandomStringUtils.randomAlphabetic(DRIVER_NAME_SIZE));

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(DRIVERS_URL + "/" + id)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(driver))
                );
        // when
        Driver resultDriver = driverServiceRest.findDriverById(id);
        // then
        mockRestServiceServer.verify();
        assertNotNull(resultDriver);
        assertEquals(resultDriver.getDriverId(), id);
        assertEquals(resultDriver.getDriverName(), driver.getDriverName());
    }

    @Test
    void shouldSaveDriver() throws JsonProcessingException, URISyntaxException {
        LOG.info("Method shouldSaveDriver() started {}",
                getClass().getName());
        // given
        Driver driver = new Driver()
                .setDriverName(RandomStringUtils.randomAlphabetic(DRIVER_NAME_SIZE));

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(DRIVERS_URL)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString("1"))
                );
        // when
        Integer id = driverServiceRest.saveDriver(driver);
        // then
        mockRestServiceServer.verify();
        assertNotNull(id);
    }

    @Test
    void shouldUpdateDriverById() throws URISyntaxException, JsonProcessingException {
        LOG.info("Method shouldUpdateDriverById() started {}",
                getClass().getName());
        // given
        Integer id = 1;
        Driver driver = new Driver()
                .setDriverId(id)
                .setDriverName(RandomStringUtils.randomAlphabetic(DRIVER_NAME_SIZE));

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(DRIVERS_URL + "/" + id)))
                .andExpect(method(HttpMethod.PATCH))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString("1"))
                );

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(DRIVERS_URL + "/" + id)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(driver))
                );
        // when
        int result = driverServiceRest.updateDriverById(id, driver);
        Driver updatedDriver = driverServiceRest.findDriverById(id);
        // then
        mockRestServiceServer.verify();
        assertEquals(1, result);

        assertNotNull(updatedDriver);
        assertEquals(updatedDriver.getDriverId(), id);
        assertEquals(updatedDriver.getDriverName(), driver.getDriverName());
    }

    @Test
    void shouldDeleteDriverById() throws URISyntaxException, JsonProcessingException {
        LOG.info("Method shouldDeleteDriverById() started {}",
                getClass().getName());
        // given
        Integer id = 1;
        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(DRIVERS_URL + "/" + id + "/delete-driver")))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString("1"))
                );
        // when
        int result = driverServiceRest.deleteDriverById(id);
        // then
        mockRestServiceServer.verify();
        assertEquals(1, result);
    }

    private DriverDto create(Integer index) {
        LOG.info("Method create() started {}",
                getClass().getName());
        DriverDto driverDto = new DriverDto();
        driverDto.setDriverId(index);
        driverDto.setDriverName("d" + index);
        driverDto.setDriverDateStartWork(new Date(index).toInstant());
        driverDto.setDriverSalary(BigDecimal.valueOf(100 + index));
        driverDto.setCountOfCarsAssignedToDriver(100 + index);
        return driverDto;
    }
}