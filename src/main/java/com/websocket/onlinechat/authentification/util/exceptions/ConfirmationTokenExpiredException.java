package com.websocket.onlinechat.authentification.util.exceptions;

public class ConfirmationTokenExpiredException extends RuntimeException{
    public ConfirmationTokenExpiredException(String message) {
        super(message);
    }
}
