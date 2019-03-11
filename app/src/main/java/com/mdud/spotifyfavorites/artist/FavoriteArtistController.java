package com.mdud.spotifyfavorites.artist;

import com.mdud.spotifyfavorites.track.FavoriteTrackService;
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
public class FavoriteArtistController {

    private final FavoriteArtistService favoriteArtistService;

    @Autowired
    public FavoriteArtistController(FavoriteArtistService favoriteArtistService) {
        this.favoriteArtistService = favoriteArtistService;
    }

    @GetMapping("/artist")
    public String getFavoriteArtists(Principal principal, Model model) {
        model.addAttribute("artists", favoriteArtistService.findUserFavoriteArtists(principal.getName()));
        return "favorites";
    }

    @DeleteMapping("/artist")
    public String removeFavoriteArtist(HttpServletRequest request, Principal principal,
                                       @ModelAttribute("removeArtist") Artist artist,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(System.out::println);
            return redirectToReferer(request);
        }
        System.out.println("removing");
        favoriteArtistService.removeFavoriteArtist(principal.getName(), artist.getId());
        return redirectToReferer(request);
    }

    private String redirectToReferer(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        return "redirect:" + referer;
    }
}
