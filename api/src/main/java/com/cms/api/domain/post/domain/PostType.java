package com.cms.api.domain.post.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostType {

    ACHIEVEMENT("성과게시물"),
    RECRUITMENT("모집공고"),
    NOTIFICATION("공지사항"),
    RESUME("이력서");

    private final String value;

}
