package com.cms.api.domain.comment.domain;

import com.cms.api.domain.model.BaseTimeEntity;
import com.cms.api.domain.post.domain.Post;
import com.cms.api.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writer")
    private User writer;

    @ManyToOne
    @JoinColumn(name = "post")
    private Post post;

    private String content;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<Comment> childComment;

    @ManyToOne
    @JoinColumn(name = "parent_comment")
    private Comment parentComment;

    @Builder
    public Comment(User writer, Post post, String content, Comment parentComment) {
        this.writer = writer;
        this.post = post;
        this.content = content;
        this.parentComment = parentComment;
    }

    public void reviseContent(String content) {
        this.content = content;
    }

}
