package com.epam.brest.dao;

import com.epam.brest.dao.rowMappers.CarDaoJdbcRowMapper;
import com.epam.brest.dao_api.CarDao;
import com.epam.brest.model.Car;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.brest.dao.Queries.*;
import static com.epam.brest.logger.ProjectLogger.log;

@Component
public class CarDaoJdbcImpl implements CarDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CarDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Car> findAllCars() {
        log.info("Method findAllCars() of class {} started", getClass().getName());
        return namedParameterJdbcTemplate.query(CAR_FIND_ALL, new CarDaoJdbcRowMapper());
    }

    @Override
    public Car findCarById(Integer id) {
        log.info("Method findCarById(with id={}) of class {} started", id, getClass().getName());
        Map<String, Integer> param = new HashMap<>();
        param.put("carId", id);
        return namedParameterJdbcTemplate.queryForObject(CAR_FIND_BY_ID, param, new CarDaoJdbcRowMapper());
    }

    @Override
    public Integer saveCar(Car car) {
        log.info("Method saveCar(with car {}) of class {} started", car, getClass().getName());

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource()
                        .addValue("carModel", car.getCarModel())
                        .addValue( "driverId", car.getDriverId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(CAR_SAVE, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer updateCarById(Integer id, Car car) {
        log.info("Method updateCarById(with id={}, car {}) of class {} started", id, car, getClass().getName());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("carId", id)
                .addValue("carModel", car.getCarModel())
                .addValue("driverId", car.getDriverId());
        return namedParameterJdbcTemplate.update(CAR_UPDATE_BY_ID, sqlParameterSource);
    }

    @Override
    public Integer deleteCarById(Integer id) {
        log.info("Method deleteCarById(with id={}) of class {} started", id, getClass().getName());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("carId", id);
        return namedParameterJdbcTemplate.update(CAR_DELETE_BY_ID, sqlParameterSource);
    }

    @Override
    public Integer count() {
        log.info("Method count() of class {} started", getClass().getName());
        return namedParameterJdbcTemplate.queryForObject(CAR_COUNT, Collections.emptyMap(), Integer.class);
    }
}
