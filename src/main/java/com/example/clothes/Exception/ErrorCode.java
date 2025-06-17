package com.example.clothes.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    INVALID_INFO(111, "Invalid information", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD_LENGTH(112, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    EMPTY_FIELD(123, "Contain empty field are not allowed", HttpStatus.BAD_REQUEST),
    DUPLICATE_FIELD_CODE(124, "Code value has been existed", HttpStatus.BAD_REQUEST),
    PARAMETER_NOT_FOUND(125, "Not found object error", HttpStatus.BAD_REQUEST);

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = httpStatusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
