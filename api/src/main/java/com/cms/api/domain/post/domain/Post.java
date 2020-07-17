package com.cms.api.domain.post.domain;

import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.comment.domain.Comment;
import com.cms.api.domain.model.BaseTimeEntity;
import com.cms.api.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writer")
    private User writer;

    @ManyToOne
    @JoinColumn(name = "club")
    private Club club;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostType postType;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(User writer, String title, String content, PostType postType, Club club) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.postType = postType;
        this.club = club;
    }

    public void updateContent(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
