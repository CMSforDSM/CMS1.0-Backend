package com.cms.api.domain.club.controller;

import com.cms.api.domain.club.dto.ClubListResponseDto;
import com.cms.api.domain.club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping
    public List<ClubListResponseDto> getClubs() {
        return clubService.getClubs();
    }

    @PatchMapping("/{club_name}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateClubIntro(@PathVariable("club_name") String clubName,
                                @RequestBody Map<String, String> introduce) {
        clubService.updateClubIntro(clubName, introduce.get("introduce"));
    }

    @PostMapping("/{club_name}/members")
    public void addClubMember(@PathVariable("club_name") String clubName,
                              @RequestBody Map<String, String> studentNo) {
        clubService.addClubMember(clubName, studentNo.get("student_number"));
    }

}
