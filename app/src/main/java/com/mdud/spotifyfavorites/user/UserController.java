package com.mdud.spotifyfavorites.user;

import com.mdud.spotifyfavorites.artist.Artist;
import com.mdud.spotifyfavorites.spotify.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final SpotifyService spotifyService;

    @Autowired
    public UserController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/me")
    public List<Artist> me() {
        return spotifyService.searchForArtists("tania");
    }
}
