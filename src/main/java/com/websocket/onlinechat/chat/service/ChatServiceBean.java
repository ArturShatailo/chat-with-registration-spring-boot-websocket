package com.websocket.onlinechat.chat.service;

import com.websocket.onlinechat.chat.domain.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ChatServiceBean implements ChatService{

    @Override
    public Message sendPublicMessage(Message message) {
//        message.setTimestamp(new Date().toString());
        log.info("Message: {}", message);
        return message;
    }

    @Override
    public Message sendPrivateMessage(Message message) {
        return null;
    }

    @Override
    public Message newUser(Message message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("user", message.getFrom());
        log.info("Message: {} \n Header: {}", message, headerAccessor.getSessionAttributes().get("user"));
        return message;
    }

}
