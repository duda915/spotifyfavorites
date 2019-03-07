package com.mdud.spotifyfavorites.user;

import com.mdud.spotifyfavorites.artist.Artist;
import com.mdud.spotifyfavorites.track.Track;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private String spotifyUserId;
    private List<Artist> favoriteArtists;
    private List<Track> favoriteSongs;
}

