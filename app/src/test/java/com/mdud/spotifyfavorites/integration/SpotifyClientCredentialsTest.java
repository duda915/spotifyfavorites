package com.mdud.spotifyfavorites.integration;

import com.mdud.spotifyfavorites.TestProfileValueSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ProfileValueSourceConfiguration(value = TestProfileValueSource.class)
@IfProfileValue(name = "integration", value = "true")
public class SpotifyClientCredentialsTest {

    @Value("${spotify.client.clientId}")
    private String clientId;

    @Value("${spotify.client.clientSecret}")
    private String clientSecret;

    @Value("${spotify.client.accessTokenUri}")
    private String accessTokenUri;

    @Test
    public void testSpotifyClientCredentials() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + Base64Utils.encodeToString((clientId + ":" + clientSecret).getBytes()));

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(accessTokenUri, HttpMethod.POST, request, String.class);
            Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        } catch (HttpStatusCodeException e) {
            System.out.println(e.getResponseBodyAsString());
            Assert.fail();
        }
    }
}
