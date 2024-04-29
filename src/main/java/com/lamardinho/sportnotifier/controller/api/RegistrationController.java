package com.lamardinho.sportnotifier.controller.api;

import com.lamardinho.sportnotifier.dto.RegistrationDTO;
import com.lamardinho.sportnotifier.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
@Log4j2
public class RegistrationController {

    @NonNull
    private final RegistrationService registrationService;

    @PostMapping
    public String registration(@RequestBody @Valid RegistrationDTO dto) {
        return registrationService.processRegistration(dto);
    }
}
