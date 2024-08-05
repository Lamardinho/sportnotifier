package com.lamardinho.sportnotifier.common;

public class ParseTokenException extends RuntimeException {

    /**
     * @param message - string message thrown
     */
    public ParseTokenException(String message) {
        super(message);
    }

    /**
     * @param message   - string message thrown
     * @param throwable - {@link Throwable}
     */
    public ParseTokenException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
