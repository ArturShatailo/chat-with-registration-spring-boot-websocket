package com.websocket.onlinechat.authentification.chatuser.service.validation;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;

public interface NicknameValidationService {

    void validate(ChatUser user, String nickname);

    void validateAvailability(String nickname);

}
