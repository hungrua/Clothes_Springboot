package com.example.clothes.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    INVALID_INFO(111, "Lỗi thông tin", HttpStatus.BAD_REQUEST),
    EMPTY_FIELD(123, "Dữ liệu chứa trường không có thông tin", HttpStatus.BAD_REQUEST),
    DUPLICATE_FIELD_CODE(124, "Mã này đã tồn tại", HttpStatus.BAD_REQUEST),
    PARAMETER_NOT_FOUND(125, "Lỗi không tìm thấy đối tượng", HttpStatus.BAD_REQUEST);

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = httpStatusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
