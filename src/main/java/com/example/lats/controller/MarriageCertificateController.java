package com.example.lats.controller;

import com.example.lats.common.BaseResponse;
import com.example.lats.service.MarriageCertificateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/marriages")
@AllArgsConstructor
public class MarriageCertificateController {
    private final MarriageCertificateService marriageCertificateService;

    @GetMapping("/average-marriage-age")
    public BaseResponse<Map<String, Double>> getAverageMarriageAgeByHometown(@RequestParam(required = false) String hometown) {
        Map<String, Double> result = marriageCertificateService.getAverageMarriageAgeByHometown(hometown);
        return BaseResponse.ok(result);
    }
}
