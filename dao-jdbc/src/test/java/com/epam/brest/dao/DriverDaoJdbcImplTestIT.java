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

import static com.epam.brest.logger.ProjectLogger.LOG;
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
        LOG.info("Method started: findAll() of {}", getClass().getName());
        assertNotNull(driverDAO);
        LOG.info("Object {} not null", driverDAO);
        assertNotNull(driverDAO.findAllDrivers());
        LOG.info("{}", driverDAO.findAllDrivers());
    }

    @Test
    void findDriverById() {
        LOG.info("Method started: findDriverById() of {}", getClass().getName());
        assertNotNull(driverDAO);
        List<Driver> drivers = driverDAO.findAllDrivers();
        if (drivers.size() == 0) {
            driverDAO.saveDriver(new Driver("TEST DRIVER", Instant.parse("2001-04-18T00:04:15Z"), new BigDecimal(1000)));
            drivers = driverDAO.findAllDrivers();
        }
        Driver driverSrc = drivers.get(0);
        Driver driverDst = driverDAO.findDriverById(driverSrc.getDriverId());
        assertEquals(driverSrc.getDriverName(), driverDst.getDriverName());
        LOG.info("Driver's name first from list: {} equals driver's name after finding: {}", driverSrc.getDriverName(), driverDst.getDriverName());

    }

    @Test
    void saveDriver() {
        LOG.info("Method started: save() of {}", getClass().getName());
        assertNotNull(driverDAO);
        int driversSizeBefore = driverDAO.count();
        Driver victimDriver = new Driver("VERANICA", Instant.parse("2002-09-15T08:09:12.4342Z"), new BigDecimal(720));
        Integer newDriverId = driverDAO.saveDriver(victimDriver);
        assertNotNull(newDriverId);
        assertEquals(driversSizeBefore, driverDAO.count() - 1);
        LOG.info("Size driver's list before save():{} equals after save minus one {}", driversSizeBefore, newDriverId - 1);
    }

    @Test
    void saveCheckUniqueName() {
        LOG.info("Method started: saveCheckUniqueName() of {}", getClass().getName());
        assertNotNull(driverDAO);
        Driver victimDriver = new Driver("VERANICA", Instant.parse("2002-09-15T08:09:12.4342Z"), new BigDecimal(720));

        assertThrows(IllegalArgumentException.class, () -> {
            driverDAO.saveDriver(victimDriver);
            driverDAO.saveDriver(victimDriver);
        });
        LOG.info("I checked that was called exception {}", IllegalArgumentException.class);
    }

    @Test
    void checkUpdateDriver() {
        LOG.info("Method started: update() of {}", getClass().getName());
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
        LOG.info("Driver's name first from list: {} equals driver's name after updating: {}", driverSrc.getDriverName(), driverDst.getDriverName());
    }

    @Test
    void checkDeleteDriver() {
        LOG.info("Method started: delete() of {}", getClass().getName());
        assertNotNull(driverDAO);
        driverDAO.saveDriver(new Driver("VERANICA", Instant.parse("2002-09-15T08:09:12.4342Z"), new BigDecimal(720)));
        List<Driver> drivers = driverDAO.findAllDrivers();

        driverDAO.deleteDriverById(drivers.get(drivers.size() - 1).getDriverId());
        assertEquals(drivers.size() - 1, driverDAO.findAllDrivers().size());
        LOG.info("First driver's size list minus one: {} equals driver's size list after deleting {}", drivers.size() - 1, driverDAO.findAllDrivers().size());
    }

    @Test
    void shouldCount() {
        LOG.info("Method started: shouldCount() of {}", getClass().getName());
        assertNotNull(driverDAO);
        Integer quantity = driverDAO.count();
        assertNotNull(quantity);
        assertTrue(quantity > 0);
        LOG.info("Quantity equals {}", quantity);
        assertEquals(driverDAO.findAllDrivers().size(), quantity);
    }
}