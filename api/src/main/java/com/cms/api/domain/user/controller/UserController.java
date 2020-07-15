package com.cms.api.domain.user.controller;

import com.cms.api.domain.user.dto.UserInfoResponseDto;
import com.cms.api.domain.user.dto.UserInfoUpdateRequestDto;
import com.cms.api.domain.user.dto.UserJoinRequestDto;
import com.cms.api.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void join(@RequestBody UserJoinRequestDto requestDto) {
        userService.join(requestDto);
    }

    @GetMapping("/me")
    public UserInfoResponseDto getMyInfo() {
        return userService.getMyInfo();
    }

    @PatchMapping("/me")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Transactional
    public void updateMyInfo(@RequestBody UserInfoUpdateRequestDto requestDto) {
        userService.updateMyInfo(requestDto);
    }
}
