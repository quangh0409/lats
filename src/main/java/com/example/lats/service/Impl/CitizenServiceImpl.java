package com.example.lats.service.Impl;

import com.example.lats.common.BaseResponse;
import com.example.lats.model.entity.Citizen;
import com.example.lats.model.response.EducationResponse;
import com.example.lats.model.response.EthnicityCount;
import com.example.lats.repository.CitizenRepository;
import com.example.lats.repository.EducationRepository;
import com.example.lats.service.CitizenService;
import com.example.lats.service.DistrictService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CitizenServiceImpl implements CitizenService {

    private final CitizenRepository citizenRepository;
    private final DistrictService districtService;
    private final EducationRepository educationRepository;


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

        long totalPopulation = 1;

        for (Object[] row : result) {
            totalPopulation += (Long) row[1];
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
        var rawData = citizenRepository.getDistrictPopulationCounts();
        ObjectMapper objectMapper = new ObjectMapper();

        // Tạo danh sách mới chứa các bản sao có thể chỉnh sửa
        List<Map<String, Object>> processedData = new ArrayList<>();

        for (Map<String, Object> row : rawData) {
            // Tạo bản sao mới của Map
            Map<String, Object> editableRow = new HashMap<>(row);

            String coordinatesJson = (String) editableRow.get("COORDINATES");
            if (coordinatesJson != null) {
                try {
                    // Chuyển từ chuỗi JSON sang Map
                    Map<String, Double> coordinatesMap = objectMapper.readValue(coordinatesJson, Map.class);
                    editableRow.put("COORDINATES", coordinatesMap); // Ghi đè giá trị mới
                } catch (Exception e) {
                    editableRow.put("COORDINATES", null); // Xử lý lỗi nếu JSON không hợp lệ
                }
            }

            processedData.add(editableRow); // Thêm bản ghi đã xử lý vào danh sách mới
        }
        return processedData;
    }

    @Override
    public List<EthnicityCount> getGroupedByEthnicity(String hometown) {
        var ethnicities= citizenRepository.findEthnicityAndCitizenCount(Objects.equals(hometown, "") ? hometown : districtService.getDistrictNameById(Long.valueOf(hometown)));

        double total = 0;

        for(EthnicityCount ethnicity: ethnicities){
            total += ethnicity.getTotal();
        }


        for(EthnicityCount ethnicity: ethnicities){
            ethnicity.setTotal(Math.round((ethnicity.getTotal() / total * 100) * 100.0) / 100.0);
        }

        return ethnicities;
    }
}
