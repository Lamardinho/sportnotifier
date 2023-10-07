package com.lamardinho.sportnotifier.footballdataorg.api;

import com.lamardinho.sportnotifier.footballdataorg.service.FootballDataOrgService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/football-data-org")
@RequiredArgsConstructor
@Log4j2
public class FootballDataOrgController {

    @NonNull
    private final FootballDataOrgService footballDataOrgService;

    @GetMapping("matches")
    public ResponseEntity<String> getChampionsLeagueMatches() {
        footballDataOrgService.test();
        return ResponseEntity.ok("Hello user");
    }
}
//https://api.football-data.org/v4/competitions/CL/matches?dateFrom=2023-10-03&dateTo=2023-10-03
