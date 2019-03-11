package com.mdud.spotifyfavorites.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LogConfiguration {

    @Bean
    public AbstractRequestLoggingFilter requestLoggingFilter() {
        System.out.println("inside logging filter");
        AbstractRequestLoggingFilter loggingFilter = new LoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        return loggingFilter;
    }
}
