package com.epam.brest.controller.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.epam.brest")
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Configuration objectMapper.
     *
     * @param converters List<HttpMessageConverter<?>>.
     */

    @Override
    public void configureMessageConverters(
            final List<HttpMessageConverter<?>> converters) {

        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .modules(new JavaTimeModule()).build()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                        false);
//                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

//                .configure(DeserializationFeature
//                .READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
//        WebMvcConfigurer.super.configureMessageConverters(converters);
    }
}
