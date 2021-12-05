package com.epam.brest.rest.controller;

import com.epam.brest.model.Car;
import com.epam.brest.model.Driver;
import com.epam.brest.model.constant.CarConstants;
import com.epam.brest.rest.controller.exception.CustomExceptionHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.epam.brest.logger.ProjectLogger.log;
import static com.epam.brest.model.constant.CarConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:rest-test-app-context.xml"})
class CarControllerTestIT {

    public static final String CARS_ENDPOINT = "/cars";

    @Autowired
    private CarController carController;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    MockMvcCarService carService = new MockMvcCarService();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(carController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(customExceptionHandler)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();

        objectMapper = new ObjectMapper();
    }

    @Test
    void findAllCars() {
    }

    @Test
    public void shouldSaveCar() throws Exception {
        log.info("Method shouldSaveCar() started of class {}", getClass().getName());

        Car car = new Car(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE));
        carService.saveCar(car);
        Integer id = car.getCarId();
        assertNotNull(id);
    }

    class MockMvcCarService {
        public List<Car> findAllCars() throws Exception {
            log.info("Method findAllCars() started of class {}", getClass().getName());

            MockHttpServletResponse response = mockMvc.perform(
                            MockMvcRequestBuilders.get(CARS_ENDPOINT)
                                    .accept(MediaType.APPLICATION_JSON)
                    ).andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andReturn().getResponse();
            assertNotNull(response);
            return objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Car>>() {
            });
        }

        public Car findCarById(Integer id) throws Exception {
            log.info("Method findCarById() with id: {} started of class {}", id, getClass().getName());

            MockHttpServletResponse response = mockMvc.perform(
                            MockMvcRequestBuilders.get(CARS_ENDPOINT + "/" + id)
                                    .accept(MediaType.APPLICATION_JSON)
                    ).andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andReturn().getResponse();
            assertNotNull(response);
            return objectMapper.readValue(response.getContentAsString(), Car.class);
        }

        public Integer saveCar(Car car) throws Exception {
            log.info("Method saveCar() with car: {} started of class {}", car, getClass().getName());

            String json = objectMapper.writeValueAsString(car);
            MockHttpServletResponse response =
                    mockMvc.perform(post(CARS_ENDPOINT)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(json)
                                    .accept(MediaType.APPLICATION_JSON)
                            ).andExpect(status().isOk())
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }
    }
}