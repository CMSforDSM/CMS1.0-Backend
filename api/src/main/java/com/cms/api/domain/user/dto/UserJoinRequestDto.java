package com.cms.api.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserJoinRequestDto {
    private String id;
    private String password;
    private String name;
    private String studentNumber;
    private String role;
}
