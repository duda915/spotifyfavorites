package com.mdud.spotifyfavorites.user;

import com.mdud.spotifyfavorites.spotify.user.SpotifyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final SpotifyUserService spotifyUserService;

    @Autowired
    public UserController(SpotifyUserService spotifyUserService) {
        this.spotifyUserService = spotifyUserService;
    }

    @GetMapping("/me")
    public String me() {
        return spotifyUserService.getAccessToken();
    }
}
