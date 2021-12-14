package com.epam.brest.rest.controller.exception;

import com.epam.brest.service.exception.DriverNotFoundException;
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
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Field constant DRIVER_NOT_FOUND.
     */

    public static final String DRIVER_NOT_FOUND = "driver.not_found";

    /**
     * Field constant VALIDATION_ERROR.
     */

    public static final String VALIDATION_ERROR = "validation_error";

    /**
     * Exception handler not found exception.
     *
     * @param ex DriverNotFoundException class.
     * @param webRequest WebRequest class.
     * @return 404 not found.
     */

    @ExceptionHandler(DriverNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleDriverNotFoundException(
            final DriverNotFoundException ex, final WebRequest webRequest) {

        LOG.info("Method handleDriverNotFoundException() started of class {}",
                getClass().getName());
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(DRIVER_NOT_FOUND, details);
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
        ErrorResponse error = new ErrorResponse(VALIDATION_ERROR, ex);
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
