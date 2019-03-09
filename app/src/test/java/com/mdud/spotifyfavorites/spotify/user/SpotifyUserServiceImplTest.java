package com.mdud.spotifyfavorites.spotify.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class SpotifyUserServiceImplTest {

    @InjectMocks
    private SpotifyUserServiceImpl spotifyUserService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private OAuth2Authentication authentication;

    @Mock
    private OAuth2AuthenticationDetails details;

    @Before
    public void setup() {
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getName()).thenReturn("user");
        Mockito.when(authentication.getDetails()).thenReturn(details);
        Mockito.when(details.getTokenValue()).thenReturn("token");
    }

    @Test
    public void getSpotifyUserId() {
        String user = spotifyUserService.getSpotifyUserId();

        assertEquals("user", user);
        Mockito.verify(authentication, Mockito.times(1)).getName();
    }

    @Test
    public void getAccessToken() {
        String token = spotifyUserService.getAccessToken();

        assertEquals("token", token);
        Mockito.verify(details, Mockito.times(1)).getTokenValue();
    }

}