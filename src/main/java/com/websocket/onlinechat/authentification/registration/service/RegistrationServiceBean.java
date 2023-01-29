package com.websocket.onlinechat.authentification.registration.service;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;
import com.websocket.onlinechat.authentification.chatuser.domain.ChatUserRole;
import com.websocket.onlinechat.authentification.chatuser.service.AuthenticationService;
import com.websocket.onlinechat.authentification.email.EmailSender;
import com.websocket.onlinechat.authentification.registration.domain.RegistrationRequest;
import com.websocket.onlinechat.authentification.registration.service.validation.RegistrationValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceBean implements RegistrationService{

    private final AuthenticationService authenticationService;

    private final ConfirmationTokenService confirmationTokenService;

    private final EmailSender emailSender;

    private final RegistrationValidationService registrationValidation;

    @Override
    public void register(RegistrationRequest request) {

        registrationValidation.validate(request);

        String token = authenticationService.signUp(
                new ChatUser(
                        request.getFirstname(),
                        request.getLastname(),
                        request.getNickname(),
                        request.getEmail(),
                        request.getPassword(),
                        ChatUserRole.USER
                )
        );
        emailSender.send(
                request.getEmail(),
                emailSender.build(request.getFirstname(), emailSender.LINK + token));
        //return token;
    }

    @Override
    public String confirmToken(String token) {
        return confirmationTokenService.confirm(token);
    }
}
