package com.epam.brest.dao.dto;

import com.epam.brest.dao_api.DriverDtoDao;
import com.epam.brest.model.Driver;
import com.epam.brest.model.dto.DriverDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.brest.dao.Queries.DRIVER_COUNT_CAR;
import static com.epam.brest.dao.Queries.DRIVER_FIND_DRIVERS_ON_RANGE_DATE;
import static com.epam.brest.logger.ProjectLogger.log;

public class DriverDtoDaoJdbcImpl implements DriverDtoDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DriverDtoDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<DriverDto> findAllDriversWithCountCars() {
        log.info("Method findAllDriversWithCountCars() of class {} started", getClass().getName());
        return namedParameterJdbcTemplate.query(DRIVER_COUNT_CAR, BeanPropertyRowMapper.newInstance(DriverDto.class));
    }

    @Override
    public List<DriverDto> chooseDriverOnDateRange(String fromDate, String toDate) {
        log.info("Method chooseDriverOnDateRange() of class {} started", getClass().getName());
        Map<String, Object> params = new HashMap<>();
        params.put("fromDateChoose", fromDate);
        params.put("toDateChoose", toDate);

        return namedParameterJdbcTemplate.query(DRIVER_FIND_DRIVERS_ON_RANGE_DATE, params, BeanPropertyRowMapper.newInstance(DriverDto.class));
    }
}
