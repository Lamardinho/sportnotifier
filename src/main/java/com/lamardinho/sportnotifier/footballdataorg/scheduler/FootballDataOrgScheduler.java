package com.lamardinho.sportnotifier.footballdataorg.scheduler;

import com.lamardinho.sportnotifier.footballdataorg.service.FootballDataOrgService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Log4j2
public class FootballDataOrgScheduler {

    @NonNull
    private final FootballDataOrgService footballDataOrgService;

    @Value("${app.telegram.bot.chat.id.lamardinho}")
    private String chatID;

    private boolean sent;

    @Scheduled(fixedRate = 1, initialDelay = 0, timeUnit = TimeUnit.HOURS)
    @Transactional
    public void updateToday() {
        if (sent) {
            return;
        }
        val now = LocalDate.now();
        sent = footballDataOrgService.getChampionsLeagueMatchesByDatesAndSendToTelegram(
                chatID, now, now
        );
    }

    @Scheduled(cron = "0 0 10 * * ?")
    @Transactional
    public void sent() {
        sent = false;
        log.info("Good morning");
        updateToday();
    }
}
