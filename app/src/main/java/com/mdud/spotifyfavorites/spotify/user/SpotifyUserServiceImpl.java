package com.mdud.spotifyfavorites.spotify.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpotifyUserServiceImpl implements SpotifyUserService {
    @Override
    public String getSpotifyUserId() {
        return getAuthentication().getName();
    }

    @Override
    public String getAccessToken() {
        return ((OAuth2AuthenticationDetails) getAuthentication().getDetails()).getTokenValue();
    }


    private OAuth2Authentication getAuthentication() {
        return (OAuth2Authentication) Optional.of(SecurityContextHolder.getContext().getAuthentication())
                .orElseThrow(() -> new SecurityException("authentication error"));
    }
}
