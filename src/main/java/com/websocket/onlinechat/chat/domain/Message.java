package com.websocket.onlinechat.chat.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Message {

    private MessageType type;

    private String message;

    private String timestamp;

    private String from;

    //private String to;

}
