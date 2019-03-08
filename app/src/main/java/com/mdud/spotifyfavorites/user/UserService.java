package com.mdud.spotifyfavorites.user;

import java.util.Optional;

public interface UserService {
    boolean checkIfUserExists(String spotifyId);
    User findUser(String spotifyId);
    User addUser(User user);
}
