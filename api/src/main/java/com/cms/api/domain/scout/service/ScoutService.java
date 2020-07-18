package com.cms.api.domain.scout.service;

import com.cms.api.domain.auth.exception.UserNotFoundException;
import com.cms.api.domain.club.dao.ClubRepository;
import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.club.exception.ClubNotFoundException;
import com.cms.api.domain.club.exception.NotClubLeaderException;
import com.cms.api.domain.scout.dao.ScoutRepository;
import com.cms.api.domain.scout.domain.Scout;
import com.cms.api.domain.scout.dto.ScoutResponseDto;
import com.cms.api.domain.scout.exception.NotMyScoutException;
import com.cms.api.domain.scout.exception.ScoutNotFoundException;
import com.cms.api.domain.user.dao.UserRepository;
import com.cms.api.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScoutService {

    private ScoutRepository scoutRepository;

    private UserRepository userRepository;

    private ClubRepository clubRepository;

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

    public List<ScoutResponseDto> getScouts() {
        String studentNo = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByStudentNumber(studentNo).orElseThrow(UserNotFoundException::new);

        List<Scout> scouts = scoutRepository.findAllByTarget(user);

        return scouts.stream()
                .map(scout -> {
                    return ScoutResponseDto.builder()
                            .scout_id(scout.getId())
                            .club(scout.getClub().getClubName())
                            .target(scout.getTarget().getStudentNumber() + "-" + scout.getTarget().getName())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public void acceptScout(Long scoutId) {
        Scout scout = scoutRepository.findById(scoutId).orElseThrow(ScoutNotFoundException::new);
        User user = checkScoutOwner(scout);

        Club club = clubRepository.findById(scout.getClub().getClubName())
                .orElseThrow(ClubNotFoundException::new);
        user.changeClub(club);

        scoutRepository.delete(scout);
    }

    public void denyScout(Long scoutId) {

        Scout scout = scoutRepository.findById(scoutId).orElseThrow(ScoutNotFoundException::new);
        checkScoutOwner(scout);

        scoutRepository.delete(scout);
    }

    private User checkScoutOwner(Scout scout) {
        String studentNo = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByStudentNumber(studentNo).orElseThrow(UserNotFoundException::new);

        if(!scout.getTarget().equals(user)) throw new NotMyScoutException();
        return user;
    }

}
