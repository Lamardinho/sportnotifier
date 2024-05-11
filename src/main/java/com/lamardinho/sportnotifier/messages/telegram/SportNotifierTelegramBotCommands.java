package com.lamardinho.sportnotifier.messages.telegram;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum SportNotifierTelegramBotCommands {

    UEFA_CH_L_MATCHES_NOTIFICATIONS_SUBSCRIBE(
            "/UEFA_CHAMPIONS_LEAGUE_MATCHES_NOTIFICATIONS_SUBSCRIBE"
    ),
    UEFA_CH_L_MATCHES_NOTIFICATIONS_UNSUBSCRIBE(
            "/UEFA_CHAMPIONS_LEAGUE_MATCHES_NOTIFICATIONS_UNSUBSCRIBE"
    ),


    ;


    private final String commandValue;


    public static @NonNull String getCommandValuesString() {
        val values = Arrays.stream(SportNotifierTelegramBotCommands.values())
                .map(SportNotifierTelegramBotCommands::getCommandValue)
                .collect(Collectors.toSet());

        return String.join("\n\n", values);
    }
}
