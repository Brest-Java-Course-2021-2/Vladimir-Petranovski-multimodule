package com.epam.brest.dao.dto;

import com.epam.brest.dao_api.dto.DriverDtoDao;
import com.epam.brest.model.dto.DriverDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

import static com.epam.brest.dao.Queries.DRIVER_COUNT_CAR;
import static com.epam.brest.logger.ProjectLogger.log;

public class DriverDtoDaoJdbcImpl implements DriverDtoDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DriverDtoDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Find all drivers Dto.
     *
     * @return list of driver Dto.
     */

    @Override
    public List<DriverDto> findAllDriversWithCountCars() {
        log.info("Method findAllDriversWithCountCars() of class {} started", getClass().getName());
        return namedParameterJdbcTemplate.query(DRIVER_COUNT_CAR, BeanPropertyRowMapper.newInstance(DriverDto.class));
    }
}
