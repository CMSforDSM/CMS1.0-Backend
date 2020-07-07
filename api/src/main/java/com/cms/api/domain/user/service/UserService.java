package com.cms.api.domain.user.service;

import com.cms.api.domain.user.dao.UserRepository;
import com.cms.api.domain.user.domain.Role;
import com.cms.api.domain.user.domain.User;
import com.cms.api.domain.user.dto.UserJoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
