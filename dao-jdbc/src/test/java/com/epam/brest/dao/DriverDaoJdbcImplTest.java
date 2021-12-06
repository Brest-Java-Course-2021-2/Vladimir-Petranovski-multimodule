package com.epam.brest.dao;

import com.epam.brest.model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.epam.brest.dao.Queries.*;
import static com.epam.brest.logger.ProjectLogger.log;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DriverDaoJdbcImplTest {

    @InjectMocks
    private DriverDaoJdbcImpl driverDaoJdbc;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Captor
    private ArgumentCaptor<RowMapper<Driver>> captorRowMapper;

    @Captor
    private ArgumentCaptor<Map<String, Object>> mapArgumentCaptor;

    @Captor
    private ArgumentCaptor<SqlParameterSource> sqlParameterSourceArgumentCaptor;

    private Driver driverOne;
    private Driver driverTwo;
    private Driver driverThree;
    private Driver driverTest;

    private final static int returnRow = 1;


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

        when(namedParameterJdbcTemplate.query(any(), ArgumentMatchers.<RowMapper<Driver>>any())).thenReturn(driversListSrc);

        List<Driver> driversDst = driverDaoJdbc.findAllDrivers();

        verify(namedParameterJdbcTemplate).query(eq(DRIVER_FIND_ALL), captorRowMapper.capture());

        RowMapper<Driver> mapper = captorRowMapper.getValue();
        assertNotNull(mapper);

        assertNotNull(driversListSrc);
        assertFalse(driversListSrc.isEmpty());
        assertSame(driverTwo, driversDst.get(1));
        assertSame(driversDst.size(), driversListSrc.size());
        log.info("Size driver's list after findAllDrivers() {} the same before {}", driversDst.size(), driversListSrc.size());
    }

    @Test
    void findDriverById() {
        log.info("Method findDriverById() of class {} started", getClass().getName());

        when(namedParameterJdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<Map<String, Integer>>any(), ArgumentMatchers.<RowMapper<Driver>>any())).thenReturn(driverOne);

        Driver driver = driverDaoJdbc.findDriverById(driverOne.getDriverId());

        verify(namedParameterJdbcTemplate).queryForObject(eq(DRIVER_FIND_BY_ID), mapArgumentCaptor.capture(), captorRowMapper.capture());
        RowMapper<Driver> rowMapper = captorRowMapper.getValue();
        Map<String, Object> map = mapArgumentCaptor.getValue();

        assertNotNull(rowMapper);
        assertNotNull(map);
        assertNotNull(driver);
        assertEquals(driver, driverOne);
        log.info("Driver after findDriverById() {} with id id = {} equals driver {}", driver, driverOne.getDriverId(), driverOne);
    }

    @Test
    void saveDriver() {
        log.info("Method saveDriver() of class {} started", getClass().getName());

        when(namedParameterJdbcTemplate.update(anyString(), ArgumentMatchers.<Map<String, Object>>any())).thenReturn(returnRow);

        Integer quantity = driverDaoJdbc.saveDriver(driverTest);
        assertNotNull(quantity);

        verify(namedParameterJdbcTemplate, times(1)).update(eq(DRIVER_SAVE), mapArgumentCaptor.capture());
        verify(namedParameterJdbcTemplate).update(eq(DRIVER_SAVE), mapArgumentCaptor.capture());
        Map<String, Object> map = mapArgumentCaptor.getValue();

        assertNotNull(map);
    }

    @Test
    void updateDriverById() {
        log.info("Method updateDriverById() of class {} started", getClass().getName());

        when(namedParameterJdbcTemplate.update(anyString(), ArgumentMatchers.<Map<String, Object>>any())).thenReturn(returnRow);

        driverDaoJdbc.updateDriverById(driverThree.getDriverId(), driverThree);

        verify(namedParameterJdbcTemplate, times(1)).update(eq(DRIVER_UPDATE_BY_ID), mapArgumentCaptor.capture());
        verify(namedParameterJdbcTemplate).update(eq(DRIVER_UPDATE_BY_ID), mapArgumentCaptor.capture());
        Map<String, Object> map = mapArgumentCaptor.getValue();

        assertNotNull(map);
    }

    @Test
    void deleteDriverById() {
        log.info("Method deleteDriverById() of class {} started", getClass().getName());

        when(namedParameterJdbcTemplate.update(anyString(), ArgumentMatchers.<Map<String, Object>>any())).thenReturn(returnRow);

        driverDaoJdbc.deleteDriverById(driverThree.getDriverId());

        verify(namedParameterJdbcTemplate, times(1)).update(eq(DRIVER_DELETE_BY_ID), mapArgumentCaptor.capture());
        verify(namedParameterJdbcTemplate).update(eq(DRIVER_DELETE_BY_ID), mapArgumentCaptor.capture());
        Map<String, Object> map = mapArgumentCaptor.getValue();

        assertNotNull(map);
    }

    @Test
    void count() {
        log.info("Method count() of class {} started", getClass().getName());

        when(namedParameterJdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<SqlParameterSource>any(), eq(Integer.class))).thenReturn(driversListSrc.size());

        Integer count = driverDaoJdbc.count();

        verify(namedParameterJdbcTemplate).queryForObject(eq(DRIVER_COUNT), sqlParameterSourceArgumentCaptor.capture(), eq(Integer.class));

        SqlParameterSource sqlParameterSource = sqlParameterSourceArgumentCaptor.getValue();

        assertNotNull(sqlParameterSource);
        assertEquals(count, driversListSrc.size());
        log.info("Quantity records in list: {} equal size driver's list: {}", count, driversListSrc.size());
    }
}