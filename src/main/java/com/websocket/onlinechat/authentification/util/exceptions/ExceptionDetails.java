package com.websocket.onlinechat.authentification.util.exceptions;

import java.util.Date;

public record ExceptionDetails(Date timestamp, String message, String details) {
}