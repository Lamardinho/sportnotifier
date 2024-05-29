package com.lamardinho.sportnotifier.messages.telegram;

import com.lamardinho.sportnotifier.config.util.AppProfile;
import lombok.NonNull;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@Profile({AppProfile.TELEGRAM_BOT})
public class SportNotifierTelegramBotConfig {

    @Value("${app.telegram.bot.sportnotifier.token}")
    private String myBotToken;

    @Bean
    TelegramBotsApi telegramBotsApi(
            @NonNull TelegramSubscriberService telegramSubscriberService,
            @NonNull TelegramMessageService telegramMessageService
    ) throws TelegramApiException {
        val botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(
                new SportNotifierTelegramBot(
                        myBotToken,
                        telegramSubscriberService,
                        telegramMessageService
                )
        );

        return botsApi;
    }
}
