package com.epam.brest.dao;

import com.epam.brest.model.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.springframework.jdbc.support.KeyHolder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.epam.brest.dao.Queries.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DriverDaoJdbcImplTest {

    public static final Logger LOG = LogManager.getLogger(DriverDaoJdbcImplTest.class);

    @InjectMocks
    private DriverDaoJdbcImpl driverDaoJdbc;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Captor
    private ArgumentCaptor<RowMapper<Driver>> captorRowMapper;

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
        LOG.info("Method findAllDrivers() of class {} started", getClass().getName());

        when(namedParameterJdbcTemplate.query(any(), ArgumentMatchers.<RowMapper<Driver>>any())).thenReturn(driversListSrc);

        List<Driver> driversDst = driverDaoJdbc.findAllDrivers();

        verify(namedParameterJdbcTemplate).query(eq(DRIVER_FIND_ALL), captorRowMapper.capture());

        RowMapper<Driver> mapper = captorRowMapper.getValue();
        assertNotNull(mapper);

        assertNotNull(driversListSrc);
        assertFalse(driversListSrc.isEmpty());
        assertSame(driverTwo, driversDst.get(1));
        assertSame(driversDst.size(), driversListSrc.size());
        LOG.info("Size driver's list after findAllDrivers() {} the same before {}", driversDst.size(), driversListSrc.size());
    }

    @Test
    void findDriverById() {
        LOG.info("Method findDriverById() of class {} started", getClass().getName());

        when(namedParameterJdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<SqlParameterSource>any(), ArgumentMatchers.<RowMapper<Driver>>any())).thenReturn(driverOne);

        Driver driver = driverDaoJdbc.findDriverById(driverOne.getDriverId());

        verify(namedParameterJdbcTemplate).queryForObject(eq(DRIVER_FIND_BY_ID), sqlParameterSourceArgumentCaptor.capture(), captorRowMapper.capture());
        RowMapper<Driver> rowMapper = captorRowMapper.getValue();
        SqlParameterSource sqlParameterSource = sqlParameterSourceArgumentCaptor.getValue();

        assertNotNull(rowMapper);
        assertNotNull(sqlParameterSource);
        assertNotNull(driver);
        assertEquals(driver, driverOne);
        LOG.info("Driver after findDriverById() {} with id id = {} equals driver {}", driver, driverOne.getDriverId(), driverOne);
    }

    @Test
    void saveDriver() {
        LOG.info("Method saveDriver() of class {} started", getClass().getName());
        int key = 3;
        when(namedParameterJdbcTemplate.queryForObject(any(), any(SqlParameterSource.class), eq(Integer.class))).thenReturn(0);
        Mockito.doAnswer(a -> {
            KeyHolder keyHolder = a.getArgument(2);
            keyHolder.getKeyList().add(Map.of("", key));
            return null;
        }).when(namedParameterJdbcTemplate).update(any(),any(),any());
        Integer result = driverDaoJdbc.saveDriver(driverTest);
        assertEquals(key, result);
        verify(namedParameterJdbcTemplate).queryForObject(eq(DRIVER_CHECK_UNIQUE_NAME), sqlParameterSourceArgumentCaptor.capture(), eq(Integer.class));
        verify(namedParameterJdbcTemplate, times(1)).update(eq(DRIVER_SAVE), sqlParameterSourceArgumentCaptor.capture(), any(KeyHolder.class));
        verify(namedParameterJdbcTemplate).update(eq(DRIVER_SAVE), sqlParameterSourceArgumentCaptor.capture(), any(KeyHolder.class));
        List<SqlParameterSource> sqlParameterSources = sqlParameterSourceArgumentCaptor.getAllValues();
        assertEquals(3, sqlParameterSources.size());
    }

    @Test
    void updateDriverById() {
        LOG.info("Method updateDriverById() of class {} started", getClass().getName());

        when(namedParameterJdbcTemplate.update(anyString(), ArgumentMatchers.<SqlParameterSource>any())).thenReturn(returnRow);

        driverDaoJdbc.updateDriverById(driverThree.getDriverId(), driverThree);

        verify(namedParameterJdbcTemplate, times(1)).update(eq(DRIVER_UPDATE_BY_ID), sqlParameterSourceArgumentCaptor.capture());
        verify(namedParameterJdbcTemplate).update(eq(DRIVER_UPDATE_BY_ID), sqlParameterSourceArgumentCaptor.capture());
        SqlParameterSource sqlParameterSource = sqlParameterSourceArgumentCaptor.getValue();

        assertNotNull(sqlParameterSource);
    }

    @Test
    void deleteDriverById() {
        LOG.info("Method deleteDriverById() of class {} started", getClass().getName());

        when(namedParameterJdbcTemplate.update(anyString(), ArgumentMatchers.<SqlParameterSource>any())).thenReturn(1);

        driverDaoJdbc.deleteDriverById(driverThree.getDriverId());

        verify(namedParameterJdbcTemplate, times(1)).update(eq(DRIVER_DELETE_BY_ID), sqlParameterSourceArgumentCaptor.capture());
        verify(namedParameterJdbcTemplate).update(eq(DRIVER_DELETE_BY_ID), sqlParameterSourceArgumentCaptor.capture());
        SqlParameterSource sqlParameterSource = sqlParameterSourceArgumentCaptor.getValue();

        assertNotNull(sqlParameterSource);
    }

    @Test
    void count() {
        LOG.info("Method count() of class {} started", getClass().getName());

        when(namedParameterJdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<SqlParameterSource>any(), eq(Integer.class))).thenReturn(driversListSrc.size());

        Integer count = driverDaoJdbc.count();

        verify(namedParameterJdbcTemplate).queryForObject(eq(DRIVER_COUNT), sqlParameterSourceArgumentCaptor.capture(), eq(Integer.class));

        SqlParameterSource sqlParameterSource = sqlParameterSourceArgumentCaptor.getValue();

        assertNotNull(sqlParameterSource);
        assertEquals(count, driversListSrc.size());
        LOG.info("Quantity records in list: {} equal size driver's list: {}", count, driversListSrc.size());
    }
}