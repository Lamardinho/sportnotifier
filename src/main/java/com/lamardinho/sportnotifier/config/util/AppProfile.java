package com.lamardinho.sportnotifier.config.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

/**
 * Константы для spring профилей {@link Profile}.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppProfile {
    public static final String DEFAULT = "default";
    public static final String PROD = "prod";
    public static final String LOCAL = "local";
    public static final String TELEGRAM_BOT = "telegram_bot";   // for local tests when need
    public static final String TEST = "test";

    public static final String NO_TEST = "!test";
}
