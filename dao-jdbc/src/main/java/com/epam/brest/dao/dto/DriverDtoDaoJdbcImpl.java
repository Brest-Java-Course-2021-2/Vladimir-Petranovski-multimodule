package com.epam.brest.dao.dto;

import com.epam.brest.dao_api.dto.DriverDtoDao;
import com.epam.brest.model.dto.DriverDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

import static com.epam.brest.dao.Queries.DRIVER_COUNT_CAR;
import static com.epam.brest.logger.ProjectLogger.LOG;

public class DriverDtoDaoJdbcImpl implements DriverDtoDao {

    /**
     * Field namedParameterJdbcTemplate.
     */

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Constructor
     *
     * @param namedParameterJdbcTemplate namedParameterJdbcTemplate.
     */

    public DriverDtoDaoJdbcImpl(
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Find all drivers Dto.
     *
     * @return list of driver Dto.
     */

    @Override
    public List<DriverDto> findAllDriversWithCountCars() {
        LOG.info("Method findAllDriversWithCountCars() of class {} started",
                getClass().getName());
        return namedParameterJdbcTemplate.query(DRIVER_COUNT_CAR,
                BeanPropertyRowMapper.newInstance(DriverDto.class));
    }
}
