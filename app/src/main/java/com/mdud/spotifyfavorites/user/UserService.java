package com.mdud.spotifyfavorites.user;

public interface UserService {
    boolean checkIfUserExists(String spotifyId);
    User findUser(String spotifyId);
    User addUser(String spotifyId);
    User update(User user);
}
