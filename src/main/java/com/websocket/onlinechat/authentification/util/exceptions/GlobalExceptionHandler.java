package com.websocket.onlinechat.authentification.util.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyTakenException.class)
    protected ResponseEntity<?> handleEmailAlreadyTakenException(EmailAlreadyTakenException ex, WebRequest request) {
        ExceptionDetails errorDetails =
                new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConfirmationTokenNotFoundException.class)
    protected ResponseEntity<?> handleConfirmationTokenNotFoundException(ConfirmationTokenNotFoundException ex, WebRequest request) {
        ExceptionDetails errorDetails =
                new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailSendFailException.class)
    protected ResponseEntity<?> handleEmailSendFailException(EmailSendFailException ex, WebRequest request) {
        ExceptionDetails errorDetails =
                new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NicknameIsTheSameException.class)
    protected ResponseEntity<?> handleNicknameIsTheSameException(NicknameIsTheSameException ex, WebRequest request) {
        ExceptionDetails errorDetails =
                new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConfirmationTokenExpiredException.class)
    protected ResponseEntity<?> handleConfirmationTokenExpiredException(ConfirmationTokenExpiredException ex, WebRequest request) {
        ExceptionDetails errorDetails =
                new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(EmailAlreadyConfirmedException.class)
    protected ResponseEntity<?> handleEmailAlreadyConfirmedException(EmailAlreadyConfirmedException ex, WebRequest request) {
        ExceptionDetails errorDetails =
                new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyGlobalExceptionHandler> globalExceptionHandler(Exception ex) {
        return new ResponseEntity<>(new MyGlobalExceptionHandler(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    @AllArgsConstructor
    private static class MyGlobalExceptionHandler {
        private String message;
    }
}
