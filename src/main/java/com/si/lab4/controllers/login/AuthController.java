package com.si.lab4.controllers.login;

import com.si.lab4.model.requests.RegisterRequest;
import com.si.lab4.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @GetMapping(value = "/login")
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication) || authentication instanceof AnonymousAuthenticationToken) {
            ModelAndView model = new ModelAndView("login");
            model.addObject("isLogged", false);

            if (!Objects.isNull(error)) {
                model.addObject("info", "Invalid username or password");
            } else if (!Objects.isNull(logout)) {
                model.addObject("info", "You have successfully logged out!");
            } else {
                model.addObject("info", "");
            }

            return model;
        }
        return new ModelAndView("redirect:/");
    }

    @GetMapping(value = "/register")
    public ModelAndView registerPage(@RequestParam(value = "exist", required = false) String exist,
                                     @RequestParam(value = "password", required = false) String password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication) || authentication instanceof AnonymousAuthenticationToken) {
            ModelAndView model = new ModelAndView("register");
            model.addObject("isLogged", false);

            if (!Objects.isNull(exist)) {
                model.addObject("info", "This username already exists!");
            } else if (!Objects.isNull(password)) {
                model.addObject("info", "Passwords do not match!");
            } else {
                model.addObject("info", "");
            }

            return model;
        }
        return new ModelAndView("redirect:/soon");
    }

    @PostMapping(value = "/register")
    public ModelAndView processRegistration(@ModelAttribute RegisterRequest registerRequest) {
        authenticationService.registerNewUser(registerRequest);
        return new ModelAndView("redirect:/login");
    }
}
