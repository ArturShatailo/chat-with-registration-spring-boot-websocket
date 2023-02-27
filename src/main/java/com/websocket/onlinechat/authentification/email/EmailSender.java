package com.websocket.onlinechat.authentification.email;

public interface EmailSender {

    String FROM_EMAIL = "support@powerfulpanda.tech";
    String SUBJECT = "Confirm your email";
    String LINK = "https://chat-mate.herokuapp.com/api/registration/confirm?token="; //"http://localhost:8095/api/registration/confirm?token="; //"http://chat-mate.us-east-1.elasticbeanstalk.com/api/registration/confirm?token=";

    void send(String to, String email);

    String build(String name, String link);

}
