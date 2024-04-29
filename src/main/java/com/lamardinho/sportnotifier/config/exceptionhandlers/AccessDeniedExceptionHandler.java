package com.lamardinho.sportnotifier.config.exceptionhandlers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(0)
@ControllerAdvice(basePackages = "com.lamardinho.sportnotifier.controller.api")
public class AccessDeniedExceptionHandler {

    @ExceptionHandler(value = AccessDeniedException.class)
    public void handleHibernateException(@NonNull HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
