package com.epam.brest.dao;

import com.epam.brest.dao.rowMappers.DriverRowMapper;
import com.epam.brest.dao_api.DaoJdbcRepository;
import com.epam.brest.model.Driver;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static com.epam.brest.dao.Queries.*;

public class DriverDaoJdbcJdbcImpl implements DaoJdbcRepository<Driver> {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DriverDaoJdbcJdbcImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Driver> findAll() {
        namedParameterJdbcTemplate.query(DRIVER_FIND_ALL, new DriverRowMapper());
        return namedParameterJdbcTemplate.query(DRIVER_FIND_ALL, new DriverRowMapper());
    }

    @Override
    public Driver findById(Integer id) {
        return null;
    }

    @Override
    public void save(Driver driver) {

    }

    @Override
    public void update(Integer id, Driver updateToDriver) {

    }

    @Override
    public void delete(Integer id) {

    }
}
