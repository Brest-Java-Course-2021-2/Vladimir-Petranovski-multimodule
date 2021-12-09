package com.epam.brest.controller.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;

import static com.epam.brest.logger.ProjectLogger.log;

@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

    /**
     * Exception handler of data base.
     *
     * @return view exception.
     */

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public ModelAndView handleDataIntegrityViolationException(HttpServletRequest req, Exception ex) {
        log.error("Method handleDataIntegrityViolationException() with request: {} raised: {} started in {}", req.getRequestURL(), ex, getClass().getName());

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", new Date());
        mav.setViewName("exception/error-db");
        return mav;
    }

    /**
     * Exception handler.
     *
     * @return view exception.
     */

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        log.error("Method handleError() with request: {} raised: {} started in {}", req.getRequestURL(), ex, getClass().getName());

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", new Date());
        mav.setViewName("exception/error");
        return mav;
    }
}
