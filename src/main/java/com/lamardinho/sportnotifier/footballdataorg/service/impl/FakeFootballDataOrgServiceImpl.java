package com.lamardinho.sportnotifier.footballdataorg.service.impl;

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
@Profile({"default", "local", "test"})
public class FakeFootballDataOrgServiceImpl implements FootballDataOrgService {

    @Override
    public boolean getChampionsLeagueMatchesByDatesAndSendToTelegram(
            @NonNull String chatID,
            @NonNull LocalDate dateFrom,
            @NonNull LocalDate dateTo
    ) {
        log.info(FakeFootballDataOrgServiceImpl.class + ": getChampionsLeagueMatchesByDatesAndSendToTelegram");
        return true;
    }

    @Override
    public FootballApiDTO getChampionsLeagueMatches(
            @NonNull LocalDate dateFrom,
            @NonNull LocalDate dateTo
    ) {
        log.info(FakeFootballDataOrgServiceImpl.class + ": getChampionsLeagueMatches");
        return new FootballApiDTO();
    }
}
