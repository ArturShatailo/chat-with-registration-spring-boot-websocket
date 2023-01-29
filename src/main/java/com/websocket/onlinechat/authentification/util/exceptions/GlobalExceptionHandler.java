package com.websocket.onlinechat.authentification.util.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {

        String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        String field = ex.getBindingResult().getFieldErrors().get(0).getField();

        FieldExceptionDetails errorDetails =
                new FieldExceptionDetails(new Date(), message, request.getDescription(false), field);
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    protected ResponseEntity<?> handleEmailAlreadyTakenException(EmailAlreadyTakenException ex, WebRequest request) {

        System.err.println(request);
        String field = "email";
        FieldExceptionDetails errorDetails =
                new FieldExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false), field);

        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConfirmationTokenNotFoundException.class)
    protected ResponseEntity<?> handleConfirmationTokenNotFoundException(ConfirmationTokenNotFoundException ex, WebRequest request) {
        ExceptionDetails errorDetails =
                new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailSendFailException.class)
    protected ResponseEntity<?> handleEmailSendFailException(EmailSendFailException ex, WebRequest request) {
        ExceptionDetails errorDetails =
                new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NicknameChangeException.class)
    protected ResponseEntity<?> handleNicknameChangeException(NicknameChangeException ex, WebRequest request) {
//        ExceptionDetails errorDetails =
//                new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));

        String field = "nickname";
        FieldExceptionDetails errorDetails =
                new FieldExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false), field);
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ChatUserValidationException.class)
    protected ResponseEntity<?> handleChatUserValidationException(ChatUserValidationException ex, WebRequest request) {
        ExceptionDetails errorDetails =
                new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConfirmationTokenExpiredException.class)
    protected ResponseEntity<?> handleConfirmationTokenExpiredException(ConfirmationTokenExpiredException ex, WebRequest request) {
        ExceptionDetails errorDetails =
                new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(EmailAlreadyConfirmedException.class)
    protected ResponseEntity<?> handleEmailAlreadyConfirmedException(EmailAlreadyConfirmedException ex, WebRequest request) {
        ExceptionDetails errorDetails =
                new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyGlobalExceptionHandler> globalExceptionHandler(Exception ex) {
        return new ResponseEntity<>(new MyGlobalExceptionHandler(ex.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    @AllArgsConstructor
    private static class MyGlobalExceptionHandler {
        private String message;
    }
}
