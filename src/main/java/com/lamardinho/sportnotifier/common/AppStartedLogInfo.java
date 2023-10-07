package com.lamardinho.sportnotifier.common;

import lombok.val;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class AppStartedLogInfo {

    @EventListener(ApplicationReadyEvent.class)
    public void printAppStarted() {
        val str = "                                                                \n" +
                " _____ _____ _____    _____ _____ _____ _____ _____ _____ ____  \n" +
                "|  _  |  _  |  _  |  |   __|_   _|  _  | __  |_   _|   __|    \\ \n" +
                "|     |   __|   __|  |__   | | | |     |    -| | | |   __|  |  |\n" +
                "|__|__|__|  |__|     |_____| |_| |__|__|__|__| |_| |_____|____/ \n" +
                "                                                                ";

        System.out.println(str);
    }
}
