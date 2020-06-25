package com.cms.api.domain.user.domain;

import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.model.Comment;
import com.cms.api.domain.model.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(name = "student_number", nullable = false)
    private String studentNumber;

    @ManyToOne
    @JoinColumn(name = "club_name")
    private Club club;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "writer")
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToMany(mappedBy = "writer")
    private List<Post> posts = new ArrayList<Post>();

    @Builder
    public User(String id, String password, String name, String studentNumber, Club club, Role role) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.studentNumber = studentNumber;
        this.club = club;
        this.role = role;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
