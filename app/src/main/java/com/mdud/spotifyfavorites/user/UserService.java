package com.mdud.spotifyfavorites.user;

public interface UserService {
    boolean checkIfUserExists(String spotifyId);
    User findUser(String spotifyId);
    User addUser(User user);
    User update(User user);
}
