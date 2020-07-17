package com.cms.api.domain.user.domain;

import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.post.domain.Post;
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
public class User {
    @Id
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(name = "student_number", nullable = false, unique = true)
    private String studentNumber;

    @Column(length = 30)
    private String introduce;

    @ManyToOne
    @JoinColumn(name = "club_name")
    private Club club;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "writer")
    private List<Post> posts = new ArrayList<>();

    @Builder
    public User(String id, String password, String name, String studentNumber, Role role) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.studentNumber = studentNumber;
        this.introduce = "";
        this.role = role;
        this.club = null;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public void reviseInfo(String password, String introduce) {
        if(password != null) this.password = password;
        if(introduce != null) this.introduce = introduce;
    }

    public void changeClub(Club club) {
        this.club = club;
    }

}
