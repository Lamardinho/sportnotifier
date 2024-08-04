package com.lamardinho.sportnotifier.controller.api;

import com.lamardinho.sportnotifier.common.aop.TrackExecutionTime;
import com.lamardinho.sportnotifier.dto.TokenRequest;
import com.lamardinho.sportnotifier.service.ParseTokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/public/parse-token")
@RequiredArgsConstructor
public class ParseTokenController {

    @NonNull
    private final ParseTokenService service;

    @Operation(summary = "распасрить токен")
    @PostMapping
    @TrackExecutionTime
    public Map<String, Object> parseToken(@RequestBody @NonNull @Valid TokenRequest token) {
        return service.parseToken(token.getToken());
    }
}
