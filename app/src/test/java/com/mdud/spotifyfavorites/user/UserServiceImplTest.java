package com.mdud.spotifyfavorites.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void addUser() {
        User user = new User("userid", null, null);
        when(userRepository.save(user)).then(it -> it.getArgument(0));

        User newUser = userService.addUser(user);

        assertEquals(user, newUser);
        verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void findUser_FindExistentUser_ShouldReturnUser() {
        User user = new User("test", null, null);
        when(userRepository.findBySpotifyUserId("test")).thenReturn(Optional.of(user));

        User foundUser = userService.findUser("test");

        assertEquals(user, foundUser);
        verify(userRepository, times(1)).findBySpotifyUserId("test");
    }

    @Test(expected = NoSuchElementException.class)
    public void findUser_FindNonExistentUser_ShouldThrowNoSuchElementException() {
        when(userRepository.findBySpotifyUserId(anyString())).thenReturn(Optional.empty());

        userService.findUser("test");
    }

    @Test
    public void checkIfUserExists_CheckExistentUser_ShouldReturnTrue() {
        when(userRepository.findBySpotifyUserId("test")).thenReturn(Optional.of(new User("test", null, null)));

        boolean exists = userService.checkIfUserExists("test");

        assertTrue(exists);
        verify(userRepository, times(1)).findBySpotifyUserId("test");
    }

    @Test
    public void checkIfUserExists_CheckNonExistentUser_ShouldReturnFalse() {
        when(userRepository.findBySpotifyUserId("test")).thenReturn(Optional.empty());

        boolean exists = userService.checkIfUserExists("test");

        assertFalse(exists);
    }
}