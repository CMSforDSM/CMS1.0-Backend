package com.cms.api.domain.model;

import com.cms.api.domain.user.domain.User;

import javax.persistence.*;

@MappedSuperclass
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "writer")
    private User writer;
}
