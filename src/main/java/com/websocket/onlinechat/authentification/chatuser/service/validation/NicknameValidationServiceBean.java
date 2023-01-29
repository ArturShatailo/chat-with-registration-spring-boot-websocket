package com.websocket.onlinechat.authentification.chatuser.service.validation;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;
import com.websocket.onlinechat.authentification.chatuser.repository.ChatUserRepository;
import com.websocket.onlinechat.authentification.util.exceptions.NicknameChangeException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class NicknameValidationServiceBean implements NicknameValidationService{

    private final ChatUserRepository chatUserRepository;

    @Override
    public void validate(ChatUser user, String nickname) {
        nickname = nickname.trim();
        validateNull(nickname);
        validateEquals(user.getNickname(), nickname);
        validateAvailability(nickname);
    }

    @Override
    public void validateAvailability(String nickname) {
        chatUserRepository.findChatUserByNickname(nickname)
                .ifPresent((user) -> {
                    throw new NicknameChangeException("Nickname " + nickname + " is already taken");
                });
    }

    private void validateNull(String nickname) {
        if (nickname == null || nickname.equals(""))
            throw new NicknameChangeException("Empty nickname is not allowed");
    }

    private void validateEquals(String nickname, String requestNickname) {
        if (nickname.equals(requestNickname))
            throw new NicknameChangeException("This is your actual nickname");
    }
}
