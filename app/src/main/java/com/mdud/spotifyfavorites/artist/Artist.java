package com.mdud.spotifyfavorites.artist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("artists.items")
public class Artist {
    @JsonProperty("id")
    private String spotifyId;
    @JsonProperty("name")
    private String artistName;
    @JsonProperty("genres")
    private List<String> genres;
}
