package com.lamardinho.sportnotifier.footballdataorg.scheduler;

import com.lamardinho.sportnotifier.footballdataorg.service.FootballDataOrgService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@ConditionalOnProperty(value = "app.football-data-org.scheduler.conditional", havingValue = "true")
@RequiredArgsConstructor
@Log4j2
@Profile({"!test"})
public class FootballDataOrgScheduler {

    @NonNull
    private final FootballDataOrgService footballDataOrgService;

    @Value("${app.telegram.chat-id.owner}")
    private String chatID;

    private LocalDate date;

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron = "0 0 5 * * *")
    public void updateToday() {
        val now = LocalDate.now();
        if (date == null || now.isAfter(date)) {
            footballDataOrgService.checkAndNotifiesChampionsLeagueMatchesByDates(
                    chatID, now, now
            );
        }
        date = now;
    }
}
