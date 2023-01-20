package com.websocket.onlinechat.authentification.chatuser.controller;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;
import com.websocket.onlinechat.authentification.chatuser.domain.ChatUserInfoDTO;
import com.websocket.onlinechat.authentification.chatuser.service.ChatUserInfoService;
import com.websocket.onlinechat.authentification.chatuser.service.ChatUserUpdateService;
import com.websocket.onlinechat.authentification.util.mapper.ChatUserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChatUserController {

    private final ChatUserInfoService chatUserInfoService;

    private final ChatUserUpdateService chatUserUpdateService;

    private final ChatUserMapper chatUserMapper;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ChatUserInfoDTO getUserInfo(){
        ChatUser user = (ChatUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return chatUserMapper.objectToChatUserInfoDTO(
                chatUserInfoService.getUserInfo(user.getEmail()));
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void setUserNickname(@RequestBody ChatUserInfoDTO dto){
        chatUserUpdateService.updateChatUserNickname(dto.email, dto.nickname);
    }

}