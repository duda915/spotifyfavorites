package com.mdud.spotifyfavorites.track;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mdud.spotifyfavorites.artist.Artist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {
    @JsonProperty("id")
    @NotBlank
    private String id;
    @JsonProperty("name")
    @NotBlank
    private String name;
    @JsonProperty("artists")
    @NotEmpty
    @Valid
    private List<Artist> artists;
}

