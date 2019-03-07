package com.mdud.spotifyfavorites.user;

import com.mdud.spotifyfavorites.artist.Artist;
import com.mdud.spotifyfavorites.track.Track;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    @Indexed
    private String spotifyUserId;
    private List<Artist> favoriteArtists;
    private List<Track> favoriteSongs;
}

