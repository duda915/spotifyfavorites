package com.mdud.spotifyfavorites.track;

import com.mdud.spotifyfavorites.artist.Artist;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Track {
    private String spotifyId;
    private String name;
    private List<Artist> artists;
    private Integer duration;
}
