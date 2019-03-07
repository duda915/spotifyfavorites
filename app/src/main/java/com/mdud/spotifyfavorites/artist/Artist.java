package com.mdud.spotifyfavorites.artist;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Artist {
    private String spotifyId;
    private String artistName;
    private List<String> genres;
}
