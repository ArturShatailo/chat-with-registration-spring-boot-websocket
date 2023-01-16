package com.websocket.onlinechat.authentification.registration.service;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;
import com.websocket.onlinechat.authentification.chatuser.domain.ChatUserRole;
import com.websocket.onlinechat.authentification.chatuser.service.AuthenticationService;
import com.websocket.onlinechat.authentification.registration.domain.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceBean implements RegistrationService{

    private final AuthenticationService authenticationService;

    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public String register(RegistrationRequest request) {
        //VALIDATION
        return authenticationService.signUp(
                new ChatUser(
                        request.getFirstname(),
                        request.getLastname(),
                        request.getEmail(),
                        request.getPassword(),
                        ChatUserRole.USER
                        )
        );
    }

    @Override
    public String confirmToken(String token) {
        return confirmationTokenService.confirm(token);
    }


}
