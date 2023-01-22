package com.websocket.onlinechat.authentification.registration.controller;

import com.websocket.onlinechat.authentification.registration.domain.RegistrationRequest;
import com.websocket.onlinechat.authentification.registration.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping(path = "api/registration", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping(path = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public String registration(/*@RequestBody */RegistrationRequest request){
        registrationService.register(request);
        return "login";
    }

    //response.sendRedirect("/personal-area");  HttpServletResponse response

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
