package com.epam.brest.rest.controller.dto;

import com.epam.brest.model.dto.DriverDto;
import com.epam.brest.service_api.dto.DriverDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class DriverDtoControllerTest {

    public static final Logger LOG = LogManager.getLogger(DriverDtoControllerTest.class);

    @InjectMocks
    private DriverDtoController driverDtoController;

    @Mock
    private DriverDtoService driverDtoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(driverDtoController)
                .build();
    }

    @AfterEach
    public void end() {
        verifyNoMoreInteractions(driverDtoService);
    }

    @Test
    void findAllDriversWithCountCars() throws Exception {
        LOG.info("Method findAllDriversWithCountCars() started of class {}", getClass().getName());

        when(driverDtoService.findAllDriverWithCountCars()).thenReturn(Arrays.asList(create(0), create(1)));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/drivers_dto")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].driverId", Matchers.is(0)))
                .andExpect(jsonPath("$[0].driverName", Matchers.is("d0")))
                .andExpect(jsonPath("$[0].driverDateStartWork", Matchers.is(0.0)))
                .andExpect(jsonPath("$[0].driverSalary", Matchers.is(100)))
                .andExpect(jsonPath("$[0].countOfCarsAssignedToDriver", Matchers.is(100)))
                .andExpect(jsonPath("$[1].driverId", Matchers.is(1)))
                .andExpect(jsonPath("$[1].driverName", Matchers.is("d1")))
                .andExpect(jsonPath("$[1].driverDateStartWork", Matchers.is(0.001)))
                .andExpect(jsonPath("$[1].driverSalary", Matchers.is(101)))
                .andExpect(jsonPath("$[1].countOfCarsAssignedToDriver", Matchers.is(101)));

        verify(driverDtoService, times(1)).findAllDriverWithCountCars();
    }

//    @Test
//    void shouldShowDriversListOnRange() throws Exception {
//        LOG.info("Method shouldShowDriversListOnRange() started of class {}", getClass().getName());
//
//        when(driverDtoService.chooseDriverOnDateRange(anyString(), anyString())).thenReturn(Arrays.asList(create(0), create(1)));
//
//        mockMvc.perform(
//                        MockMvcRequestBuilders.get("/drivers_dto/drivers-range")
//                ).andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$[0].driverId", Matchers.is(0)))
//                .andExpect(jsonPath("$[0].driverName", Matchers.is("d0")))
//                .andExpect(jsonPath("$[0].driverDateStartWork", Matchers.is(0.0)))
//                .andExpect(jsonPath("$[0].driverSalary", Matchers.is(100)))
//                .andExpect(jsonPath("$[0].countOfCarsAssignedToDriver", Matchers.is(100)))
//                .andExpect(jsonPath("$[1].driverId", Matchers.is(1)))
//                .andExpect(jsonPath("$[1].driverName", Matchers.is("d1")))
//                .andExpect(jsonPath("$[1].driverDateStartWork", Matchers.is(0.001)))
//                .andExpect(jsonPath("$[1].driverSalary", Matchers.is(101)))
//                .andExpect(jsonPath("$[1].countOfCarsAssignedToDriver", Matchers.is(101)));
//
//        verify(driverDtoService, times(1)).chooseDriverOnDateRange(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
//    }

    private DriverDto create(Integer index) {
        DriverDto driverDto = new DriverDto();
        driverDto.setDriverId(index);
        driverDto.setDriverName("d" + index);
        driverDto.setDriverDateStartWork(new Date(index).toInstant());
        driverDto.setDriverSalary(BigDecimal.valueOf(100 + index));
        driverDto.setCountOfCarsAssignedToDriver(100 + index);
        return driverDto;
    }
}