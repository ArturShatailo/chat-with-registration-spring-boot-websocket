package com.websocket.onlinechat.authentification.registration.service;

import com.websocket.onlinechat.authentification.registration.domain.RegistrationRequest;

public interface RegistrationService {

    String register(RegistrationRequest request);

    String confirmToken(String token);

}
