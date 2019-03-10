package com.mdud.spotifyfavorites.spotify;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.mdud.spotifyfavorites.artist.Artist;
import com.mdud.spotifyfavorites.track.Track;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class SpotifyResponseMapperTest {
    @InjectMocks
    private SpotifyResponseMapper spotifyResponseMapper;

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

    private final String spotifyTrackListJson =
    "{\n"+
            "  \"tracks\": {\n"+
            "    \"href\": \"https://api.spotify.com/v1/search?query=billie+jean&type=track&market=PL&offset=0&limit=20\",\n"+
            "    \"items\": [\n"+
            "      {\n"+
            "        \"album\": {\n"+
            "          \"album_type\": \"album\",\n"+
            "          \"artists\": [\n"+
            "            {\n"+
            "              \"external_urls\": {\n"+
            "                \"spotify\": \"https://open.spotify.com/artist/3fMbdgg4jU18AjLCKBhRSm\"\n"+
            "              },\n"+
            "              \"href\": \"https://api.spotify.com/v1/artists/3fMbdgg4jU18AjLCKBhRSm\",\n"+
            "              \"id\": \"3fMbdgg4jU18AjLCKBhRSm\",\n"+
            "              \"name\": \"Michael Jackson\",\n"+
            "              \"type\": \"artist\",\n"+
            "              \"uri\": \"spotify:artist:3fMbdgg4jU18AjLCKBhRSm\"\n"+
            "            }\n"+
            "          ],\n"+
            "          \"available_markets\": [\n"+
            "            \"AD\",\n"+
            "            \"AE\",\n"+
            "            \"AR\",\n"+
            "            \"AT\",\n"+
            "            \"AU\",\n"+
            "            \"BE\",\n"+
            "            \"BG\",\n"+
            "            \"BH\",\n"+
            "            \"BO\",\n"+
            "            \"BR\",\n"+
            "            \"CA\",\n"+
            "            \"CH\",\n"+
            "            \"CL\",\n"+
            "            \"CO\",\n"+
            "            \"CR\",\n"+
            "            \"CY\",\n"+
            "            \"CZ\",\n"+
            "            \"DE\",\n"+
            "            \"DK\",\n"+
            "            \"DO\",\n"+
            "            \"DZ\",\n"+
            "            \"EC\",\n"+
            "            \"EE\",\n"+
            "            \"EG\",\n"+
            "            \"ES\",\n"+
            "            \"FI\",\n"+
            "            \"FR\",\n"+
            "            \"GB\",\n"+
            "            \"GR\",\n"+
            "            \"GT\",\n"+
            "            \"HK\",\n"+
            "            \"HN\",\n"+
            "            \"HU\",\n"+
            "            \"ID\",\n"+
            "            \"IE\",\n"+
            "            \"IL\",\n"+
            "            \"IN\",\n"+
            "            \"IS\",\n"+
            "            \"IT\",\n"+
            "            \"JO\",\n"+
            "            \"JP\",\n"+
            "            \"KW\",\n"+
            "            \"LB\",\n"+
            "            \"LI\",\n"+
            "            \"LT\",\n"+
            "            \"LU\",\n"+
            "            \"LV\",\n"+
            "            \"MA\",\n"+
            "            \"MC\",\n"+
            "            \"MT\",\n"+
            "            \"MX\",\n"+
            "            \"MY\",\n"+
            "            \"NI\",\n"+
            "            \"NL\",\n"+
            "            \"NO\",\n"+
            "            \"NZ\",\n"+
            "            \"OM\",\n"+
            "            \"PA\",\n"+
            "            \"PE\",\n"+
            "            \"PH\",\n"+
            "            \"PL\",\n"+
            "            \"PS\",\n"+
            "            \"PT\",\n"+
            "            \"PY\",\n"+
            "            \"QA\",\n"+
            "            \"RO\",\n"+
            "            \"SA\",\n"+
            "            \"SE\",\n"+
            "            \"SG\",\n"+
            "            \"SK\",\n"+
            "            \"SV\",\n"+
            "            \"TH\",\n"+
            "            \"TN\",\n"+
            "            \"TR\",\n"+
            "            \"TW\",\n"+
            "            \"US\",\n"+
            "            \"UY\",\n"+
            "            \"VN\",\n"+
            "            \"ZA\"\n"+
            "          ],\n"+
            "          \"external_urls\": {\n"+
            "            \"spotify\": \"https://open.spotify.com/album/1C2h7mLntPSeVYciMRTF4a\"\n"+
            "          },\n"+
            "          \"href\": \"https://api.spotify.com/v1/albums/1C2h7mLntPSeVYciMRTF4a\",\n"+
            "          \"id\": \"1C2h7mLntPSeVYciMRTF4a\",\n"+
            "          \"images\": [\n"+
            "            {\n"+
            "              \"height\": 568,\n"+
            "              \"url\": \"https://i.scdn.co/image/3c294000d8739af300d9a842934d6f7e090471c7\",\n"+
            "              \"width\": 640\n"+
            "            },\n"+
            "            {\n"+
            "              \"height\": 266,\n"+
            "              \"url\": \"https://i.scdn.co/image/d50dab8e3338d47ddc84195a0779b6b80704f8c5\",\n"+
            "              \"width\": 300\n"+
            "            },\n"+
            "            {\n"+
            "              \"height\": 57,\n"+
            "              \"url\": \"https://i.scdn.co/image/eb2b6bb819fd2b1795acd65efc8e1a975c487afc\",\n"+
            "              \"width\": 64\n"+
            "            }\n"+
            "          ],\n"+
            "          \"name\": \"Thriller 25 Super Deluxe Edition\",\n"+
            "          \"release_date\": \"1982-11-30\",\n"+
            "          \"release_date_precision\": \"day\",\n"+
            "          \"total_tracks\": 30,\n"+
            "          \"type\": \"album\",\n"+
            "          \"uri\": \"spotify:album:1C2h7mLntPSeVYciMRTF4a\"\n"+
            "        },\n"+
            "        \"artists\": [\n"+
            "          {\n"+
            "            \"external_urls\": {\n"+
            "              \"spotify\": \"https://open.spotify.com/artist/3fMbdgg4jU18AjLCKBhRSm\"\n"+
            "            },\n"+
            "            \"href\": \"https://api.spotify.com/v1/artists/3fMbdgg4jU18AjLCKBhRSm\",\n"+
            "            \"id\": \"3fMbdgg4jU18AjLCKBhRSm\",\n"+
            "            \"name\": \"Michael Jackson\",\n"+
            "            \"type\": \"artist\",\n"+
            "            \"uri\": \"spotify:artist:3fMbdgg4jU18AjLCKBhRSm\"\n"+
            "          }\n"+
            "        ],\n"+
            "        \"available_markets\": [\n"+
            "          \"AD\",\n"+
            "          \"AE\",\n"+
            "          \"AR\",\n"+
            "          \"AT\",\n"+
            "          \"AU\",\n"+
            "          \"BE\",\n"+
            "          \"BG\",\n"+
            "          \"BH\",\n"+
            "          \"BO\",\n"+
            "          \"BR\",\n"+
            "          \"CA\",\n"+
            "          \"CH\",\n"+
            "          \"CL\",\n"+
            "          \"CO\",\n"+
            "          \"CR\",\n"+
            "          \"CY\",\n"+
            "          \"CZ\",\n"+
            "          \"DE\",\n"+
            "          \"DK\",\n"+
            "          \"DO\",\n"+
            "          \"DZ\",\n"+
            "          \"EC\",\n"+
            "          \"EE\",\n"+
            "          \"EG\",\n"+
            "          \"ES\",\n"+
            "          \"FI\",\n"+
            "          \"FR\",\n"+
            "          \"GB\",\n"+
            "          \"GR\",\n"+
            "          \"GT\",\n"+
            "          \"HK\",\n"+
            "          \"HN\",\n"+
            "          \"HU\",\n"+
            "          \"ID\",\n"+
            "          \"IE\",\n"+
            "          \"IL\",\n"+
            "          \"IN\",\n"+
            "          \"IS\",\n"+
            "          \"IT\",\n"+
            "          \"JO\",\n"+
            "          \"JP\",\n"+
            "          \"KW\",\n"+
            "          \"LB\",\n"+
            "          \"LI\",\n"+
            "          \"LT\",\n"+
            "          \"LU\",\n"+
            "          \"LV\",\n"+
            "          \"MA\",\n"+
            "          \"MC\",\n"+
            "          \"MT\",\n"+
            "          \"MX\",\n"+
            "          \"MY\",\n"+
            "          \"NI\",\n"+
            "          \"NL\",\n"+
            "          \"NO\",\n"+
            "          \"NZ\",\n"+
            "          \"OM\",\n"+
            "          \"PA\",\n"+
            "          \"PE\",\n"+
            "          \"PH\",\n"+
            "          \"PL\",\n"+
            "          \"PS\",\n"+
            "          \"PT\",\n"+
            "          \"PY\",\n"+
            "          \"QA\",\n"+
            "          \"RO\",\n"+
            "          \"SA\",\n"+
            "          \"SE\",\n"+
            "          \"SG\",\n"+
            "          \"SK\",\n"+
            "          \"SV\",\n"+
            "          \"TH\",\n"+
            "          \"TN\",\n"+
            "          \"TR\",\n"+
            "          \"TW\",\n"+
            "          \"US\",\n"+
            "          \"UY\",\n"+
            "          \"VN\",\n"+
            "          \"ZA\"\n"+
            "        ],\n"+
            "        \"disc_number\": 1,\n"+
            "        \"duration_ms\": 293826,\n"+
            "        \"explicit\": false,\n"+
            "        \"external_ids\": {\n"+
            "          \"isrc\": \"USSM19902991\"\n"+
            "        },\n"+
            "        \"external_urls\": {\n"+
            "          \"spotify\": \"https://open.spotify.com/track/5ChkMS8OtdzJeqyybCc9R5\"\n"+
            "        },\n"+
            "        \"href\": \"https://api.spotify.com/v1/tracks/5ChkMS8OtdzJeqyybCc9R5\",\n"+
            "        \"id\": \"5ChkMS8OtdzJeqyybCc9R5\",\n"+
            "        \"is_local\": false,\n"+
            "        \"name\": \"Billie Jean\",\n"+
            "        \"popularity\": 83,\n"+
            "        \"preview_url\": \"https://p.scdn.co/mp3-preview/4eb779428d40d579f14d12a9daf98fc66c7d0be4?cid=774b29d4f13844c495f206cafdad9c86\",\n"+
            "        \"track_number\": 6,\n"+
            "        \"type\": \"track\",\n"+
            "        \"uri\": \"spotify:track:5ChkMS8OtdzJeqyybCc9R5\"\n"+
            "      }]}}";

    @Test
    public void toArtistsList() {

        List<Artist> artists = spotifyResponseMapper.toArtistsList(spotifyArtistListJson);

        Artist expectedArtist = new Artist("08td7MxkoHQkXnWAYD8d6Q", "Tania Bowra");
        List<Artist> expectedArtists = new ArrayList<>();
        expectedArtists.add(expectedArtist);

        Assert.assertEquals(expectedArtists, artists);
    }

    @Test
    public void toSongsList() {
        List<Track> songs = spotifyResponseMapper.toSongsList(spotifyTrackListJson);

        Artist expectedArtist = new Artist("3fMbdgg4jU18AjLCKBhRSm", "Michael Jackson");
        Track expectedTrack = new Track("5ChkMS8OtdzJeqyybCc9R5", "Billie Jean", Collections.singletonList(expectedArtist), 293826);

        assertEquals(Collections.singletonList(expectedTrack), songs);
    }

}