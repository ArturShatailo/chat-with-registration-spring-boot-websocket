package com.websocket.onlinechat.authentification.chatuser.service;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;
import com.websocket.onlinechat.authentification.chatuser.repository.ChatUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatUserConfirmationTokenEnabled implements EnableService{

    private final static String USER_NOT_FOUND_MESSAGE = "User with username: %s not found";

    private final ChatUserRepository chatUserRepository;

    @Override
    public void enable(String email) {
        ChatUser chatUser = chatUserRepository.findChatUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email)));
        chatUser.setEnabled(true);
        chatUserRepository.save(chatUser);
    }
}
