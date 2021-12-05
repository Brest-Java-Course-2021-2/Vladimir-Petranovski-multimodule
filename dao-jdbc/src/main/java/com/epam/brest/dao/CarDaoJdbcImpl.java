package com.epam.brest.dao;

import com.epam.brest.dao.rowMappers.CarDaoJdbcRowMapper;
import com.epam.brest.dao_api.CarDao;
import com.epam.brest.model.Car;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
//        Map<String, Object> params = new HashMap<>();
//        params.put("carModel", car.getCarModel());
//        params.put("driverId", car.getDriverId());
//        namedParameterJdbcTemplate.execute(CAR_SAVE, params, new PreparedStatementCallback<Integer>() {
//            @Override
//            public Integer doInPreparedStatement(PreparedStatement ps)
//                    throws SQLException, DataAccessException {
//                return ps.executeUpdate();
//            }
//        });
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource()
                        .addValue("carModel", car.getCarModel())
                        .addValue( "driverId", car.getDriverId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(CAR_SAVE, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public void updateCarById(Integer id, Car car) {
        log.info("Method updateCarById(with id={}, car {}) of class {} started", id, car, getClass().getName());
        Map<String, Object> params = new HashMap<>();
        params.put("carId", id);
        params.put("carModel", car.getCarModel());
        params.put("driverId", car.getDriverId());
        namedParameterJdbcTemplate.update(CAR_UPDATE_BY_ID, params);
    }

    @Override
    public void deleteCarById(Integer id) {
        log.info("Method deleteCarById(with id={}) of class {} started", id, getClass().getName());
        Map<String, Integer> param = new HashMap<>();
        param.put("carId", id);
        namedParameterJdbcTemplate.update(CAR_DELETE_BY_ID, param);
    }

    @Override
    public Integer count() {
        log.info("Method count() of class {} started", getClass().getName());
        return namedParameterJdbcTemplate.queryForObject(CAR_COUNT, Collections.emptyMap(), Integer.class);
    }
}
