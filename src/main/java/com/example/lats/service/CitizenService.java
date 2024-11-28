package com.example.lats.service;

import com.example.lats.common.BaseResponse;
import com.example.lats.common.Response;
import com.example.lats.model.entity.Citizen;

import java.util.Map;

public interface CitizenService {
    BaseResponse<Citizen> create(Citizen citizen);

    /**
     * findPopulationPercentageByAgeRangeAndGender.
     *
     * @param ageMin int
     * @param ageMax int
     * @param gender string
     * @return Double
     */
    Double findPopulationPercentageByAgeRangeAndGender(int ageMin, int ageMax, String gender);

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
}
