package com.lamardinho.sportnotifier.common;

import com.lamardinho.sportnotifier.messages.dto.TelegramSendMessageDTO;
import com.lamardinho.sportnotifier.messages.service.TelegramMessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class AppStartedLogInfo {

    @NonNull
    private final Environment environment;
    @NonNull
    private final TelegramMessageService telegramMessageService;

    @Value("${app.telegram.chat-id.owner}")
    private String ownerChatID;

    @EventListener(ApplicationReadyEvent.class)
    public void printAppStarted() {
        val profiles = environment.getActiveProfiles();
        val profileList = Arrays.stream(profiles).collect(Collectors.toSet());
        log.info("PROFILES: " + profileList);

        if (profileList.contains("vdsina")) {
            telegramMessageService.sendMessage(
                    new TelegramSendMessageDTO(ownerChatID, "sportnotifier started")
            );
        }

        val str = """
                                                                               \s
                 _____ _____ _____    _____ _____ _____ _____ _____ _____ ____ \s
                |  _  |  _  |  _  |  |   __|_   _|  _  | __  |_   _|   __|    \\\s
                |     |   __|   __|  |__   | | | |     |    -| | | |   __|  |  |
                |__|__|__|  |__|     |_____| |_| |__|__|__|__| |_| |_____|____/\s
                                                                               \s""";

        log.info(str);
        log.info("swagger local: {}", "http://localhost:8090/swagger-ui/index.html");
    }
}
