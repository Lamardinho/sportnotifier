package com.lamardinho.sportnotifier.exceptionhandlers;

import com.lamardinho.sportnotifier.common.ParseTokenException;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Order(0)
@ControllerAdvice(basePackages = "com.lamardinho.sportnotifier.controller.api")
@Log4j2
public class ParseTokenExceptionHandler {

    @ExceptionHandler(value = ParseTokenException.class)
    public ResponseEntity<Map<String, String>> handleHibernateException(@NonNull ParseTokenException ex) {
        log.warn(ex.getMessage());
        val result = new HashMap<String, String>();
        result.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
