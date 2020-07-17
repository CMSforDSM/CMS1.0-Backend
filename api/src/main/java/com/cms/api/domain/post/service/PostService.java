package com.cms.api.domain.post.service;

import com.cms.api.domain.auth.exception.UserNotFoundException;
import com.cms.api.domain.club.dao.ClubRepository;
import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.club.exception.ClubNotFoundException;
import com.cms.api.domain.post.dao.PostRepository;
import com.cms.api.domain.post.domain.Post;
import com.cms.api.domain.post.domain.PostType;
import com.cms.api.domain.post.dto.PostCreateRequestDto;
import com.cms.api.domain.user.dao.UserRepository;
import com.cms.api.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final ClubRepository clubRepository;

    public Long createPost(PostCreateRequestDto request) {
        String studentNo = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByStudentNumber(studentNo).orElseThrow(UserNotFoundException::new);
        Club club = null;

        if(request.getClub_name() != null) {
            club = clubRepository.findById(request.getClub_name()).orElseThrow(ClubNotFoundException::new);
        }

        Post post = postRepository.save(Post.builder()
                .club(club)
                .title(request.getTitle())
                .writer(user)
                .content(request.getContent())
                .postType(PostType.valueOf(request.getType()))
                .build());

        return post.getId();
    }

}
