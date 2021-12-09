package com.epam.brest.service.impl;

import com.epam.brest.model.Driver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static com.epam.brest.logger.ProjectLogger.LOG;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
class DriverServiceImplTestIT {

    @Autowired
    private DriverServiceImpl driverService;

    @Test
    void findAllDrivers() {
        LOG.info("Method started: findAllDrivers() of {}", getClass().getName());
        assertNotNull(driverService);
        assertNotNull(driverService.findAllDrivers());
        LOG.info("{}", driverService.findAllDrivers());
    }

    @Test
    void shouldCount() {
        LOG.info("Method started: shouldCount() of {}", getClass().getName());
        assertNotNull(driverService);
        Integer quantity = driverService.count();
        assertNotNull(quantity);
        assertTrue(quantity > 0);
        LOG.info("Quantity equals {}", quantity);
        assertEquals(Integer.valueOf(3), quantity);
    }

    @Test
    void saveDriver() {
        LOG.info("Method started:saveDriver() of {}", getClass().getName());
        assertNotNull(driverService);
        Integer driversSizeBefore = driverService.count();
        assertNotNull(driversSizeBefore);
        Driver driver = new Driver("VERANICA", Instant.parse("2002-09-15T08:09:12.4342Z"), new BigDecimal(720));
        Integer newDriverId = driverService.saveDriver(driver);
        assertNotNull(newDriverId);
        assertEquals(driversSizeBefore, driverService.count() - 1);
        LOG.info("Size driver's list before save():{} equals after save minus one {}", driversSizeBefore, driverService.count() - 1);

    }

    @Test
    void tryToCreateEqualsDepartments() {
        LOG.info("Method started: tryToCreateEqualsDepartments() of {}", getClass().getName());
        assertNotNull(driverService);
        Driver driver = new Driver("VERANICA", Instant.parse("2002-09-15T08:09:12.4342Z"), new BigDecimal(720));

        assertThrows(IllegalArgumentException.class, () -> {
            driverService.saveDriver(driver);
            driverService.saveDriver(driver);
        });
        LOG.info("I checked that was called exception {}", IllegalArgumentException.class);
    }

    @Test
    void checkFindDriverById() {
        LOG.info("Method started: checkFindDriverById() of {}", getClass().getName());
        assertNotNull(driverService);
        List<Driver> drivers = driverService.findAllDrivers();
        if (drivers.size() == 0) {
            driverService.saveDriver(new Driver("TEST DRIVER", Instant.parse("2001-04-18T00:04:15Z"), new BigDecimal(1000)));
            drivers = driverService.findAllDrivers();
        }
        Driver driverSrc = drivers.get(0);
        Driver driverDst = driverService.findDriverById(driverSrc.getDriverId());
        assertEquals(driverSrc.getDriverName(), driverDst.getDriverName());
        LOG.info("Driver's name first from list: {} equals driver's name after finding: {}", driverSrc.getDriverName(), driverDst.getDriverName());
    }

    @Test
    void checkUpdateDriverById() {
        LOG.info("Method started: checkUpdateDriverById() of {}", getClass().getName());
        assertNotNull(driverService);
        List<Driver> drivers = driverService.findAllDrivers();
        if (drivers.size() == 0) {
            driverService.saveDriver(new Driver("PETIA", Instant.parse("2003-05-01T00:00:01.01Z"), new BigDecimal(790)));
            drivers = driverService.findAllDrivers();
        }

        Driver driverSrc = drivers.get(0);
        driverSrc.setDriverName(driverSrc.getDriverName() + "_TEST");
        driverService.updateDriverById(driverSrc.getDriverId(), driverSrc);

        Driver driverDst = driverService.findDriverById(driverSrc.getDriverId());
        assertEquals(driverSrc.getDriverName(), driverDst.getDriverName());
        LOG.info("Driver's name first from list: {} equals driver's name after updating: {}", driverSrc.getDriverName(), driverDst.getDriverName());
    }

    @Test
    void checkDeleteDriverById() {
        LOG.info("Method started: checkDeleteDriverById() of {}", getClass().getName());
        assertNotNull(driverService);
        driverService.saveDriver(new Driver("VERANICA", Instant.parse("2002-09-15T08:09:12.4342Z"), new BigDecimal(720)));
        List<Driver> drivers = driverService.findAllDrivers();

        driverService.deleteDriverById(drivers.get(drivers.size() - 1).getDriverId());
        assertEquals(drivers.size() - 1, driverService.findAllDrivers().size());
        LOG.info("First driver's size list minus one: {} equals driver's size list after deleting {}", drivers.size() - 1, driverService.findAllDrivers().size());
    }
}