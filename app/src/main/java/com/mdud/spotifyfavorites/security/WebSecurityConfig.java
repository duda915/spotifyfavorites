package com.mdud.spotifyfavorites.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableOAuth2Client
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuth2ClientContext oAuth2ClientContext;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public WebSecurityConfig(@Qualifier("oauth2ClientContext") OAuth2ClientContext oAuth2ClientContext, ApplicationEventPublisher applicationEventPublisher) {
        this.oAuth2ClientContext = oAuth2ClientContext;
        this.applicationEventPublisher = applicationEventPublisher;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
                .formLogin().loginPage("/login/spotify").permitAll()
                .and().logout().logoutSuccessUrl("/login/spotify").permitAll();
    }

    private Filter ssoFilter() {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter("/login/spotify");
        OAuth2RestTemplate spotifyTemplate = new OAuth2RestTemplate(spotify(), oAuth2ClientContext);
        filter.setRestTemplate(spotifyTemplate);
        UserInfoTokenServices userInfoTokenServices = new UserInfoTokenServices(spotifyResource().getUserInfoUri(), spotify().getClientId());
        userInfoTokenServices.setRestTemplate(spotifyTemplate);
        filter.setTokenServices(userInfoTokenServices);
        filter.setApplicationEventPublisher(applicationEventPublisher);
        return filter;
    }

    @Bean
    @ConfigurationProperties("spotify.resource")
    public ResourceServerProperties spotifyResource() {
        return new ResourceServerProperties();
    }

    @Bean
    @ConfigurationProperties("spotify.client")
    public AuthorizationCodeResourceDetails spotify() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }
}
