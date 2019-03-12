package com.mdud.spotifyfavorites.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Configuration
public class LogConfiguration {
    private final LoggingService loggingService;

    @Autowired
    public LogConfiguration(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Bean
    public AbstractRequestLoggingFilter requestLoggingFilter() {
        AbstractRequestLoggingFilter loggingFilter = new LoggingFilter(loggingService);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(100000);
        loggingFilter.setAfterMessagePrefix("request: [");
        return loggingFilter;
    }
}
