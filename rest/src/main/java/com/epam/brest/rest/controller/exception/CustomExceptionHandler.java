package com.epam.brest.rest.controller.exception;

import com.epam.brest.service.exception.CarNotFoundException;
import com.epam.brest.service.exception.DriverNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String DRIVER_NOT_FOUND = "driver.not_found";
    public static final String VALIDATION_ERROR = "validation_error";

//    public static final String CAR_NOT_FOUND = "car.not_found";

    @ExceptionHandler(DriverNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleDriverNotFoundException(DriverNotFoundException ex, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(DRIVER_NOT_FOUND, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(CarNotFoundException.class)
//    public final ResponseEntity<ErrorResponse> handleCarNotFoundException(CarNotFoundException ex, WebRequest webRequest) {
//        List<String> details = new ArrayList<>();
//        details.add(ex.getLocalizedMessage());
//        ErrorResponse error = new ErrorResponse(CAR_NOT_FOUND, details);
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ErrorResponse> handleIllegalArgumentException(Exception ex, WebRequest webRequest) {
        ErrorResponse error = new ErrorResponse(VALIDATION_ERROR, ex);
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
