package com.mdud.spotifyfavorites.track;

import com.mdud.spotifyfavorites.user.User;

import java.util.List;

public interface FavoriteTrackService {
    List<Track> findUserFavoriteTracks(String spotifyUserId);
    Track findUserFavoriteTrack(String spotifyUserId, String spotifyTrackId);
    User addFavoriteTrack(String spotifyUserId, Track favoriteTrack);
    User removeFavoriteTrack(String spotifyUserId, String spotifyTrackId);
}
