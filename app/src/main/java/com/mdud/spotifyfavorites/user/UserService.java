package com.mdud.spotifyfavorites.user;

import java.util.Optional;

public interface UserService {
    Optional<User> findUser(String spotifyId);
    User addUser(User user);
}
