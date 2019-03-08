package com.mdud.spotifyfavorites.user;

import java.util.Optional;

public interface UserService {
    User findUser(String spotifyId);
    User addUser(User user);
}
