package com.cms.api.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDto {

    private String id;
    private String name;
    private String studentNumber;
    private String role;
    private String club;
}
