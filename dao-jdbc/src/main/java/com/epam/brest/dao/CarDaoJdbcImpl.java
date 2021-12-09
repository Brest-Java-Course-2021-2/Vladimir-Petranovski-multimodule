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

import java.util.List;

import static com.epam.brest.dao.Queries.*;
import static com.epam.brest.logger.ProjectLogger.LOG;

@Component
public class CarDaoJdbcImpl implements CarDao {

    /**
     * Field namedParameterJdbcTemplate.
     */

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Constructor.
     *
     * @param namedParameterJdbcTemplate namedParameterJdbcTemplate.
     */

    public CarDaoJdbcImpl(
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Find all cars.
     *
     * @return list of cars.
     */

    @Override
    public List<Car> findAllCars() {
        LOG.info("Method findAllCars() of class {} started",
                getClass().getName());
        return namedParameterJdbcTemplate.query(CAR_FIND_ALL,
                new CarDaoJdbcRowMapper());
    }

    /**
     * Find car by Id.
     *
     * @param id car Id.
     * @return car.
     */

    @Override
    public Car findCarById(final Integer id) {
        LOG.info("Method findCarById(with id={}) of class {} started",
                id, getClass().getName());

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("carId", id);
        return namedParameterJdbcTemplate.queryForObject(
                CAR_FIND_BY_ID, sqlParameterSource,
                new CarDaoJdbcRowMapper());
    }

    /**
     * Persist new car.
     *
     * @param car car.
     * @return persisted car id.
     */

    @Override
    public Integer saveCar(final Car car) {
        LOG.info("Method saveCar(with car {}) of class {} started",
                car, getClass().getName());

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource()
                        .addValue("carModel", car.getCarModel())
                        .addValue("driverId", car.getDriverId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(CAR_SAVE,
                sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    /**
     * Update car.
     *
     * @param id car id.
     * @param car car.
     * @return number of updated records in the database.
     */

    @Override
    public Integer updateCarById(final Integer id, final Car car) {
        LOG.info("Method updateCarById(with id={}, car {}) of class {} started",
                id, car, getClass().getName());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("carId", id)
                .addValue("carModel", car.getCarModel())
                .addValue("driverId", car.getDriverId());
        return namedParameterJdbcTemplate.update(
                CAR_UPDATE_BY_ID, sqlParameterSource);
    }

    /**
     * Delete car.
     *
     * @param id car id.
     * @return number of updated records in the database.
     */

    @Override
    public Integer deleteCarById(final Integer id) {
        LOG.info("Method deleteCarById(with id={}) of class {} started",
                id, getClass().getName());

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("carId", id);
        return namedParameterJdbcTemplate.update(
                CAR_DELETE_BY_ID, sqlParameterSource);
    }

    /**
     * Count cars.
     *
     * @return quantity of the cars.
     */

    @Override
    public Integer count() {
        LOG.info("Method count() of class {} started",
                getClass().getName());

        return namedParameterJdbcTemplate.queryForObject(
                CAR_COUNT, new MapSqlParameterSource(), Integer.class);
    }
}
