package com.example.lats.controller;

import com.example.lats.common.BaseResponse;
import com.example.lats.model.response.GenderRatioResponse;
import com.example.lats.service.BirthCertificateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/births")
@AllArgsConstructor
public class BirthCertificateController {

    private final BirthCertificateService birthCertificateService;

    @GetMapping
    public BaseResponse
            <List<GenderRatioResponse>> getHometownGenderData(@RequestParam(required = false) String hometown) {
        return BaseResponse.ok(birthCertificateService.getGenderRatio());
    }
}
