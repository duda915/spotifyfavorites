package com.mdud.spotifyfavorites.spotify;

import com.mdud.spotifyfavorites.artist.Artist;
import com.mdud.spotifyfavorites.track.Track;

import java.util.List;

public interface SpotifyService {
    List<Artist> searchForArtists(String query);
    List<Track> searchForSongs(String query);
}