package com.websocket.onlinechat.chat.controller;

import com.websocket.onlinechat.chat.domain.Message;
import com.websocket.onlinechat.chat.service.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class ChatController {

    //private final SimpMessagingTemplate simpMessagingTemplate;

    private final ChatService chatService;

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @MessageMapping("/room-message")
    @SendTo("/topic/public")
    public Message sendPublicMessage(@Payload final Message message){
        return chatService.sendPublicMessage(message);
    }

    @MessageMapping("/new-user")
    @SendTo("/topic/public")
    public Message newUser(@Payload final Message message, SimpMessageHeaderAccessor headerAccessor){
        return chatService.newUser(message, headerAccessor);
    }

//    @MessageMapping("/private-message")
//    public Message receivePrivateMessage(@Payload final Message message){
//        simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/private", message); //user/Artur/private
//        return message;
//    }
}
