package com.example.lats.service.Impl;

import com.example.lats.model.entity.District;
import com.example.lats.repository.DistrictRepository;
import com.example.lats.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTMLDocument;
import java.util.List;
import java.util.Spliterators;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    @Override
    public String getDistrictNameById(Long districtId) {
        return districtRepository.findDistrictNameById(districtId);
    }

    @Override
    public List<District> getDistricts() {
        return districtRepository.findAll(Specification.allOf());
    }
}
