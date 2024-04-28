package com.lamardinho.sportnotifier.controller.api;

import com.lamardinho.sportnotifier.dto.RegistrationDTO;
import com.lamardinho.sportnotifier.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
@Log4j2
public class RegistrationController {

    @NonNull
    private final RegistrationService registrationService;

    @GetMapping
    @PreAuthorize("isAnonymous()")
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@RequestBody @Valid RegistrationDTO form) {
        return registrationService.processRegistration(form);
    }
}
