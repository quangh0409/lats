package com.example.lats.service;

import com.example.lats.common.BaseResponse;
import com.example.lats.common.Response;
import com.example.lats.model.entity.Citizen;

import java.util.List;
import java.util.Map;

public interface CitizenService {
    BaseResponse<Citizen> create(Citizen citizen);

    /**
     * findPopulationPercentageByAgeRangeAndGender.
     *
     * @param hometown string
     * @return Double
     */
    List<Map<String, Object>> findPopulationByAgeGroupAndHometown(String hometown);

    /**
     *
     * @param gender String
     * @param hometown String
     * @return long
     */
    Long countByGenderAndHometown(String gender, String hometown);

    Double calculateAgingIndex();

    Map<String, Double> calculatePopulationDistribution();

    Map<String, Double> calculateEducationLevelPercentages(String hometown);

    List<Map<String, Object>> getDistrictPopulationCounts();
}
