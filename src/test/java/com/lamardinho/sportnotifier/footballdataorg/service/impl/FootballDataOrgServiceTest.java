package com.lamardinho.sportnotifier.footballdataorg.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamardinho.sportnotifier.footballdataorg.dto.MatchDTO;
import com.lamardinho.sportnotifier.footballdataorg.dto.TeamDTO;
import com.lamardinho.sportnotifier.messages.telegram.impl.TelegramMessageServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FootballDataOrgServiceTest {

    private final FootballDataOrgServiceImpl footballDataOrgService =
            new FootballDataOrgServiceImpl(
                    new ObjectMapper(),
                    new RestTemplate(),
                    new TelegramMessageServiceImpl(new RestTemplate())
            );

    @Test
    void createMatchesString() {
        val a1 = LocalDate.of(2023, 10, 3);
        val a2 = LocalDate.of(2023, 10, 5);
        final var m1 = new MatchDTO()
                .setUtcDate("2023-10-03T16:45:00Z")
                .setHomeTeam(new TeamDTO().setName("Real Madrid"))
                .setAwayTeam(new TeamDTO().setName("Manchester United"));
        final var m2 = new MatchDTO()
                .setUtcDate("2023-10-03T18:45:00Z")
                .setHomeTeam(new TeamDTO().setName("Barcelona"))
                .setAwayTeam(new TeamDTO().setName("Chelsea"));
        final var m3 = new MatchDTO()
                .setUtcDate("2023-10-03T19:45:00Z")
                .setHomeTeam(new TeamDTO().setName("PSG"))
                .setAwayTeam(new TeamDTO().setName("Manchester City"));
        final var matches = List.of(m1, m2, m3);

        // act:
        final var result = footballDataOrgService.createMatchesString(matches, a1, a2);

        System.out.println("\n" + result);
        assertThat(result).isEqualTo(
                """
                        Расписание матчей лиги чемпионов за период (2023-10-03 - 2023-10-05):

                        Real Madrid vs Manchester United (время: 2023-10-03T16:45:00Z)

                        Barcelona vs Chelsea (время: 2023-10-03T18:45:00Z)

                        PSG vs Manchester City (время: 2023-10-03T19:45:00Z)

                        """
        );
    }
}
