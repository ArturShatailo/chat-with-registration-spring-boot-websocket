package com.websocket.onlinechat.authentification.registration.service.validation;

import com.websocket.onlinechat.authentification.registration.domain.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationValidationServiceBean implements RegistrationValidationService{

    @Override
    public void validate(RegistrationRequest request) {
        validateEmail(request.getEmail());
        validatePassword(request.getPassword());
    }

    private void validatePassword(String password) {
    }

    private void validateEmail(String email) {
    }
}
