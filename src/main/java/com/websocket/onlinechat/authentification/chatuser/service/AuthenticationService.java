package com.websocket.onlinechat.authentification.chatuser.service;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;

public interface AuthenticationService {

    String signUp(ChatUser chatUser);

}
