package com.mdud.spotifyfavorites.spotify;

import com.mdud.spotifyfavorites.artist.Artist;
import com.mdud.spotifyfavorites.spotify.user.SpotifyUserService;
import com.mdud.spotifyfavorites.track.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.List;

@Service
public class SpotifyServiceImpl implements SpotifyService {

    private final RestOperations restOperations;
    private final String spotifySearchEndPointTemplate = "https://api.spotify.com/v1/search?q={query}&type={type}";

    @Autowired
    public SpotifyServiceImpl(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    @Override
    public List<Artist> searchForArtists(String query) {
        String response = restOperations.getForObject(spotifySearchEndPointTemplate, String.class, query, "artist");
        System.out.println(response);
        return null;
    }

    @Override
    public List<Track> searchForSongs(String query) {
        return null;
    }
}
