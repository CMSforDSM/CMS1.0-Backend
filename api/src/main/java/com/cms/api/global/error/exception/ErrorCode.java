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
    ALREADY_CLUB_MEMBER(409, "C005", "Already Club member"),
    APPLICATION_NOT_FOUND(400, "C006", "Club Application Not Found"),
    INVALID_APPLICATION(400, "C007", "Application Invalid"),
    NOT_ALLOW_DELETE_APPLICATION(403, "C008", "Not Allow Delete Application"),

    // Post
    POST_NOT_FOUND(400, "P001", "Post Not Found"),
    NOT_MY_POST(403, "P002", "Not My Post"),

    // Comment
    COMMENT_NOT_FOUND(400, "M001", "Comment Not Found"),
    NOT_MY_COMMENT(403, "M002", "Not My Comment"),
    CANNOT_ADD_COMMENT(400, "M003", "Cannot Add Comment"),

    // Scout
    SCOUT_NOT_FOUND(400, "S001", "Scout Not Found"),
    NOT_MY_SCOUT(403, "S002", "Not My Scout");

    private final int status;
    private final String code;
    private final String message;
}
