package com.mdud.spotifyfavorites.artist;

import com.mdud.spotifyfavorites.user.User;
import com.mdud.spotifyfavorites.user.UserService;
import com.mdud.spotifyfavorites.util.CustomCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteArtistServiceImpl implements FavoriteArtistService {

    private final UserService userService;

    @Autowired
    public FavoriteArtistServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<Artist> findUserFavoriteArtists(String spotifyUserId) {
        return userService.findUser(spotifyUserId).getFavoriteArtists();
    }

    @Override
    public Artist findUserFavoriteArtist(String spotifyUserId, String spotifyArtistId) {
        return userService.findUser(spotifyUserId).getFavoriteArtists().stream().filter(artist -> artist.getId().equals(spotifyArtistId))
                .collect(CustomCollectors.singleElementCollector());
    }

    @Override
    public User addFavoriteArtist(String spotifyUserId, Artist favoriteArtist) {
        User user = userService.findUser(spotifyUserId);
        List<Artist> mutableList = new ArrayList<>(user.getFavoriteArtists());
        mutableList.add(favoriteArtist);
        user.setFavoriteArtists(mutableList);

        return userService.update(user);
    }

    @Override
    public User removeFavoriteArtist(String spotifyUserId, String spotifyArtistId) {
        User user = userService.findUser(spotifyUserId);
        Artist artist = findUserFavoriteArtist(user.getSpotifyUserId(), spotifyArtistId);
        List<Artist> mutableList = new ArrayList<>(user.getFavoriteArtists());
        mutableList.removeIf(it -> it.getId().equals(artist.getId()));
        user.setFavoriteArtists(mutableList);

        return userService.update(user);
    }
}
