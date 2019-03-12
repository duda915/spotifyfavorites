package com.mdud.spotifyfavorites.logging;

import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class LoggingFilter extends AbstractRequestLoggingFilter {

    private final LoggingService loggingService;

    LoggingFilter(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Override
    protected void beforeRequest(HttpServletRequest httpServletRequest, String s) {
    }

    @Override
    protected void afterRequest(HttpServletRequest httpServletRequest, String s) {
        if(httpServletRequest.getMethod().equals("GET")) {
            return;
        }

        Log log = new Log(httpServletRequest.getMethod(), s, LocalDateTime.now());
        loggingService.log(httpServletRequest.getUserPrincipal().getName(), log);
    }
}
