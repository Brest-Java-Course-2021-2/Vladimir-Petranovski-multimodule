package com.epam.brest.controller;

import com.epam.brest.model.Car;
import com.epam.brest.service_api.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import java.util.List;

import static com.epam.brest.logger.ProjectLogger.log;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @Test
    void shouldShowFormAddingCars() throws Exception {
        log.info("Method shouldShowFormAddingCars() started of class {}", getClass().getName());
        assertNotNull(carService);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars/new-car")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("cars/new-car"));
    }

    @Test
    void shouldSaveCar() throws Exception {
        log.info("Method shouldSaveCar() started of class {}", getClass().getName());
        assertNotNull(carService);
        Integer carSizeBefore = carService.count();
        assertNotNull(carSizeBefore);
        Car car = new Car("LUAZIK", 2);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/cars")
                        .param("carModel", car.getCarModel())
                        .param("driverId", String.valueOf(car.getDriverId()))
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cars"))
                .andExpect(redirectedUrl("/cars"));

        assertEquals(carSizeBefore, carService.count() - 1);
        log.info("Car's size list before save {} equals car's size list after save minus one {}", carSizeBefore, carService.count() - 1);
    }

    @Test
    void shouldFailAddCarOnEmptyName() throws Exception {
        log.info("Method shouldFailAddCarOnEmptyName() started of class {}", getClass().getName());

        // WHEN
        Car car = new Car("");

        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/cars")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("carModel", car.getCarModel())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("cars/new-car"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "car", "carModel"
                        )
                );
    }

    @Test
    void shouldFailAddCarOnEmptyDriverId() throws Exception {
        log.info("Method shouldFailAddCarOnEmptyDriverId() started of class {}", getClass().getName());

        // WHEN
        Car car = new Car("");

        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/cars")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverId", String.valueOf(car.getDriverId()))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("cars/new-car"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "car", "driverId"
                        )
                );
    }

    @Test
    void shouldShowFormForUpdateCar() throws Exception {
        log.info("Method shouldShowFormForUpdateCar() started of class {}", getClass().getName());
        assertNotNull(carService);
        List<Car> cars = carService.findAllCars();
        if (cars.size() ==  0) {
            carService.saveCar(new Car("NIVA", 1));
            cars = carService.findAllCars();
        }
        Car carSrc = cars.get(0);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars/" + carSrc.getCarId() + "/update-car")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("cars/update-car"));

        Car carDst = carService.findCarById(carSrc.getCarId());
        assertEquals(carSrc.getCarModel(), carDst.getCarModel());
        log.info("Car's name first from list: {} equals car's name after updating: {}", carSrc.getCarModel(), carDst.getCarModel());
    }

    @Test
    void shouldUpdateCar() throws Exception {
        log.info("Method shouldUpdateCar() started of class {}", getClass().getName());
        assertNotNull(carService);
        List<Car> cars = carService.findAllCars();
        if(cars.size() == 0) {
            carService.saveCar(new Car("NIVA", 2));
            cars = carService.findAllCars();
        }
        assertNotNull(cars);
        Car carSrc = cars.get(0);
        carSrc.setCarModel(carSrc.getCarModel() + "_TEST");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/cars/" + carSrc.getCarId())
                        .param("carModel", carSrc.getCarModel())
                        .param("driverId", String.valueOf(carSrc.getDriverId()))
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cars"))
                .andExpect(redirectedUrl("/cars"));

        carService.updateCarById(carSrc.getCarId(), carSrc);
        Car carDst = carService.findCarById(carSrc.getCarId());
        assertEquals(carSrc.getCarModel(), carDst.getCarModel());
        log.info("Car's name first from list: {} equals car's name after updating: {}", carSrc.getCarModel(), carDst.getCarModel());
    }

    @Test
    void shouldFailUpdateCarOnEmptyName() throws Exception {
        // WHEN
        log.info("Method shouldFailUpdateCarOnEmptyName() started of class {}", getClass().getName());
        assertNotNull(carService);
        List<Car> cars = carService.findAllCars();
        if (cars.size() == 0) {
            carService.saveCar(new Car("NIVA", 2));
            cars = carService.findAllCars();
        }

        Car carSrc = cars.get(0);
        carSrc.setCarModel("");
        carService.updateCarById(carSrc.getDriverId(), carSrc);

        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/cars/" + carSrc.getCarId())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("carModel", carSrc.getCarModel())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("cars/update-car"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "car", "carModel"
                        )
                );
        Car carDst = carService.findCarById(carSrc.getCarId());
        assertEquals(carSrc.getCarModel(), carDst.getCarModel());
        log.info("Car's name first from list: {} equals car's name after updating: {}", carSrc.getCarModel(), carDst.getCarModel());
    }

    @Test
    void shouldFailUpdateCarOnEmptyDriverId() throws Exception {
        // WHEN
        log.info("Method shouldFailUpdateCarOnEmptyDriverId() started of class {}", getClass().getName());
        assertNotNull(carService);
        List<Car> cars = carService.findAllCars();
        if (cars.size() == 0) {
            carService.saveCar(new Car("NIVA", 2));
            cars = carService.findAllCars();
        }

        Car carSrc = cars.get(0);
        carSrc.setDriverId(null);
        carService.updateCarById(carSrc.getDriverId(), carSrc);

        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/cars/" + carSrc.getCarId())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverId", String.valueOf(carSrc.getDriverId()))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("cars/update-car"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "car", "driverId"
                        )
                );
        Car carDst = carService.findCarById(carSrc.getCarId());
        assertEquals(carSrc.getCarModel(), carDst.getCarModel());
        log.info("Car's name first from list: {} equals car's name after updating: {}", carSrc.getCarModel(), carDst.getCarModel());
    }

    @Test
    void shouldDeleteCar() throws Exception {
        log.info("Method shouldDeleteCar() started of class {}", getClass().getName());
        assertNotNull(carService);
        carService.saveCar(new Car("NIVA_TEST", 2));
        List<Car> cars = carService.findAllCars();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars/" + cars.get(cars.size() - 1).getCarId() + "/delete-car")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cars"))
                .andExpect(redirectedUrl("/cars"));

        carService.deleteCarById(cars.get(cars.size() - 1).getCarId());
        assertEquals(cars.size() - 1, carService.findAllCars().size());
        log.info("First car's size list minus one: {} equals car's size list after deleting {}", cars.size() - 1, carService.findAllCars().size());
    }
}