package com.cms.api.domain.club.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClubMemberResponseDto {

    private String club_name;
    private String student_no;
    private String name;

}
