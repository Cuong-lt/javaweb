package com.javaweb.batdongsan.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(500, "Uncategorized", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(400, "Invalid key",HttpStatus.BAD_REQUEST),
    USER_EXISTED(400, "User with user name Existed",HttpStatus.BAD_REQUEST),
    USERNAME_PASSWORD_INVALID(400, "User name / pass word  invalid (at least 6 characters)",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(404, "User not found",HttpStatus.NOT_FOUND),
    ROLE_EXISTED(400, "Role with this code existed",HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(404, "Role not found",HttpStatus.NOT_FOUND),
    USER_ROLE_NOT_FOUND(404,"User with role not found" ,HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(401,"unauthenticated",HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN(401,"Token invalid",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(403,"You are not authorized",HttpStatus.FORBIDDEN)
    ;

    int code;
    String message;
    HttpStatusCode statusCode;

}
