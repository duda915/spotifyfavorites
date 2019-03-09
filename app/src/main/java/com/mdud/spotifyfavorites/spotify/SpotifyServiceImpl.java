package com.mdud.spotifyfavorites.spotify;

import com.mdud.spotifyfavorites.artist.Artist;
import com.mdud.spotifyfavorites.spotify.user.SpotifyUserService;
import com.mdud.spotifyfavorites.track.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotifyServiceImpl implements SpotifyService {

    private final SpotifyUserService spotifyUserService;

    @Autowired
    public SpotifyServiceImpl(SpotifyUserService spotifyUserService) {
        this.spotifyUserService = spotifyUserService;
    }

    @Override
    public List<Artist> searchForArtists(String query) {
        return null;
    }

    @Override
    public List<Track> searchForSongs(String query) {
        return null;
    }
}
