package com.cms.api.domain.club.controller;

import com.cms.api.domain.club.dto.ClubResponseDto;
import com.cms.api.domain.club.dto.UpdateClubInfoRequestDto;
import com.cms.api.domain.club.service.ClubService;
import com.cms.api.domain.club.service.MemberListService;
import com.cms.api.domain.club.service.MemberListServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/clubs")
@RestController
public class ClubController {

    private final ClubService clubService;

    private final MemberListServiceImpl memberListService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createClub(@RequestBody Map<String, String> clubName) {
        return clubService.createClub(clubName.get("club_name"));
    }

    @GetMapping
    public List<ClubResponseDto> getClubs() {
        return clubService.getClubs();
    }

    @GetMapping("/members")
    public void getClubMembersList(HttpServletResponse response) {
        memberListService.getMemberList(response);
    }

    @GetMapping("/{club_name}")
    public ClubResponseDto getClub(@PathVariable("club_name") String clubName) {
        return clubService.getClub(clubName);
    }

    @PatchMapping("/{club_name}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateClubIntro(@PathVariable("club_name") String clubName,
                                @RequestBody UpdateClubInfoRequestDto requestDto) {
        clubService.updateClubIntro(clubName, requestDto);
    }

    @PostMapping("/{club_name}/members")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addClubMember(@PathVariable("club_name") String clubName,
                              @RequestBody Map<String, String> studentNo) {
        clubService.addClubMember(clubName, studentNo.get("student_number"));
    }

    @PatchMapping("/{club_name}/members")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Transactional
    public void changeLeader(@PathVariable("club_name") String clubName,
                             @RequestBody Map<String, String> studentNo) {
        clubService.changeClubLeader(clubName, studentNo.get("student_number"));
    }

    @DeleteMapping("/{club_name}/members")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void expelMember(@PathVariable("club_name") String clubName,
                            @RequestParam String student_number) {
        clubService.expelMember(clubName, student_number);
    }

}
