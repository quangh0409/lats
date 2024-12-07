package com.example.lats.service.Impl;

import com.example.lats.repository.MarriageCertificateRepository;
import com.example.lats.service.DistrictService;
import com.example.lats.service.MarriageCertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MarriageCertificateServiceImpl implements MarriageCertificateService {

    private final MarriageCertificateRepository marriageCertificateRepository;
    private final DistrictService districtService;

    @Override
    public Map<String, Double> getAverageMarriageAgeByHometown(String hometown) {
        // Lấy dữ liệu từ repository
        var wifeAges = marriageCertificateRepository.findWifeMarriageAges(Objects.equals(hometown, "") ? hometown : districtService.getDistrictNameById(Long.valueOf(hometown)));
        var husbandAges = marriageCertificateRepository.findHusbandMarriageAges(Objects.equals(hometown, "") ? hometown : districtService.getDistrictNameById(Long.valueOf(hometown)));
        var allAges = new ArrayList<Map<String, Object>>();

        allAges.addAll(wifeAges);
        allAges.addAll(husbandAges);

        // Tính trung bình
        double averageWifeAge = calculateAverageAge(wifeAges);
        double averageHusbandAge = calculateAverageAge(husbandAges);
        double averageAll = calculateAverageAge(allAges);

        // Trả về kết quả
        return Map.of(
                "female", averageWifeAge,
                "male", averageHusbandAge,
                "allAge", averageAll
        );
    }


    private double calculateAverageAge(List<Map<String, Object>> data) {
        int totalAgeSum = 0; // Tổng tích (age * total)
        int totalCount = 0;  // Tổng của total

        for (Map<String, Object> entry : data) {
            int age = ((Number) entry.get("age")).intValue(); // Lấy giá trị age
            int count = ((Number) entry.get("total")).intValue(); // Lấy giá trị total
            totalAgeSum += age * count; // Cộng dồn tích (age * total)
            totalCount += count; // Cộng dồn total
        }
        double average = 0;

        // Kiểm tra để tránh chia cho 0
        if (totalCount != 0) {
            // Tính trung bình
            average = (double) totalAgeSum / totalCount;
        }


        // Làm tròn đến 2 chữ số thập phân
        return Math.round(average * 100.0) / 100.0;
    }
}
