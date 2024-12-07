package com.example.lats.service.Impl;

import com.example.lats.repository.CitizenRepository;
import com.example.lats.repository.JobExperienceRepository;
import com.example.lats.service.CitizenService;
import com.example.lats.service.DistrictService;
import com.example.lats.service.JobExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JobExperienceServiceImpl implements JobExperienceService {

    private final DistrictService districtService;
    private final JobExperienceRepository jobExperienceRepository;
    private final CitizenRepository citizenRepository;

    @Override
    public Map<String, Object> findGenderCountOver18ByHometown(String hometown) {
        var all = citizenRepository.countOver18ByHometown(
                Objects.equals(hometown, "") ? hometown : districtService.getDistrictNameById(Long.valueOf(hometown)));
        List<Object[]> results = jobExperienceRepository.findGenderCountOver18ByHometown(
                Objects.equals(hometown, "") ? hometown : districtService.getDistrictNameById(Long.valueOf(hometown)));

        Map<String, Double> genderCountMap = new HashMap<>();
        for (Object[] result : results) {
            String gender = (String) result[0];
            var count = ((Number) result[1]).longValue();
            genderCountMap.put(gender, (double) Math.round(((double) count /all) * 100.0));
        }

        // Calculate total count
        long totalCount = genderCountMap.values().stream().mapToLong(Double::longValue).sum();

        // Add "all" key for the total count
        Map<String, Object> response = new HashMap<>(genderCountMap);
        response.put("all", totalCount);

        return response;
    }
}
