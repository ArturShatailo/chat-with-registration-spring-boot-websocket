package com.websocket.onlinechat.authentification.chatuser.repository;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {

    Optional<ChatUser> findChatUserByEmail (String email);

    Optional<ChatUser> findChatUserByEmailAndEnabled (String email, Boolean enabled);

    Optional<ChatUser> findChatUserByNickname(String nickname);

}
