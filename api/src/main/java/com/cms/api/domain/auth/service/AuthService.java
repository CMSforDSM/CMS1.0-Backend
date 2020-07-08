package com.cms.api.domain.auth.service;

import com.cms.api.domain.auth.dto.LoginRequestDto;
import com.cms.api.domain.auth.dto.TokenResponseDto;
import com.cms.api.domain.auth.exception.UserNotFoundException;
import com.cms.api.domain.user.dao.UserRepository;
import com.cms.api.domain.user.domain.User;
import com.cms.api.global.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    public TokenResponseDto login(LoginRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getId())
                .filter(u -> passwordEncoder.matches(requestDto.getPassword(), u.getPassword()))
                .orElseThrow(UserNotFoundException::new);

        return responseToken(user.getStudentNumber());
    }

    private TokenResponseDto responseToken(String studentNo) {
        return TokenResponseDto.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(studentNo))
                .refreshToken(jwtTokenProvider.generateRefreshToken(studentNo))
                .build();
    }
}
