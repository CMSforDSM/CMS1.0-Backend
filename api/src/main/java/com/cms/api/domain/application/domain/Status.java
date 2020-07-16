package com.cms.api.domain.application.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    WAIT("WAITING"),
    REJECT("REJECTED"),
    ACCEPT("ACCEPTED");

    private final String status;
}
