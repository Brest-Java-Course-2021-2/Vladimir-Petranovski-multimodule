package com.epam.brest.controller.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;

@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

    public static final Logger LOG = LogManager.getLogger(
            ExceptionHandlingControllerAdvice.class);

    /**
     * Exception handler of data base.
     *
     * @param req HttpServletRequest.
     * @param ex Exception.
     * @return view exception.
     */

    @ExceptionHandler({SQLException.class,
            DataAccessException.class})
    public ModelAndView handleDataIntegrityViolationException(
            final HttpServletRequest req,
            final Exception ex) {
        LOG.error("Method handleDataIntegrityViolationException()"
                        + " with request: {} raised: {} started in {}",
                req.getRequestURL(), ex, getClass().getName());

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
     * @param req HttpServletRequest.
     * @param ex Exception.
     * @return view exception.
     */

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(final HttpServletRequest req,
                                    final Exception ex) {
        LOG.error("Method handleError() with request:"
                        + " {} raised: {} started in {}",
                req.getRequestURL(), ex, getClass().getName());

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", new Date());
        mav.setViewName("exception/error");
        return mav;
    }
}
