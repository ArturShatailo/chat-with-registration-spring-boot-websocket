package com.websocket.onlinechat.authentification.chatuser.service;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;
import com.websocket.onlinechat.authentification.chatuser.repository.ChatUserRepository;
import com.websocket.onlinechat.authentification.registration.domain.ConfirmationToken;
import com.websocket.onlinechat.authentification.registration.service.ConfirmationTokenService;
import com.websocket.onlinechat.authentification.util.exceptions.EmailAlreadyTakenException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatUserServiceBean implements UserDetailsService, AuthenticationService {

    private final static String USER_NOT_FOUND_MESSAGE = "User with username: %s not found";

    private final ChatUserRepository chatUserRepository;

    private final ConfirmationTokenService confirmationTokenService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return chatUserRepository.findChatUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email)));
    }

    @Override
    @Transactional
    public String signUp(ChatUser chatUser){

        chatUserRepository.findChatUserByEmail(chatUser.getEmail())
                .ifPresent(cu -> {
                    throw new EmailAlreadyTakenException("Email " + cu.getEmail() + " is already taken");
                });
        String password = bCryptPasswordEncoder.encode(chatUser.getPassword());
        chatUser.setPassword(password);
        chatUserRepository.save(chatUser);

        ConfirmationToken token = confirmationTokenService.create(chatUser);
        confirmationTokenService.saveConfirmationToken(token);

        return token.getToken();
    }
}
