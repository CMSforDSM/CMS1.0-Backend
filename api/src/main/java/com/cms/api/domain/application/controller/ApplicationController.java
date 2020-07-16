package com.cms.api.domain.application.controller;
import com.cms.api.domain.application.dto.ApplicationInfoResponseDto;
import com.cms.api.domain.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/applications")
@RestController
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Long applyClub(@RequestBody Map<String, String> club) {
        return applicationService.createApplication(club.get("club_name"));
    }

    @GetMapping("/{club_name}")
    public List<ApplicationInfoResponseDto> getApplications(@PathVariable String club_name) {
        return applicationService.getApplications(club_name);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Transactional
    public void acceptApplication(@RequestBody Map<String, String> application) {
        applicationService.acceptApplication(Long.parseLong(application.get("application_id")));
    }

    @DeleteMapping("/{application_id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void cancelApplication(@PathVariable("application_id") String applicationId) {
        applicationService.cancelApplication(Long.parseLong(applicationId));
    }

}
