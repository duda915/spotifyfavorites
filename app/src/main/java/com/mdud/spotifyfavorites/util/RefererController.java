package com.mdud.spotifyfavorites.util;

import javax.servlet.http.HttpServletRequest;

public abstract class RefererController {
    protected String redirectToReferer(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        return "redirect:" + referer;
    }
}
