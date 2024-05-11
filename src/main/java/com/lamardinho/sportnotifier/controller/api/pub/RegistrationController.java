package com.lamardinho.sportnotifier.controller.api.pub;

import com.lamardinho.sportnotifier.dto.RegistrationDTO;
import com.lamardinho.sportnotifier.service.registration.RegistrationService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/registration")
@RequiredArgsConstructor
@Log4j2
public class RegistrationController {

    @NonNull
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<String> registration(@RequestBody @Valid RegistrationDTO dto) {
        val result = registrationService.processRegistration(dto);
        if (result > 0) {
            return ResponseEntity.ok()
                    .header("Location", "/login")
                    .body("Registration successful. Please login.");
        } else {
            // Если регистрация не удалась, возвращаем HTTP код 400 (Bad Request) и сообщение об ошибке
            return ResponseEntity.badRequest().body(null);
        }
    }
}
