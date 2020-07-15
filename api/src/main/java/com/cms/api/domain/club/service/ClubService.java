package com.cms.api.domain.club.service;

import com.cms.api.domain.auth.exception.UserNotFoundException;
import com.cms.api.domain.club.dao.ClubRepository;
import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.club.exception.ClubDuplicateException;
import com.cms.api.domain.user.dao.UserRepository;
import com.cms.api.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

}
