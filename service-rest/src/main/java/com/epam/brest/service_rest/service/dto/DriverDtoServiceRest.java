package com.epam.brest.service_rest.service.dto;

import com.epam.brest.model.dto.DriverDto;
import com.epam.brest.service_api.dto.DriverDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.epam.brest.logger.ProjectLogger.LOG;

@Service
public class DriverDtoServiceRest implements DriverDtoService {

    /**
     * Field url String.
     */

    private String url;

    /**
     * Field restTemplate RestTemplate.
     */

    private RestTemplate restTemplate;

    public DriverDtoServiceRest() {
    }

    /**
     * Constructor.
     *
     * @param url String.
     * @param restTemplate RestTemplate.
     */

    public DriverDtoServiceRest(final String url, final RestTemplate restTemplate) {
//        this();
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * Find driver's list rest Dto.
     *
     * @return driver's list rest Dto.
     */
    @Override
    public List<DriverDto> findAllDriverWithCountCars() {

        LOG.info("Method findAllDriverWithCountCars() started {}",
                getClass().getName());
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<DriverDto>) responseEntity.getBody();
    }
}
