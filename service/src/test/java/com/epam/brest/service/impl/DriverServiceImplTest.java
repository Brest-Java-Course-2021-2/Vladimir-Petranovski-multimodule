package com.epam.brest.service.impl;

import com.epam.brest.dao_api.DriverDao;
import com.epam.brest.model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.epam.brest.logger.ProjectLogger.log;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceImplTest {

    @InjectMocks
    private DriverServiceImpl driverService;

    @Mock
    private DriverDao driverDao;

    private Driver driverOne;
    private Driver driverTwo;
    private Driver driverThree;
    private Driver driverTest;

    private List<Driver> driversListSrc;

    @BeforeEach
    void setUp() {
        driverOne = new Driver(1, "VASIA", Instant.parse("1998-10-01T12:02:01.8472Z"), new BigDecimal(500));
        driverTwo = new Driver(2, "VOVA", Instant.parse("2010-10-11T08:30:30.1234Z"), new BigDecimal(850));
        driverThree = new Driver(3, "VITALIY", Instant.parse("2005-04-28T14:44:50.5327Z"), new BigDecimal(650));
        driverTest = new Driver(4, "SACHA", Instant.parse("2012-09-28T14:44:50.5327Z"), new BigDecimal(320));

        driversListSrc = new ArrayList<>();
        driversListSrc.add(driverOne);
        driversListSrc.add(driverTwo);
        driversListSrc.add(driverThree);
    }

    @Test
    void findAllDrivers() {
        log.info("Method findAllDrivers() of class {} started", getClass().getName());

        when(driverDao.findAllDrivers()).thenReturn(driversListSrc);

        List<Driver> driversDst = driverService.findAllDrivers();

        verify(driverDao, times(1)).findAllDrivers();

        assertNotNull(driversListSrc);
        assertFalse(driversListSrc.isEmpty());
        assertSame(driverTwo, driversDst.get(1));
        assertSame(driversDst.size(), driversListSrc.size());
        log.info("Size driver's list after findAllDrivers() {} the same before {}", driversDst.size(), driversListSrc.size());
    }

    @Test
    void findDriverById() {

        Integer id = driverOne.getDriverId();
        log.info("Method findDriverById() of class {} started", getClass().getName());

        when(driverDao.findDriverById(anyInt())).thenReturn(driverOne);

        Driver driverDst = driverService.findDriverById(id);

        verify(driverDao, times(1)).findDriverById(eq(id));

        assertNotNull(driverDst);
        assertEquals(driverDst, driverOne);
        log.info("Driver after findDriverById() {} with id id = {} equals driver {}", driverDst, driverOne.getDriverId(), driverOne);
    }

    @Test
    void saveDriver() {
        log.info("Method saveDriver() of class {} started", getClass().getName());

        when(driverDao.saveDriver(any(Driver.class))).thenReturn(anyInt());

        driverService.saveDriver(driverTest);

        verify(driverDao, times(1)).saveDriver(eq(driverTest));

        assertNotNull(driverDao);
        assertNotNull(driverService);
    }

    @Test
    void updateDriverById() {
        log.info("Method updateDriverById() of class {} started", getClass().getName());

        when(driverDao.updateDriverById(anyInt(), any(Driver.class))).thenReturn(1);

        driverService.updateDriverById(driverTwo.getDriverId(), driverTwo);

        verify(driverDao, times(1)).updateDriverById(eq(driverTwo.getDriverId()), eq(driverTwo));

        assertNotNull(driverDao);
        assertNotNull(driverService);
    }

    @Test
    void deleteDriverById() {
        log.info("Method deleteDriverById() of class {} started", getClass().getName());

        when(driverDao.deleteDriverById(anyInt())).thenReturn(anyInt());

        driverService.deleteDriverById(driverThree.getDriverId());

        verify(driverDao).deleteDriverById(eq(driverThree.getDriverId()));

        assertNotNull(driverDao);
        assertNotNull(driverService);
    }

    @Test
    void count() {
        log.info("Method count() of class {} started", getClass().getName());

        when(driverDao.count()).thenReturn(driversListSrc.size());

        Integer count = driverService.count();

        verify(driverDao, times(1)).count();

        assertNotNull(driverDao);
        assertNotNull(driverService);
        assertEquals(count, driversListSrc.size());
        log.info("Quantity records in list: {} equal size driver's list: {}", count, driversListSrc.size());
    }
}