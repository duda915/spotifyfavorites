package com.mdud.spotifyfavorites.security;

import com.mdud.spotifyfavorites.spotify.user.SpotifyUserService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UsernameControllerAdvice {
    private final SpotifyUserService spotifyUserService;

    public UsernameControllerAdvice(SpotifyUserService spotifyUserService) {
        this.spotifyUserService = spotifyUserService;
    }

    @ModelAttribute("username")
    public String getUsername() {
        return spotifyUserService.getSpotifyUserId();
    }
}
