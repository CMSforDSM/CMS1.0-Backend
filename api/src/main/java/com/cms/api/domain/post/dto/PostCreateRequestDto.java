package com.cms.api.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequestDto {

    private String title;
    private String content;
    private String type;
    private String club_name;

}
