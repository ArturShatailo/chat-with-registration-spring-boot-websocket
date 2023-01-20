package com.websocket.onlinechat.authentification.util.exceptions;

public class EmailSendFailException extends RuntimeException{
    public EmailSendFailException(String message) {
        super(message);
    }
}
