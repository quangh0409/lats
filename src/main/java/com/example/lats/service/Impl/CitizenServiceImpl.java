package com.example.lats.service.Impl;

import com.example.lats.common.BaseResponse;
import com.example.lats.model.entity.Citizen;
import com.example.lats.model.response.EducationResponse;
import com.example.lats.repository.CitizenRepository;
import com.example.lats.service.CitizenService;
import com.example.lats.service.DistrictService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CitizenServiceImpl implements CitizenService {

    private final CitizenRepository citizenRepository;
    private final DistrictService districtService;

    @Override
    @Transactional
    public BaseResponse<Citizen> create(Citizen citizen) {
        return BaseResponse.ok(citizenRepository.persistAndFlush(citizen));
    }

    /**
     * findPopulationPercentageByAgeRangeAndGender.
     *
     * @param hometown string
     * @return Double
     */
    @Override
    public List<Map<String, Object>> findPopulationByAgeGroupAndHometown(String hometown) {
        return citizenRepository.findPopulationByAgeGroupAndHometown(Objects.equals(hometown, "") ? hometown : districtService.getDistrictNameById(Long.valueOf(hometown)));
    }

    /**
     * @param hometown String
     * @return long
     */
    @Override
    public Map<String, Long> countByGenderAndHometown(String hometown) {
        Map<String, Object> rawCounts = citizenRepository.countByGenderAndHometown(Objects.equals(hometown, "") ? hometown : districtService.getDistrictNameById(Long.valueOf(hometown)));

        // Convert raw results into a more readable format
        Map<String, Long> result = new HashMap<>();
        result.put("all", ((Number) rawCounts.get("all_count")).longValue());
        result.put("male", ((Number) rawCounts.get("male_count")).longValue());
        result.put("female", ((Number) rawCounts.get("female_count")).longValue());

        return result;
    }


    public Double calculateAgingIndex() {
        Long seniorCount = citizenRepository.countSeniorCitizens();
        Long childrenCount = citizenRepository.countChildren();

        if (childrenCount == 0) {
            throw new ArithmeticException("Number of children cannot be zero when calculating aging index.");
        }

        // Tính chỉ số già hóa
        return Math.round((double) seniorCount / childrenCount * 100 * 100) / 100.0;
    }

    public Map<String, Double> calculatePopulationDistribution() {
        // Tổng dân số
        Long totalPopulation = citizenRepository.countByGenderAndHometown(null, null);

        if (totalPopulation == 0) {
            throw new IllegalArgumentException("No population data available.");
        }

        // Dân số theo độ tuổi
        Long populationAge0To14 = citizenRepository.countPopulationAge0To14();
        Long populationAge15To64 = citizenRepository.countPopulationAge15To64();
        Long populationAge65Plus = citizenRepository.countPopulationAge65Plus();

        // Tính tỷ trọng
        double youthPopulationRate = Math.round((double) populationAge0To14 / totalPopulation * 100 * 100) / 100.0;
        double workingAgePopulationRate = Math.round((double) populationAge15To64 / totalPopulation * 100 * 100) / 100.0;
        double seniorPopulationRate = Math.round((double) populationAge65Plus / totalPopulation * 100 * 100) / 100.0;

        // Kết quả
        Map<String, Double> populationDistribution = new HashMap<>();
        populationDistribution.put("youth", youthPopulationRate);
        populationDistribution.put("workingAge", workingAgePopulationRate);
        populationDistribution.put("senior", seniorPopulationRate);

        return populationDistribution;
    }

    public List<EducationResponse> calculateEducationLevelPercentages(String hometown) {
        // Lấy dữ liệu về số dân theo từng trình độ học vấn và HOMETOWN (hoặc toàn bộ nếu HOMETOWN là null)
        List<Object[]> result = citizenRepository.countPopulationByEducationLevelAndHometown(Objects.equals(hometown, "") ? hometown : districtService.getDistrictNameById(Long.valueOf(hometown)));

        // Lấy tổng số dân
        Long totalPopulation = citizenRepository.countByGenderAndHometown(null, Objects.equals(hometown, "") ? hometown : districtService.getDistrictNameById(Long.valueOf(hometown)));

        // Kiểm tra tổng số dân có khác 0 để tránh lỗi chia cho 0
        if (totalPopulation == 0) {
            System.out.println("Tổng số dân là 0, không thể tính tỷ lệ.");
            return new ArrayList<EducationResponse>();
        }

        // Khởi tạo Map để lưu trữ tỷ lệ phần trăm

        var list = new ArrayList<EducationResponse>();

        // Tính toán và thêm vào Map tỷ lệ phần trăm cho từng cấp bậc học vấn
        for (Object[] row : result) {
            String educationalLevel = (String) row[0];
            Long populationCount = (Long) row[1];
            double percentage = Math.round((populationCount / (double) totalPopulation) * 100 * 100.0) / 100.0;
            list.add(new EducationResponse().setPercentage(percentage).setLevel(educationalLevel));
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getDistrictPopulationCounts() {
        return citizenRepository.getDistrictPopulationCounts();
    }
}
