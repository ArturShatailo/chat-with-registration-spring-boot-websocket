package com.websocket.onlinechat.authentification.util.exceptions;

public class NicknameIsTheSameException extends RuntimeException{
    public NicknameIsTheSameException(String message) {
        super(message);
    }
}
