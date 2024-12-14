package com.example.lats.service.Impl;

import com.example.lats.repository.BirthCertificateRepository;
import com.example.lats.repository.DeathCertificateRepository;
import com.example.lats.service.DeathCertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeathCertificateServiceImpl implements DeathCertificateService {
    private final DeathCertificateRepository deathCertificateRepository;

    private final BirthCertificateRepository birthCertificateRepository;

    @Override
    public Map<String, Double> calculateUnderOneYearDeathRate() {
        DecimalFormat df = new DecimalFormat("#.00");
        // Fetch counts for deaths
        Map<String, Object> deathCounts = deathCertificateRepository.countUnderOneYearDeathsByGender();

        // Fetch counts for births
        Map<String, Object> birthCounts = birthCertificateRepository.countByGender();

        // Extract data
        long totalDeaths = ((Number) deathCounts.get("all_count")).longValue();
        long maleDeaths = ((Number) deathCounts.get("male_count")).longValue();
        long femaleDeaths = ((Number) deathCounts.get("female_count")).longValue();

        long totalBirths = ((Number) birthCounts.get("all_count")).longValue();
        long maleBirths = ((Number) birthCounts.get("male_count")).longValue();
        long femaleBirths = ((Number) birthCounts.get("female_count")).longValue();

        // Calculate death rates
        double totalRate = totalBirths > 0 ? (totalDeaths * 1000.0 / totalBirths) : 0;
        double maleRate = maleBirths > 0 ? (maleDeaths * 1000.0 / maleBirths) : 0;
        double femaleRate = femaleBirths > 0 ? (femaleDeaths * 1000.0 / femaleBirths) : 0;

        // Làm tròn kết quả
        totalRate = Double.parseDouble(df.format(totalRate));
        maleRate = Double.parseDouble(df.format(maleRate));
        femaleRate = Double.parseDouble(df.format(femaleRate));

        // Prepare response
        Map<String, Double> result = new HashMap<>();
        result.put("all", totalRate);
        result.put("male", maleRate);
        result.put("female", femaleRate);

        return result;
    }

}
