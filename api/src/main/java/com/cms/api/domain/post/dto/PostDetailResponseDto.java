package com.cms.api.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailResponseDto {

    private Long post_id;
    private String post_type;
    private String writer;
    private String club;
    private String title;
    private String content;

}
