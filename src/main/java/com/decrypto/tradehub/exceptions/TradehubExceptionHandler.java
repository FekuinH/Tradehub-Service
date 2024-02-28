package com.decrypto.tradehub.exceptions;

import com.decrypto.tradehub.models.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TradehubExceptionHandler {

    @ExceptionHandler(TradehubException.class)
    public ResponseEntity<ErrorModel> handleCustomErrorException(TradehubException ex) {
        ErrorModel errorResponse = new ErrorModel(ex.getStatusCode(), ex.getMessage());
        HttpStatus httpStatus = HttpStatus.valueOf(ex.getStatusCode());
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
