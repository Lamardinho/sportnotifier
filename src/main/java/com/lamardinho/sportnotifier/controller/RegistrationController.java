package com.lamardinho.sportnotifier.controller;

import com.lamardinho.sportnotifier.dto.RegistrationForm;
import com.lamardinho.sportnotifier.service.RegistrationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
@Log4j2
public class RegistrationController {

    @NonNull
    private final RegistrationService registrationService;

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        return registrationService.processRegistration(form);
    }
}
