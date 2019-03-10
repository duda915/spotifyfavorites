package com.mdud.spotifyfavorites.spotify;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView searchView() {
        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView getResults(@ModelAttribute SpotifySearchQuery spotifySearchQuery) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("results", "results");
        System.out.println(spotifyService.searchForArtists(spotifySearchQuery.getQuery()));
        return modelAndView;
    }
}
