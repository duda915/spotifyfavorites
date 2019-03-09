package com.mdud.spotifyfavorites.user;

import com.mdud.spotifyfavorites.spotify.user.SpotifyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

@RestController
public class UserController {

    private final SpotifyUserService spotifyUserService;
    private final RestOperations restOperations;

    @Autowired
    public UserController(SpotifyUserService spotifyUserService, RestOperations restOperations) {
        this.spotifyUserService = spotifyUserService;
        this.restOperations = restOperations;
    }

    @GetMapping("/me")
    public String me() {
        return spotifyUserService.getAccessToken();
    }
}
