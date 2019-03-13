package com.mdud.spotifyfavorites.track;

import com.mdud.spotifyfavorites.artist.Artist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FavoriteTrackControllerTest {

    @InjectMocks
    private FavoriteTrackController favoriteTrackController;

    @Mock
    private FavoriteTrackService favoriteTrackService;

    private MockMvc mockMvc;
    private Principal principal = () -> "user";
    private String endpoint = "/track";

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(favoriteTrackController).alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    public void getFavoriteTracks() throws Exception {
        List<Track> trackList = Arrays.asList(new Track(), new Track());
        when(favoriteTrackService.findUserFavoriteTracks("user")).thenReturn(trackList);

        mockMvc.perform(get(endpoint)
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tracks", trackList))
                .andExpect(view().name("favorites"));

        verify(favoriteTrackService, times(1)).findUserFavoriteTracks("user");
    }

    @Test
    public void removeFavoriteTrack() throws Exception {
        mockMvc.perform(delete(endpoint + "/test")
                .principal(principal))
                .andExpect(status().isFound());

        verify(favoriteTrackService, times(1)).removeFavoriteTrack(principal.getName(), "test");
    }

    @Test
    public void addFavoriteTrack_AddValidFavoriteTrack_ShouldAttributeHaveNoErrors() throws Exception {
        Track track = new Track("track", "trackname", Collections.singletonList(new Artist("id", "name")));

        mockMvc.perform(post(endpoint)
                .principal(principal)
                .flashAttr("newTrack", track))
                .andExpect(status().isFound())
                .andExpect(model().attributeHasNoErrors("newTrack"));
        verify(favoriteTrackService, times(1)).addFavoriteTrack(principal.getName(), track);
    }

    @Test
    public void addFavoriteTrack_AddInvalidTrack_ShouldAttributeHaveThreeErrors() throws Exception {
        Track track = new Track();

        mockMvc.perform(post(endpoint)
                .principal(principal)
                .flashAttr("newTrack", track))
                .andExpect(status().isFound())
                .andExpect(model().attributeErrorCount("newTrack", 3));
    }

    @Test
    public void addFavoriteTrack_AddTrackWithInvalidArtists_ShouldAttributeHaveErrors() throws Exception {
        Track track = new Track("track", "track", Collections.singletonList(new Artist()));


        mockMvc.perform(post(endpoint)
                .principal(principal)
                .flashAttr("newTrack", track))
                .andExpect(status().isFound())
                .andExpect(model().attributeErrorCount("newTrack", 2));
    }
}