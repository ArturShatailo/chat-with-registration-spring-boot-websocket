package com.websocket.onlinechat.authentification.registration.service.validation;

import com.websocket.onlinechat.authentification.registration.domain.ConfirmationToken;
import com.websocket.onlinechat.authentification.util.exceptions.ConfirmationTokenExpiredException;
import com.websocket.onlinechat.authentification.util.exceptions.EmailAlreadyConfirmedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class TokenValidationServiceBean implements TokenValidationService{

    @Override
    public void validate(ConfirmationToken token) {
        validateConfirmation(token);
        validateExpired(token);
    }

    private void validateExpired(ConfirmationToken token) {
        if (token.getExpired()
                .isBefore(LocalDateTime.now()))
            throw new ConfirmationTokenExpiredException("Token '" + token.getToken() + "' expired");
    }

    private void validateConfirmation(ConfirmationToken token) {
        if (token.getConfirmed() != null)
            throw new EmailAlreadyConfirmedException("Email already confirmed");
    }
}
