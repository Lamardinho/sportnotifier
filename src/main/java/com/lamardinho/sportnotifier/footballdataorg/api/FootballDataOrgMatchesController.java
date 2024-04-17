package com.lamardinho.sportnotifier.footballdataorg.api;

import com.lamardinho.sportnotifier.footballdataorg.dto.FootballApiDTO;
import com.lamardinho.sportnotifier.footballdataorg.service.FootballDataOrgService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/football-data-org/match")
@RequiredArgsConstructor
@Log4j2
public class FootballDataOrgMatchesController {

    @NonNull
    private final FootballDataOrgService footballDataOrgService;

    @GetMapping("/schedule/champions-league/by-period/look")
    @Operation(
            summary = "просмотр расписания",
            description = "позволяет посмотреть матчи лиги чемпионов за конкретный период"
    )
    public ResponseEntity<FootballApiDTO> getChampionsLeagueMatches(
            @RequestParam(value = "dateFrom", defaultValue = "2023-10-03") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dateFrom,
            @RequestParam(value = "dateTo", defaultValue = "2023-10-03") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dateTo
    ) {
        val result =
                footballDataOrgService.getChampionsLeagueMatches(dateFrom, dateTo);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/schedule/champions-league/by-period/send")
    @Operation(
            summary = "для тестов: отправка расписания",
            description = "для тестов: отправляет конкретному пользователю сообщение о матчах лиги чемпионов за указанный период времени"
    )
    public ResponseEntity<Boolean> checkAndNotifiesChampionsLeagueMatchesByDates(
            @RequestParam("chatId") String chatId,
            @RequestParam(value = "dateFrom", defaultValue = "2023-10-03") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dateFrom,
            @RequestParam(value = "dateTo", defaultValue = "2023-10-03") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dateTo
    ) {
        footballDataOrgService.checkAndNotifiesChampionsLeagueMatchesByDates(chatId, dateFrom, dateTo);
        return ResponseEntity.ok(true);
    }
}
