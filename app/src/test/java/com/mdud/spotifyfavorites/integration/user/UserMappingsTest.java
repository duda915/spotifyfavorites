package com.mdud.spotifyfavorites.integration.user;

import com.mdud.spotifyfavorites.TestProfileValueSource;
import com.mdud.spotifyfavorites.artist.Artist;
import com.mdud.spotifyfavorites.track.Track;
import com.mdud.spotifyfavorites.user.User;
import com.mdud.spotifyfavorites.user.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ProfileValueSourceConfiguration(value = TestProfileValueSource.class)
@IfProfileValue(name = "integration", value = "true")
public class UserMappingsTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void setup() {
        Artist favoriteArtist = new Artist("spotifyid", "name");
        Track favoriteTrack = new Track("spotifyId", "name", Collections.singletonList(favoriteArtist));
        user = new User("spotifyUserId", Collections.singletonList(favoriteArtist), Collections.singletonList(favoriteTrack), null);
    }

    @After
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void saveUser_ValidSave_ShouldSaveUser() {
        userRepository.save(user);

        User persistedUser = userRepository.findBySpotifyUserId("spotifyUserId").orElseThrow(NoSuchElementException::new);
        Assert.assertEquals(user, persistedUser);
    }

    @Test
    public void saveUser_AddNewArtist_ShouldUpdateUser() {
        userRepository.save(user);

        User persisted = userRepository.findBySpotifyUserId(user.getSpotifyUserId()).get();

        Artist newArtist = new Artist("spotifyid2", "name");
        ArrayList<Artist> mutableList = new ArrayList<>(persisted.getFavoriteArtists());
        mutableList.add(newArtist);
        persisted.setFavoriteArtists(mutableList);
        userRepository.save(persisted);

        Assert.assertEquals(1, userRepository.count());
    }

    @Test
    public void saveUser_RemoveArtist_ShouldUpdateUser() {
        userRepository.save(user);
        User persisted = userRepository.findBySpotifyUserId(user.getSpotifyUserId()).get();
        persisted.setFavoriteArtists(new ArrayList<>());
        userRepository.save(persisted);

        User updated = userRepository.findBySpotifyUserId(persisted.getSpotifyUserId()).get();
        Assert.assertEquals(0, updated.getFavoriteArtists().size());
    }
}
