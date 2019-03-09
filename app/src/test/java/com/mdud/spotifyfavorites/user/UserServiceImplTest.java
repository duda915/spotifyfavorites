package com.mdud.spotifyfavorites.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
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
    public void addUser_AddNonExistentUser_ShouldAddUser() {
        String spotifyId = "user";
        when(userRepository.findBySpotifyUserId(spotifyId)).thenReturn(Optional.empty());
        when(userRepository.save(any())).then(it -> it.getArgument(0));

        User newUser = userService.addUser(spotifyId);

        User expectedUser = new User("user", new ArrayList<>(), new ArrayList<>());
        assertEquals(expectedUser, newUser);
        verify(userRepository, Mockito.times(1)).save(expectedUser);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void addUser_AddExistentUser_ShouldThrowUserAlreadyExistsExcetpion() {
        String spotifyId = "user";
        when(userRepository.findBySpotifyUserId(spotifyId)).thenReturn(Optional.of(new User("user", null, null)));
        userService.addUser(spotifyId);
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

    @Test(expected = NoSuchElementException.class)
    public void update_UpdateNonExistentUser_ShouldThrowNoSuchElementException() {
        when(userRepository.findBySpotifyUserId("test")).thenReturn(Optional.empty());

        userService.update(new User("test", null, null));
    }

    @Test
    public void update_UpdateExistentUser_ShouldUpdateUser() {
        User user = new User("test", null, null);
        when(userRepository.findBySpotifyUserId("test")).thenReturn(Optional.of(user));

        user.setFavoriteSongs(new ArrayList<>());
        userService.update(user);

        verify(userRepository, times(1)).save(user);
    }
}