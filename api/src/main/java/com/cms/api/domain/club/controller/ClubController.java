package com.cms.api.domain.club.controller;

import com.cms.api.domain.club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/clubs")
@RestController
public class ClubController {

    private final ClubService clubService;

    @PostMapping
    public String createClub(@RequestBody Map<String, String> clubName) {
        return clubService.createClub(clubName.get("club_name"));
    }

}
