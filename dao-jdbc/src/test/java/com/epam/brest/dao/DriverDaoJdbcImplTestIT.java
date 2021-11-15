package com.epam.brest.dao;

import com.epam.brest.dao_api.DaoJdbcRepository;
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

    private DriverDaoJdbcImpl driverDAO;

    DriverDaoJdbcImplTestIT(@Autowired DaoJdbcRepository<Driver> driverDAO) {
        this.driverDAO = (DriverDaoJdbcImpl) driverDAO;
    }

    @Test
    void findAll() {
        log.info("Method started: findAll() of {}", getClass().getName());
        assertNotNull(driverDAO);
        log.info("Object {} not null", driverDAO);
        assertNotNull(driverDAO.findAll());
        log.info("{}", driverDAO.findAll());
    }

    @Test
    void findDriverById() {
        log.info("Method started: findDriverById() of {}", getClass().getName());
        assertNotNull(driverDAO);
        Integer victim_id = 3;
        Driver victimDriver = new Driver(3, "VITALIY", Instant.parse("2005-04-28T10:44:50.532700Z"), new BigDecimal(650));
        assertEquals(victimDriver, driverDAO.findById(victim_id));
        log.info("VictimDriver {} equals {}", victimDriver, driverDAO.findById(victim_id));
    }

    @Test
    void save() {
        log.info("Method started: save() of {}", getClass().getName());
        assertNotNull(driverDAO);
        Integer driverListBeforeSave = driverDAO.findAll().size();
        Driver victimDriver = new Driver("VERANICA", Instant.parse("2002-09-15T08:09:12.4342Z"), new BigDecimal(720));
        driverDAO.save(victimDriver);
        Integer driverListAfterSave = driverDAO.findAll().size();
        assertTrue(driverListBeforeSave < driverListAfterSave);
        log.info("Driver {} was saved {}", victimDriver, driverDAO.findAll());
    }

    @Test
    void saveCheckUniqueName() {
        log.info("Method started: saveCheckUniqueName() of {}", getClass().getName());
        assertNotNull(driverDAO);
        Driver victimDriver = new Driver("VERANICA", Instant.parse("2002-09-15T08:09:12.4342Z"), new BigDecimal(720));

        assertThrows(IllegalArgumentException.class, () -> {
            driverDAO.save(victimDriver);
            driverDAO.save(victimDriver);
        });
        log.info("I checked that was called exception {}", IllegalArgumentException.class);
    }

    @Test
    void update() {
        log.info("Method started: update() of {}", getClass().getName());
        assertNotNull(driverDAO);
        Integer driverListBeforeUpdate = driverDAO.findAll().size();

        Integer updateId = 2;
        Driver driverToUpdate = driverDAO.findById(updateId);

        Driver victimDriver = new Driver("PETIA", Instant.parse("2003-05-01T00:00:01.01Z"), new BigDecimal(790));
        driverDAO.update(updateId, victimDriver);
        assertNotEquals(driverToUpdate, driverDAO.findById(updateId));
        log.info("Driver before updating {} not equals driver after updating {}", driverToUpdate, driverDAO.findById(updateId));

        Integer driverListAfterUpdate = driverDAO.findAll().size();
        assertEquals(driverListBeforeUpdate, driverListAfterUpdate);
    }

    @Test
    void delete() {
        log.info("Method started: delete() of {}", getClass().getName());
        assertNotNull(driverDAO);
        Integer driverListBeforeDelete = driverDAO.findAll().size();
        Integer victim_id = 1;
        driverDAO.delete(victim_id);
        Integer driverListAfterDelete = driverDAO.findAll().size();
        assertTrue(driverListBeforeDelete > driverListAfterDelete);
        log.info("Driver with id equals {} was deleted {}", victim_id, driverDAO.findAll());
    }

    @Test
    void findNameDriverById() {
        log.info("Method started: findNameDriverById() of {}", getClass().getName());
        assertNotNull(driverDAO);
        String expectedName = "VITALIY";
        Integer expectedId = 3;
        assertEquals(expectedName, driverDAO.findNameDriverById(expectedId));
        log.info("ExpectedName {} equals name from method findNameDriverById() --- {}", expectedName, driverDAO.findNameDriverById(expectedId));
    }

    @Test
    void findAllNameDrivers() {
        log.info("Method started: findAllNameDrivers() of {}", getClass().getName());
        assertNotNull(driverDAO);
        List<String> expectedListName = List.of("VASIA", "VITALIY", "VOVA");
        assertEquals(expectedListName, driverDAO.findAllNameDrivers());
        log.info("Expected list of name {} equals list of name from method findAllNameDrivers() --- {}", expectedListName, driverDAO.findAllNameDrivers());
    }
}