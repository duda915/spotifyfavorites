package com.mdud.spotifyfavorites.spotify;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.mdud.spotifyfavorites.artist.Artist;
import com.mdud.spotifyfavorites.track.Track;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class SpotifyResponseMapper {
    public List<Artist> toArtistsList(String response)  {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode node = objectMapper.readTree(response);
            return objectMapper.readValue(node.get("artists").get("items").toString(), new TypeReference<List<Artist>>(){});
        } catch (IOException e) {
            throw new RuntimeJsonMappingException("failed to convert spotify artists search response");
        }
    }

    public List<Track> toSongsList(String response) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode node = objectMapper.readTree(response);
            return objectMapper.readValue(node.get("tracks").get("items").toString(), new TypeReference<List<Track>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeJsonMappingException("failed to convert spotify tracks search response");
        }
    }
}
