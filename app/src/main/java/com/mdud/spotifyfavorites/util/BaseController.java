package com.mdud.spotifyfavorites.util;

import lombok.extern.java.Log;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

@Log
public abstract class BaseController {
    protected String redirectToReferer(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        return "redirect:" + referer;
    }

    protected void executeOnSuccessfulBinding(BindingResult result, Execute functionToExecute) {
        if(result.hasErrors()) {
            result.getFieldErrors().forEach(error -> log.warning(error.toString()));
        } else {
            functionToExecute.execute();
        }
    }
}

