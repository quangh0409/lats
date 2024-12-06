package com.example.lats.repository;

import com.example.lats.model.entity.BirthCertificate;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface BirthCertificateRepository extends BaseJpaRepository<BirthCertificate, String> {
    // Đếm số trẻ em theo giới tính
    @Query(value = "SELECT COUNT(b) FROM BirthCertificate b JOIN Citizen c ON b.childId = c.citizenId WHERE c.gender = :gender")
    Long countGenderBirths(@Param(value = "gender") String gender);


    @Query(value = """
                SELECT 'ALL' AS province,
                       ROUND(COUNT(CASE WHEN GENDER = 'Nam' THEN 1 END) * 100.0 / COUNT(CASE WHEN GENDER = 'Nữ' THEN 1 END), 2) AS percentage
                FROM BIRTH_CERTIFICATE
                WHERE DATE_OF_BIRTH IS NOT NULL
            """, nativeQuery = true)
    List<Map<String, Object>> findGenderRatioProvince();

    @Query(value = """
                SELECT REPLACE(REGEXP_SUBSTR(HOMETOWN, 'Huyện [^,]+'), 'Huyện Huyện', 'Huyện') AS district,
                       ROUND(COUNT(CASE WHEN GENDER = 'Nam' THEN 1 END) * 100.0 / COUNT(CASE WHEN GENDER = 'Nữ' THEN 1 END), 2) AS percentage
                FROM BIRTH_CERTIFICATE
                WHERE DATE_OF_BIRTH IS NOT NULL
                GROUP BY REGEXP_SUBSTR(HOMETOWN, 'Huyện [^,]+')
            """, nativeQuery = true)
    List<Map<String, Object>> findGenderRatioByDistrict();

    @Query(value = """
        SELECT
            COUNT(*) AS all_count,
            COUNT(CASE WHEN GENDER = 'Nam' THEN 1 END) AS male_count,
            COUNT(CASE WHEN GENDER = 'Nữ' THEN 1 END) AS female_count
        FROM BIRTH_CERTIFICATE
        """, nativeQuery = true)
    Map<String, Object> countByGender();
}
