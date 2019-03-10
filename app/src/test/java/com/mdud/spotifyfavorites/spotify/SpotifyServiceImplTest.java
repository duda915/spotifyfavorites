package com.mdud.spotifyfavorites.spotify;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.RestOperations;

import static org.junit.Assert.*;

public class SpotifyServiceImplTest {

    @InjectMocks
    private SpotifyServiceImpl spotifyService;

    @Mock
    private RestOperations restOperations;

    @Test
    public void searchForArtists() {
    }

}