package com.example.lats.controller;

import com.example.lats.common.BaseResponse;
import com.example.lats.model.entity.District;
import com.example.lats.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/districts")
@RequiredArgsConstructor
public class DistrictController {
    private final DistrictService districtService;

    @GetMapping
    public BaseResponse<List<District>> getDistricts(){
        return BaseResponse.ok(districtService.getDistricts());
    }
}
