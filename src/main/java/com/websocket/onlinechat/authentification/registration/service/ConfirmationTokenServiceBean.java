package com.websocket.onlinechat.authentification.registration.service;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;
import com.websocket.onlinechat.authentification.chatuser.service.EnableService;
import com.websocket.onlinechat.authentification.registration.domain.ConfirmationToken;
import com.websocket.onlinechat.authentification.registration.repository.ConfirmationTokenRepository;
import com.websocket.onlinechat.authentification.registration.service.validation.TokenValidationService;
import com.websocket.onlinechat.authentification.util.exceptions.ConfirmationTokenNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceBean implements ConfirmationTokenService{

    private final ConfirmationTokenRepository confirmationTokenRepository;

    private final TokenValidationService tokenValidationService;

    private final EnableService enableService;

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    @Override
    public ConfirmationToken create(ChatUser chatUser) {
        return new ConfirmationToken(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                chatUser
        );
    }

    @Override
    public ConfirmationToken getToken(String token) {
        return confirmationTokenRepository.findConfirmationTokenByToken(token)
                .orElseThrow(() -> new ConfirmationTokenNotFoundException("Can't find confirmation token " + token));
    }

    @Override
    @Transactional
    public String confirm(String token) {
        ConfirmationToken confirmationToken = getToken(token);

        tokenValidationService.validate(confirmationToken);

        confirmationToken.setConfirmed(LocalDateTime.now());
        confirmationTokenRepository.save(confirmationToken);

        enableService.enable(
                confirmationToken.getChatUser().getEmail());
        return "confirmed";
    }


}
