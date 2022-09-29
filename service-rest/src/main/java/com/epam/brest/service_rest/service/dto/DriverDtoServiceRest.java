package com.epam.brest.service_rest.service.dto;

import com.epam.brest.model.dto.DriverDto;
import com.epam.brest.service_api.dto.DriverDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class DriverDtoServiceRest implements DriverDtoService {

    public static final Logger LOG = LogManager.getLogger(
            DriverDtoServiceRest.class);

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

    public DriverDtoServiceRest() {
    }

    /**
     * Constructor.
     *
     * @param enterUrl String.
     * @param enterRestTemplate RestTemplate.
     */

    public DriverDtoServiceRest(final String enterUrl,
                                final RestTemplate enterRestTemplate) {
        this.url = enterUrl;
        this.restTemplate = enterRestTemplate;
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
        ResponseEntity responseEntity = restTemplate.getForEntity(
                url, List.class);
        return (List<DriverDto>) responseEntity.getBody();
    }

    /**
     * Find driver's list rest Dto from date to date.
     *
     * @return driver's list rest Dto.
     */
    @Override
    public List<DriverDto> chooseDriverOnDateRange(final String fromDateChoose,
                                                   final String toDateChoose) {

        LOG.info("Method chooseDriverOnDateRange()"
                        + " with fromDate {} and toDate {} started {}",
                fromDateChoose, toDateChoose, getClass().getName());
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(
                        url + "/drivers-range")
                .queryParam("fromDateChoose", fromDateChoose)
                .queryParam("toDateChoose", toDateChoose);
        ParameterizedTypeReference<List<DriverDto>> typeReference =
                new ParameterizedTypeReference<>(){};
        ResponseEntity<List<DriverDto>> responseEntity =
                restTemplate.exchange(uriComponentsBuilder.toUriString(),
                        HttpMethod.GET,null, typeReference);
        return responseEntity.getBody();
    }
}
