package com.lamardinho.sportnotifier.common.exceptionhandlers;

import com.lamardinho.sportnotifier.common.AppMsgErrors;
import com.lamardinho.sportnotifier.common.ContractResult;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@Log4j2
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = HttpClientErrorException.BadRequest.class)
    public ResponseEntity<String> handleBadRequestException(@NonNull HttpClientErrorException.BadRequest ex) {
        val errorMessage = "Error " + ex.getRawStatusCode() + ": " + ex.getResponseBodyAsString();
        log.error(errorMessage, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ContractResult<String>> handle(@NonNull Exception ex) {
        log.error(AppMsgErrors.AN_UNEXPECTED_ERROR_OCCURRED_EN, ex);

        val contractResult = new ContractResult<String>();
        contractResult.getViolations().add(AppMsgErrors.AN_UNEXPECTED_ERROR_OCCURRED_RU);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(contractResult);
    }
}
