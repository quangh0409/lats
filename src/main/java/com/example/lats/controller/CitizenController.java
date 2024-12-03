package com.example.lats.controller;

import com.example.lats.common.BaseResponse;
import com.example.lats.common.Response;
import com.example.lats.model.entity.Citizen;
import com.example.lats.service.CitizenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/citizen")
@AllArgsConstructor
public class CitizenController {

    private final CitizenService citizenService;

    @PostMapping("/")
    public BaseResponse<List<Map<String, Object>>> findPopulationByAgeGroupAndHometown(@RequestParam String hometown) {
        return BaseResponse.ok(citizenService.findPopulationByAgeGroupAndHometown(hometown));
    }

    @PostMapping("/count")
    public BaseResponse<Long> countByGenderAndHometown(@RequestParam String gender, @RequestParam String hometown) {
        return BaseResponse.ok(citizenService.countByGenderAndHometown(gender, hometown));
    }

    @GetMapping("/aging")
    public BaseResponse<Double> calculateAgingIndex() {
        return BaseResponse.ok(citizenService.calculateAgingIndex());
    }

    @GetMapping("/distribution")
    public BaseResponse<Map<String, Double>> calculatePopulationDistribution() {
        return BaseResponse.ok(citizenService.calculatePopulationDistribution());
    }

    @GetMapping("/education-level-percentages")
    public Map<String, Double> getEducationLevelPercentages(@RequestParam(required = false) String hometown) {
        return citizenService.calculateEducationLevelPercentages(hometown);
    }

    @GetMapping("/district-population")
    public List<Map<String, Object>> getDistrictPopulationCounts() {
        return citizenService.getDistrictPopulationCounts();
    }
}
