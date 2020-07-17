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

    private String title;

    private String content;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> childComment;

    @ManyToOne
    @JoinColumn(name = "parent_comment")
    private Comment parentComment;

    @Builder
    public Comment(User writer, Post post, String title, String content) {
        this.writer = writer;
        this.post = post;
        this.title = title;
        this.content = content;
    }

    public void reviseContent(String title, String content) {
        if(title != null) this.title = title;
        if(content != null) this.content = content;
    }

}
