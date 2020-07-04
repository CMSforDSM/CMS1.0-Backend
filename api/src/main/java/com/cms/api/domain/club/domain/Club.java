package com.cms.api.domain.club.domain;

import com.cms.api.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Club {
    @Id
    @Column(name = "club_name")
    private String clubName;

    @Column(length = 30)
    private String introduce;

    @OneToOne
    @JoinColumn(name = "leader")
    private User leader;

    @OneToMany(mappedBy = "club")
    private List<User> members = new ArrayList<>();
}
