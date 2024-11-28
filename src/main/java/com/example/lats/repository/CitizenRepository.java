package com.example.lats.repository;

import com.example.lats.model.entity.Citizen;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CitizenRepository extends BaseJpaRepository<Citizen, String> {
    @Query(value = """
             WITH TOTAL_COUNT AS (
                 SELECT COUNT(*) AS TOTAL FROM CITIZEN
             ),
             FILTERED_COUNT AS (
                 SELECT COUNT(*) AS FILTERED
                 FROM CITIZEN
                 WHERE DATE_OF_BIRTH BETWEEN ADD_MONTHS(TRUNC(SYSDATE), -(:ageMax * 12))
                                         AND ADD_MONTHS(TRUNC(SYSDATE), -(:ageMin * 12))
                   AND GENDER = :gender
             )
             SELECT
                 (FILTERED_COUNT.FILTERED * 100.0 / TOTAL_COUNT.TOTAL) AS PERCENTAGE
             FROM
                 TOTAL_COUNT, FILTERED_COUNT
            """, nativeQuery = true)
    Double findPopulationPercentageByAgeRangeAndGender(
            @Param("ageMin") int ageMin,
            @Param("ageMax") int ageMax,
            @Param("gender") String gender
    );

    @Query("SELECT COUNT(c) FROM Citizen c WHERE " +
            "(:gender IS NULL OR c.gender = :gender) AND " +
            "(:hometown IS NULL OR c.hometown LIKE %:hometown%)")
    Long countByGenderAndHometown(@Param("gender") String gender, @Param("hometown") String hometown);

    @Query(value = "SELECT COUNT(*) FROM Citizen c WHERE EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM c.date_of_birth) >= 60", nativeQuery = true)
    Long countSeniorCitizens();

    @Query(value = "SELECT COUNT(*) FROM Citizen c WHERE " +
            "EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM c.date_of_birth) BETWEEN 0 AND 14",
            nativeQuery = true)
    Long countChildren();

    @Query(value = """
                SELECT
                    SUM(CASE WHEN TIMESTAMPDIFF(YEAR, c.date_of_birth, CURRENT_DATE) BETWEEN 0 AND 14 THEN 1 ELSE 0 END) AS age_0_14,
                    SUM(CASE WHEN TIMESTAMPDIFF(YEAR, c.date_of_birth, CURRENT_DATE) BETWEEN 15 AND 65 THEN 1 ELSE 0 END) AS age_15_65,
                    SUM(CASE WHEN TIMESTAMPDIFF(YEAR, c.date_of_birth, CURRENT_DATE) > 65 THEN 1 ELSE 0 END) AS age_65_plus
                FROM Citizen c
            """, nativeQuery = true)
    Object[] countPopulationByAgeGroups();

    @Query(value = "SELECT COUNT(*) FROM Citizen WHERE EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM date_of_birth) BETWEEN 0 AND 14", nativeQuery = true)
    Long countPopulationAge0To14();

    @Query(value = "SELECT COUNT(*) FROM Citizen WHERE EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM date_of_birth) BETWEEN 15 AND 64", nativeQuery = true)
    Long countPopulationAge15To64();

    @Query(value = "SELECT COUNT(*) FROM Citizen WHERE EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM date_of_birth) >= 65", nativeQuery = true)
    Long countPopulationAge65Plus();

    @Query("SELECT e.educationalLevel, COUNT(e) " +
            "FROM Education e " +
            "JOIN Citizen c ON e.citizenId = c.citizenId " +
            "WHERE (:hometown IS NULL OR c.hometown LIKE %:hometown%) " +
            "GROUP BY e.educationalLevel")
    List<Object[]> countPopulationByEducationLevelAndHometown(@Param("hometown") String hometown);


}
