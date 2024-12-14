package com.example.lats.service;

import com.example.lats.model.entity.District;

import java.util.List;

public interface DistrictService {
    String getDistrictNameById(Long districtId);

    List<District> getDistricts();
}
