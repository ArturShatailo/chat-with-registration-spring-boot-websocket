package com.websocket.onlinechat.chat.service;

import com.websocket.onlinechat.chat.domain.Message;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public interface ChatService {

    Message sendPublicMessage(Message message);

    Message sendPrivateMessage(Message message);

    Message newUser (Message message, SimpMessageHeaderAccessor headerAccessor);
}
