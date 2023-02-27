package com.websocket.onlinechat.authentification.util.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

//public record ExceptionDetails(Date timestamp, String message, String details) {
//}

@AllArgsConstructor
@Data
public class ExceptionDetails {

    private Date timestamp;

    private String message;

    private String details;

}