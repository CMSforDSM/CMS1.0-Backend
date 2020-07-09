package com.cms.api.domain.user.service;

import com.cms.api.domain.auth.exception.UserNotFoundException;
import com.cms.api.domain.club.dao.ClubRepository;
import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.user.dao.UserRepository;
import com.cms.api.domain.user.domain.Role;
import com.cms.api.domain.user.domain.User;
import com.cms.api.domain.user.dto.UserInfoResponseDto;
import com.cms.api.domain.user.dto.UserInfoUpdateRequestDto;
import com.cms.api.domain.user.dto.UserJoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User join(UserJoinRequestDto requestDto) {
        String password = passwordEncoder.encode(requestDto.getPassword());

        return userRepository.save(
                User.builder()
                        .id(requestDto.getId())
                        .name(requestDto.getName())
                        .password(password)
                        .studentNumber(requestDto.getStudentNumber())
                        .role(Role.valueOf(requestDto.getRole()))
                        .build()
        );
    }

    public UserInfoResponseDto getMyInfo() {
        String studentNo = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByStudentNumber(studentNo)
                .map(user -> {
                    String clubName = (user.getClub() != null) ? user.getClub().getClubName() : null;
                    return UserInfoResponseDto.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .studentNumber(user.getStudentNumber())
                            .introduce(user.getIntroduce())
                            .club(clubName)
                            .role(user.getRoleKey())
                            .build();
                })
                .orElseThrow(UserNotFoundException::new);
    }

    public void updateMyInfo(UserInfoUpdateRequestDto requestDto) {
        String studentNo = SecurityContextHolder.getContext().getAuthentication().getName();
        String password = (requestDto.getPassword() != null) ? passwordEncoder.encode(requestDto.getPassword()) : null;

        userRepository.findByStudentNumber(studentNo)
                .map(user -> {
                    user.reviseInfo(password, requestDto.getIntroduce());
                    return true;
                }).orElseThrow(UserNotFoundException::new);
    }
}
