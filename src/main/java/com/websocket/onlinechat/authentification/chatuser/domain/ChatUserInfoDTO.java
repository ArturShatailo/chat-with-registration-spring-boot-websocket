package com.websocket.onlinechat.authentification.chatuser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatUserInfoDTO {

    public String firstname;

    public String lastname;

    public String nickname;

    public String email;

    public String role;
}