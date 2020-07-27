package com.cms.api.domain.post.service;

import com.cms.api.domain.auth.exception.UserNotFoundException;
import com.cms.api.domain.club.dao.ClubRepository;
import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.club.exception.ClubNotFoundException;
import com.cms.api.domain.club.exception.NotClubMemberException;
import com.cms.api.domain.post.dao.PostRepository;
import com.cms.api.domain.post.domain.Post;
import com.cms.api.domain.post.domain.PostType;
import com.cms.api.domain.post.dto.PostCreateRequestDto;
import com.cms.api.domain.post.dto.PostDetailResponseDto;
import com.cms.api.domain.post.dto.PostListResponseDto;
import com.cms.api.domain.post.dto.PostUpdateRequestDto;
import com.cms.api.domain.post.exception.NotMyPostException;
import com.cms.api.domain.post.exception.PostNotFoundException;
import com.cms.api.domain.user.dao.UserRepository;
import com.cms.api.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        PostType postType = PostType.valueOf(request.getType());

        if(request.getClub_name() != null || postType == PostType.ACHIEVEMENT || postType == PostType.RECRUITMENT) {
            if(request.getClub_name() == null) throw new ClubNotFoundException();
            club = clubRepository.findById(request.getClub_name()).orElseThrow(ClubNotFoundException::new);
            if(!club.checkMember(user)) throw new NotClubMemberException();
        }

        Post post = postRepository.save(Post.builder()
                .club(club)
                .title(request.getTitle())
                .writer(user)
                .content(request.getContent())
                .postType(postType)
                .build());

        return post.getId();
    }

    public PostDetailResponseDto getPost(String postId) {
        Post post = postRepository.findById(Long.parseLong(postId)).orElseThrow(PostNotFoundException::new);
        String club = post.getClub() != null ? post.getClub().getClubName() : null;

        return PostDetailResponseDto.builder()
                .post_id(post.getId())
                .post_type(post.getPostType().getValue())
                .writer(post.getWriter().getStudentNumber() + "-" + post.getWriter().getName())
                .club(club)
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public List<PostListResponseDto> getPosts(String type, String club_name) {

        Club club;
        List<Post> posts;

        if(club_name != null) {
            club = clubRepository.findById(club_name).orElseThrow(ClubNotFoundException::new);
            posts = postRepository.findAllByPostTypeAndClubOrderByUpdatedDateDesc(PostType.valueOf(type), club);
        } else {
            posts = postRepository.findAllByPostTypeOrderByUpdatedDateDesc(PostType.valueOf(type));
        }

        return posts.stream()
                .map(post -> {
                    return PostListResponseDto.builder()
                            .title(post.getTitle())
                            .writer(post.getWriter().getStudentNumber() + "-" + post.getWriter().getName())
                            .date_time(post.getUpdatedDate())
                            .post_id(post.getId())
                            .content(post.getContent())
                            .build();
                })
                .collect(Collectors.toList());

    }

    public void editPost(String postId, PostUpdateRequestDto request) {
        Post post = postRepository.findById(Long.parseLong(postId)).orElseThrow(PostNotFoundException::new);

        checkWriter(post);

        post.updateContent(request.getTitle(), request.getContent());
        postRepository.save(post);
    }

    public void deletePost(String postId) {
        Post post = postRepository.findById(Long.parseLong(postId)).orElseThrow(PostNotFoundException::new);

        checkWriter(post);

        postRepository.delete(post);
    }

    private void checkWriter(Post post) {
        String studentNo = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByStudentNumber(studentNo).orElseThrow(UserNotFoundException::new);

        if(!user.equals(post.getWriter())) throw new NotMyPostException();
    }

}
