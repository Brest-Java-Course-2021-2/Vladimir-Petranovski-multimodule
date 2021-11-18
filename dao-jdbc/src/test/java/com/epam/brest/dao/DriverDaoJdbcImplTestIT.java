package com.epam.brest.dao;

import com.epam.brest.dao_api.DriverDao;
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

import static com.epam.brest.logger.ProjectLogger.log;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-config.xml"})
@Transactional
class DriverDaoJdbcImplTestIT {

    private final DriverDaoJdbcImpl driverDAO;

    DriverDaoJdbcImplTestIT(@Autowired DriverDao driverDAO) {
        this.driverDAO = (DriverDaoJdbcImpl) driverDAO;
    }

    @Test
    void findAll() {
        log.info("Method started: findAll() of {}", getClass().getName());
        assertNotNull(driverDAO);
        log.info("Object {} not null", driverDAO);
        assertNotNull(driverDAO.findAllDrivers());
        log.info("{}", driverDAO.findAllDrivers());
    }

    @Test
    void findDriverById() {
        log.info("Method started: findDriverById() of {}", getClass().getName());
        assertNotNull(driverDAO);
        List<Driver> drivers = driverDAO.findAllDrivers();
        if (drivers.size() == 0) {
            driverDAO.saveDriver(new Driver("TEST DRIVER", Instant.parse("2001-04-18T00:04:15Z"), new BigDecimal(1000)));
            drivers = driverDAO.findAllDrivers();
        }
        Driver driverSrc = drivers.get(0);
        Driver driverDst = driverDAO.findDriverById(driverSrc.getDriverId());
        assertEquals(driverSrc.getDriverName(), driverDst.getDriverName());
        log.info("Driver's name first from list: {} equals driver's name after finding: {}", driverSrc.getDriverName(), driverDst.getDriverName());

    }

    @Test
    void saveDriver() {
        log.info("Method started: save() of {}", getClass().getName());
        assertNotNull(driverDAO);
        int driversSizeBefore = driverDAO.count();
        Driver victimDriver = new Driver("VERANICA", Instant.parse("2002-09-15T08:09:12.4342Z"), new BigDecimal(720));
        driverDAO.saveDriver(victimDriver);
        Integer driversSizeAfterSave = driverDAO.count();
        assertNotNull(driversSizeAfterSave);
        assertEquals(driversSizeBefore, driverDAO.count() - 1);
        log.info("Size driver's list before save():{} equals after save minus one {}", driversSizeBefore, driversSizeAfterSave - 1);
    }

    @Test
    void saveCheckUniqueName() {
        log.info("Method started: saveCheckUniqueName() of {}", getClass().getName());
        assertNotNull(driverDAO);
        Driver victimDriver = new Driver("VERANICA", Instant.parse("2002-09-15T08:09:12.4342Z"), new BigDecimal(720));

        assertThrows(IllegalArgumentException.class, () -> {
            driverDAO.saveDriver(victimDriver);
            driverDAO.saveDriver(victimDriver);
        });
        log.info("I checked that was called exception {}", IllegalArgumentException.class);
    }

    @Test
    void checkUpdateDriver() {
        log.info("Method started: update() of {}", getClass().getName());
        assertNotNull(driverDAO);
        List<Driver> drivers = driverDAO.findAllDrivers();
        if (drivers.size() == 0) {
            driverDAO.saveDriver(new Driver("PETIA", Instant.parse("2003-05-01T00:00:01.01Z"), new BigDecimal(790)));
            drivers = driverDAO.findAllDrivers();
        }

        Driver driverSrc = drivers.get(0);
        driverSrc.setDriverName(driverSrc.getDriverName() + "_TEST");
        driverDAO.updateDriverById(driverSrc.getDriverId(), driverSrc);

        Driver driverDst = driverDAO.findDriverById(driverSrc.getDriverId());
        assertEquals(driverSrc.getDriverName(), driverDst.getDriverName());
        log.info("Driver's name first from list: {} equals driver's name after updating: {}", driverSrc.getDriverName(), driverDst.getDriverName());
    }

    @Test
    void checkDeleteDriver() {
        log.info("Method started: delete() of {}", getClass().getName());
        assertNotNull(driverDAO);
        driverDAO.saveDriver(new Driver("VERANICA", Instant.parse("2002-09-15T08:09:12.4342Z"), new BigDecimal(720)));
        List<Driver> drivers = driverDAO.findAllDrivers();

        driverDAO.deleteDriverById(drivers.get(drivers.size() - 1).getDriverId());
        assertEquals(drivers.size() - 1, driverDAO.findAllDrivers().size());
        log.info("First driver's size list minus one: {} equals driver's size list after deleting {}", drivers.size() - 1, driverDAO.findAllDrivers().size());
    }

    @Test
    void shouldCount() {
        log.info("Method started: shouldCount() of {}", getClass().getName());
        assertNotNull(driverDAO);
        Integer quantity = driverDAO.count();
        assertNotNull(quantity);
        assertTrue(quantity > 0);
        log.info("Quantity equals {}", quantity);
        assertEquals(Integer.valueOf(3), quantity);
    }
}