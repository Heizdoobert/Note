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
    UNAUTHENTICATED(404, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    CATEGORY_NOT_FOUND(1003, "Category not found", HttpStatus.NOT_FOUND),
    CANNOT_ACCESS(1004, "Cannot access", HttpStatus.FORBIDDEN),
    COMMENT_NOT_FOUND(1005, "Comment not found", HttpStatus.NOT_FOUND),
    COMMENT_EXISTED(1006, "Comment exitsted", HttpStatus.BAD_REQUEST),
    ;
    int code;
    String message;
    org.springframework.http.HttpStatus status;

    ErrorCode(int code, String message, org.springframework.http.HttpStatus status){
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
