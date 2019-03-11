package com.mdud.spotifyfavorites.track;

import com.mdud.spotifyfavorites.artist.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class FavoriteTrackController {

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

    @DeleteMapping("/track")
    public String removeFavoriteTracks(HttpServletRequest request, Principal principal,
                                       @ModelAttribute("removeTrack") Track track,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(System.out::println);
            return redirectToReferer(request);
        }

        favoriteTrackService.removeFavoriteTrack(principal.getName(), track.getId());
        return redirectToReferer(request);
    }

    private String redirectToReferer(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        return "redirect:" + referer;
    }
}
