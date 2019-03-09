package com.mdud.spotifyfavorites.user;

import com.mdud.spotifyfavorites.security.OAuthUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/me")
    public String me() {
        return OAuthUser.getSpotifyUserId();
    }
}
