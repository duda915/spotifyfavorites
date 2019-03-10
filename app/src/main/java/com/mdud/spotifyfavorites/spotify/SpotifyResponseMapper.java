package com.mdud.spotifyfavorites.spotify;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.mdud.spotifyfavorites.artist.Artist;
import com.mdud.spotifyfavorites.track.Track;

import java.io.IOException;
import java.util.List;

class SpotifyResponseMapper {
    static List<Artist> toArtistsList(String response)  {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode node = objectMapper.readTree(response);
            return objectMapper.readValue(node.get("artists").get("items").toString(), new TypeReference<List<Artist>>(){});
        } catch (IOException e) {
            throw new RuntimeJsonMappingException("failed to convert spotify artists search response");
        }
    }

    static List<Track> toSongsList(String response) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode node = objectMapper.readTree(response);
            return objectMapper.readValue(node.get("tracks").get("items").toString(), new TypeReference<List<Artist>>(){});
        } catch (IOException e) {
            throw new RuntimeJsonMappingException("failed to convert spotify tracks search response");
        }
    }
}
