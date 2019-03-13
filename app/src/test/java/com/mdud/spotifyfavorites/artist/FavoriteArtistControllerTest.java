package com.mdud.spotifyfavorites.artist;

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
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FavoriteArtistControllerTest {

    @InjectMocks
    private FavoriteArtistController favoriteArtistController;

    @Mock
    private FavoriteArtistService favoriteArtistService;

    private MockMvc mockMvc;
    private String endpoint = "/artist";

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(favoriteArtistController).alwaysDo(MockMvcResultHandlers.print()).build();
    }

    @Test
    public void getFavoriteArtists() throws Exception {
        List<Artist> returnList = Collections.singletonList(new Artist("id", "name"));
        when(favoriteArtistService.findUserFavoriteArtists("user")).thenReturn(returnList);
        Principal principal = () -> "user";

        mockMvc.perform(get(endpoint)
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute("artists", returnList))
                .andExpect(view().name("favorites"));

        Mockito.verify(favoriteArtistService, Mockito.times(1)).findUserFavoriteArtists("user");
    }

    @Test
    public void removeFavoriteArtist() throws Exception {
        Principal principal = () -> "user";

        mockMvc.perform(delete(endpoint + "/test")
                .principal(principal))
                .andExpect(status().isFound());

        verify(favoriteArtistService, times(1)).removeFavoriteArtist("user", "test");
    }

    @Test
    public void addFavoriteArtist_AddValidFavoriteArtist_ShouldAttributeHaveNoErrors() throws Exception {
        Principal principal = () -> "user";
        Artist artist = new Artist("id", "name");
        mockMvc.perform(post(endpoint)
                .principal(principal)
                .flashAttr("artist", artist))
                .andExpect(status().isFound())
                .andExpect(model().attributeHasNoErrors("artist"));

        verify(favoriteArtistService, times(1)).addFavoriteArtist("user", artist);
    }

    @Test
    public void addFavoriteArtist_AddInvalidFavoriteArtist_ShouldAttributeHaveErrors() throws Exception {
        Principal principal = () -> "user";
        Artist artist = new Artist();
        mockMvc.perform(post(endpoint)
                .principal(principal)
                .flashAttr("artist", artist))
                .andExpect(status().isFound())
                .andExpect(model().attributeHasFieldErrors("artist", "id", "name"));
    }
}