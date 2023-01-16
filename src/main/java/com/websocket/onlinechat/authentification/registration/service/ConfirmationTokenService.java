package com.websocket.onlinechat.authentification.registration.service;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;
import com.websocket.onlinechat.authentification.registration.domain.ConfirmationToken;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);

    ConfirmationToken create(ChatUser chatUser);

    ConfirmationToken getToken(String token);

    String confirm(String token);

}
