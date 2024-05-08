package com.lamardinho.sportnotifier.common.controller.api;

import com.lamardinho.sportnotifier.common.aop.TrackExecutionTime;
import com.lamardinho.sportnotifier.common.service.DecodeTokenService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/decode-token")
@RequiredArgsConstructor
public class DecodeTokenController {

    @NonNull
    private final DecodeTokenService service;

    @Operation(summary = "распасрить токен")
    @GetMapping
    @TrackExecutionTime
    public Map<String, Object> decodeToken(@RequestParam("token") String token) {
        return service.decodeToken(token);
    }
}
