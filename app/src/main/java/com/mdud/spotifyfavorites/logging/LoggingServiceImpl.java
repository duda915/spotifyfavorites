package com.mdud.spotifyfavorites.logging;

import com.mdud.spotifyfavorites.user.User;
import com.mdud.spotifyfavorites.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LoggingServiceImpl implements LoggingService {

    private final UserService userService;

    @Autowired
    public LoggingServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User log(String spotifyUserId, Log log) {
        User user = userService.findUser(spotifyUserId);
        ArrayList<Log> mutableList = new ArrayList<>(user.getUserLogs());
        mutableList.add(log);

        user.setUserLogs(mutableList);
        return userService.update(user);
    }

    @Override
    public List<Log> getUserLogs(String spotifyUserId) {
        List<Log> logs = userService.findUser(spotifyUserId).getUserLogs();
        Collections.reverse(logs);
        return logs;
    }
}
