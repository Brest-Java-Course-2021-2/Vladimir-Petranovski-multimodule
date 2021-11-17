package com.epam.brest.dao;

import com.epam.brest.dao.rowMappers.DriverDaoJdbcRowMapper;
import com.epam.brest.dao_api.DriverDao;
import com.epam.brest.model.Driver;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import static com.epam.brest.dao.Queries.*;

public class DriverDriverDaoJdbcImpl implements DriverDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DriverDriverDaoJdbcImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Driver> findAll() {
        return namedParameterJdbcTemplate.query(DRIVER_FIND_ALL, new DriverDaoJdbcRowMapper());
    }

    @Override
    public Driver findById(Integer id) {
        Map<String, Integer> params = new HashMap<>();
        params.put("driverId", id);
        return namedParameterJdbcTemplate.queryForObject(DRIVER_FIND_BY_ID, params, new DriverDaoJdbcRowMapper());
    }

    @Override
    public void save(Driver driver) {
        if (!findAllNameDrivers().contains(driver.getDriverName().toUpperCase())) {
            Map<String, Object> params = new HashMap<>();
            params.put("driverName", driver.getDriverName());
            params.put("driverDateStartWork", driver.getDriverDateStartWork());
            params.put("driverSalary", driver.getDriverSalary());
            namedParameterJdbcTemplate.execute(DRIVER_SAVE, params, new PreparedStatementCallback<Integer>() {
                @Override
                public Integer doInPreparedStatement(PreparedStatement ps)
                        throws SQLException, DataAccessException {
                    return ps.executeUpdate();
                }
            });
        } else {
            throw new IllegalArgumentException("Name must be unique");
        }
    }

//    @Override
//    public Integer save(Driver driver) {
//        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("name", driver.getName())
//                .addValue("dateStartWork", driver.getDateStartWork())
//                .addValue("salary", driver.getSalary());
//        return namedParameterJdbcTemplate.update(DRIVER_SAVE, sqlParameterSource);
//    }

    @Override
    public void update(Integer id, Driver updateToDriver) {
        Map<String, Object> params = new HashMap<>();
        params.put("driverId", id);
        params.put("driverName", updateToDriver.getDriverName());
        params.put("driverDateStartWork", updateToDriver.getDriverDateStartWork());
        params.put("driverSalary", updateToDriver.getDriverSalary());
        namedParameterJdbcTemplate.update(DRIVER_UPDATE_BY_ID, params);
    }

    @Override
    public void delete(Integer id) {
        Map<String, Integer> param = new HashMap<>();
        param.put("driverId", id);
        namedParameterJdbcTemplate.update(DRIVER_DELETE_BY_ID, param);
    }

//    private String findNameDriverById(Integer id) {
//        Map<String, Integer> param = new HashMap<>();
//        param.put("driverId", id);
//        return namedParameterJdbcTemplate.queryForObject(DRIVER_FIND_NAME_BY_ID, param, String.class);
//    }

    private List<String> findAllNameDrivers() {
        return namedParameterJdbcTemplate.queryForList(DRIVER_FIND_ALL_NAME, Collections.emptyMap(), String.class);
    }
}
