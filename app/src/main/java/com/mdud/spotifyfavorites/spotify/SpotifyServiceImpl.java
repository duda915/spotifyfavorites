package com.mdud.spotifyfavorites.spotify;

import com.mdud.spotifyfavorites.artist.Artist;
import com.mdud.spotifyfavorites.track.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.List;

@Service
public class SpotifyServiceImpl implements SpotifyService {

    private final RestOperations restOperations;
    private final SpotifyResponseMapper spotifyResponseMapper;
    private final String spotifySearchEndPointTemplate = "https://api.spotify.com/v1/search?q={query}&type={type}";

    @Autowired
    public SpotifyServiceImpl(RestOperations restOperations, SpotifyResponseMapper spotifyResponseMapper) {
        this.restOperations = restOperations;
        this.spotifyResponseMapper = spotifyResponseMapper;
    }

    @Override
    public List<Artist> searchForArtists(String query) {
        String response = restOperations.getForObject(spotifySearchEndPointTemplate, String.class, query, "artist");
        return spotifyResponseMapper.toArtistsList(response);
    }

    @Override
    public List<Track> searchForSongs(String query) {
        String response = restOperations.getForObject(spotifySearchEndPointTemplate, String.class, query, "track");
        return spotifyResponseMapper.toSongsList(response);
    }
}
