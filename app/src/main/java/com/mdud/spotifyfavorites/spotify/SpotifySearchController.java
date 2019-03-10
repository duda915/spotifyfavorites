package com.mdud.spotifyfavorites.spotify;

import com.mdud.spotifyfavorites.artist.Artist;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SpotifySearchController {
    private final SpotifyService spotifyService;

    public SpotifySearchController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @ModelAttribute("searchQuery")
    public SpotifySearchQuery searchQuery() {
        return new SpotifySearchQuery();
    }

    @GetMapping({"/", "/search"})
    public String searchView() {
        return "search";
    }

    @PostMapping("/search")
    public String getResults(@ModelAttribute @Valid SpotifySearchQuery spotifySearchQuery, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "search";
        }

        List<Artist> artistList = spotifyService.searchForArtists(spotifySearchQuery.getQuery());
        model.addAttribute("artists", artistList);
        return "search";
    }
}
