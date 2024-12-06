package com.example.lats.service.Impl;

import com.example.lats.model.response.GenderRatioResponse;
import com.example.lats.repository.BirthCertificateRepository;
import com.example.lats.service.BirthCertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BirthCertificateServiceImpl implements BirthCertificateService {

    private final BirthCertificateRepository birthCertificateRepository;

    public List<GenderRatioResponse> getGenderRatio() {
        List<GenderRatioResponse> result = new ArrayList<>();

        // Query for the entire province
        birthCertificateRepository.findGenderRatioProvince().forEach(record -> {
            result.add(new GenderRatioResponse((String) record.get("province"),
                    (BigDecimal) record.get("percentage")));
        });

        // Query for each district
        birthCertificateRepository.findGenderRatioByDistrict().forEach(record -> {
            if (record.get("district") != null) {
                result.add(new GenderRatioResponse((String) record.get("district"),
                        (BigDecimal) record.get("percentage")));
            }
        });

        return result;
    }
}
