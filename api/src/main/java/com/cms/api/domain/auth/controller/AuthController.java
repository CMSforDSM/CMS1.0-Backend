package com.cms.api.domain.auth.controller;

import com.cms.api.domain.auth.dto.LoginRequestDto;
import com.cms.api.domain.auth.dto.TokenResponseDto;
import com.cms.api.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @PutMapping("/refresh")
    public TokenResponseDto refreshToken(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return authService.refreshToken(refreshToken);
    }
}
