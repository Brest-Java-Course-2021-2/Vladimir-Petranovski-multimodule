package com.epam.brest.dao;

import com.epam.brest.dao_api.DaoJdbcRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.epam.brest.logger.ProjectLogger.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-config.xml"})
class DriverDaoJdbcImplTest {

    private DriverDaoJdbcJdbcImpl driverDAO;

    DriverDaoJdbcImplTest(@Autowired DaoJdbcRepository driverDAO) {
        this.driverDAO = (DriverDaoJdbcJdbcImpl) driverDAO;
    }

    @Test
    void findAll() {
        assertNotNull(driverDAO);
        log.info("Object {} not null", driverDAO);
        assertNotNull(driverDAO.findAll());
        log.info("Size collection equals: {}", driverDAO.findAll().size());
    }
}