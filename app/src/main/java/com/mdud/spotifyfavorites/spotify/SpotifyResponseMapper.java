package com.mdud.spotifyfavorites.spotify;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdud.spotifyfavorites.artist.Artist;

import java.io.IOException;
import java.util.List;

public class SpotifyResponseMapper {
    public static List<Artist> toArtistsList(String response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode node = objectMapper.readTree(response);
        return objectMapper.readValue(node.get("artists").get("items").toString(), new TypeReference<List<Artist>>(){});
    }
}
