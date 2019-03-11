package com.mdud.spotifyfavorites.spotify;

import com.mdud.spotifyfavorites.artist.Artist;
import com.mdud.spotifyfavorites.artist.FavoriteArtistService;
import com.mdud.spotifyfavorites.track.FavoriteTrackService;
import com.mdud.spotifyfavorites.track.Track;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Arrays;

@Controller
public class SpotifySearchController {
    private final SpotifyService spotifyService;
    private final FavoriteArtistService favoriteArtistService;
    private final FavoriteTrackService favoriteTrackService;

    @Autowired
    public SpotifySearchController(SpotifyService spotifyService, FavoriteArtistService favoriteArtistService, FavoriteTrackService favoriteTrackService) {
        this.spotifyService = spotifyService;
        this.favoriteArtistService = favoriteArtistService;
        this.favoriteTrackService = favoriteTrackService;
    }

    @GetMapping({"/", "search"})
    public String searchView(Principal principal, @RequestParam(required = false) String query, @RequestParam(required = false) String type, Model model) {
        if(StringUtils.isBlank(query) || StringUtils.isBlank(type)) {
            return "search";
        }

        switch (type) {
            case "artist":
                ArrayList<Artist> artistList = new ArrayList<>(spotifyService.searchForArtists(query));
                artistList.removeAll(favoriteArtistService.findUserFavoriteArtists(principal.getName()));
                model.addAttribute("artists", artistList);
                break;
            case "track":
                ArrayList<Track> tracks = new ArrayList<>(spotifyService.searchForSongs(query));
                tracks.removeAll(favoriteTrackService.findUserFavoriteTracks(principal.getName()));
                model.addAttribute("tracks", tracks);
                break;
        }

        return "search";
    }

    @PostMapping("/track")
    public String addFavoriteTrack(HttpServletRequest request, Principal principal,
                                   @ModelAttribute("newTrack") @Valid Track newTrack,
                                   BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(System.out::println);
            return redirectToReferer(request);
        }

        favoriteTrackService.addFavoriteTrack(principal.getName(), newTrack);
        return redirectToReferer(request);
    }

    @PostMapping("/artist")
    public String addFavoriteArtist(HttpServletRequest request,
                                    Principal principal,
                                    @ModelAttribute("artist") @Valid Artist newArtist,
                                    BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(System.out::println);
            return redirectToReferer(request);
        }

        favoriteArtistService.addFavoriteArtist(principal.getName(), newArtist);
        return redirectToReferer(request);
    }

    private String redirectToReferer(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        return "redirect:" + referer;
    }
}
