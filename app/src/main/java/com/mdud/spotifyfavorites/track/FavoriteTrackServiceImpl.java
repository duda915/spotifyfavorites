package com.mdud.spotifyfavorites.track;

import com.mdud.spotifyfavorites.exception.ResourceAlreadyExistsException;
import com.mdud.spotifyfavorites.user.User;
import com.mdud.spotifyfavorites.user.UserService;
import com.mdud.spotifyfavorites.util.CustomCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteTrackServiceImpl implements FavoriteTrackService {

    private final UserService userService;

    @Autowired
    public FavoriteTrackServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<Track> findUserFavoriteTracks(String spotifyUserId) {
        return userService.findUser(spotifyUserId).getFavoriteSongs();
    }

    @Override
    public Track findUserFavoriteTrack(String spotifyUserId, String spotifyTrackId) {
        return userService.findUser(spotifyUserId).getFavoriteSongs().stream().filter(track -> track.getId().equals(spotifyTrackId))
                .collect(CustomCollectors.singleElementCollector());
    }

    private boolean checkIfTrackExists(String spotifyUserId, Track track) {
        return userService.findUser(spotifyUserId).getFavoriteSongs().contains(track);
    }

    @Override
    public User addFavoriteTrack(String spotifyUserId, Track favoriteTrack) {
        if(checkIfTrackExists(spotifyUserId, favoriteTrack)) {
            throw new ResourceAlreadyExistsException("track already added");
        }
        User user = userService.findUser(spotifyUserId);
        List<Track> mutableList = new ArrayList<>(user.getFavoriteSongs());
        mutableList.add(favoriteTrack);
        user.setFavoriteSongs(mutableList);

        return userService.update(user);
    }

    @Override
    public User removeFavoriteTrack(String spotifyUserId, String spotifyTrackId) {
        Track removeTrack = findUserFavoriteTrack(spotifyUserId, spotifyTrackId);
        User user = userService.findUser(spotifyUserId);
        List<Track> mutableList = new ArrayList<>(user.getFavoriteSongs());
        mutableList.removeIf(track -> track.equals(removeTrack));
        user.setFavoriteSongs(mutableList);

        return userService.update(user);
    }

}
