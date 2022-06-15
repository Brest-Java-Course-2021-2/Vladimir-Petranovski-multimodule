package com.epam.brest.service_rest.service;

import com.epam.brest.model.Driver;
import com.epam.brest.service_api.DriverService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DriverServiceRest implements DriverService {

    public static final Logger LOG = LogManager.getLogger(
            DriverServiceRest.class);

    /**
     * Field url String.
     */

    private String url;

    /**
     * Field restTemplate RestTemplate.
     */

    private RestTemplate restTemplate;

    /**
     * Constructor without parameters.
     */

    public DriverServiceRest() {
    }

    /**
     * Constructor.
     *
     * @param enterUrl String.
     * @param enterRestTemplate RestTemplate.
     */

    public DriverServiceRest(final String enterUrl,
                             final RestTemplate enterRestTemplate) {
        this.url = enterUrl;
        this.restTemplate = enterRestTemplate;
    }

    /**
     * Find all drivers.
     *
     * @return list of drivers.
     */

    @Override
    public List<Driver> findAllDrivers() {
        LOG.info("Method findAllDrivers() started of class {}",
                getClass().getName());
        ResponseEntity responseEntity = restTemplate.getForEntity(
                url, List.class);
        return (List<Driver>) responseEntity.getBody();
    }

    /**
     * Find driver by Id.
     *
     * @param id driver Id.
     * @return driver.
     */

    @Override
    public Driver findDriverById(final Integer id) {
        LOG.info("Method findDriverById()"
                        + " with id: {} started of class {}",
                id, getClass().getName());
        ResponseEntity<Driver> responseEntity =
                restTemplate.getForEntity(url + "/" + id,
                        Driver.class);
        return responseEntity.getBody();
    }

    /**
     * Persist new driver.
     *
     * @param driver driver.
     * @return persisted driver id.
     */

    @Override
    public Integer saveDriver(final Driver driver) {
        LOG.info("Method saveDriver()"
                        + " with driver: {} started of class {}",
                driver, getClass().getName());
        ResponseEntity responseEntity = restTemplate.postForEntity(
                url, driver, Integer.class);
        return (Integer) responseEntity.getBody();
    }

    /**
     * Update driver.
     *
     * @param id driver id.
     * @param driver driver.
     * @return number of updated records in the database.
     */

    @Override
    public Integer updateDriverById(final Integer id,
                                    final Driver driver) {
        LOG.info("Method updateDriverById()"
                        + " with id {} and with driver: {} started of class {}",
                id, driver, getClass().getName());
        // restTemplate.put(url, driver) // may be mistakes

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Driver> entity = new HttpEntity<>(driver, headers);
        ResponseEntity<Integer> result = restTemplate.exchange(
                url + "/" + id, HttpMethod.PATCH, entity, Integer.class);
        return result.getBody();
    }

    /**
     * Delete driver.
     *
     * @param id driver id.
     * @return number of updated records in the database.
     */

    @Override
    public Integer deleteDriverById(final Integer id) {
        LOG.info("Method deleteDriverById()"
                        + " with id: {} started of class {}",
                id, getClass().getName());
        // restTemplate.delete(url, driver) // may be mistakes
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Driver> entity = new HttpEntity<>(headers);
        ResponseEntity<Integer> result = restTemplate.exchange(
                url + "/" + id + "/delete-driver", HttpMethod.DELETE,
                entity, Integer.class);
        return result.getBody();
    }

    /**
     * Count drivers.
     *
     * @return quantity of the drivers.
     */

    @Override
    public Integer count() {
        LOG.info("Method count() started of class {}",
                getClass().getName());
        ResponseEntity<Integer> responseEntity = restTemplate.getForEntity(
                url + "/count", Integer.class);
        return responseEntity.getBody();
    }
}
