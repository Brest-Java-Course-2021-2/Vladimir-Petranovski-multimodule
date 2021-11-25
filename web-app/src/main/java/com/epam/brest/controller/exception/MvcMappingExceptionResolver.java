package com.epam.brest.controller.exception;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class MvcMappingExceptionResolver extends SimpleMappingExceptionResolver {

    public MvcMappingExceptionResolver() {
        setWarnLogCategory(MvcMappingExceptionResolver.class.getName());
    }

    @Override
    public String buildLogMessage(Exception e, HttpServletRequest req) {
        ExceptionHandlerExceptionResolver r;
        return e.getLocalizedMessage();
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest req, HttpServletResponse resp,
                                              Object handler, Exception e) {
        ModelAndView modelAndView = super.doResolveException(req, resp, handler, e);
        modelAndView.addObject("timestamp", new Date());
        modelAndView.addObject("url", req.getRequestURL());
//        modelAndView.setViewName("exception-handler/error");
        return modelAndView;
    }
}
