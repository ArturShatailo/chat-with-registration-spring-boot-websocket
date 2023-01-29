package com.websocket.onlinechat.authentification.chatuser.service.validation;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;
import com.websocket.onlinechat.authentification.chatuser.repository.ChatUserRepository;
import com.websocket.onlinechat.authentification.util.exceptions.ChatUserValidationException;
import com.websocket.onlinechat.authentification.util.exceptions.EmailAlreadyTakenException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ChatUserValidationServiceBean implements ChatUserValidationService{

    private final ChatUserRepository chatUserRepository;

    @Override
    public void validate(ChatUser user) {
        validateNull(user);
        validateAvailability(user.getEmail());
/*
Instead of findChatUserByEmailAndEnabled can be implemented simple validation
with the following principles:
if email is found and there is confirmed token -> email is already taken
if email is found but there is not expired, not confirmed token -> show "Confirm Email Exception"
if email is found but there is expired and not confirmed token -> generate new token without saving user
*/
    }

    private void validateAvailability(String email) {
        chatUserRepository.findChatUserByEmailAndEnabled(email, true)
                .ifPresent(cu -> {
                    throw new EmailAlreadyTakenException("Email " + cu.getEmail() + " is already taken");
                });
    }

    private void validateNull(ChatUser user) {
        if (user == null) throw new ChatUserValidationException("User is null");
    }
}
