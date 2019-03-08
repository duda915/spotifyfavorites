package com.mdud.spotifyfavorites.artist;

import com.mdud.spotifyfavorites.user.User;

import java.util.List;

public interface FavoriteArtistService {
    List<Artist> findUserFavoriteArtists(String spotifyUserId);
    Artist findUserFavoriteArtist(String spotifyUserId, String spotifyArtistId);
    User addFavoriteArtist(String spotifyUserId, Artist favoriteArtist);
    User removeFavoriteArtist(String spotifyUserId, String spotifyArtistId);
}
