package com.cms.api.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequestDto {

    private String id;
    private String password;

}
