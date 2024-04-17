package com.lamardinho.sportnotifier.footballdataorg.service;

import com.lamardinho.sportnotifier.footballdataorg.dto.FootballApiDTO;
import lombok.NonNull;

import java.time.LocalDate;

public interface FootballDataOrgService {

    void checkAndNotifiesChampionsLeagueMatchesByDates(
            @NonNull String chatID,
            @NonNull LocalDate dateFrom,
            @NonNull LocalDate dateTo
    );

    FootballApiDTO getChampionsLeagueMatches(
            @NonNull LocalDate dateFrom,
            @NonNull LocalDate dateTo
    );
}
