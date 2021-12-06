package com.epam.brest.dao.dto;

import com.epam.brest.dao_api.DriverDtoDao;
import com.epam.brest.model.dto.DriverDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static com.epam.brest.logger.ProjectLogger.log;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-config.xml"})
@Transactional
class DriverDtoDaoJdbcImplTestIT {

    private DriverDtoDaoJdbcImpl driverDtoDaoJdbc;

    public DriverDtoDaoJdbcImplTestIT(@Autowired DriverDtoDao driverDtoDaoJdbc) {
        this.driverDtoDaoJdbc = (DriverDtoDaoJdbcImpl) driverDtoDaoJdbc;
    }

    @Test
    void findWithCountCars() {
        log.info("Method started: findWithCountCars() of {}", getClass().getName());
        List<DriverDto> drivers = driverDtoDaoJdbc.findAllDriversWithCountCars();
        assertNotNull(drivers);
        assertTrue(drivers.size() > 0);
        log.info("List of driver Dto was created {}", drivers);
        assertTrue(drivers.get(0).getCountOfCarsAssignedToDriver() > 0);
    }

    @Test
    void chooseDriverOnDateRange() {
        log.info("Method started: chooseDriverOnDateRange() of {}", getClass().getName());
//        Instant fromDate = Instant.parse("1990-01-02T10:10:10.002Z");
//        Instant toDate = Instant.parse("2021-01-02T10:10:10.002Z");
        String fromDate = "1990-01-02T10:10:10.002Z";
        String toDate = "2021-01-02T10:10:10.002Z";
//        Timestamp fromDate = Timestamp.valueOf("1990-01-02 10:10:10.002");
//        Timestamp toDate = Timestamp.valueOf("2021-01-02 10:10:10.002");
        List<DriverDto> drivers = driverDtoDaoJdbc.chooseDriverOnDateRange(fromDate, toDate);
        assertNotNull(drivers);
        log.info("List of driver Dto was created {}", drivers);
    }
}