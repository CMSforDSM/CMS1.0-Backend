package com.cms.api.domain.model;

import com.cms.api.domain.user.domain.User;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "writer", insertable = false, updatable = false)
    private User writer;

    @OneToOne
    @JoinColumn(name = "comment_id")
    private Comment child_comment;
}
