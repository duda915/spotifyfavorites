package com.mdud.spotifyfavorites.integration.user;

import com.mdud.spotifyfavorites.TestProfileValueSource;
import com.mdud.spotifyfavorites.artist.Artist;
import com.mdud.spotifyfavorites.track.Track;
import com.mdud.spotifyfavorites.user.User;
import com.mdud.spotifyfavorites.user.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ProfileValueSourceConfiguration(value = TestProfileValueSource.class)
@IfProfileValue(name = "integration", value = "true")
public class UserMappingsTest {

    @Autowired
    private UserRepository userRepository;

    @After
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void saveUser() {
        Artist favoriteArtist = new Artist("spotifyid", "name", Collections.singletonList("test"));
        Track track = new Track("spotifyId", "name", Collections.singletonList(favoriteArtist), 300);
        User user = new User("spotifyUserId", Collections.singletonList(favoriteArtist), Collections.singletonList(track));

        userRepository.save(user);

        User persistedUser = userRepository.findBySpotifyUserId("spotifyUserId").orElseThrow(NoSuchElementException::new);
        Assert.assertEquals(user, persistedUser);
    }

}
