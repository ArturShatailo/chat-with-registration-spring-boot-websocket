package com.websocket.onlinechat.authentification.chatuser.service;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;
import com.websocket.onlinechat.authentification.chatuser.repository.ChatUserRepository;
import com.websocket.onlinechat.authentification.chatuser.service.validation.ChatUserValidationService;
import com.websocket.onlinechat.authentification.chatuser.service.validation.NicknameValidationService;
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
public class ChatUserServiceBean implements UserDetailsService, AuthenticationService, ChatUserInfoService, ChatUserUpdateService {

    private final static String USER_NOT_FOUND_MESSAGE = "User not found with parameter %s";

    private final ChatUserRepository chatUserRepository;

    private final ConfirmationTokenService confirmationTokenService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final NicknameValidationService nicknameValidation;

    private final ChatUserValidationService chatUserValidation;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return chatUserRepository.findChatUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email)));
    }

    @Override
    @Transactional
    public String signUp(ChatUser chatUser){

        chatUserValidation.validate(chatUser);
        nicknameValidation.validateAvailability(chatUser.getNickname());

        String password = bCryptPasswordEncoder.encode(chatUser.getPassword());
        chatUser.setPassword(password);
        chatUserRepository.save(chatUser);

        ConfirmationToken token = confirmationTokenService.create(chatUser);
        confirmationTokenService.saveConfirmationToken(token);

        return token.getToken();
    }

    @Override
    public ChatUser getUserInfo(String email) {
        return chatUserRepository.findChatUserByEmailAndEnabled(email, true)
                .orElseThrow(() -> new EmailAlreadyTakenException("Email " + email + " is not confirmed or unregistered"));
    }

    @Override
    public ChatUser findChatUserByNickname(String nickname) {
        return chatUserRepository.findChatUserByNickname(nickname)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, nickname)));
    }

    @Transactional
    @Override
    public void updateChatUserNickname(String email, String nickname) {
        ChatUser chatUser = getUserInfo(email);
        nicknameValidation.validate(chatUser, nickname);
        chatUser.setNickname(nickname);
        chatUserRepository.save(chatUser);
    }
}
