package com.epam.brest.service_rest.service;

import com.epam.brest.model.Car;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static com.epam.brest.logger.ProjectLogger.LOG;
import static com.epam.brest.model.constant.CarConstants.CAR_MODEL_SIZE;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:service-rest-test-app-context.xml"})
class CarServiceRestTest {

    public static final String CARS_URL = "http://localhost:8088/cars";

    @Autowired
    private RestTemplate restTemplate;

    private CarServiceRest carServiceRest;

    private MockRestServiceServer mockRestServiceServer;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        carServiceRest = new CarServiceRest(CARS_URL, restTemplate);
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldFindAllDrivers() throws URISyntaxException, JsonProcessingException {
        LOG.info("Method shouldFindAllDrivers() started {}",
                getClass().getName());
        // given
        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(Arrays.asList(create(0), create(1))))
                );
        // when
        List<Car> cars = carServiceRest.findAllCars();
        // then
        mockRestServiceServer.verify();
        assertNotNull(cars);
        assertTrue(cars.size() > 0);
    }

    @Test
    void shouldFindCarById() throws URISyntaxException, JsonProcessingException {
        LOG.info("Method shouldFindCarById() started {}",
                getClass().getName());
        // given
        Integer id = 1;
        Car car = new Car()
                .setCarId(id)
                .setCarModel(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE));

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL + "/" + id)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(car))
                );
        // when
        Car resultCar = carServiceRest.findCarById(id);
        // then
        mockRestServiceServer.verify();
        assertNotNull(resultCar);
        assertEquals(resultCar.getCarId(), id);
        assertEquals(resultCar.getCarModel(), car.getCarModel());
    }

    @Test
    void shouldSaveCar() throws JsonProcessingException, URISyntaxException {
        LOG.info("Method shouldSaveCar() started {}",
                getClass().getName());
        // given
        Car car = new Car()
                .setCarModel(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE));

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString("1"))
                );
        // when
        Integer id = carServiceRest.saveCar(car);
        // then
        mockRestServiceServer.verify();
        assertNotNull(id);
    }

    @Test
    void shouldUpdateCarById() throws URISyntaxException, JsonProcessingException {
        LOG.info("Method shouldUpdateCarById() started {}",
                getClass().getName());
        // given
        Integer id = 1;
        Car car = new Car()
                .setCarId(id)
                .setCarModel(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE));

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL + "/" + id)))
                .andExpect(method(HttpMethod.PATCH))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString("1"))
                );

        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL + "/" + id)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(car))
                );
        // when
        int result = carServiceRest.updateCarById(id, car);
        Car updatedCar = carServiceRest.findCarById(id);
        // then
        mockRestServiceServer.verify();
        assertEquals(1, result);

        assertNotNull(updatedCar);
        assertEquals(updatedCar.getCarId(), id);
        assertEquals(updatedCar.getCarModel(), car.getCarModel());
    }

    @Test
    void shouldDeleteCarById() throws URISyntaxException, JsonProcessingException {
        LOG.info("Method shouldDeleteCarById() started {}",
                getClass().getName());
        // given
        Integer id = 1;
        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(CARS_URL + "/" + id + "/delete-car")))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString("1"))
                );
        // when
        int result = carServiceRest.deleteCarById(id);
        // then
        mockRestServiceServer.verify();
        assertEquals(1, result);
    }

    private Car create(Integer index) {
        LOG.info("Method create() started {}", getClass().getName());
        Car car = new Car();
        car.setCarId(index);
        car.setCarModel("d" + index);
        car.setDriverId(index);
        return car;
    }
}