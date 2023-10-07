package com.lamardinho.sportnotifier.footballdataorg.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamardinho.sportnotifier.footballdataorg.dto.FootballApiDTO;
import com.lamardinho.sportnotifier.footballdataorg.dto.MatchDTO;
import com.lamardinho.sportnotifier.messages.dto.TelegramSendMessageDTO;
import com.lamardinho.sportnotifier.messages.service.TelegramMessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Log4j2
public class FootballDataOrgService {

    @NonNull
    private final ObjectMapper objectMapper;
    @NonNull
    private final RestTemplate restTemplate;
    @NonNull
    private final TelegramMessageService telegramMessageService;

    @Value("${app.football-data-org.token}")
    private String token;

    public void test(@NonNull String chatID) {
        val dto = getChampionsLeagueMatches("2023-10-03", "2023-10-03");
        val matches = dto.getMatches();
        val msg = createMatchesString(matches);
        log.info(msg);
        telegramMessageService.sendMessage(new TelegramSendMessageDTO(chatID, msg));
    }

    @NonNull
    public String createMatchesString(@NonNull Collection<MatchDTO> matches) {
        val sb = new StringBuilder();
        sb.append("Расписание матчей лиги чемпионов на сегодня:\n\n");
        for (val match : matches) {
            sb
                    .append(match.getHomeTeam().getName()).append(" vs ").append(match.getAwayTeam().getName())
                    .append(" (время: ").append(match.getUtcDate()).append(")")
                    .append("\n\n");
        }

        return sb.toString();
    }

    @SneakyThrows
    public FootballApiDTO getChampionsLeagueMatches(
            @NonNull String dateFrom,
            @NonNull String dateTo
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
        val result = objectMapper.readValue(body, FootballApiDTO.class);
        log.info("ok");
        return result;
    }
}
