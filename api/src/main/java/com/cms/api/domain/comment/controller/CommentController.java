package com.cms.api.domain.comment.controller;

import com.cms.api.domain.comment.dto.CreateCommentRequestDto;
import com.cms.api.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public long createComment(@RequestBody CreateCommentRequestDto requestDto) {
        return commentService.createComment(requestDto);
    }

}
