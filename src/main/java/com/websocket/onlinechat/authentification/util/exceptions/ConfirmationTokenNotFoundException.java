package com.websocket.onlinechat.authentification.util.exceptions;

public class ConfirmationTokenNotFoundException extends RuntimeException{
    public ConfirmationTokenNotFoundException(String message) {
        super(message);
    }
}
