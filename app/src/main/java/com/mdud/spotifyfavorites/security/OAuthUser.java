package com.mdud.spotifyfavorites.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class OAuthUser {
    public static String getSpotifyUserId() {
        OAuth2Authentication auth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        return auth2Authentication.getName();
    }

    public static String getAccessToken() {
        OAuth2Authentication auth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth2Authentication.getDetails();
        return auth2AuthenticationDetails.getTokenValue();
    }
}
