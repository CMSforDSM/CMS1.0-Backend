package com.cms.api.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostListResponseDto {

    private Long post_id;
    private String title;
    private String writer;
    private LocalDateTime date_time;

}
