package com.websocket.onlinechat.authentification.registration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class RegistrationRequest {

    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    private final String firstname;

    @NotNull(message = "Surname may not be null")
    @Size(min = 2, max = 32, message = "Surname must be between 2 and 32 characters long")
    private final String lastname;

    @NotNull(message = "Nickname may not be null")
    @Size(min = 2, max = 32, message = "Nickname must be between 2 and 32 characters long")
    private final String nickname;

    @Email
    @NotNull(message = "Email may not be null")
    private final String email;

    @NotNull(message = "Password may not be null")
    @Size(min = 6, message = "Password must be longer than 6 characters")
    private final String password;

}
