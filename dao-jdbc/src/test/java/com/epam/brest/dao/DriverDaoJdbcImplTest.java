package com.epam.brest.dao;

import com.epam.brest.dao.rowMappers.DriverDaoJdbcRowMapper;
import com.epam.brest.model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.epam.brest.logger.ProjectLogger.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
class DriverDaoJdbcImplTest {

    @InjectMocks
    private DriverDaoJdbcImpl driverDaoJdbc;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Captor
    private ArgumentCaptor<RowMapper<Driver>> captor;

    private Driver driverOne;
    private Driver driverTwo;
    private Driver driverThree;

    private List<Driver> driversSrc;

    @BeforeEach
    void setUp() {
        driverOne = new Driver(1, "VASIA", Instant.parse("1998-10-01T12:02:01.8472Z"), new BigDecimal(500));
        driverTwo = new Driver(2, "VOVA", Instant.parse("2010-10-11T08:30:30.1234Z"), new BigDecimal(850));
        driverThree = new Driver(3, "VITALIY", Instant.parse("2005-04-28T14:44:50.5327Z"), new BigDecimal(650));

        driversSrc = new ArrayList<>();
        driversSrc.add(driverOne);
        driversSrc.add(driverTwo);
        driversSrc.add(driverThree);
    }

    @Test
    void findAllDrivers() {
        log.info("Method findAllDrivers() of class {} started", getClass().getName());

        String sql = "SELECT * FROM driver";

        when(namedParameterJdbcTemplate.query(any(), ArgumentMatchers.<RowMapper<Driver>>any())).thenReturn(driversSrc);

        verify(namedParameterJdbcTemplate).query(eq(sql), captor.capture());

        RowMapper<Driver> mapper = captor.getValue();
        assertNotNull(mapper);

        List<Driver> driversDst = driverDaoJdbc.findAllDrivers();

        assertNotNull(driversSrc);
        assertFalse(driversSrc.isEmpty());
        assertSame(driverTwo, driversDst.get(1));
        assertSame(driversDst.size(), driversSrc.size());
        log.info("Size driver's list after findAllDrivers() {} the same before {}", driversDst.size(), driversSrc.size());
    }

    @Test
    void findDriverById() {
        log.info("Method findDriverById() of class {} started", getClass().getName());

        when(namedParameterJdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<Map<String, Integer>>any(), ArgumentMatchers.<RowMapper<Driver>>any())).thenReturn(driverOne);

        Driver driver = driverDaoJdbc.findDriverById(driverOne.getDriverId());

        assertNotNull(driver);
        assertEquals(driver, driverOne);
        log.info("Driver after findDriverById() {} with id id = {} equals driver {}", driver, driverOne.getDriverId(), driverOne);
    }

    @Test
    void saveDriver() {
        log.info("Method saveDriver() of class {} started", getClass().getName());
    }

    @Test
    void updateDriverById() {
        log.info("Method updateDriverById() of class {} started", getClass().getName());
    }

    @Test
    void deleteDriverById() {
        log.info("Method deleteDriverById() of class {} started", getClass().getName());
    }

    @Test
    void count() {
        log.info("Method count() of class {} started", getClass().getName());
    }
}