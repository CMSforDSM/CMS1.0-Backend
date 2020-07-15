package com.cms.api.domain.club.domain;

import com.cms.api.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Builder
    public Club(String clubName, User leader) {
        this.clubName = clubName;
        this.leader = leader;
        this.introduce = "";
    }

    public void updateIntro(String introduce) {
        this.introduce = introduce;
    }

    public void addMember(User member) {
        this.members.add(member);
    }

    public boolean checkMember(User member) {
        return this.members.contains(member) || this.leader.equals(member);
    }

    public List<String> getMembers() {
        return this.members.stream()
                .map(member -> {
                    return member.getStudentNumber() + "-" + member.getName();
                })
                .collect(Collectors.toList());
    }

}
