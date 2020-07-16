package com.cms.api.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // User
    USER_NOT_FOUND(400, "U001", "User Not Found"),
    USER_DUPLICATE(409, "U002", "User Duplicated"),

    // Auth
    AUTHENTICATION(401, "A001", "Authentication Error"),
    INVALID_TOKEN(401, "A002", "Invalid Token"),

    // Club
    CLUB_NOT_FOUND(400, "C001", "Club Not Found"),
    CLUB_DUPLICATE(409, "C002", "Club Duplicated"),
    NOT_CLUB_LEADER(403, "C003", "Not a club leader"),
    NOT_CLUB_MEMBER(403, "C004", "Not a Club Member"),
    ALREADY_CLUB_MEMBER(409, "C005", "Already Club member");

    private final int status;
    private final String code;
    private final String message;
}
