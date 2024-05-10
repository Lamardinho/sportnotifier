package com.lamardinho.sportnotifier.controller.thymeleaf;

import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage(@NonNull Model model, @NonNull Principal principal) {
        setDefaultModelAttributes(model, principal);
        return "main";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @GetMapping("/decode-token")
    public String decodeToken(@NonNull Model model, @NonNull Principal principal) {
        setDefaultModelAttributes(model, principal);
        return "decode-token";
    }

    private void setDefaultModelAttributes(@NonNull Model model, @NonNull Principal principal) {
        if (principal.getName() != null) {
            model.addAttribute("username", principal.getName());
            model.addAttribute("avatarPath", "/images/default_avatar.jpeg");
        }
    }
}
