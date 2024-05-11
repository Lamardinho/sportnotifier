package com.lamardinho.sportnotifier.footballdataorg.service.impl;

import com.lamardinho.sportnotifier.config.util.AppProfile;
import com.lamardinho.sportnotifier.footballdataorg.dto.FootballApiDTO;
import com.lamardinho.sportnotifier.footballdataorg.service.FootballDataOrgService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Log4j2
@Profile({AppProfile.DEFAULT, AppProfile.LOCAL, AppProfile.TEST})
public class FakeFootballDataOrgServiceImpl implements FootballDataOrgService {

    @Override
    public void checkAndNotifiesChampionsLeagueMatchesByDates(
            @NonNull String chatID,
            @NonNull LocalDate dateFrom,
            @NonNull LocalDate dateTo
    ) {
        log.info("{}: getChampionsLeagueMatchesByDatesAndSendToTelegram called", FakeFootballDataOrgServiceImpl.class);
    }

    @Override
    public FootballApiDTO getChampionsLeagueMatches(
            @NonNull LocalDate dateFrom,
            @NonNull LocalDate dateTo
    ) {
        log.info("{}: getChampionsLeagueMatches", FakeFootballDataOrgServiceImpl.class);
        return new FootballApiDTO();
    }
}
