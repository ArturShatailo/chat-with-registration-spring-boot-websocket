package com.websocket.onlinechat.authentification.registration.controller;

import com.websocket.onlinechat.authentification.registration.domain.RegistrationRequest;
import com.websocket.onlinechat.authentification.registration.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/chat/user/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegistrationRequest request){
        registrationService.register(request);
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
