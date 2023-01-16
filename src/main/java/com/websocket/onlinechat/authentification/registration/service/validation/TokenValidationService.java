package com.websocket.onlinechat.authentification.registration.service.validation;

import com.websocket.onlinechat.authentification.registration.domain.ConfirmationToken;

public interface TokenValidationService {

    void validate (ConfirmationToken confirmationToken);

}
