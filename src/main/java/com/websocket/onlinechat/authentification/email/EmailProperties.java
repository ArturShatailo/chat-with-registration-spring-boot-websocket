package com.websocket.onlinechat.authentification.email;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailProperties {

    @Value("${email.user}")
    private String emailUsername;
    @Value("${email.password}")
    String emailPassword;

    public Session set(){
        final String username = emailUsername;
        final String password = emailPassword;


        Properties prop = new Properties();
        prop.put("mail.smtp.host", "mail.adm.tools");
        prop.put("mail.smtp.port", "2525"); //no ssl port 2525, use 465 for ssl
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        return Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

}
