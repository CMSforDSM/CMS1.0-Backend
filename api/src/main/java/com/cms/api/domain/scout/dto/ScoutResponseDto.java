package com.cms.api.domain.scout.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScoutResponseDto {

    private Long scout_id;
    private String target;
    private String club;

}
