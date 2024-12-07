package com.example.lats.controller;

import com.example.lats.common.BaseResponse;
import com.example.lats.service.JobExperienceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/job-experience")
@AllArgsConstructor
public class JobExperienceController {

    private final JobExperienceService jobExperienceService;

    @GetMapping("/statistics")
    public BaseResponse<Map<String, Object>> getJobStatistics(@RequestParam(required = false) String hometown) {
        Map<String, Object> statistics = jobExperienceService.findGenderCountOver18ByHometown(hometown);
        return BaseResponse.ok(statistics);
    }
}
