package com.mdud.spotifyfavorites.spotify;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.mdud.spotifyfavorites.artist.Artist;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class SpotifyResponseMapperTest {

    private final String spotifyArtistListJson =
            "{\n" +
                    "  \"artists\": {\n" +
                    "    \"href\": \"https://api.spotify.com/v1/search?query=tania+bowra&type=artist&market=PL&offset=0&limit=20\",\n" +
                    "    \"items\": [\n" +
                    "      {\n" +
                    "        \"external_urls\": {\n" +
                    "          \"spotify\": \"https://open.spotify.com/artist/08td7MxkoHQkXnWAYD8d6Q\"\n" +
                    "        },\n" +
                    "        \"followers\": {\n" +
                    "          \"href\": null,\n" +
                    "          \"total\": 168\n" +
                    "        },\n" +
                    "        \"genres\": [],\n" +
                    "        \"href\": \"https://api.spotify.com/v1/artists/08td7MxkoHQkXnWAYD8d6Q\",\n" +
                    "        \"id\": \"08td7MxkoHQkXnWAYD8d6Q\",\n" +
                    "        \"images\": [\n" +
                    "          {\n" +
                    "            \"height\": 640,\n" +
                    "            \"url\": \"https://i.scdn.co/image/a529b65b4bd322b16bee34672ce45278e890e176\",\n" +
                    "            \"width\": 640\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"height\": 300,\n" +
                    "            \"url\": \"https://i.scdn.co/image/985cc10acdbbedb6a16d7c74f9e23553e2b28dbc\",\n" +
                    "            \"width\": 300\n" +
                    "          },\n" +
                    "          {\n" +
                    "            \"height\": 64,\n" +
                    "            \"url\": \"https://i.scdn.co/image/37b46a2662c09502885d1804c1c865b199cc3d67\",\n" +
                    "            \"width\": 64\n" +
                    "          }\n" +
                    "        ],\n" +
                    "        \"name\": \"Tania Bowra\",\n" +
                    "        \"popularity\": 0,\n" +
                    "        \"type\": \"artist\",\n" +
                    "        \"uri\": \"spotify:artist:08td7MxkoHQkXnWAYD8d6Q\"\n" +
                    "      }\n" +
                    "    ],\n" +
                    "    \"limit\": 20,\n" +
                    "    \"next\": null,\n" +
                    "    \"offset\": 0,\n" +
                    "    \"previous\": null,\n" +
                    "    \"total\": 1\n" +
                    "  }\n" +
                    "}";

    @Test
    public void toArtistsList() throws IOException {

        List<Artist> artists = SpotifyResponseMapper.toArtistsList(spotifyArtistListJson);

        Artist expectedArtist = new Artist("08td7MxkoHQkXnWAYD8d6Q", "Tania Bowra", new ArrayList<>());
        List<Artist> expectedArtists = new ArrayList<>();
        expectedArtists.add(expectedArtist);

        Assert.assertEquals(expectedArtists, artists);
    }

}