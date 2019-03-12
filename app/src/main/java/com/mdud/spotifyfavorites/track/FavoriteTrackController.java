package com.mdud.spotifyfavorites.track;

import com.mdud.spotifyfavorites.util.RefererController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class FavoriteTrackController extends RefererController {

    private final FavoriteTrackService favoriteTrackService;

    @Autowired
    public FavoriteTrackController(FavoriteTrackService favoriteTrackService) {
        this.favoriteTrackService = favoriteTrackService;
    }

    @GetMapping("/track")
    public String getFavoriteTracks(Principal principal, Model model) {
        model.addAttribute("tracks", favoriteTrackService.findUserFavoriteTracks(principal.getName()));
        return "favorites";
    }

    @DeleteMapping("/track/{trackId}")
    public String removeFavoriteTracks(HttpServletRequest request, Principal principal,
                                       @PathVariable("trackId") String trackId) {
        favoriteTrackService.removeFavoriteTrack(principal.getName(), trackId);
        return redirectToReferer(request);
    }

}
