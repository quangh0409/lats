package com.example.lats.service;

import com.example.lats.model.response.GenderRatioResponse;

import java.util.List;
import java.util.Map;

public interface BirthCertificateService {
    public List<GenderRatioResponse> getGenderRatio();
}
