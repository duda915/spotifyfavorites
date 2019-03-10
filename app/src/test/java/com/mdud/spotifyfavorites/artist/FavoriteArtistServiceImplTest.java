package com.mdud.spotifyfavorites.artist;

import com.mdud.spotifyfavorites.user.User;
import com.mdud.spotifyfavorites.user.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FavoriteArtistServiceImplTest {

    @InjectMocks
    private FavoriteArtistServiceImpl favouriteArtistService;

    @Mock
    private UserService userService;

    private User user;
    private List<Artist> favoriteArtists;

    @Before
    public void setup() {
        favoriteArtists = Collections.singletonList(new Artist("id", null));
        user = new User("spotifyUserId", favoriteArtists, null);
        when(userService.findUser(user.getSpotifyUserId())).thenReturn(user);
    }

    @Test
    public void findUserFavoriteArtists() {
        List<Artist> artists = favouriteArtistService.findUserFavoriteArtists(user.getSpotifyUserId());

        Assert.assertEquals(favoriteArtists, artists);
        verify(userService, timeout(1)).findUser(user.getSpotifyUserId());
    }

    @Test
    public void findUserFavoriteArtist_FindExistentArtist_ShouldReturnArtist() {
        Artist artist = favouriteArtistService.findUserFavoriteArtist(user.getSpotifyUserId(), "id");

        Assert.assertEquals(user.getFavoriteArtists().get(0), artist);
        verify(userService, times(1)).findUser(user.getSpotifyUserId());
    }

    @Test(expected = NoSuchElementException.class)
    public void findUserFavoriteArtist_FindNonExistentArtist_ShouldThrowNoSuchElementException() {
        favouriteArtistService.findUserFavoriteArtist(user.getSpotifyUserId(), "nonexistent");
    }

    @Test
    public void addFavoriteArtist() {
        Artist newFavoriteArtist = new Artist("newid", null);
        when(userService.update(any())).then(it -> it.getArgument(0));

        User userWithNewFavoriteArtist = favouriteArtistService.addFavoriteArtist(user.getSpotifyUserId(), newFavoriteArtist);

        List<Artist> expectedArtists = new ArrayList<>(favoriteArtists);
        expectedArtists.add(newFavoriteArtist);
        Assert.assertEquals(expectedArtists, userWithNewFavoriteArtist.getFavoriteArtists());
        verify(userService, times(1)).update(userWithNewFavoriteArtist);
    }

    @Test
    public void removeFavoriteArtist() {
        Artist artistToRemove = favoriteArtists.get(0);
        when(userService.update(any())).then(it -> it.getArgument(0));
        User userWithArtistRemoved = favouriteArtistService.removeFavoriteArtist(user.getSpotifyUserId(), artistToRemove.getSpotifyId());

        List<Artist> expectedArtists = new ArrayList<>(favoriteArtists);
        expectedArtists.removeIf(artist -> artist.getSpotifyId().equals(artistToRemove.getSpotifyId()));
        Assert.assertEquals(userWithArtistRemoved.getFavoriteArtists(), expectedArtists);

        verify(userService, times(1)).update(userWithArtistRemoved);
    }
}