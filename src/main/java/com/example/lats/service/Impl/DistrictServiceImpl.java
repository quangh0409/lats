package com.example.lats.service.Impl;

import com.example.lats.repository.DistrictRepository;
import com.example.lats.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    @Override
    public String getDistrictNameById(Long districtId) {
        return districtRepository.findDistrictNameById(districtId);
    }
}
