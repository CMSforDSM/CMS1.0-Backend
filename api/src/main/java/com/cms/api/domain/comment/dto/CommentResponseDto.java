package com.cms.api.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {

    private Long comment_id;
    private Long post_id;
    private String writer;
    private String content;
    private List<Long> child_comments;

}
