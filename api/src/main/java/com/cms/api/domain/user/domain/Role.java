package com.cms.api.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    STUDENT("ROLE_STUDENT", "학생"),
    LEADER("ROLE_LEADER", "부장"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String value;
}
