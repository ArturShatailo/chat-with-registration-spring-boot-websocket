package com.websocket.onlinechat.authentification.registration.service.validation;


import com.websocket.onlinechat.authentification.registration.domain.RegistrationRequest;

public interface RegistrationValidationService {

    void validate (RegistrationRequest request);

}
