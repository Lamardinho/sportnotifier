package com.lamardinho.sportnotifier.footballdataorg.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamardinho.sportnotifier.footballdataorg.dto.FootballApiDTO;
import com.lamardinho.sportnotifier.footballdataorg.dto.MatchDTO;
import com.lamardinho.sportnotifier.footballdataorg.service.FootballDataOrgService;
import com.lamardinho.sportnotifier.messages.dto.TelegramSendMessageDTO;
import com.lamardinho.sportnotifier.messages.service.TelegramMessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Collection;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Log4j2
@Profile({"prod", "vdsina"})
public class FootballDataOrgServiceImpl implements FootballDataOrgService {

    @NonNull
    private final ObjectMapper objectMapper;
    @NonNull
    private final RestTemplate restTemplate;
    @NonNull
    private final TelegramMessageService telegramMessageService;

    @Value("${app.football-data-org.token}")
    private String token;

    @Override
    public void checkAndNotifiesChampionsLeagueMatchesByDates(
            @NonNull String chatID,
            @NonNull LocalDate dateFrom,
            @NonNull LocalDate dateTo
    ) {
        val dto = getChampionsLeagueMatches(dateFrom, dateTo);
        val matches = dto.getMatches();
        if (matches.isEmpty()) {
            log.info(format("В указанном диапазоне нет матчей (%s - %s)", dateFrom, dateTo));
        } else {
            val msg = createMatchesString(matches, dateFrom, dateTo);
            log.info(msg);
            telegramMessageService.sendMessage(new TelegramSendMessageDTO(chatID, msg));

        }
    }

    @Override
    @SneakyThrows
    public FootballApiDTO getChampionsLeagueMatches(
            @NonNull LocalDate dateFrom,
            @NonNull LocalDate dateTo
    ) {
        val url = format(
                "https://api.football-data.org/v4/competitions/CL/matches?dateFrom=%s&dateTo=%s",
                dateFrom,
                dateTo
        );

        val headers = new HttpHeaders();
        headers.set("X-Auth-Token", token);
        val entity = new HttpEntity<>(headers);

        val response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        val body = response.getBody();
        return objectMapper.readValue(body, FootballApiDTO.class);
    }

    @NonNull
    protected String createMatchesString(
            @NonNull Collection<MatchDTO> matches,
            @NonNull LocalDate dateFrom,
            @NonNull LocalDate dateTo
    ) {
        val sb = new StringBuilder();
        val header = format("Расписание матчей лиги чемпионов за период (%s - %s):", dateFrom, dateTo);
        sb.append(header).append("\n\n");
        for (val match : matches) {
            sb
                    .append(match.getHomeTeam().getName()).append(" vs ").append(match.getAwayTeam().getName())
                    .append(" (время: ").append(match.getUtcDate()).append(")")
                    .append("\n\n");
        }

        return sb.toString();
    }
}
