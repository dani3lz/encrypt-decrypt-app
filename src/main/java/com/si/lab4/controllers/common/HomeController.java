package com.si.lab4.controllers.common;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView homePage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView model = new ModelAndView("home");
        model.addObject("isLogged", true);
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addObject("isLogged", false);
        }
        return model;
    }
}
