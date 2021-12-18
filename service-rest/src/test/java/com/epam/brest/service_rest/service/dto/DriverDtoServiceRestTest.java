package com.epam.brest.service_rest.service.dto;

import com.epam.brest.model.dto.DriverDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:service-rest-test-app-context.xml"})
class DriverDtoServiceRestTest {

    public static final Logger LOG = LogManager.getLogger(
            DriverDtoServiceRestTest.class);

    public static final String URL = "http://localhost:8088/drivers_dto";

    @Autowired
    private RestTemplate restTemplate;

    private DriverDtoServiceRest driverDtoServiceRest;

    private MockRestServiceServer mockRestServiceServer;

    private ObjectMapper objectMapper;

    private String fromDate;
    private String toDate;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        driverDtoServiceRest = new DriverDtoServiceRest(URL, restTemplate);
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);

        fromDate = "1990-01-02T10:10:10.002Z";
        toDate = "2021-01-02T10:10:10.002Z";
    }

    @Test
    void shouldFindAllDriverWithCountCars() throws JsonProcessingException, URISyntaxException {

        LOG.info("Method shouldFindAllDriverWithCountCars() started {}",
                getClass().getName());
        // given
        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(Arrays.asList(create(0), create(1))))
                );
        // when
        List<DriverDto> list = driverDtoServiceRest.findAllDriverWithCountCars();
        // then
        mockRestServiceServer.verify();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    void shouldChooseDriverOnDateRange() throws JsonProcessingException, URISyntaxException {

        LOG.info("Method shouldChooseDriverOnDateRange() started {}",
                getClass().getName());
        // given
        mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI(URL + "/drivers-range")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(Arrays.asList(create(0), create(1))))
                );
        // when
        List<DriverDto> listDst = driverDtoServiceRest.chooseDriverOnDateRange(fromDate, toDate);
        // then
        mockRestServiceServer.verify();
        assertNotNull(listDst);
        assertTrue(listDst.size() > 0);
    }

    private DriverDto create(Integer index) {
        DriverDto driverDto = new DriverDto();
        driverDto.setDriverId(index);
        driverDto.setDriverName("d" + index);
        driverDto.setDriverDateStartWork(new Date(index).toInstant());
        driverDto.setDriverSalary(BigDecimal.valueOf(100 + index));
        driverDto.setCountOfCarsAssignedToDriver(100 + index);
        return driverDto;
    }
}