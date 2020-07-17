package com.cms.api.domain.comment.service;

import com.cms.api.domain.auth.exception.UserNotFoundException;
import com.cms.api.domain.comment.dao.CommentRepository;
import com.cms.api.domain.comment.domain.Comment;
import com.cms.api.domain.comment.dto.CommentResponseDto;
import com.cms.api.domain.comment.dto.CreateCommentRequestDto;
import com.cms.api.domain.comment.exception.CommentNotFoundException;
import com.cms.api.domain.comment.exception.NotMyCommentException;
import com.cms.api.domain.post.dao.PostRepository;
import com.cms.api.domain.post.domain.Post;
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
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    public long createComment(CreateCommentRequestDto request) {
        String studentNo = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByStudentNumber(studentNo).orElseThrow(UserNotFoundException::new);

        Post post = postRepository.findById(Long.parseLong(request.getPost_id()))
                .orElseThrow(PostNotFoundException::new);

        Comment parent = commentRepository.findById(Long.parseLong(request.getParent_comment_id()))
                .orElseThrow(CommentNotFoundException::new);

        Comment comment = commentRepository.save(Comment.builder()
                .content(request.getContent())
                .parentComment(parent)
                .writer(user)
                .post(post)
                .build());
        return comment.getId();
    }

    public List<CommentResponseDto> getComments(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        return post.getComments().stream()
                .map(comment -> {
                    List<Long> comments = comment.getChildComment().stream()
                            .map(Comment::getId)
                            .collect(Collectors.toList());

                    return CommentResponseDto.builder()
                            .content(comment.getContent())
                            .comment_id(comment.getId())
                            .post_id(comment.getPost().getId())
                            .writer(comment.getWriter().getStudentNumber() + "-" + comment.getWriter().getName())
                            .child_comments(comments)
                            .build();
                })
                .collect(Collectors.toList());
    }

    public void reviseComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        checkOwner(comment);

        comment.reviseContent(content);
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        checkOwner(comment);

        commentRepository.delete(comment);
    }

    private void checkOwner(Comment comment) {
        String studentNo = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByStudentNumber(studentNo).orElseThrow(UserNotFoundException::new);

        if(!user.equals(comment.getWriter())) throw new NotMyCommentException();
    }

}
