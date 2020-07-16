package com.cms.api.domain.application.domain;

import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "applicant")
    private User user;

    @ManyToOne
    @JoinColumn(name = "club_name")
    private Club club;

    @Builder
    public Application(User user, Club club) {
        this.user = user;
        this.club = club;
    }
}
