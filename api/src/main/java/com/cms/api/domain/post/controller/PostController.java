package com.cms.api.domain.post.controller;

import com.cms.api.domain.post.dto.PostCreateRequestDto;
import com.cms.api.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Long createPost(@RequestBody PostCreateRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

}
