package org.notebasement.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    USER_EXISTED(1001, "User exitsted", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1002, "User not found", HttpStatus.NOT_FOUND),
    UNCATEGORIZED_EXCEPTION(9999, "Uncated Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    ;
    int code;
    String message;
    org.springframework.http.HttpStatus status;

    ErrorCode(int code, String message, org.springframework.http.HttpStatus status){
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
