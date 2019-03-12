package com.mdud.spotifyfavorites.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoggingController {

    private final LoggingService loggingService;

    @Autowired
    public LoggingController(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @GetMapping("/log")
    public String getUserLogs(Principal principal, Model model) {
        model.addAttribute("logs", loggingService.getUserLogs(principal.getName()));
        return "logs";
    }
}
