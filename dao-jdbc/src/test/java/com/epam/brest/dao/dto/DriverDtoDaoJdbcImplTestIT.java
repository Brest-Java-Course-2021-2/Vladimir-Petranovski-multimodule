package com.epam.brest.dao.dto;

import com.epam.brest.dao_api.DriverDtoDao;
import com.epam.brest.model.dto.DriverDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

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
        List<DriverDto> drivers = driverDtoDaoJdbc.findWithCountCars();
        assertNotNull(drivers);
        assertTrue(drivers.size() > 0);
        log.info("List of driver Dto was created {}", drivers);
        assertTrue(drivers.get(0).getCountOfCarsAssignedToDriver() > 0);
    }
}