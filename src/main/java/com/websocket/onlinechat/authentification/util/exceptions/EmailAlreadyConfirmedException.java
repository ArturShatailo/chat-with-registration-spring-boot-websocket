package com.websocket.onlinechat.authentification.util.exceptions;

public class EmailAlreadyConfirmedException extends RuntimeException{
    public EmailAlreadyConfirmedException(String message) {
        super(message);
    }
}
