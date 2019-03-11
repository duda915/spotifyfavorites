package com.mdud.spotifyfavorites.logging;

import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

public class LoggingFilter extends AbstractRequestLoggingFilter {
    @Override
    protected void beforeRequest(HttpServletRequest httpServletRequest, String s) {
    }

    @Override
    protected void afterRequest(HttpServletRequest httpServletRequest, String s) {
        logger.info(httpServletRequest.getMethod());
    }
}
