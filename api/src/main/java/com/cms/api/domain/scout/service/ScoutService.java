package com.cms.api.domain.scout.service;

import com.cms.api.domain.auth.exception.UserNotFoundException;
import com.cms.api.domain.club.dao.ClubRepository;
import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.club.exception.ClubNotFoundException;
import com.cms.api.domain.club.exception.NotClubLeaderException;
import com.cms.api.domain.scout.dao.ScoutRepository;
import com.cms.api.domain.scout.domain.Scout;
import com.cms.api.domain.user.dao.UserRepository;
import com.cms.api.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScoutService {

    public ScoutRepository scoutRepository;

    public UserRepository userRepository;

    public ClubRepository clubRepository;

    public Long createScout(String targetNo) {
        User target = userRepository.findByStudentNumber(targetNo).orElseThrow(UserNotFoundException::new);

        String userNo = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByStudentNumber(userNo).orElseThrow(UserNotFoundException::new);

        Club club = clubRepository.findById(user.getClub().getClubName()).orElseThrow(ClubNotFoundException::new);
        if(!club.checkLeader(user)) throw new NotClubLeaderException();

        Scout scout = scoutRepository.save(Scout.builder()
                .target(target)
                .club(club)
                .build());

        return scout.getId();
    }

}
