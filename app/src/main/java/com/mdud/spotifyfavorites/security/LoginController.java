package com.mdud.spotifyfavorites.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {
    @GetMapping("login")
    public String loginView(@ModelAttribute("username") String username) {
        if(username != null) {
            return "redirect:/";
        }
        return "login";
    }
}
