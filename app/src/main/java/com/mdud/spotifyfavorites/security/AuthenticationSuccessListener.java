package com.mdud.spotifyfavorites.security;

import com.mdud.spotifyfavorites.spotify.user.SpotifyUserService;
import com.mdud.spotifyfavorites.user.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Log
public class AuthenticationSuccessListener  implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    private final UserService userService;
    private final SpotifyUserService spotifyUserService;

    @Autowired
    public AuthenticationSuccessListener(UserService userService, SpotifyUserService spotifyUserService) {
        this.userService = userService;
        this.spotifyUserService = spotifyUserService;
    }

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent interactiveAuthenticationSuccessEvent) {
        String spotifyUserId = spotifyUserService.getSpotifyUserId();

        if(userService.checkIfUserExists(spotifyUserId)) {
            log.info("user logged, already exists");
        } else {
            log.info("new user logged, persisting");
            userService.addUser(spotifyUserId);
        }
    }
}
