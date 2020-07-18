package com.cms.api.domain.scout.domain;

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
public class Scout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "target")
    private User target;

    @ManyToOne
    @JoinColumn(name = "club")
    private Club club;

    @Builder
    public Scout(User target, Club club) {
        this.target = target;
        this.club = club;
    }

}
