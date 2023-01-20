package com.websocket.onlinechat.chat.controller;

import com.websocket.onlinechat.chat.domain.Message;
import com.websocket.onlinechat.chat.domain.MessageType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Component
@AllArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations sendingOperations;

    @EventListener
    public void handleWebSocketConnectionListener(final SessionConnectedEvent event){
        log.info("Wow, a new user in the chat");
    }

    @EventListener
    public void handleWebSocketDisconnectionListener(final SessionDisconnectEvent event){
        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        final String user = (String) headerAccessor.getSessionAttributes().get("user");
        final Message message = Message.builder()
                .type(MessageType.DISCONNECT)
                .from(user)
                .build();
        log.info("Message: {}", message);
        sendingOperations.convertAndSend("/topic/public", message);
    }

}
