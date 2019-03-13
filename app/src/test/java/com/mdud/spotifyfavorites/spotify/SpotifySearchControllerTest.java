package com.mdud.spotifyfavorites.spotify;

import com.mdud.spotifyfavorites.artist.FavoriteArtistService;
import com.mdud.spotifyfavorites.track.FavoriteTrackService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class SpotifySearchControllerTest {

    @InjectMocks
    private SpotifySearchController spotifySearchController;

    @Mock
    private SpotifyService spotifyService;

    @Mock
    private FavoriteArtistService favoriteArtistService;

    @Mock
    private FavoriteTrackService favoriteTrackService;

    private MockMvc mockMvc;
    private Principal principal = () -> "user";
    private String endpoint = "/search";

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(spotifySearchController)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    public void searchView_SearchViewWithoutParameters_ShouldReturnTemplateWithoutModel() throws Exception {
        mockMvc.perform(get(endpoint)
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(model().size(0))
                .andExpect(view().name("searchview"));
    }

    @Test
    public void searchView_SearchForArtist_ShouldReturnTemplateWithArtistsAttribute() throws Exception {
        mockMvc.perform(get(endpoint)
                .principal(principal)
                .param("type", "artist")
                .param("query", "test"))
                .andExpect(status().isOk())
                .andExpect(model().size(3))
                .andExpect(view().name("searchview"));
    }

    @Test
    public void searchView_SearchForTrack_ShouldReturnTemplateWithTracksAttribute() throws Exception {
        mockMvc.perform(get(endpoint)
                .principal(principal)
                .param("type", "track")
                .param("query", "test"))
                .andExpect(status().isOk())
                .andExpect(model().size(3))
                .andExpect(view().name("searchview"));
    }
}