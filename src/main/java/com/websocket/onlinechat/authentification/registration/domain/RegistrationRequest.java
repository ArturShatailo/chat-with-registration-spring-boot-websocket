package com.websocket.onlinechat.authentification.registration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegistrationRequest {

    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;

}
