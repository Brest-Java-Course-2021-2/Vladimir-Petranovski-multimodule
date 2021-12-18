package com.epam.brest.controller;

import com.epam.brest.model.Driver;
import com.epam.brest.model.dto.DriverDto;
import com.epam.brest.service_api.DriverService;
import com.epam.brest.service_api.dto.DriverDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:app-context-web-test.xml"})
@Transactional
class DriverControllerTestIT {

    public static final Logger LOG = LogManager.getLogger(
            DriverControllerTestIT.class);

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverDtoService driverDtoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void shouldReturnDriverPage() throws Exception {
        LOG.info("Method shouldReturnDriverPage() started of class {}", getClass().getName());
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/drivers_dto")
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
    void shouldShowFormAddingDriver() throws Exception {
        LOG.info("Method showFormAddingDriver() started of class {}", getClass().getName());
        assertNotNull(driverService);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/drivers/new-driver")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/new-driver"));
    }

    @Test
    void shouldSaveDriver() throws Exception {
        LOG.info("Method shouldSaveDriver() started of class {}", getClass().getName());
        assertNotNull(driverService);
        Integer driverSizeBefore = driverService.count();
        assertNotNull(driverSizeBefore);
        Driver driver = new Driver("VLADIMIR", Instant.parse("1996-10-10T00:00:00.001Z"), new BigDecimal(840));

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/drivers_dto")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverName", driver.getDriverName())
                                .param("driverDateStartWork", String.valueOf(driver.getDriverDateStartWork()))
                                .param("driverSalary", String.valueOf(driver.getDriverSalary()))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/drivers_dto"))
                .andExpect(redirectedUrl("/drivers_dto"));

        assertEquals(driverSizeBefore, driverService.count() - 1);
        LOG.info("Driver's size list before save {} equals driver's size list after save minus one {}", driverSizeBefore, driverService.count() - 1);
    }

    @Test
    void shouldFailAddDriverOnEmptyName() throws Exception {
        LOG.info("Method shouldFailAddDriverOnEmptyName() started of class {}", getClass().getName());

        // WHEN
        Driver driver = new Driver("");

        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/drivers_dto")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverName", driver.getDriverName())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/new-driver"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "driver", "driverName"
                        )
                );
    }

    @Test
    void shouldFailAddDriverOnEmptyDRiverDateStartWork() throws Exception {
        LOG.info("Method shouldFailAddDriverOnEmptyName() started of class {}", getClass().getName());

        // WHEN
        Driver driver = new Driver("TEST", null, new BigDecimal(300));

        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/drivers_dto")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverName", driver.getDriverName())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/new-driver"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "driver", "driverDateStartWork"
                        )
                );
    }

    @Test
    void shouldFailAddDriverOnEmptySalary() throws Exception {
        LOG.info("Method shouldFailAddDriverOnEmptySalary() started of class {}", getClass().getName());

        // WHEN
        Driver driver = new Driver("TEST", Instant.parse("1996-10-10T00:00:00.001Z"), null);

        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/drivers_dto")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverSalary", String.valueOf(driver.getDriverSalary()))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/new-driver"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "driver", "driverSalary"
                        )
                );
    }

    @Test
    void shouldShowPageUpdatingDriver() throws Exception {
        LOG.info("Method shouldShowPageUpdatingDriver() started of class {}", getClass().getName());
        assertNotNull(driverService);
        List<Driver> drivers = driverService.findAllDrivers();
        if(drivers.size() == 0) {
            driverService.saveDriver(new Driver("VLADIMIR", Instant.parse("1996-10-10T00:00:00.001Z"), new BigDecimal(840)));
            drivers = driverService.findAllDrivers();
        }

        Driver driverSrc = drivers.get(0);
        Driver driverDst = driverService.findDriverById(drivers.get(0).getDriverId());
        assertNotNull(driverSrc);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/drivers/" + driverSrc.getDriverId() + "/update-driver")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/update-driver"));

        assertEquals(driverSrc.getDriverName(), driverDst.getDriverName());
        LOG.info("Driver's name first from list: {} equals driver's name after updating: {}", driverSrc.getDriverName(), driverDst.getDriverName());
    }

    @Test
    void shouldUpdateDriver() throws Exception {
        LOG.info("Method shouldUpdateDriver() started of class {}", getClass().getName());
        assertNotNull(driverService);
        List<Driver> drivers = driverService.findAllDrivers();
        if (drivers.size() == 0) {
            driverService.saveDriver(new Driver("PETIA", Instant.parse("2003-05-01T00:00:01.01Z"), new BigDecimal(790)));
            drivers = driverService.findAllDrivers();
        }

        Driver driverSrc = drivers.get(0);
        driverSrc.setDriverName(driverSrc.getDriverName() + "_TEST");
        driverService.updateDriverById(driverSrc.getDriverId(), driverSrc);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/drivers/" + driverSrc.getDriverId())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverName", driverSrc.getDriverName())
                                .param("driverDateStartWork", String.valueOf(driverSrc.getDriverDateStartWork()))
                                .param("driverSalary", String.valueOf(driverSrc.getDriverSalary()))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/drivers_dto"))
                .andExpect(redirectedUrl("/drivers_dto"));

        Driver driverDst = driverService.findDriverById(driverSrc.getDriverId());
        assertEquals(driverSrc.getDriverName(), driverDst.getDriverName());
        LOG.info("Driver's name first from list: {} equals driver's name after updating: {}", driverSrc.getDriverName(), driverDst.getDriverName());
    }

    @Test
    void shouldFailUpdateDriverOnEmptyName() throws Exception {
        // WHEN
        LOG.info("Method shouldFailUpdateDriverOnEmptyName() started of class {}", getClass().getName());
        assertNotNull(driverService);
        List<Driver> drivers = driverService.findAllDrivers();
        if (drivers.size() == 0) {
            driverService.saveDriver(new Driver("PETIA", Instant.parse("2003-05-01T00:00:01.01Z"), new BigDecimal(790)));
            drivers = driverService.findAllDrivers();
        }

        Driver driverSrc = drivers.get(0);
        driverSrc.setDriverName("");
        driverService.updateDriverById(driverSrc.getDriverId(), driverSrc);

        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/drivers/" + driverSrc.getDriverId())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverName", driverSrc.getDriverName())
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/update-driver"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "driver", "driverName"
                        )
                );
        Driver driverDst = driverService.findDriverById(driverSrc.getDriverId());
        assertEquals(driverSrc.getDriverName(), driverDst.getDriverName());
        LOG.info("Driver's name first from list: {} equals driver's name after updating: {}", driverSrc.getDriverName(), driverDst.getDriverName());
    }

    @Test
    void shouldFailUpdateDriverOnEmptyDriverDateStartWork() throws Exception {
        // WHEN
        LOG.info("Method shouldFailUpdateDriverOnEmptyDriverDateStartWork() started of class {}", getClass().getName());
        assertNotNull(driverService);
        List<Driver> drivers = driverService.findAllDrivers();
        if (drivers.size() == 0) {
            driverService.saveDriver(new Driver("PETIA", Instant.parse("2003-05-01T00:00:01.01Z"), new BigDecimal(790)));
            drivers = driverService.findAllDrivers();
        }

        Driver driverSrc = drivers.get(0);
        driverSrc.setDriverDateStartWork(null);
        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/drivers/" + driverSrc.getDriverId())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverDateStartWork", String.valueOf(driverSrc.getDriverDateStartWork()))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/update-driver"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "driver", "driverDateStartWork"
                        )
                );

        Driver driverDst = driverService.findDriverById(driverSrc.getDriverId());
        assertEquals(driverSrc.getDriverName(), driverDst.getDriverName());
        LOG.info("Driver's name first from list: {} equals driver's name after updating: {}", driverSrc.getDriverName(), driverDst.getDriverName());
    }

    @Test
    void shouldFailUpdateDriverOnEmptySalary() throws Exception {
        // WHEN
        LOG.info("Method shouldFailUpdateDriverOnEmptyName() started of class {}", getClass().getName());
        assertNotNull(driverService);
        List<Driver> drivers = driverService.findAllDrivers();
        if (drivers.size() == 0) {
            driverService.saveDriver(new Driver("PETIA", Instant.parse("2003-05-01T00:00:01.01Z"), new BigDecimal(790)));
            drivers = driverService.findAllDrivers();
        }

        Driver driverSrc = drivers.get(0);
        driverSrc.setDriverSalary(null);
        // THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/drivers/" + driverSrc.getDriverId())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("driverSalary", String.valueOf(driverSrc.getDriverSalary()))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/update-driver"))
                .andExpect(
                        model().attributeHasFieldErrors(
                                "driver", "driverSalary"
                        )
                );
        Driver driverDst = driverService.findDriverById(driverSrc.getDriverId());
        assertEquals(driverSrc.getDriverName(), driverDst.getDriverName());
        LOG.info("Driver's name first from list: {} equals driver's name after updating: {}", driverSrc.getDriverName(), driverDst.getDriverName());
    }

    @Test
    void shouldDeleteDriver() throws Exception {
        LOG.info("Method started: checkDeleteDriverById() of {}", getClass().getName());
        assertNotNull(driverService);
        driverService.saveDriver(new Driver("VERANICA", Instant.parse("2002-09-15T08:09:12.4342Z"), new BigDecimal(720)));
        List<Driver> drivers = driverService.findAllDrivers();
        Driver driver = drivers.get(drivers.size() - 1);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/drivers/" + driver.getDriverId() + "/delete-driver")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/drivers_dto"))
                .andExpect(redirectedUrl("/drivers_dto"));

        driverService.deleteDriverById(driver.getDriverId());

        assertEquals(drivers.size() - 1, driverService.findAllDrivers().size());
        LOG.info("First driver's size list minus one: {} equals driver's size list after deleting {}", drivers.size() - 1, driverService.findAllDrivers().size());
    }

    @Test
    void shouldReturnFormForChoosingDriversByDate() throws Exception {
        LOG.info("Method shouldReturnFormForChoosingDriversByDate() started of class {}", getClass().getName());
        assertNotNull(driverService);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/drivers_dto/form-range")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("drivers/form-range"));
    }

    @Test
    void shouldDoChooseDriverFromDateToDate() throws Exception {
        LOG.info("Method shouldDoChooseDriverFromDateToDate() started of class {}", getClass().getName());
        assertNotNull(driverService);
        List<DriverDto> drivers = driverDtoService.findAllDriverWithCountCars();

        assertNotNull(drivers);
        String fromDate = drivers.get(0).getDriverDateStartWork().toString();
        String toDate = drivers.get(drivers.size() - 1).getDriverDateStartWork().toString();

        List<DriverDto> driversDst = driverDtoService.chooseDriverOnDateRange(fromDate, toDate);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/drivers_dto/drivers-range")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("drivers/drivers-range"));

        assertEquals(drivers.get(0).getDriverName(), driversDst.get(0).getDriverName());
    }

}