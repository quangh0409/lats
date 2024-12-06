package com.example.lats.repository;

import com.example.lats.model.entity.DeathCertificate;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface DeathCertificateRepository extends BaseJpaRepository<DeathCertificate, String> {

    @Query(value = """
        SELECT
            COUNT(*) AS all_count,
            COUNT(CASE WHEN GENDER = 'Nam' THEN 1 END) AS male_count,
            COUNT(CASE WHEN GENDER = 'Nữ' THEN 1 END) AS female_count
        FROM DEATH_CERTIFICATE
        WHERE TIME_OF_DEATH IS NOT NULL
          AND DATE_OF_BIRTH IS NOT NULL
          AND MONTHS_BETWEEN(TIME_OF_DEATH, DATE_OF_BIRTH) < 12
        """, nativeQuery = true)
    Map<String, Object> countUnderOneYearDeathsByGender();
}
