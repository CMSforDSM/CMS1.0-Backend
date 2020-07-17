package com.cms.api.domain.post.controller;

import com.cms.api.domain.post.dto.PostCreateRequestDto;
import com.cms.api.domain.post.dto.PostDetailResponseDto;
import com.cms.api.domain.post.dto.PostListResponseDto;
import com.cms.api.domain.post.dto.PostUpdateRequestDto;
import com.cms.api.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

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

    @GetMapping("/{post_id}")
    public PostDetailResponseDto getPost(@PathVariable("post_id") String postId) {
        return postService.getPost(postId);
    }

    @GetMapping
    public List<PostListResponseDto> getPosts(@RequestParam String type,
                                              @RequestParam(required = false) String club) {
        return postService.getPosts(type, club);
    }

    @PatchMapping("/{post_id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Transactional
    public void updatePost(@PathVariable("post_id") String postId,
                           @RequestBody PostUpdateRequestDto requestDto) {
        postService.editPost(postId, requestDto);
    }

    @DeleteMapping("/{post_id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("post_id") String postId) {
        postService.deletePost(postId);
    }

}
