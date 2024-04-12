package com.lamardinho.sportnotifier.footballdataorg.scheduler;

import com.lamardinho.sportnotifier.footballdataorg.service.FootballDataOrgService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Component
@ConditionalOnProperty(value = "app.football-data-org.scheduler.conditional", havingValue = "true")
@RequiredArgsConstructor
@Log4j2
public class FootballDataOrgScheduler {

    @NonNull
    private final FootballDataOrgService footballDataOrgService;

    @Value("${app.telegram.chat-id.owner}")
    private String chatID;

    private LocalDate date;

    @Scheduled(initialDelay = 0, fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    public void updateToday() {
        val now = LocalDate.now();
        if (date == null || now.isAfter(date)) {
            footballDataOrgService.getChampionsLeagueMatchesByDatesAndSendToTelegram(
                    chatID, now, now
            );
        }
        date = now;
    }
}
