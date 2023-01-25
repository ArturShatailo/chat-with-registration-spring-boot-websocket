package com.websocket.onlinechat.authentification.registration.controller;

import com.websocket.onlinechat.authentification.registration.domain.RegistrationRequest;
import com.websocket.onlinechat.authentification.registration.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/registration", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping(path = "/", produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public void registration(@RequestBody RegistrationRequest request){
        registrationService.register(request);
    }
}
