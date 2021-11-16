package com.epam.brest.dao.dto;

import com.epam.brest.dao.rowMappers.DriverDtoDaoJdbcRowMapper;
import com.epam.brest.dao_api.DriverDtoDao;
import com.epam.brest.model.dto.DriverDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

import static com.epam.brest.dao.Queries.DRIVER_COUNT_CAR;

@Component
public class DriverDtoDaoJdbcImpl implements DriverDtoDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DriverDtoDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<DriverDto> findAllDriverWithCountCars() {
        return namedParameterJdbcTemplate.query(DRIVER_COUNT_CAR, BeanPropertyRowMapper.newInstance(DriverDto.class));
    }
}
