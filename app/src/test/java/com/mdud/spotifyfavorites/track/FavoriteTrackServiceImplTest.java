package com.mdud.spotifyfavorites.track;

import com.mdud.spotifyfavorites.artist.Artist;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FavoriteTrackServiceImplTest {

    @InjectMocks
    private FavoriteTrackServiceImpl favoriteTrackService;

    @Mock
    private UserService userService;

    private List<Artist> artists;
    private List<Track> favoriteTracks;
    private User testUser;

    @Before
    public void setup() {
        artists =
                Collections.singletonList(new Artist("id", "artist"));
        favoriteTracks =
                Collections.singletonList(new Track("id", "name", artists));
        testUser = new User("userId", artists, favoriteTracks, null);

        when(userService.findUser(testUser.getSpotifyUserId())).thenReturn(testUser);
    }

    @Test
    public void findUserFavoriteTracks() {
        List<Track> tracks = favoriteTrackService.findUserFavoriteTracks(testUser.getSpotifyUserId());

        Assert.assertThat(tracks, CoreMatchers.hasItems(testUser.getFavoriteSongs().stream().toArray(Track[]::new)));
        verify(userService, times(1)).findUser(testUser.getSpotifyUserId());
    }

    @Test
    public void findUserFavoriteTrack_FindExistentTrack_ShouldReturnTrack() {
        Track track = favoriteTrackService.findUserFavoriteTrack(testUser.getSpotifyUserId(), "id");

        Track expectedTrack = favoriteTracks.get(0);

        assertEquals(expectedTrack, track);
    }

    @Test(expected = NoSuchElementException.class)
    public void findUserFavoriteTrack_FindNonExistentTrack_ShouldThrowNoSuchElementException() {
        favoriteTrackService.findUserFavoriteTrack(testUser.getSpotifyUserId(), "nonexistent");
    }

    @Test
    public void addFavoriteTrack() {
        Track newFavoriteTrack = new Track("newid", "name", null);
        when(userService.update(testUser)).then(it -> it.getArgument(0));

        User userWithNewTrack = favoriteTrackService.addFavoriteTrack(testUser.getSpotifyUserId(), newFavoriteTrack);

        List<Track> expectedTracks = new ArrayList<>(testUser.getFavoriteSongs());
        expectedTracks.add(newFavoriteTrack);
        Assert.assertThat(userWithNewTrack.getFavoriteSongs(), CoreMatchers.hasItems(expectedTracks.stream().toArray(Track[]::new)));
        verify(userService, times(1)).update(userWithNewTrack);
    }

    @Test
    public void removeFavoriteTrack() {
        when(userService.update(testUser)).then(it -> it.getArgument(0));

        Track toRemove = favoriteTracks.get(0);
        User removedTrackUser = favoriteTrackService.removeFavoriteTrack(testUser.getSpotifyUserId(), toRemove.getId());

        Assert.assertThat(removedTrackUser.getFavoriteSongs(), CoreMatchers.everyItem(CoreMatchers.not(toRemove)));
        verify(userService, times(1)).update(testUser);
    }
}