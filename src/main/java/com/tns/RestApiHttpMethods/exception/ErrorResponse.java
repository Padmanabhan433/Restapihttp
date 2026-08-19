package com.tns.RestApiHttpMethods.exception;

import java.time.LocalDateTime;

public record ErrorResponse(

        LocalDateTime timestamp,
        int status,
        String message,
        String errors,
        String path

) {
}