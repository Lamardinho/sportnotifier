package com.lamardinho.uefanotifier.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class AppStartedLogInfo {

    @EventListener(ApplicationReadyEvent.class)
    public void printAppStarted() {
        final var str = """
                                                                               \s
                 _____ _____ _____    _____ _____ _____ _____ _____ _____ ____ \s
                |  _  |  _  |  _  |  |   __|_   _|  _  | __  |_   _|   __|    \\\s
                |     |   __|   __|  |__   | | | |     |    -| | | |   __|  |  |
                |__|__|__|  |__|     |_____| |_| |__|__|__|__| |_| |_____|____/\s
                                                                               \s""";

        System.out.println(str);
    }
}
