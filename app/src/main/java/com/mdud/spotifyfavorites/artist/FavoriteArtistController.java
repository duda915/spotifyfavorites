package com.mdud.spotifyfavorites.artist;

import com.mdud.spotifyfavorites.util.RefererController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class FavoriteArtistController extends RefererController {

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

    @DeleteMapping("/artist/{artistId}")
    public String removeFavoriteArtist(HttpServletRequest request, Principal principal,
                                       @PathVariable("artistId") String artistId) {

        favoriteArtistService.removeFavoriteArtist(principal.getName(), artistId);
        return redirectToReferer(request);
    }

    @PostMapping("/artist")
    public String addFavoriteArtist(HttpServletRequest request,
                                    Principal principal,
                                    @ModelAttribute("artist") @Valid Artist newArtist,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(System.out::println);
            return redirectToReferer(request);
        }

        favoriteArtistService.addFavoriteArtist(principal.getName(), newArtist);
        return redirectToReferer(request);
    }
}
