package com.example.lats.repository;

import com.example.lats.model.entity.Citizen;
import com.example.lats.model.response.EthnicityCount;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface CitizenRepository extends BaseJpaRepository<Citizen, String> {
    @Query(value = """
             WITH ALL_AGE_GROUPS AS (
                 SELECT '0-04' AS AGE_GROUP FROM DUAL UNION ALL
                 SELECT '05-09' FROM DUAL UNION ALL
                 SELECT '10-14' FROM DUAL UNION ALL
                 SELECT '15-19' FROM DUAL UNION ALL
                 SELECT '20-24' FROM DUAL UNION ALL
                 SELECT '25-29' FROM DUAL UNION ALL
                 SELECT '30-34' FROM DUAL UNION ALL
                 SELECT '35-39' FROM DUAL UNION ALL
                 SELECT '40-44' FROM DUAL UNION ALL
                 SELECT '45-49' FROM DUAL UNION ALL
                 SELECT '50-54' FROM DUAL UNION ALL
                 SELECT '55-59' FROM DUAL UNION ALL
                 SELECT '60-64' FROM DUAL UNION ALL
                 SELECT '65-69' FROM DUAL UNION ALL
                 SELECT '70-74' FROM DUAL UNION ALL
                 SELECT '75-79' FROM DUAL UNION ALL
                 SELECT '80+' FROM DUAL
             ),
             TOTAL_COUNT AS (
                 SELECT COUNT(*) AS TOTAL
                 FROM CITIZEN
                 WHERE (:hometown IS NULL OR HOMETOWN LIKE %:hometown%)
             ),
             AGE_GROUPS AS (
                 SELECT
                     CASE
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 0 AND 4 THEN '0-04'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 5 AND 9 THEN '05-09'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 10 AND 14 THEN '10-14'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 15 AND 19 THEN '15-19'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 20 AND 24 THEN '20-24'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 25 AND 29 THEN '25-29'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 30 AND 34 THEN '30-34'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 35 AND 39 THEN '35-39'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 40 AND 44 THEN '40-44'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 45 AND 49 THEN '45-49'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 50 AND 54 THEN '50-54'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 55 AND 59 THEN '55-59'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 60 AND 64 THEN '60-64'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 65 AND 69 THEN '65-69'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 70 AND 74 THEN '70-74'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 75 AND 79 THEN '75-79'
                         ELSE '80+'
                     END AS AGE_GROUP,
                     GENDER,
                     COUNT(*) AS COUNT
                 FROM CITIZEN
                 WHERE (:hometown IS NULL OR HOMETOWN LIKE %:hometown%)
                 GROUP BY
                     CASE
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 0 AND 4 THEN '0-04'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 5 AND 9 THEN '05-09'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 10 AND 14 THEN '10-14'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 15 AND 19 THEN '15-19'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 20 AND 24 THEN '20-24'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 25 AND 29 THEN '25-29'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 30 AND 34 THEN '30-34'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 35 AND 39 THEN '35-39'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 40 AND 44 THEN '40-44'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 45 AND 49 THEN '45-49'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 50 AND 54 THEN '50-54'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 55 AND 59 THEN '55-59'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 60 AND 64 THEN '60-64'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 65 AND 69 THEN '65-69'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 70 AND 74 THEN '70-74'
                         WHEN MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12 BETWEEN 75 AND 79 THEN '75-79'
                         ELSE '80+'
                     END,
                     GENDER
             )
             SELECT
                 ALL_AGE_GROUPS.AGE_GROUP,
                 ROUND(COALESCE(SUM(CASE WHEN AGE_GROUPS.GENDER = 'Nam' THEN COUNT * 100.0 / TOTAL_COUNT.TOTAL ELSE 0 END), 0), 2) AS MALE,
                 ROUND(COALESCE(SUM(CASE WHEN AGE_GROUPS.GENDER = 'Nữ' THEN COUNT * 100.0 / TOTAL_COUNT.TOTAL ELSE 0 END), 0), 2) AS FEMALE
             FROM ALL_AGE_GROUPS
             LEFT JOIN AGE_GROUPS ON ALL_AGE_GROUPS.AGE_GROUP = AGE_GROUPS.AGE_GROUP
             LEFT JOIN TOTAL_COUNT ON 1 = 1
             GROUP BY ALL_AGE_GROUPS.AGE_GROUP
             ORDER BY ALL_AGE_GROUPS.AGE_GROUP
            """, nativeQuery = true)
    List<Map<String, Object>> findPopulationByAgeGroupAndHometown(@Param("hometown") String hometown);


    @Query("SELECT COUNT(c) FROM Citizen c WHERE " + "(:gender IS NULL OR c.gender = :gender) AND " + "(:hometown IS NULL OR c.hometown LIKE %:hometown%)")
    Long countByGenderAndHometown(@Param("gender") String gender, @Param("hometown") String hometown);

    @Query(value = """
            SELECT
                COUNT(*) AS all_count,
                COUNT(CASE WHEN c.gender = 'Nam' THEN 1 END) AS male_count,
                COUNT(CASE WHEN c.gender = 'Nữ' THEN 1 END) AS female_count
            FROM Citizen c
            WHERE (:hometown IS NULL OR c.hometown LIKE %:hometown%)
            """)
    Map<String, Object> countByGenderAndHometown(@Param("hometown") String hometown);


    @Query(value = "SELECT COUNT(*) FROM Citizen c WHERE EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM c.date_of_birth) >= 60", nativeQuery = true)
    Long countSeniorCitizens();

    @Query(value = "SELECT COUNT(*) FROM Citizen c WHERE " + "EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM c.date_of_birth) BETWEEN 0 AND 14", nativeQuery = true)
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

    @Query("SELECT e.technicalQualification, COUNT(e) " + "FROM Education e " + "JOIN Citizen c ON e.citizenId = c.citizenId " + "WHERE (:hometown IS NULL OR c.hometown LIKE %:hometown%) " + "GROUP BY e.technicalQualification")
    List<Object[]> countPopulationByEducationLevelAndHometown(@Param("hometown") String hometown);

    @Query(value = """
            SELECT
                TRIM(REGEXP_SUBSTR(
                        HOMETOWN,
                        'Huyện [^,]+|Thành Phố [^,]+', 1, 1, NULL, 0)) AS full_name, -- Lấy cụm từ đầy đủ từ HOMETOWN
                COUNT(*) AS populationCount, -- Đếm số dân theo huyện hoặc thành phố
                d.DISTRICT_ID, -- Mã định danh của huyện/thành phố
                d.COORDINATES -- Tọa độ của huyện/thành phố
            FROM CITIZEN c
                     LEFT JOIN DISTRICT d
                               ON TRIM(REGEXP_SUBSTR(
                                       HOMETOWN,
                                       'Huyện [^,]+|Thành Phố [^,]+', 1, 1, NULL, 0)) = TRIM(d.DISTRICT_NAME) -- Ghép với DISTRICT_NAME
            WHERE HOMETOWN LIKE '%Huyện%' OR HOMETOWN LIKE '%Thành Phố%' -- Chỉ xử lý các bản ghi có huyện hoặc thành phố
            GROUP BY
                TRIM(REGEXP_SUBSTR(
                        HOMETOWN,
                        'Huyện [^,]+|Thành Phố [^,]+', 1, 1, NULL, 0)), -- Group theo tên đầy đủ
                d.DISTRICT_ID,
                d.COORDINATES -- Thêm COORDINATES vào GROUP BY nếu không muốn lỗi
            ORDER BY full_name
            """, nativeQuery = true)
    List<Map<String, Object>> getDistrictPopulationCounts();

    @Query(value = """
            SELECT
                COUNT(*) AS COUNT_OVER_18
            FROM
                CITIZEN
            WHERE
                FLOOR(MONTHS_BETWEEN(SYSDATE, DATE_OF_BIRTH) / 12) > 18
                AND (HOMETOWN LIKE %:hometown% OR :hometown IS NULL OR :hometown = '')
            """, nativeQuery = true)
    Long countOver18ByHometown(@Param("hometown") String hometown);

    @Query("SELECT new com.example.lats.model.response.EthnicityCount(c.ethnicity, COUNT(c)) " +
            "FROM Citizen c " +
            "WHERE (:hometown IS NULL OR :hometown = '' OR c.hometown LIKE %:hometown%) " +
            "GROUP BY c.ethnicity " +
            "ORDER BY COUNT(c) DESC")
    List<EthnicityCount> findEthnicityAndCitizenCount(@Param("hometown") String hometown);

}
