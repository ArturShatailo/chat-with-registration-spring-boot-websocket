package com.websocket.onlinechat.authentification.chatuser.service;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;

public interface ChatUserInfoService {

    ChatUser getUserInfo(String email);

    ChatUser findChatUserByNickname(String nickname);

}
