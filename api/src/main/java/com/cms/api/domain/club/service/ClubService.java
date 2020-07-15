package com.cms.api.domain.club.service;

import com.cms.api.domain.auth.exception.UserNotFoundException;
import com.cms.api.domain.club.dao.ClubRepository;
import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.club.dto.ClubListResponseDto;
import com.cms.api.domain.club.exception.ClubDuplicateException;
import com.cms.api.domain.user.dao.UserRepository;
import com.cms.api.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClubService {

    private final ClubRepository clubRepository;

    private final UserRepository userRepository;

    public String createClub(String clubName) {
        String studentNo = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByStudentNumber(studentNo).orElseThrow(UserNotFoundException::new);

        if(clubRepository.findById(clubName).isPresent()) throw new ClubDuplicateException();

        Club club = clubRepository.save(Club.builder()
                .clubName(clubName)
                .leader(user)
                .build());
        return club.getClubName();
    }

    public List<ClubListResponseDto> getClubs() {
        List<Club> clubs = clubRepository.findAll();

        return clubs.stream()
                .map(club -> {
                    return ClubListResponseDto.builder()
                            .club_name(club.getClubName())
                            .introduce(club.getIntroduce())
                            .leader_name(club.getLeader().getName())
                            .leader_number(club.getLeader().getStudentNumber())
                            .build();
                })
                .collect(Collectors.toList());
    }

}
