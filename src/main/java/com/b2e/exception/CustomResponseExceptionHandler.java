package com.b2e.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice // If exception occure then call otherwise not call. That's why it's advice
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {
    public final ResponseEntity<?> handleWalletException(WalletException walletException, WebRequest webRequest) {
        WalletExceptionResponse walletExceptionResponse = new WalletExceptionResponse(walletException.getMessage());
        return new ResponseEntity<WalletExceptionResponse>(walletExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}