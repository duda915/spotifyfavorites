package com.mdud.spotifyfavorites.logging;

import com.mdud.spotifyfavorites.user.User;
import com.mdud.spotifyfavorites.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoggingServiceImplTest {
    @InjectMocks
    private LoggingServiceImpl loggingService;

    @Mock
    private UserService userService;

    @Test
    public void log() {
        User findUser = new User("", null, null, new ArrayList<>());
        when(userService.findUser("test")).thenReturn(findUser);
        when(userService.update(any())).then(it -> it.getArgument(0));

        Log log = new Log("get", "test", LocalDateTime.now());
        User logged = loggingService.log("test", log);

        User expectedUser = new User("", null, null, Collections.singletonList(log));

        assertEquals(logged, expectedUser);
        verify(userService, times(1)).update(expectedUser);
    }

    @Test
    public void getUserLogs_LogsShouldBeReversed() {
        List<Log> logs = new ArrayList<>();

        Log expectedFirstLog = new Log("get", "get", LocalDateTime.now().plusHours(1));
        Log expectedLastLog= new Log("post", "post", LocalDateTime.now());

        logs.add(expectedLastLog);
        logs.add(expectedFirstLog);
        when(userService.findUser("test")).thenReturn(new User("test", null, null, logs));

        List<Log> userLogs = loggingService.getUserLogs("test");

        assertEquals(expectedFirstLog, userLogs.get(0));
    }
}