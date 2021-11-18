package com.epam.brest.service.impl;

import com.epam.brest.model.Driver;
import com.epam.brest.service.impl.dto.DriverDtoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

import static com.epam.brest.logger.ProjectLogger.log;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
class DriverServiceImplTestIT {

    @Autowired
    private DriverServiceImpl driverService;

    @Test
    void findAllDrivers() {
        log.info("Method started: findAllDrivers() of {}", getClass().getName());
    }

    @Test
    void shouldCount() {
        log.info("Method started: shouldCount() of {}", getClass().getName());
        assertNotNull(driverService);
        Integer quantity = driverService.count();
        assertNotNull(quantity);
        assertTrue(quantity > 0);
        log.info("Quantity equals {}", quantity);
        assertEquals(Integer.valueOf(3), quantity);
    }

    @Test
    void saveDriver() {
        log.info("Method started:saveDriver() of {}", getClass().getName());
        assertNotNull(driverService);
        Integer driversSizeBefore = driverService.count();
        assertNotNull(driversSizeBefore);
        Driver driver = new Driver("VERANICA", Instant.parse("2002-09-15T08:09:12.4342Z"), new BigDecimal(720));
        driverService.saveDriver(driver);
        Integer newDriverId = driverService.count();
        assertNotNull(newDriverId);
        assertEquals(driversSizeBefore, driverService.count() - 1);
    }

    @Test
    void tryToCreateEqualsDepartments() {
        log.info("Method started: tryToCreateEqualsDepartments() of {}", getClass().getName());
        assertNotNull(driverService);
        Driver driver = new Driver("VERANICA", Instant.parse("2002-09-15T08:09:12.4342Z"), new BigDecimal(720));

        assertThrows(IllegalArgumentException.class, () -> {
            driverService.saveDriver(driver);
            driverService.saveDriver(driver);
        });
    }
}