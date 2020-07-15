package com.cms.api.domain.club.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClubResponseDto {

    private String club_name;
    private String introduce;
    private String leader;
    private List<String> members;
}
