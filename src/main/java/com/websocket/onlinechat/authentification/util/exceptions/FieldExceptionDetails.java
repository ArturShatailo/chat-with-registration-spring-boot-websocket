package com.websocket.onlinechat.authentification.util.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

//public record FieldExceptionDetails(Date timestamp, String message, String details, String field) {
//}

@AllArgsConstructor
@Data
public class FieldExceptionDetails {

    private Date timestamp;

    private String message;

    private String details;

    private String field;

}