package com.epam.brest.rest.controller.exception;

import com.epam.brest.service.exception.CarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static com.epam.brest.logger.ProjectLogger.LOG;

@ControllerAdvice
public class CustomExceptionHandlerCar extends ResponseEntityExceptionHandler {

    /**
     * Field CAR_NOT_FOUND.
     */

    public static final String CAR_NOT_FOUND = "car.not_found";

    /**
     * Field CAR_VALIDATION_ERROR.
     */

    public static final String CAR_VALIDATION_ERROR = "car.validation_error";

    /**
     * Exception handler not found exception.
     *
     * @param ex DriverNotFoundException class.
     * @param webRequest WebRequest class.
     * @return 404 not found.
     */

    @ExceptionHandler(CarNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleCarNotFoundException(
            final CarNotFoundException ex, final WebRequest webRequest) {

        LOG.info("Method handleCarNotFoundException() started of class {}",
                getClass().getName());
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(CAR_NOT_FOUND, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler IllegalArgumentException.
     *
     * @param ex Exception class.
     * @param webRequest WebRequest class.
     * @return 422 Unprocessable Entity.
     */

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            final Exception ex, final WebRequest webRequest) {

        LOG.info("Method handleIllegalArgumentException() started of class {}",
                getClass().getName());
        ErrorResponse error = new ErrorResponse(CAR_VALIDATION_ERROR, ex);
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
