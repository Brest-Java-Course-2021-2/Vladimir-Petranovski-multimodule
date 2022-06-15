package com.epam.brest.rest.controller;

import com.epam.brest.model.Car;
import com.epam.brest.rest.controller.exception.CustomExceptionHandlerCar;
import com.epam.brest.rest.controller.exception.ErrorResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import static com.epam.brest.model.constant.CarConstants.CAR_MODEL_SIZE;
import static com.epam.brest.rest.controller.exception.CustomExceptionHandlerCar.CAR_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:rest-test-app-context.xml"})
class CarControllerTestIT {

    public static final Logger LOG = LogManager.getLogger(CarControllerTestIT.class);

    public static final String CARS_ENDPOINT = "/cars";

    @Autowired
    private CarController carController;

    @Autowired
    private CustomExceptionHandlerCar customExceptionHandlerCar;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    MockMvcCarService carService = new MockMvcCarService();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(carController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(customExceptionHandlerCar)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();

        objectMapper = new ObjectMapper();
    }

    @Test
    void findAllCars() throws Exception {
        LOG.info("Method findAllCars() started of class {}", getClass().getName());
        //given
        Car car = new Car(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE), carService.findAllCars().get(0).getDriverId());
        Integer id = carService.saveCar(car);
        assertNotNull(id);
        //when
        List<Car> cars = carService.findAllCars();
        //then
        assertNotNull(cars);
        assertTrue(cars.size() > 0);
    }

    @Test
    public void shouldSaveCar() throws Exception {
        LOG.info("Method shouldSaveCar() started of class {}", getClass().getName());

        Car car = new Car(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE), carService.findAllCars().get(0).getDriverId());
        Integer id = carService.saveCar(car);
        assertNotNull(id);
    }

    @Test
    public void shouldFindCarById() throws Exception {
        LOG.info("Method shouldFindCarById() started of class {}", getClass().getName());
        // given
        Car car = new Car(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE), carService.findAllCars().get(0).getDriverId());
        Integer id = carService.saveCar(car);
        assertNotNull(id);
        // when
        Car carSrc = carService.findCarById(id);
        // then
        assertNotNull(carSrc);
        assertEquals(carSrc.getCarId(), id);
        assertEquals(car.getCarModel(), carSrc.getCarModel());
    }

    @Test
    public void shouldUpdateCar() throws Exception {
        LOG.info("Method shouldUpdateCar() started of class {}", getClass().getName());

        // given
        Car car = new Car(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE), carService.findAllCars().get(0).getDriverId());
        Integer id = carService.saveCar(car);
        assertNotNull(id);

        Car carSrc = carService.findCarById(id);
        assertNotNull(carSrc);

        carSrc.setCarModel(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE));

        // when
        int result = carService.updateCar(id, carSrc);

        // then
        assertEquals(1, result);

        Car carDst = carService.findCarById(id);
        assertNotNull(carDst);
        assertEquals(carDst.getCarId(), id);
        assertEquals(carSrc.getCarModel(), carDst.getCarModel());

    }

    @Test
    public void shouldDeleteCar() throws Exception {
        LOG.info("Method shouldDeleteCar() started of class {}", getClass().getName());
        // given
        Car car = new Car(RandomStringUtils.randomAlphabetic(CAR_MODEL_SIZE), carService.findAllCars().get(0).getDriverId());
        Integer id = carService.saveCar(car);
        assertNotNull(id);

        List<Car> cars = carService.findAllCars();
        assertNotNull(cars);
        // when
        int result = carService.deleteCar(id);
        // then
        assertEquals(1, result);

        List<Car> currentCars = carService.findAllCars();
        assertNotNull(currentCars);

        assertEquals(cars.size() - 1, currentCars.size());
    }

    @Test
    public void shouldReturnCarNotFoundError() throws Exception {
        LOG.info("Method shouldReturnCarNotFoundError() started of class {}", getClass().getName());

        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.get(CARS_ENDPOINT + "/9999")
                                .accept(MediaType.APPLICATION_JSON)
                        ).andExpect(status().isNotFound())
                        .andReturn().getResponse();
        assertNotNull(response);
        ErrorResponse errorResponse = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);
        assertNotNull(errorResponse);
        assertEquals(errorResponse.getMessage(), CAR_NOT_FOUND);
    }

    class MockMvcCarService {
        public List<Car> findAllCars() throws Exception {
            LOG.info("Method findAllCars() started of class {}", getClass().getName());

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
            LOG.info("Method findCarById() with id: {} started of class {}", id, getClass().getName());

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
            LOG.info("Method saveCar() with car: {} started of class {}", car, getClass().getName());

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

        public Integer updateCar(Integer id, Car car) throws Exception {
            LOG.info("Method saveCar() with car: {} started of class {}", car, getClass().getName());

            MockHttpServletResponse response =
                    mockMvc.perform(MockMvcRequestBuilders.patch(CARS_ENDPOINT + "/" +
                                            id)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(car))
                                    .accept(MediaType.APPLICATION_JSON)
                            ).andExpect(status().isOk())
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }

        public Integer deleteCar(Integer id) throws Exception {
            LOG.info("Method deleteCar() with id: {} started of class {}", id, getClass().getName());

            MockHttpServletResponse response = mockMvc.perform(
                            MockMvcRequestBuilders.delete(CARS_ENDPOINT + "/" +
                                            id + "/delete-car")
                                    .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }
    }
}