package com.websocket.onlinechat.authentification.email;

public interface EmailSender {

    String FROM_EMAIL = "support@powerfulpanda.tech";
    String SUBJECT = "Confirm your email";
    String LINK = "http://localhost:8095/api/chat/user/registration/confirm?token=";

    void send(String to, String email);

    String build(String name, String link);

}
