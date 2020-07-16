package com.cms.api.domain.club.service;

import com.cms.api.domain.auth.exception.UserNotFoundException;
import com.cms.api.domain.club.dao.ClubRepository;
import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.club.dto.ClubListResponseDto;
import com.cms.api.domain.club.dto.ClubResponseDto;
import com.cms.api.domain.club.dto.UpdateClubInfoRequestDto;
import com.cms.api.domain.club.exception.*;
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
                            .logo(club.getLogo())
                            .leader(club.getLeader().getStudentNumber() + "-" + club.getLeader().getName())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public ClubResponseDto getClub(String clubName) {
        Club club = clubRepository.findById(clubName).orElseThrow(ClubNotFoundException::new);
        User leader = club.getLeader();

        return ClubResponseDto.builder()
                .club_name(club.getClubName())
                .introduce(club.getIntroduce())
                .logo(club.getLogo())
                .leader(leader.getStudentNumber() + "-" + leader.getName())
                .members(club.getMembers())
                .build();
    }

    public void updateClubIntro(String clubName, UpdateClubInfoRequestDto request) {
        Club club = clubRepository.findById(clubName).orElseThrow(ClubNotFoundException::new);

        checkLeader(club);

        club.updateInfo(request.getIntroduce(), request.getLogo());
        clubRepository.save(club);
    }


    public void addClubMember(String clubName, String studentNo) {
        Club club = clubRepository.findById(clubName).orElseThrow(ClubNotFoundException::new);
        User user = userRepository.findByStudentNumber(studentNo).orElseThrow(UserNotFoundException::new);

        if(club.checkMemberOrLeader(user)) throw new AlreadyClubMemberException();

        user.changeClub(club);
        userRepository.save(user);
    }

    public void changeClubLeader(String clubName, String studentNo) {
        Club club = clubRepository.findById(clubName).orElseThrow(ClubNotFoundException::new);
        User user = userRepository.findByStudentNumber(studentNo).orElseThrow(UserNotFoundException::new);

        if(!club.checkMember(user)) throw new NotClubMemberException();
        User leader = userRepository.findByStudentNumber(club.getLeader().getStudentNumber())
                .orElseThrow(UserNotFoundException::new);

        user.changeClub(null);
        userRepository.save(user);
        club.changeLeader(user);
        clubRepository.save(club);

        leader.changeClub(null);
        userRepository.save(leader);
        leader.changeClub(club);
        userRepository.save(leader);

    }

    public void expelMember(String clubName, String studentNo) {
        Club club = clubRepository.findById(clubName).orElseThrow(ClubNotFoundException::new);
        checkLeader(club);

        User user = userRepository.findByStudentNumber(studentNo).orElseThrow(UserNotFoundException::new);
        if(!club.checkMember(user)) throw new NotClubMemberException();

        user.changeClub(null);
        userRepository.save(user);
    }

    private void checkLeader(Club club) {
        String studentNo = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByStudentNumber(studentNo).orElseThrow(UserNotFoundException::new);

        if(!user.equals(club.getLeader())) throw new NotClubLeaderException();
    }

}
