package com.mdud.spotifyfavorites.spotify;

import com.mdud.spotifyfavorites.artist.Artist;
import com.mdud.spotifyfavorites.artist.FavoriteArtistService;
import com.mdud.spotifyfavorites.spotify.user.SpotifyUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SpotifySearchController {
    private final SpotifyService spotifyService;
    private final FavoriteArtistService favoriteArtistService;


    public SpotifySearchController(SpotifyService spotifyService, FavoriteArtistService favoriteArtistService) {
        this.spotifyService = spotifyService;
        this.favoriteArtistService = favoriteArtistService;
    }

    @GetMapping({"/", "search"})
    public String searchView(Principal principal, @RequestParam(required = false) String query, Model model) {
        if(StringUtils.isBlank(query)) {
            return "search";
        }

        ArrayList<Artist> artistList = new ArrayList<>(spotifyService.searchForArtists(query));
        artistList.removeAll(favoriteArtistService.findUserFavoriteArtists(principal.getName()));
        model.addAttribute("artists", artistList);
        return "search";
    }

    @PostMapping("/artist")
    public String addFavoriteArtist(HttpServletRequest request,
                                    Principal principal,
                                    @ModelAttribute("artist") @Valid Artist newArtist,
                                    BindingResult bindingResult, Model model) {
        String referer = request.getHeader("referer");
        if(bindingResult.hasErrors()) {
            return "redirect:" + referer;
        }

        favoriteArtistService.addFavoriteArtist(principal.getName(), newArtist);
        return "redirect:" + referer;
    }
}
