package com.example.lats.service.Impl;

import com.example.lats.common.BaseResponse;
import com.example.lats.model.entity.Citizen;
import com.example.lats.repository.CitizenRepository;
import com.example.lats.service.CitizenService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CitizenServiceImpl implements CitizenService {

    private final CitizenRepository citizenRepository;

    @Override
    @Transactional
    public BaseResponse<Citizen> create(Citizen citizen) {
        return BaseResponse.ok(citizenRepository.persistAndFlush(citizen));
    }

    /**
     * findPopulationPercentageByAgeRangeAndGender.
     *
     * @param ageMin int
     * @param ageMax int
     * @param gender string
     * @return Double
     */
    @Override
    public Double findPopulationPercentageByAgeRangeAndGender(int ageMin, int ageMax, String gender) {
        return citizenRepository.findPopulationPercentageByAgeRangeAndGender(ageMin,ageMax,gender);
    }

    /**
     * @param gender String
     * @param hometown String
     * @return long
     */
    @Override
    public Long countByGenderAndHometown(String gender, String hometown) {
        return citizenRepository.countByGenderAndHometown(gender, hometown);
    }

    public Double calculateAgingIndex() {
        Long seniorCount = citizenRepository.countSeniorCitizens();
        Long childrenCount = citizenRepository.countChildren();

        if (childrenCount == 0) {
            throw new ArithmeticException("Number of children cannot be zero when calculating aging index.");
        }

        // Tính chỉ số già hóa
        return (double) seniorCount / childrenCount * 100;
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
        double youthPopulationRate = (double) populationAge0To14 / totalPopulation * 100;
        double workingAgePopulationRate = (double) populationAge15To64 / totalPopulation * 100;
        double seniorPopulationRate = (double) populationAge65Plus / totalPopulation * 100;

        // Kết quả
        Map<String, Double> populationDistribution = new HashMap<>();
        populationDistribution.put("youth", youthPopulationRate);
        populationDistribution.put("workingAge", workingAgePopulationRate);
        populationDistribution.put("senior", seniorPopulationRate);

        return populationDistribution;
    }

    public Map<String, Double> calculateEducationLevelPercentages(String hometown) {
        // Lấy dữ liệu về số dân theo từng trình độ học vấn và HOMETOWN (hoặc toàn bộ nếu HOMETOWN là null)
        List<Object[]> result = citizenRepository.countPopulationByEducationLevelAndHometown(hometown);

        // Lấy tổng số dân
        Long totalPopulation = citizenRepository.countByGenderAndHometown(null, hometown);

        // Kiểm tra tổng số dân có khác 0 để tránh lỗi chia cho 0
        if (totalPopulation == 0) {
            System.out.println("Tổng số dân là 0, không thể tính tỷ lệ.");
            return new HashMap<>();
        }

        // Khởi tạo Map để lưu trữ tỷ lệ phần trăm
        Map<String, Double> educationLevelPercentages = new HashMap<>();

        // Tính toán và thêm vào Map tỷ lệ phần trăm cho từng cấp bậc học vấn
        for (Object[] row : result) {
            String educationalLevel = (String) row[0];
            Long populationCount = (Long) row[1];
            double percentage = (populationCount / (double) totalPopulation) * 100;
            educationLevelPercentages.put(educationalLevel, percentage);
        }

        return educationLevelPercentages;
    }
}
