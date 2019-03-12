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
}