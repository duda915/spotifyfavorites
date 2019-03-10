package com.mdud.spotifyfavorites.spotify;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SpotifySearchQuery {
    @NotEmpty
    private String query;
}
