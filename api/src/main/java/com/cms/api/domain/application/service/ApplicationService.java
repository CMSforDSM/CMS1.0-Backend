package com.cms.api.domain.application.service;

import com.cms.api.domain.application.dao.ApplicationRepository;
import com.cms.api.domain.application.domain.Application;
import com.cms.api.domain.auth.exception.UserNotFoundException;
import com.cms.api.domain.club.dao.ClubRepository;
import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.club.exception.AlreadyClubMemberException;
import com.cms.api.domain.club.exception.ClubNotFoundException;
import com.cms.api.domain.user.dao.UserRepository;
import com.cms.api.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final ClubRepository clubRepository;

    private final UserRepository userRepository;

    public Long createApplication(String clubName) {

        Club club = clubRepository.findById(clubName).orElseThrow(ClubNotFoundException::new);

        String studentNo = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByStudentNumber(studentNo).orElseThrow(UserNotFoundException::new);

        if(club.checkMemberOrLeader(user)) throw new AlreadyClubMemberException();

        Application application = applicationRepository.save(Application.builder()
                .club(club)
                .user(user)
                .build());

        return application.getId();
    }

}
