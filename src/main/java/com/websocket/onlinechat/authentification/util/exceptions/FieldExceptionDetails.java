package com.websocket.onlinechat.authentification.util.exceptions;

import java.util.Date;

public record FieldExceptionDetails(Date timestamp, String message, String details, String field) {
}