package com.mdud.spotifyfavorites.logging;

import com.mdud.spotifyfavorites.user.User;

import java.util.List;

public interface LoggingService {
    User log(String spotifyUserId, Log log);
    List<Log> getUserLogs(String spotifyUserId);
}
