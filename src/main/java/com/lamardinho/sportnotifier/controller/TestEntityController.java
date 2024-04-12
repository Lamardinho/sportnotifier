package com.lamardinho.sportnotifier.controller;

import com.lamardinho.sportnotifier.sevice.TestEntityService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Log4j2
public class TestEntityController {

    @NonNull
    private final TestEntityService service;

    @GetMapping
    @Operation(summary = "найти все имена")
    public ResponseEntity<List<String>> getChampionsLeagueMatches() {
        return ResponseEntity.ok(service.getAllNames());
    }
}
