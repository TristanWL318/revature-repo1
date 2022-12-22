package com.example.demo.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex)
            throws Exception
    {
        MDC.clear();
        System.out.println("LoggingInterceptor afterCompletion method executed");
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler)
            throws Exception
    {
        MDC.put("METHOD", request.getMethod());
        MDC.put("URI", request.getRequestURI());
        System.out.println("LoggingInterceptor preHandle method executed.");

        return true;
    }

}
