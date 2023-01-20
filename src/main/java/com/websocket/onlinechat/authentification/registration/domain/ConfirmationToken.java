package com.websocket.onlinechat.authentification.registration.domain;

import com.websocket.onlinechat.authentification.chatuser.domain.ChatUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime created;
    @Column(nullable = false)
    private LocalDateTime expired;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "chat_user_id"
    )
    private ChatUser chatUser;

    private LocalDateTime confirmed;

    public ConfirmationToken(String token,
                             LocalDateTime created,
                             LocalDateTime expired,
                             ChatUser chatUser) {
        this.token = token;
        this.created = created;
        this.expired = expired;
        this.chatUser = chatUser;
    }
}
