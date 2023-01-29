package com.websocket.onlinechat.authentification.chatuser.service.validation;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;

public interface ChatUserValidationService {

    void validate(ChatUser user);

}
