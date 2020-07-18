package com.cms.api.domain.scout.controller;

import com.cms.api.domain.scout.dto.ScoutResponseDto;
import com.cms.api.domain.scout.service.ScoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/scouts")
@RestController
public class ScoutController {

    private final ScoutService scoutService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Long createScout(@RequestBody Map<String, String> target) {
        return scoutService.createScout(target.get("target"));
    }

    @GetMapping
    public List<ScoutResponseDto> getScouts() {
        return scoutService.getScouts();
    }

    @PatchMapping("/{scout_id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Transactional
    public void acceptScout(@PathVariable("scout_id") String scoutId) {
        scoutService.acceptScout(Long.parseLong(scoutId));
    }

    @DeleteMapping("/{scout_id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void denyScout(@PathVariable("scout_id") String scoutId) {
        scoutService.denyScout(Long.parseLong(scoutId));
    }

}
