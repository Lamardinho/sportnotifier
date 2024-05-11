package com.lamardinho.sportnotifier.exceptionhandlers;

import com.lamardinho.sportnotifier.common.AppException;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.net.UnknownHostException;

@Log4j2
@Order(10)
@ControllerAdvice(basePackages = "com.lamardinho.sportnotifier.controller.api")
public class ApiExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(@NonNull Exception ex) {
        log.error(ex.getMessage(), ex);
        val statusCode = getHttpStatus(ex);

        return ResponseEntity
                .status(statusCode)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ex.getMessage());
    }

    private HttpStatus getHttpStatus(@NonNull Exception ex) {
        if (ex instanceof ResponseStatusException) {
            return HttpStatus.resolve(((ResponseStatusException) ex).getStatusCode().value());
        } else if (ex instanceof HttpClientErrorException) {
            return HttpStatus.resolve(((HttpClientErrorException) ex).getStatusCode().value());
        } else if (ex instanceof UnknownHostException) {
            return HttpStatus.SERVICE_UNAVAILABLE;
        } else if (ex instanceof EntityNotFoundException) {
            return HttpStatus.NOT_FOUND;
        } else if (ex instanceof ResourceAccessException) {
            return HttpStatus.SERVICE_UNAVAILABLE;
        } else if (ex instanceof AppException ||
                ex instanceof HttpMessageNotReadableException ||
                ex instanceof MissingServletRequestParameterException ||
                ex instanceof MethodArgumentTypeMismatchException ||
                ex instanceof MethodArgumentNotValidException
        ) {
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
