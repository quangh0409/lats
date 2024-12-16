package com.example.lats.service;

import com.example.lats.common.BaseResponse;
import com.example.lats.model.entity.Citizen;
import com.example.lats.model.response.EducationResponse;
import com.example.lats.model.response.EthnicityCount;

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
     * @param hometown String
     * @return long
     */
    Map<String, Long> countByGenderAndHometown(String hometown);

    Double calculateAgingIndex();

    Map<String, Double> calculatePopulationDistribution();

    List<EducationResponse> calculateEducationLevelPercentages(String hometown);

    List<Map<String, Object>> getDistrictPopulationCounts();

    List<EthnicityCount> getGroupedByEthnicity(String hometown);
}
