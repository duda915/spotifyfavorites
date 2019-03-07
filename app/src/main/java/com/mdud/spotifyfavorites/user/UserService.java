package com.mdud.spotifyfavorites.user;

public interface UserService {
    User findUser(String spotifyId);
    User addUser(User user);
}
