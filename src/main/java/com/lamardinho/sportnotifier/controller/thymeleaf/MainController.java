package com.lamardinho.sportnotifier.controller.thymeleaf;

import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {
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
    public String decodeToken() {
        return "decode-token";
    }

    @ModelAttribute
    public void addAttributes(@NonNull Model model, Principal principal) {
        if (principal != null && principal.getName() != null) {
            model.addAttribute("username", principal.getName());
            model.addAttribute("avatarPath", "/images/default_avatar.jpeg");
        }
    }
}
