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

import static com.epam.brest.logger.ProjectLogger.log;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-config.xml"})
@Transactional
class DriverDaoJdbcImplTestIT {

    private DriverDaoJdbcImpl driverDAO;

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
        Integer victim_id = 3;
        Driver victimDriver = new Driver(3, "VITALIY", Instant.parse("2005-04-28T10:44:50.532700Z"), new BigDecimal(650));
        assertEquals(victimDriver, driverDAO.findDriverById(victim_id));
        log.info("VictimDriver {} equals {}", victimDriver, driverDAO.findDriverById(victim_id));
    }

    @Test
    void save() {
        log.info("Method started: save() of {}", getClass().getName());
        assertNotNull(driverDAO);
        Integer driverListBeforeSave = driverDAO.findAllDrivers().size();
        Driver victimDriver = new Driver("VERANICA", Instant.parse("2002-09-15T08:09:12.4342Z"), new BigDecimal(720));
        driverDAO.saveDriver(victimDriver);
        Integer driverListAfterSave = driverDAO.findAllDrivers().size();
        assertTrue(driverListBeforeSave < driverListAfterSave);
        log.info("Driver {} was saved {}", victimDriver, driverDAO.findAllDrivers());
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
        Integer driverListBeforeUpdate = driverDAO.findAllDrivers().size();

        Integer updateId = 2;
        Driver driverToUpdate = driverDAO.findDriverById(updateId);

        Driver victimDriver = new Driver("PETIA", Instant.parse("2003-05-01T00:00:01.01Z"), new BigDecimal(790));
        driverDAO.updateDriverById(updateId, victimDriver);
        assertNotEquals(driverToUpdate, driverDAO.findDriverById(updateId));
        log.info("Driver before updating {} not equals driver after updating {}", driverToUpdate, driverDAO.findDriverById(updateId));

        Integer driverListAfterUpdate = driverDAO.findAllDrivers().size();
        assertEquals(driverListBeforeUpdate, driverListAfterUpdate);
    }

    @Test
    void checkDeleteDriver() {
        log.info("Method started: delete() of {}", getClass().getName());
        assertNotNull(driverDAO);
        Integer driverListBeforeDelete = driverDAO.findAllDrivers().size();
        Integer victim_id = 1;
        driverDAO.deleteDriverById(victim_id);
        Integer driverListAfterDelete = driverDAO.findAllDrivers().size();
        assertTrue(driverListBeforeDelete > driverListAfterDelete);
        log.info("Driver with id equals {} was deleted {}", victim_id, driverDAO.findAllDrivers());
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

//    @Test
//    void findNameDriverById() {
//        log.info("Method started: findNameDriverById() of {}", getClass().getName());
//        assertNotNull(driverDAO);
//        String expectedName = "VITALIY";
//        Integer expectedId = 3;
//        assertEquals(expectedName, driverDAO.findNameDriverById(expectedId));
//        log.info("ExpectedName {} equals name from method findNameDriverById() --- {}", expectedName, driverDAO.findNameDriverById(expectedId));
//    }

//    @Test
//    void findAllNameDrivers() {
//        log.info("Method started: findAllNameDrivers() of {}", getClass().getName());
//        assertNotNull(driverDAO);
//        List<String> expectedListName = List.of("VASIA", "VITALIY", "VOVA");
//        assertEquals(expectedListName, driverDAO.findAllNameDrivers());
//        log.info("Expected list of name {} equals list of name from method findAllNameDrivers() --- {}", expectedListName, driverDAO.findAllNameDrivers());
//    }
}