package com.lamardinho.sportnotifier.footballdataorg.api;

import com.lamardinho.sportnotifier.footballdataorg.dto.FootballApiDTO;
import com.lamardinho.sportnotifier.footballdataorg.service.FootballDataOrgService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/football-data-org")
@RequiredArgsConstructor
@Log4j2
public class FootballDataOrgController {

    @NonNull
    private final FootballDataOrgService footballDataOrgService;

    @GetMapping("/matches")
    public ResponseEntity<FootballApiDTO> getChampionsLeagueMatches(
            @RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo
    ) {
        val result =
                footballDataOrgService.getChampionsLeagueMatches(dateFrom, dateTo);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/test")
    public ResponseEntity<Boolean> getChampionsLeagueMatchesByDatesAndSendToTelegram(
            @RequestParam("chatId") String chatId,
            @RequestParam("dateFrom") LocalDate dateFrom,
            @RequestParam("dateTo") LocalDate dateTo
    ) {
        footballDataOrgService.getChampionsLeagueMatchesByDatesAndSendToTelegram(chatId, dateFrom, dateTo);
        return ResponseEntity.ok(true);
    }
}
