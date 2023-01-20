package com.websocket.onlinechat.authentification.util.mapper;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;
import com.websocket.onlinechat.authentification.chatuser.domain.ChatUserInfoDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ChatUserMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ChatUserMapper {

    ChatUserInfoDTO objectToChatUserInfoDTO(ChatUser object);

    ChatUser chatUserInfoDTOtoObject(ChatUserInfoDTO dto);

}
