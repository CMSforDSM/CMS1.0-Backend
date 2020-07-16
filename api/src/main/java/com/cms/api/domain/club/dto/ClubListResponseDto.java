package com.cms.api.domain.club.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClubListResponseDto {

    private String club_name;
    private String introduce;
    private String leader;
    private String logo;
}
