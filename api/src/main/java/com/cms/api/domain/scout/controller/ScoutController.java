package com.cms.api.domain.scout.controller;

import com.cms.api.domain.scout.service.ScoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}
