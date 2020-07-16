package com.cms.api.domain.application.service;

import com.cms.api.domain.application.dao.ApplicationRepository;
import com.cms.api.domain.application.domain.Application;
import com.cms.api.domain.application.dto.ApplicationInfoResponseDto;
import com.cms.api.domain.application.exception.ApplicationNotFoundException;
import com.cms.api.domain.application.exception.InvalidApplicationException;
import com.cms.api.domain.auth.exception.UserNotFoundException;
import com.cms.api.domain.club.dao.ClubRepository;
import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.club.exception.AlreadyClubMemberException;
import com.cms.api.domain.club.exception.ClubNotFoundException;
import com.cms.api.domain.club.exception.NotClubLeaderException;
import com.cms.api.domain.user.dao.UserRepository;
import com.cms.api.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ApplicationInfoResponseDto> getApplications(String clubName) {

        Club club = clubRepository.findById(clubName).orElseThrow(ClubNotFoundException::new);

        String studentNo = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByStudentNumber(studentNo).orElseThrow(UserNotFoundException::new);

        if(!user.equals(club.getLeader())) throw new NotClubLeaderException();

        return club.getApplications().stream()
                .map(application -> {
                    return ApplicationInfoResponseDto.builder()
                            .applicant(application.getUser().getStudentNumber() + "-" + application.getUser().getName())
                            .application_no(application.getId())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public void acceptApplication(Long application_id) {
        String studentNo = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByStudentNumber(studentNo).orElseThrow(UserNotFoundException::new);

        Application application = applicationRepository.findById(application_id)
                .orElseThrow(ApplicationNotFoundException::new);
        Club club = application.getClub();
        
        if(!application.getClub().equals(user.getClub())) throw new InvalidApplicationException();
        if(!user.equals(club.getLeader())) throw new NotClubLeaderException();

        User applicant = application.getUser();
        applicant.changeClub(club);
        userRepository.save(applicant);

        applicationRepository.delete(application);
    }

}
