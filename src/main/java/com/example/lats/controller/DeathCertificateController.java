package com.example.lats.controller;

import com.example.lats.common.BaseResponse;
import com.example.lats.service.DeathCertificateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/deaths")
@AllArgsConstructor
public class DeathCertificateController {
    private final DeathCertificateService deathCertificateService;

    @GetMapping("/under-one-year")
    public BaseResponse<Map<String, Double>> getUnderOneYearDeathRate() {
        return BaseResponse.ok(deathCertificateService.calculateUnderOneYearDeathRate());
    }
}
